package geometria;

import geometria.cuaterniones.Cuaternion;
import geometria.esferica.CoordenadaEsferica;

public interface Vector
{
	public Cuaternion aCuaternion();

	public CoordenadaEsferica aCoordenadaEsferica();
}
