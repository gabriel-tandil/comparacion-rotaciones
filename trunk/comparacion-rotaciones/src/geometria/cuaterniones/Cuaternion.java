package geometria.cuaterniones;

import geometria.Rotacion;
import geometria.Vector;
import geometria.esferica.CoordenadaEsferica;
import geometria.esferica.RotacionEsferica;

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

	public Cuaternion(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.angulo = 0;
	}

	public CoordenadaEsferica aEsferica()
	{
		double modulo = getModulo();
		double phi = Math.acos(z / modulo);
		double lambda = Math.acos(x / (modulo * Math.sin(phi)));
		return new CoordenadaEsferica(lambda, phi, modulo);
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
	public Vector rotar(Vector v)
	{
		Cuaternion cuaternion = null;
		if (v instanceof Cuaternion)
		{
			cuaternion = (Cuaternion) v;
		}
		if (v instanceof CoordenadaEsferica)
		{
			cuaternion = ((CoordenadaEsferica) v).aCuaternion();
		}

		return rotar(cuaternion);

	}

	public Cuaternion rotar(Cuaternion v)
	{
		Cuaternion rotacion = aRotacion();
		Cuaternion rotado = rotacion.mult(v).mult(rotacion.conjugado());
		return rotado;
	}

	public Cuaternion aRotacion()
	{
		double anguloSobre2 = angulo / 2;
		double senoAnguloSobre2 = Math.sin(anguloSobre2);
		return new Cuaternion(x * senoAnguloSobre2, y * senoAnguloSobre2, z * senoAnguloSobre2, Math.cos(anguloSobre2));
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

	public static Vector rotar(Rotacion rotacion, Vector vector)
	{
		Cuaternion cuaternion = null;
		if (rotacion instanceof Cuaternion)
		{
			cuaternion = (Cuaternion) rotacion;

		}
		if (rotacion instanceof RotacionEsferica)
		{
			cuaternion = ((RotacionEsferica) rotacion).aCuaternion();

		}
		return cuaternion.rotar(vector);		
	
	}

	public Cuaternion conjugado()
	{
		return new Cuaternion(angulo, -x, -y, -z);
	}

	public Cuaternion suma(Cuaternion b)
	{
		Cuaternion a = this;
		return new Cuaternion(a.angulo + b.angulo, a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public Cuaternion mult(Cuaternion b)
	{
		Cuaternion a = this;
		double y0 = a.angulo * b.angulo - a.x * b.x - a.y * b.y - a.z * b.z;
		double y1 = a.angulo * b.x + a.x * b.angulo + a.y * b.z - a.z * b.y;
		double y2 = a.angulo * b.y - a.x * b.z + a.y * b.angulo + a.z * b.x;
		double y3 = a.angulo * b.z + a.x * b.y - a.y * b.x + a.z * b.angulo;
		return new Cuaternion(y0, y1, y2, y3);
	}

	public Cuaternion inverso()
	{
		double d = angulo * angulo + x * x + y * y + z * z;
		return new Cuaternion(angulo / d, -x / d, -y / d, -z / d);
	}

	public Cuaternion divide(Cuaternion b)
	{
		Cuaternion a = this;
		return a.inverso().mult(b);
	}

	public double getModulo()
	{
		return Math.sqrt(x * x + y * y + z * z);
	}

	public RotacionEsferica aRotacionEsferica()
	{
		return new RotacionEsferica(aEsferica(), angulo);
	}

}
