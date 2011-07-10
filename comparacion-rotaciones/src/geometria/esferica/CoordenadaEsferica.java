package geometria.esferica;

import geometria.cuaterniones.Cuaternion;

public class CoordenadaEsferica {

	private double lambda, phi, radio;

	public CoordenadaEsferica(double lambda, double phi) {
		this.lambda = lambda;
		this.phi = phi;
		this.setRadio(1);
	}

	public CoordenadaEsferica(double lambda, double phi, double radio) {
		this.lambda = lambda;
		this.phi = phi;
		this.setRadio(radio);
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

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public double getRadio() {
		return radio;
	}
}
