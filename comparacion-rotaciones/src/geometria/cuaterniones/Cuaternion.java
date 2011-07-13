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

	public CoordenadaEsferica aCoordenadaEsferica()
	{
		double modulo = getModulo();
		double phi, lambda;

		if (z > 0)
		{
			phi = Math.atan(Math.sqrt(x * x + y * y) / z);
		}
		else if (z < 0)
		{
			phi = Math.PI + Math.atan(Math.sqrt(x * x + y * y) / z);
		}
		else
		{
			phi = Math.PI / 2;
		}
		if (x > 0)
		{
			if (y > 0)
			{
				lambda = Math.atan(y / x);
			}
			else
			{
				lambda = 2 * Math.PI + Math.atan(y / x);
			}
		}
		else if (x < 0)
		{
			lambda = Math.PI + Math.atan(y / x);
		}
		else
		{
			lambda = Math.PI / 2 * Math.signum(y);
		}
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
		Cuaternion rotation = aRotacion();
		Cuaternion rotated = rotation.mult(v).mult(rotation.conjugado());
		return rotated;

		// Cuaternion rotacion = aRotacion();
		// // Cuaternion rotado = rotacion.mult(v);
		// // rotado = rotado.mult(rotacion.conjugado());
		// Cuaternion rotado = v.mult(rotacion);
		// rotado = rotacion.conjugado().mult(rotado);
		// return rotado;
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
			return cuaternion.rotar(vector);
		}
		if (rotacion instanceof RotacionEsferica)
		{
			cuaternion = ((RotacionEsferica) rotacion).aCuaternion();
			return cuaternion.rotar(vector).aCoordenadaEsferica();
		}

		return null;

	}

	public Cuaternion conjugado()
	{
		return new Cuaternion(-x, -y, -z,angulo);
	}

	public Cuaternion suma(Cuaternion c)
	{
		return new Cuaternion(x + c.x, y + c.y, z + c.z,angulo + c.angulo);
	}

	public Cuaternion mult(Cuaternion otro)
	{
		Cuaternion otroXangulo = otro.multFactor(getAngulo());
		Cuaternion esteXotroAngulob = multFactor(otro.getAngulo());
		Cuaternion vectorial = multVectorial(otro);

		return new Cuaternion(otroXangulo.getX() + esteXotroAngulob.getX() + vectorial.getX(), otroXangulo.getY() + esteXotroAngulob.getY() + vectorial.getY(), otroXangulo.getZ() + esteXotroAngulob.getZ() + vectorial.getZ(), getAngulo() * otro.getAngulo() - multEscalar(otro));

		// Cuaternion a = this;
		// double angulo = a.angulo * otro.angulo - a.x * otro.x - a.y * otro.y - a.z * otro.z;
		// double x = a.angulo * otro.x + a.x * otro.angulo + a.y * otro.z - a.z * otro.y;
		// double y = a.angulo * otro.y - a.x * otro.z + a.y * otro.angulo + a.z * otro.x;
		// double z = a.angulo * otro.z + a.x * otro.y - a.y * otro.x + a.z * otro.angulo;
		// return new Cuaternion(x, y, z, angulo);

		// final double w0 = this.getAngulo();
		//
		// final double x0 = this.getX();
		//
		// final double y0 = this.getY();
		//
		// final double z0 = this.getZ();
		//
		// final double w1 = otro.getAngulo();
		//
		// final double x1 = otro.getX();
		//
		// final double y1 = otro.getY();
		//
		// final double z1 = otro.getZ();
		//
		// return new Cuaternion(w0 * w1 - x0 * x1 - y0 * y1 - z0 * z1, y0 * z1 - z0 * y1 + w0 * x1 + x0 * w1, z0 * x1 - x0 * z1 + w0 * y1 + y0 * w1, x0 * y1 - y0 * x1 + w0 * z1 + z0 * w1);

		// double angulo = this.angulo * otro.angulo - this.x * otro.x - this.y * otro.y - this.z * otro.z;
		// double x = this.y * otro.z - this.z * otro.y + this.angulo * otro.x + otro.angulo * this.x;
		// double y = -this.x * otro.z + otro.x * this.z + this.angulo * otro.y + otro.angulo * this.y;
		// double z = this.x * otro.y - otro.x * this.y + this.angulo * otro.z + otro.angulo * this.z;
		// return new Cuaternion(x, y, z, angulo);

		// double escalar = multEscalar(this, otro);
		//
		// Cuaternion otroXAngulo = multFactor(this.getAngulo(), otro);
		// Cuaternion esteXAngulo = multFactor(otro.getAngulo(), this);
		// Cuaternion vectorial = multVectorial(this, otro);
		//
		// return new Cuaternion(otroXAngulo.x + esteXAngulo.x + vectorial.x, otroXAngulo.y + esteXAngulo.y + vectorial.y, otroXAngulo.z + esteXAngulo.z + vectorial.z, (this.getAngulo() *
		// otro.getAngulo()) - escalar);
	}

	private Cuaternion multFactor(double escalar)
	{
		return new Cuaternion(x * escalar, y * escalar, z * escalar, angulo * escalar);
	}

	public Cuaternion inverso()
	{
		double d = angulo * angulo + x * x + y * y + z * z;
		return new Cuaternion(-x / d, -y / d, -z / d,angulo / d);
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
		return new RotacionEsferica(aCoordenadaEsferica(), angulo);
	}

	public String toString()
	{
		return "x: " + x + " y: " + y + " z: " + z + " angulo: " + angulo + " Modulo: " + getModulo();
	}

	@Override
	public Cuaternion aCuaternion()
	{
		return new Cuaternion(x, y, z, angulo);
	}

	public Cuaternion multVectorial(Cuaternion c)
	{
		double x = y * c.z - z * c.y;

		double y = z * c.x - x * c.z  ;

		double z = x * c.y - y * c.x;

		return new Cuaternion(x, y, z);
	}

	public double multEscalar(Cuaternion c)
	{
		return (x * c.x) + (y * c.y) + (z * c.z);
	}
}
