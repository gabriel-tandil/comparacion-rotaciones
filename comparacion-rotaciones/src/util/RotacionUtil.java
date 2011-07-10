package util;

import geometria.Rotacion;
import geometria.cuaterniones.Cuaternion;
import geometria.esferica.CoordenadaEsferica;
import geometria.esferica.RotacionEsferica;

import java.util.ArrayList;
import java.util.List;

public class RotacionUtil {

	public static double[][] iniciarComparacion(int i) {
		double[][] tiempos = new double[2][2];

		try {
			List<Rotacion> tablaEsfericas = new ArrayList<Rotacion>(i);
			List<Rotacion> tablaCuaterniones = new ArrayList<Rotacion>(i);
			llenarTablas(tablaEsfericas, tablaCuaterniones, i);
			// rotaciones cuaterniones datos cuaterniones
			long tiempo = System.currentTimeMillis();
			rotarCuaterniones(tablaCuaterniones);
			tiempos[0][0] = System.currentTimeMillis() - tiempo;

			// rotaciones cuaterniones datos esfericas
			tiempo = System.currentTimeMillis();
			rotarCuaterniones(tablaEsfericas);
			tiempos[0][1] = System.currentTimeMillis() - tiempo;

			// rotaciones esfericas datos cuaterniones
			tiempo = System.currentTimeMillis();
			rotarEsfericas(tablaCuaterniones);
			tiempos[1][0] = System.currentTimeMillis() - tiempo;

			// rotaciones esfericas datos esfericas
			tiempo = System.currentTimeMillis();
			rotarEsfericas(tablaEsfericas);
			tiempos[1][1] = System.currentTimeMillis() - tiempo;

		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
		}
		return tiempos;

	}

	private static void rotarCuaterniones(List<Rotacion> tabla) {
		// TODO Auto-generated method stub

	}

	private static void rotarEsfericas(List<Rotacion> tabla) {
		// TODO Auto-generated method stub

	}

	private static void llenarTablas(List<Rotacion> tablaEsfericas,
			List<Rotacion> tablaCuaterniones, int cantidad) {

		for (int i = 0; i < cantidad; i++) {
			CoordenadaEsferica coordenadaEsferica = new CoordenadaEsferica(
					Math.random(), Math.random());
			RotacionEsferica rotacionEsferica = new RotacionEsferica(
					coordenadaEsferica, Math.random());
			Cuaternion cuaternion = rotacionEsferica.aCuaternion();

			tablaEsfericas.add(rotacionEsferica);
			tablaCuaterniones.add(cuaternion);
		}
	}

}
