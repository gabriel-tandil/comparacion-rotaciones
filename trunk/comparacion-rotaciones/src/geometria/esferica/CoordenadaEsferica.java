package geometria.esferica;

import geometria.cuaterniones.Cuaternion;

public class CoordenadaEsferica {

	private double lambda, phi, radio;

	public CoordenadaEsferica(double lambda, double phi) {
		this.lambda = lambda;
		this.phi = phi;
		this.radio = 1;
	}

	public CoordenadaEsferica(double lambda, double phi, double radio) {
		this.lambda = lambda;
		this.phi = phi;
		this.radio = radio;
	}

	public Cuaternion aCuaternion() {
		return null;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public double getLambda() {
		return lambda;
	}

	public void setPhi(double phi) {
		this.phi = phi;
	}

	public double getPhi() {
		return phi;
	}
}
