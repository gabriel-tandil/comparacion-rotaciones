package geometria.cuaterniones;

import geometria.Rotacion;
import geometria.Vector;
import geometria.esferica.CoordenadaEsferica;

public class Cuaternion implements Rotacion, Vector {
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

	public CoordenadaEsferica aEsferica() {
		return null;
	}

	public double getAngulo() {
		return angulo;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	@Override
	public Vector rotar(Vector v) {
		Cuaternion cuaternion = null;
		if (v instanceof Cuaternion) {
			 cuaternion = (Cuaternion) v;
		}
		if (v instanceof CoordenadaEsferica) {
			 cuaternion = ((CoordenadaEsferica) v).aCuaternion();
		}
		
		return rotar(cuaternion);

	}

	public Cuaternion rotar(Cuaternion v) {
		Cuaternion rotado = this.mult(v).mult(this.conjugado());
		return rotado;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public static void rotar(Rotacion rotacion, Vector vector) {
		// TODO Auto-generated method stub

	}

	public Cuaternion conjugado() {
		return new Cuaternion(angulo, -x, -y, -z);
	}

	public Cuaternion suma(Cuaternion b) {
		Cuaternion a = this;
		return new Cuaternion(a.angulo + b.angulo, a.x + b.x, a.y + b.y, a.z
				+ b.z);
	}

	public Cuaternion mult(Cuaternion b) {
		Cuaternion a = this;
		double y0 = a.angulo * b.angulo - a.x * b.x - a.y * b.y - a.z * b.z;
		double y1 = a.angulo * b.x + a.x * b.angulo + a.y * b.z - a.z * b.y;
		double y2 = a.angulo * b.y - a.x * b.z + a.y * b.angulo + a.z * b.x;
		double y3 = a.angulo * b.z + a.x * b.y - a.y * b.x + a.z * b.angulo;
		return new Cuaternion(y0, y1, y2, y3);
	}

	public Cuaternion inverso() {
		double d = angulo * angulo + x * x + y * y + z * z;
		return new Cuaternion(angulo / d, -x / d, -y / d, -z / d);
	}

	// return a / b
	public Cuaternion divides(Cuaternion b) {
		Cuaternion a = this;
		return a.inverso().mult(b);
	}

}
