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
		this.radio=1;
	}

	public CoordenadaEsferica(double lambda, double phi, double radio)
	{
		this.lambda = lambda;
		this.phi = phi;
		this.radio=radio;
	}

	public Cuaternion aCuaternion()
	{
	    double x = radio*Math.sin(phi)*Math.cos(lambda); 
	    double y = radio*Math.sin(phi)*Math.sin(lambda); 
	    double z = radio*Math.cos(phi); 
		
		return new Cuaternion(x, y, z);
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
