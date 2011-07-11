package geometria.cuaterniones;

import geometria.Rotacion;
import geometria.Vector;
import geometria.esferica.CoordenadaEsferica;

public class Cuaternion implements Rotacion, Vector
{
	private double	x;
	private double	y;
	private double	z;
	private double	angulo;

	public Cuaternion(double x, double y, double z, double angulo)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.angulo = angulo;
	}

	public CoordenadaEsferica aEsferica()
	{
		return null;
	}

	public double getAngulo()
	{
		return angulo;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getZ()
	{
		return z;
	}

	@Override
	public void rotar(Vector v)
	{
		// TODO Auto-generated method stub

	}

	public void setAngulo(double angulo)
	{
		this.angulo = angulo;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public void setZ(double z)
	{
		this.z = z;
	}

	public static void rotar(Rotacion rotacion, Vector vector)
	{
		// TODO Auto-generated method stub
		
	}

}
