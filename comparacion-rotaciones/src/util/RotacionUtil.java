package util;

import geometria.Rotacion;
import geometria.Vector;
import geometria.cuaterniones.Cuaternion;
import geometria.esferica.CoordenadaEsferica;
import geometria.esferica.RotacionEsferica;

import java.util.ArrayList;
import java.util.List;

public class RotacionUtil
{

	private static final String	ESFERICAS		= "esfericas";
	private static final String	CUATERNIONES	= "cuaterniones";

	public static double[][] iniciarComparacion(int i)
	{
		final double[][] tiempos = new double[2][2];

		try
		{
			final List<Rotacion> tablaEsfericas = new ArrayList<Rotacion>(i);
			final List<Rotacion> tablaCuaterniones = new ArrayList<Rotacion>(i);
			llenarTablas(tablaEsfericas, tablaCuaterniones, i);
			final CoordenadaEsferica vectorEsferico = new CoordenadaEsferica(0, 0);
			final Cuaternion vectorCuaternion = vectorEsferico.aCuaternion();

			// rotaciones cuaterniones datos cuaterniones
			long tiempo = System.currentTimeMillis();
			rotarLista(tablaCuaterniones, vectorCuaternion, CUATERNIONES);
			tiempos[0][0] = System.currentTimeMillis() - tiempo;
System.out.println(tiempos[0][0]);

// rotaciones cuaterniones datos esfericas
			tiempo = System.currentTimeMillis();
			rotarLista(tablaEsfericas, vectorEsferico, CUATERNIONES);
			tiempos[0][1] = System.currentTimeMillis() - tiempo;
			System.out.println(tiempos[0][1]);
			
			// rotaciones esfericas datos cuaterniones
			tiempo = System.currentTimeMillis();
			rotarLista(tablaCuaterniones, vectorCuaternion, ESFERICAS);
			tiempos[1][0] = System.currentTimeMillis() - tiempo;
			System.out.println(tiempos[1][0]);
			
			// rotaciones esfericas datos esfericas
			tiempo = System.currentTimeMillis();
			rotarLista(tablaEsfericas, vectorEsferico, ESFERICAS);
			tiempos[1][1] = System.currentTimeMillis() - tiempo;
			System.out.println(tiempos[1][1]);
		}
		catch (final Exception e)
		{
			System.out.print(e.getLocalizedMessage());
		}
		return tiempos;

	}

	private static void llenarTablas(List<Rotacion> tablaEsfericas, List<Rotacion> tablaCuaterniones, int cantidad)
	{

		for (int i = 0; i < cantidad; i++)
		{
			final CoordenadaEsferica coordenadaEsferica = new CoordenadaEsferica((1-Math.random())*2*Math.PI, Math.random()*Math.PI);
			final RotacionEsferica rotacionEsferica = new RotacionEsferica(coordenadaEsferica, Math.random()*2*Math.PI);
			final Cuaternion cuaternion = rotacionEsferica.aCuaternion();

			tablaEsfericas.add(rotacionEsferica);
			tablaCuaterniones.add(cuaternion);
		}
	}

	private static void rotarLista(List<Rotacion> tabla, Vector vector, String metodo)
	{
		for (final Object element : tabla)
		{
			final Rotacion rotacion = (Rotacion) element;
			if (metodo.equals(CUATERNIONES)) Cuaternion.rotar(rotacion, vector);
			if (metodo.equals(ESFERICAS)) RotacionEsferica.rotar(rotacion, vector);
//			if (metodo.equals(CUATERNIONES)) System.out.println(Cuaternion.rotar(rotacion, vector));
//			if (metodo.equals(ESFERICAS)) System.out.println(((CoordenadaEsferica) RotacionEsferica.rotar(rotacion, vector)).aCuaternion());

		}

	}
public static void main(String[] args)
{
//	final CoordenadaEsferica coordenadaEsferica = new CoordenadaEsferica((1-Math.random())*2*Math.PI, Math.random()*Math.PI);
//	final RotacionEsferica rotacionEsferica = new RotacionEsferica(coordenadaEsferica, Math.random()*2*Math.PI);
//	final Cuaternion cuaternion = rotacionEsferica.aCuaternion();
//	final CoordenadaEsferica vectorEsferico = new CoordenadaEsferica(0, 0);
//	final Cuaternion vectorCuaternion = vectorEsferico.aCuaternion();
//	
//	
//	 System.out.println(vectorEsferico.aCuaternion().aEsferica().aCuaternion());
	
		final CoordenadaEsferica coordenadaEsferica = new CoordenadaEsferica(Math.PI/2,Math.PI/2);
		final RotacionEsferica rotacionEsferica = new RotacionEsferica(coordenadaEsferica, Math.PI/2);
		final Cuaternion cuaternion = rotacionEsferica.aCuaternion();
		final CoordenadaEsferica vectorEsferico = new CoordenadaEsferica(Math.PI/1.5,Math.PI/2);
		final Cuaternion vectorCuaternion = vectorEsferico.aCuaternion();
		
		
		 System.out.println(vectorEsferico);
		 System.out.println(rotacionEsferica.rotar(vectorEsferico));

}
}
