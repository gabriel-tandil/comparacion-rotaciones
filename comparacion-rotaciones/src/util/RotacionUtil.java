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
			final List<Vector> tablaVectoresEsfericas = new ArrayList<Vector>(i);
			final List<Vector> tablaVectoresCuaterniones = new ArrayList<Vector>(i);
			llenarTablas(tablaEsfericas, tablaCuaterniones, i);
			llenarTablasVectores(tablaVectoresEsfericas, tablaVectoresCuaterniones, i);

			// rotaciones cuaterniones datos cuaterniones
			long tiempo = System.currentTimeMillis();
			rotarLista(tablaCuaterniones, tablaVectoresCuaterniones, CUATERNIONES);
			tiempos[0][0] = System.currentTimeMillis() - tiempo;
	//		 System.out.println("Rotacion concuaterniones, datos cuaterniones: "+tiempos[0][0]);

			// rotaciones cuaterniones datos esfericas
			tiempo = System.currentTimeMillis();
			rotarLista(tablaEsfericas, tablaVectoresEsfericas, CUATERNIONES);
			tiempos[0][1] = System.currentTimeMillis() - tiempo;
		//	System.out.println("Rotacion concuaterniones, datos esfericas: "+tiempos[0][1]);

			// rotaciones esfericas datos cuaterniones
			tiempo = System.currentTimeMillis();
			rotarLista(tablaCuaterniones, tablaVectoresCuaterniones, ESFERICAS);
			tiempos[1][0] = System.currentTimeMillis() - tiempo;
	//		System.out.println("Rotacion esfericas, datos cuaterniones: "+tiempos[1][0]);

			// rotaciones esfericas datos esfericas
			tiempo = System.currentTimeMillis();
			rotarLista(tablaEsfericas, tablaVectoresEsfericas, ESFERICAS);
			tiempos[1][1] = System.currentTimeMillis() - tiempo;
		//	System.out.println("Rotacion esfericas, datos esfericas: "+tiempos[1][1]);
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
			final CoordenadaEsferica coordenadaEsferica = new CoordenadaEsferica((1 - Math.random()) *  Math.PI, Math.random() * Math.PI);
			final RotacionEsferica rotacionEsferica = new RotacionEsferica(coordenadaEsferica, Math.random() * 2 * Math.PI);
			final Cuaternion cuaternion = rotacionEsferica.aCuaternion();

			tablaEsfericas.add(rotacionEsferica);
			tablaCuaterniones.add(cuaternion);
		}
	}
	private static void llenarTablasVectores(List<Vector> tablaEsfericas, List<Vector> tablaCuaterniones, int cantidad)
	{

		for (int i = 0; i < cantidad; i++)
		{
			final CoordenadaEsferica coordenadaEsferica = new CoordenadaEsferica(Math.PI+((1 - Math.random()) *  Math.PI), Math.random() * Math.PI);
			final Cuaternion cuaternion = coordenadaEsferica.aCuaternion();

			tablaEsfericas.add(coordenadaEsferica);
			tablaCuaterniones.add(cuaternion);
		}
	}
	
	private static void rotarLista(List<Rotacion> tabla, List<Vector> tablaVectores, String metodo)
	{
//		System.out.println("Modo : "+metodo);

		for (int i=0;i<tabla.size();i++)
		{
			final Rotacion rotacion = (Rotacion) tabla.get(i);
//			System.out.println("U : "+rotacion);
//			System.out.println("V : "+tablaVectores.get(i));			
//
//			 if (metodo.equals(CUATERNIONES)) System.out.println("V': "+Cuaternion.rotar(rotacion,  tablaVectores.get(i)));
//			 if (metodo.equals(ESFERICAS)) System.out.println("V': "+ RotacionEsferica.rotar(rotacion,  tablaVectores.get(i)));
						if (metodo.equals(CUATERNIONES)) Cuaternion.rotar(rotacion, tablaVectores.get(i));
						if (metodo.equals(ESFERICAS)) RotacionEsferica.rotar(rotacion, tablaVectores.get(i));
		}

	}

	public static void main(String[] args)
	{
	//	final CoordenadaEsferica coordenadaEsferica = new CoordenadaEsferica((1 - Math.random()) *  Math.PI, Math.random() * Math.PI);
	//	final RotacionEsferica rotacionEsferica = new RotacionEsferica(coordenadaEsferica, Math.random() * 2 * Math.PI);
		 //final Cuaternion cuaternion = rotacionEsferica.aCuaternion();
		//	final CoordenadaEsferica vectorEsferico = new CoordenadaEsferica(Math.PI+((1 - Math.random()) *  Math.PI), Math.random() * Math.PI);
			//final Cuaternion vectorCuaternion = coordenadaEsferica.aCuaternion();
//			System.out.println(vectorEsferico);			
//			System.out.println(vectorEsferico.aCuaternion().aCoordenadaEsferica());			
//
//		System.out.println(vectorEsferico.aCuaternion());
//		 System.out.println(vectorEsferico.aCuaternion().aCoordenadaEsferica().aCuaternion());

		 final Cuaternion cuaternion = new Cuaternion(1, 0, 0, Math.toRadians(90));
		
			final Cuaternion vectorCuaternion = new Cuaternion(0, 1, 0);

			System.out.println("Rotacion  "+cuaternion);
			System.out.println("Punto     "+vectorCuaternion);
		System.out.println("Resultado "+cuaternion.aRotacionEsferica().rotar(vectorCuaternion.aCoordenadaEsferica()));
		System.out.println("Resultado "+cuaternion.rotar(vectorCuaternion).aCoordenadaEsferica());
		System.out.println("Resultado "+cuaternion.aRotacionEsferica().rotar(vectorCuaternion.aCoordenadaEsferica()).aCuaternion());
		System.out.println("Resultado "+cuaternion.rotar(vectorCuaternion));

	}
}
