package geometria.cuaterniones;

import geometria.esferica.CoordenadaEsferica;

public class Cuaternion {
	private double x;
	private double y;
	private double z;
	private double angulo;

	public Cuaternion(double x, double y, double z, double angulo) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.angulo = angulo;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public CoordenadaEsferica aEsferica() {
		return null;
	}

}
