package geometria.esferica;

import geometria.Vector;
import geometria.cuaterniones.Cuaternion;

public class CoordenadaEsferica implements Vector
{

	private double	lambda, phi, radio;

	public CoordenadaEsferica(double lambda, double phi)
	{
		this.lambda = lambda;
		this.phi = phi;
		this.setRadio(1);
	}

	public CoordenadaEsferica(double lambda, double phi, double radio)
	{
		this.lambda = lambda;
		this.phi = phi;
		this.setRadio(radio);
	}

	public Cuaternion aCuaternion()
	{
		return null;
	}

	public double getLambda()
	{
		return lambda;
	}

	public double getPhi()
	{
		return phi;
	}

	public double getRadio()
	{
		return radio;
	}

	public void setLambda(double lambda)
	{
		this.lambda = lambda;
	}

	public void setPhi(double phi)
	{
		this.phi = phi;
	}

	public void setRadio(double radio)
	{
		this.radio = radio;
	}
}
