package geometria.esferica;

import geometria.cuaterniones.Cuaternion;

public class RotacionEsferica {

	private CoordenadaEsferica eje;
	private double angulo;

	public RotacionEsferica(CoordenadaEsferica eje, double angulo) {
		this.eje = eje;
		this.angulo = angulo;
	}

	public Cuaternion aCuaternion() {
		return null;
	}

	public CoordenadaEsferica getEje() {
		return eje;
	}

	public void setEje(CoordenadaEsferica eje) {
		this.eje = eje;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

}
