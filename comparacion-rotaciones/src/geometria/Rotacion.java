package geometria;

import geometria.cuaterniones.Cuaternion;
import geometria.esferica.RotacionEsferica;

public interface Rotacion
{
	Vector rotar(Vector v);
	RotacionEsferica aRotacionEsferica();
	Cuaternion aCuaternion();
}
