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

public class Aplicacion {
	private static double[] ySeries1 = new double[2];
	private static double[] ySeries2 = new double[2];
	private Chart chart;
	private static final String[] cagetorySeries = { "Datos como Cuaterniones",
			"Datos como Esfericas", };
	protected Shell shlComparacinRotacionesEsfericas;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Aplicacion window = new Aplicacion();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlComparacinRotacionesEsfericas.open();
		shlComparacinRotacionesEsfericas.layout();
		while (!shlComparacinRotacionesEsfericas.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlComparacinRotacionesEsfericas = new Shell();
		shlComparacinRotacionesEsfericas.setModified(true);
		shlComparacinRotacionesEsfericas.setSize(578, 300);
		shlComparacinRotacionesEsfericas
				.setText("Comparación Rotaciones Esfericas vs. Cuaterniones");
		shlComparacinRotacionesEsfericas.setLayout(new FillLayout(
				SWT.HORIZONTAL));

		Composite composite = new Composite(shlComparacinRotacionesEsfericas,
				SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		RowLayout rl_composite_1 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_1.center = true;
		rl_composite_1.fill = true;
		rl_composite_1.justify = true;
		composite_1.setLayout(rl_composite_1);

		Label lblCantidadDeRotaciones = new Label(composite_1, SWT.NONE);
		lblCantidadDeRotaciones.setText("Cantidad de Rotaciones");

		final Combo combo_1 = new Combo(composite_1, SWT.NONE);
		combo_1.setLayoutData(new RowData(128, SWT.DEFAULT));
		combo_1.setItems(new String[] { "100", "1000", "10000", "100000",
				"1000000" });
		combo_1.select(0);

		Button btnIniciar = new Button(composite_1, SWT.NONE);
		btnIniciar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double[][] tiempos = RotacionUtil.iniciarComparacion((combo_1
						.getSelectionIndex() + 1) * 100);

				IBarSeries series1 = (IBarSeries) chart.getSeriesSet()
						.createSeries(SeriesType.BAR, "Rot. con Cuaterniones");
				series1.setYSeries(tiempos[0]);

				IBarSeries series2 = (IBarSeries) chart
						.getSeriesSet()
						.createSeries(SeriesType.BAR, "Rot. con Trig. Esferica");
				series2.setYSeries(tiempos[1]);
				series2.setBarColor(Display.getDefault().getSystemColor(
						SWT.COLOR_GREEN));
				// adjust the axis range
				chart.getAxisSet().adjustRange();
				chart.redraw();
			}
		});
		btnIniciar.setLayoutData(new RowData(109, SWT.DEFAULT));
		btnIniciar.setText("Iniciar");

		chart = createChart(composite);
		GridData gd_chart = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		chart.setLayoutData(gd_chart);

	}

	/**
	 * create the chart.
	 * 
	 * @param parent
	 *            The parent composite
	 * @return The created chart
	 */
	static public Chart createChart(Composite parent) {

		// create a chart
		final Chart chart = new Chart(parent, SWT.NONE);
		chart.getTitle().setText("Comparacion de Tiempos");

		// set category
		chart.getAxisSet().getXAxis(0).enableCategory(true);
		chart.getAxisSet().getXAxis(0).setCategorySeries(cagetorySeries);
		// chart.getAxisSet().getXAxis(0).getTick().setTickLabelAngle(45);
		chart.getAxisSet().getXAxis(0).getTitle().setVisible(false);
		chart.getAxisSet().getYAxis(0).getTitle().setText("Tiempo (ms.)");

		// add mouse move listener to open tooltip on data point
		chart.getPlotArea().addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent e) {
				for (ISeries series : chart.getSeriesSet().getSeries()) {
					Rectangle[] rs = ((IBarSeries) series).getBounds();
					for (int i = 0; i < rs.length; i++) {
						if (rs[i] != null) {
							if (rs[i].x < e.x && e.x < rs[i].x + rs[i].width
									&& rs[i].y < e.y
									&& e.y < rs[i].y + rs[i].height) {
								setToolTipText(series, i);
								return;
							}
						}
					}
				}
				chart.getPlotArea().setToolTipText(null);
			}

			private void setToolTipText(ISeries series, int index) {
				chart.getPlotArea().setToolTipText(
						"Tiempo " + series.getId() + ": "
								+ series.getYSeries()[index] + " ms.");
			}
		});

		return chart;
	}

}
