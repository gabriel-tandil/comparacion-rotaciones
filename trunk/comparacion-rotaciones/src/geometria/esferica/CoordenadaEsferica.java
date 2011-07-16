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
	    double senoPhi=Math.sin(phi);
		double x = radio*senoPhi*Math.cos(lambda); 
	    double y = radio*senoPhi*Math.sin(lambda); 
	    double z = radio*senoPhi; 
	    
	    return new Cuaternion(x, y, z);

		// Cuaternion retorno=new Cuaternion(x, y, z);
		// String inspeccion=this.toString();
		// // System.out.println(inspeccion);
		// String inspeccion=retorno.toString();
		// System.out.println(inspeccion);
		// return retorno ;
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
	@Override
	public String toString()
	{
		return "phi: "+phi+" lambda: "+ lambda+" radio: "+radio;
	}

	@Override
	public CoordenadaEsferica aCoordenadaEsferica()
	{
		return new CoordenadaEsferica(lambda, phi);
	}
}
