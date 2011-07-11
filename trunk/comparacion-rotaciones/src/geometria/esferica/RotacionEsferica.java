package geometria.esferica;

import geometria.Rotacion;
import geometria.Vector;
import geometria.cuaterniones.Cuaternion;

public class RotacionEsferica implements Rotacion {

	private CoordenadaEsferica eje;
	private double angulo;

	public RotacionEsferica(CoordenadaEsferica eje, double angulo) {
		this.eje = eje;
		this.angulo = angulo;
	}

	public RotacionEsferica(CoordenadaEsferica eje, double angulo,
			CoordenadaEsferica vector) {
		this.eje = eje;
		this.angulo = angulo;
	}

	public Cuaternion aCuaternion() {
		return null;
	}

	public double getAngulo() {
		return angulo;
	}

	public CoordenadaEsferica getEje() {
		return eje;
	}

	@Override
	public Vector rotar(Vector v) {
		CoordenadaEsferica coordenadaEsferica = null;
		if (v instanceof Cuaternion) {
			coordenadaEsferica = ((Cuaternion) v).aEsferica();
		}
		if (v instanceof CoordenadaEsferica) {
			coordenadaEsferica = (CoordenadaEsferica) v;
		}

		return rotar(coordenadaEsferica);

	}

	public CoordenadaEsferica rotar(CoordenadaEsferica v) {

		return v;

	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public void setEje(CoordenadaEsferica eje) {
		this.eje = eje;
	}

	public static void rotar(Rotacion rotacion, Vector vector) {
		// TODO Auto-generated method stub

	}

}
