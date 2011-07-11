package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.IBarSeries;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;

import util.RotacionUtil;

public class Aplicacion
{

	/**
	 * create the chart.
	 * 
	 * @param parent
	 *            The parent composite
	 * @return The created chart
	 */
	static public Chart createChart(Composite parent)
	{

		// create a chart
		final Chart chart = new Chart(parent, SWT.NONE);
		chart.getTitle().setText("Comparacion de Tiempos");

		// categorias
		chart.getAxisSet().getXAxis(0).enableCategory(true);
		chart.getAxisSet().getXAxis(0).setCategorySeries(new String[] { "Datos como Cuaterniones", "Datos como Esfericas" });

		chart.getAxisSet().getXAxis(0).getTitle().setVisible(false);
		chart.getAxisSet().getYAxis(0).getTitle().setText("Tiempo (ms.)");

		// tooltip
		chart.getPlotArea().addMouseMoveListener(new MouseMoveListener()
			{
				@Override
				public void mouseMove(MouseEvent e)
				{
					for (final ISeries series : chart.getSeriesSet().getSeries())
					{
						final Rectangle[] rs = ((IBarSeries) series).getBounds();
						for (int i = 0; i < rs.length; i++)
							if (rs[i] != null) if (rs[i].x < e.x && e.x < rs[i].x + rs[i].width && rs[i].y < e.y && e.y < rs[i].y + rs[i].height)
							{
								setToolTipText(series, i);
								return;
							}
					}
					chart.getPlotArea().setToolTipText(null);
				}

				private void setToolTipText(ISeries series, int index)
				{
					chart.getPlotArea().setToolTipText("Tiempo " + series.getId() + ": " + series.getYSeries()[index] + " ms.");
				}
			});

		return chart;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			final Aplicacion window = new Aplicacion();
			window.open();
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	protected Shell	shlComparacinRotacionesEsfericas;

	/**
	 * Create contents of the window.
	 */
	protected void createContents()
	{
		shlComparacinRotacionesEsfericas = new Shell();
		shlComparacinRotacionesEsfericas.setModified(true);
		shlComparacinRotacionesEsfericas.setSize(578, 300);
		shlComparacinRotacionesEsfericas.setText("Comparación Rotaciones Esfericas vs. Cuaterniones");
		shlComparacinRotacionesEsfericas.setLayout(new FillLayout(SWT.HORIZONTAL));

		final Composite composite = new Composite(shlComparacinRotacionesEsfericas, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		final Composite composite_1 = new Composite(composite, SWT.NONE);
		final RowLayout rl_composite_1 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_1.center = true;
		rl_composite_1.fill = true;
		rl_composite_1.justify = true;
		composite_1.setLayout(rl_composite_1);

		final Label lblCantidadDeRotaciones = new Label(composite_1, SWT.NONE);
		lblCantidadDeRotaciones.setText("Cantidad de Rotaciones");

		final Combo combo_1 = new Combo(composite_1, SWT.NONE);
		combo_1.setLayoutData(new RowData(128, SWT.DEFAULT));
		combo_1.setItems(new String[] { "1", "10", "100", "1000", "10000", "100000", "1000000", "10000000" });
		combo_1.select(2);

		final Button btnIniciar = new Button(composite_1, SWT.NONE);
		btnIniciar.setText("Iniciar");
		btnIniciar.setLayoutData(new RowData(109, SWT.DEFAULT));

		final Chart chart = createChart(composite);
		final GridData gd_chart = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		chart.setLayoutData(gd_chart);
		btnIniciar.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					// invoco a ejecutar las rotaciones
					final double[][] tiempos = RotacionUtil.iniciarComparacion((int) Math.pow(10, combo_1.getSelectionIndex()));

					// Y actualizo la tabla con los tiempos
					final IBarSeries series1 = (IBarSeries) chart.getSeriesSet().createSeries(SeriesType.BAR, "Rot. con Cuaterniones");
					series1.setYSeries(tiempos[0]);

					final IBarSeries series2 = (IBarSeries) chart.getSeriesSet().createSeries(SeriesType.BAR, "Rot. con Trig. Esferica");
					series2.setYSeries(tiempos[1]);
					series2.setBarColor(Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
					chart.getAxisSet().adjustRange();
					chart.redraw();
				}
			});

	}

	/**
	 * Open the window.
	 */
	public void open()
	{
		final Display display = Display.getDefault();
		createContents();
		shlComparacinRotacionesEsfericas.open();
		shlComparacinRotacionesEsfericas.layout();
		while (!shlComparacinRotacionesEsfericas.isDisposed())
			if (!display.readAndDispatch()) display.sleep();
	}

}
