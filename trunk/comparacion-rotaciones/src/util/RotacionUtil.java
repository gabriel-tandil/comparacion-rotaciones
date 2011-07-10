package util;

import geometria.cuaterniones.Cuaternion;
import geometria.esferica.CoordenadaEsferica;
import geometria.esferica.RotacionEsferica;

import java.util.ArrayList;
import java.util.List;

public class RotacionUtil {

	public static double[][] iniciarComparacion(int i) {
		double[][] tiempos = new double[2][2];

		try {
			List<RotacionEsferica> tablaEsfericas = new ArrayList<RotacionEsferica>(
					i);
			List<Cuaternion> tablaCuaterniones = new ArrayList<Cuaternion>(i);
			llenarTablas(tablaEsfericas, tablaCuaterniones, i);
			// rotaciones cuaterniones datos cuaterniones
			long tiempo = System.currentTimeMillis();
			Thread.sleep(100);
			tiempos[0][0] = System.currentTimeMillis() - tiempo;

			// rotaciones cuaterniones datos esfericas
			tiempo = System.currentTimeMillis();
			Thread.sleep(200);
			tiempos[0][1] = System.currentTimeMillis() - tiempo;

			// rotaciones esfericas datos cuaterniones
			tiempo = System.currentTimeMillis();
			Thread.sleep(300);
			tiempos[1][0] = System.currentTimeMillis() - tiempo;

			// rotaciones esfericas datos esfericas
			tiempo = System.currentTimeMillis();
			Thread.sleep(400);
			tiempos[1][1] = System.currentTimeMillis() - tiempo;

		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
		}
		return tiempos;

	}

	private static void llenarTablas(List<RotacionEsferica> tablaEsfericas,
			List<Cuaternion> tablaCuaterniones, int cantidad) {

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
