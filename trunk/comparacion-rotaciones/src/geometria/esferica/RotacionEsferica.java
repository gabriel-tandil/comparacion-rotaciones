package geometria.esferica;

import geometria.Rotacion;
import geometria.Vector;
import geometria.cuaterniones.Cuaternion;

public class RotacionEsferica implements Rotacion {

	private CoordenadaEsferica eje;
	private double angulo;

	public RotacionEsferica(CoordenadaEsferica eje, double angulo) {
		this.eje = eje;
		this.angulo = angulo;
	}

	public RotacionEsferica(CoordenadaEsferica eje, double angulo,
			CoordenadaEsferica vector) {
		this.eje = eje;
		this.angulo = angulo;
	}

	public Cuaternion aCuaternion() {
		Cuaternion cua = eje.aCuaternion();
		cua.setAngulo(angulo);
		return cua;
	}

	public double getAngulo() {
		return angulo;
	}

	public CoordenadaEsferica getEje() {
		return eje;
	}

	@Override
	public Vector rotar(Vector v) {
		CoordenadaEsferica coordenadaEsferica = null;
		if (v instanceof Cuaternion) {
			coordenadaEsferica = ((Cuaternion) v).aEsferica();
			System.out.println("rotar esfecica dato cuaternion");
		}
		if (v instanceof CoordenadaEsferica) {
			coordenadaEsferica = (CoordenadaEsferica) v;
			System.out.println("rotar esfecica dato esferica");
		}

		return rotar(coordenadaEsferica);
	}

	public CoordenadaEsferica rotar(CoordenadaEsferica p) {
		// eje de la rotacion u
		// vector a rotar p
		CoordenadaEsferica u = eje;
		double difLambda = p.getLambda() - u.getLambda();

		double cosenoDifLambda = Math.cos(difLambda);
		double senoDifLambda = Math.sin(difLambda);

		double cosenoPhiP = Math.cos(p.getPhi());
		double senoPhiP = Math.sin(p.getPhi());

		double cosenoPhiU = Math.cos(u.getPhi());
		double senoPhiU = Math.sin(u.getPhi());

		double cosenoAngulo = Math.cos(angulo);
		double senoAngulo = Math.sin(angulo);

		// Calculation of the cosine of the arc UP (equal to UP’)
		double cosenoUP = cosenoPhiU * cosenoPhiP + senoPhiU * senoPhiP
				* cosenoDifLambda;

		// Calculation of the sine of the arc UP. OJO, ESTO LO HACIA ABAJO
		// seno^2(a)+coseno^2(a)=1
		double senoUP = Math.sqrt(1 - cosenoUP * cosenoUP);
		// double senoUP =-1*
		// (senoPhiP*cosenoDifLambda-senoPhiU*cosenoUP)/(cosenoPUZ*cosenoPhiU);

		// Calculation of the cosine of the dihedron angle PUZ
		double cosenoPUZ = (cosenoPhiP - cosenoPhiU * cosenoUP) / senoPhiU
				* senoUP;

		// Calculation of the sine of PUZ
		double senoPUZ = (senoUP * senoPhiP) / senoDifLambda;

		// cos(P'UZ) = cos(PUZ) * cos(omega) - sin(PUZ) * sin(omega)
		double cosenoP1UZ = (cosenoPUZ * cosenoAngulo) - (senoPUZ * senoAngulo);

		// cos(phiP') = cos(up') * cos(phiU) + sin(up') * sin(phiU) * cos(P'UZ)
		double cosenoPhiP1 = (cosenoUP * cosenoPhiU)
				+ (senoUP * senoPhiU * cosenoP1UZ);

		// cos(up) = RAIZ(1 - cos2(up))
		double senoPhiP1 = Math.sqrt(1 - cosenoPhiP1 * cosenoPhiP1);

		// cos(P'ZU) = ( cos(up) - cos(phiU) * cos(phiP') ) / sin(phiU) *
		// sin(phiP')
		double cosenoP1ZU = (cosenoUP - cosenoPhiU * cosenoPhiP1)
				/ (senoPhiU * senoPhiP1);


		// P'ZU = arc cos(cos(P'ZU))

		double P1ZU = Math.acos(cosenoP1ZU);


		return new CoordenadaEsferica(u.getLambda() + P1ZU,
				Math.acos(cosenoPhiP1));
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public void setEje(CoordenadaEsferica eje) {
		this.eje = eje;
	}

	public static Vector rotar(Rotacion rotacion, Vector vector) {
		RotacionEsferica rotacionEsferica = null;
		if (rotacion instanceof RotacionEsferica) {
			rotacionEsferica = (RotacionEsferica) rotacion;

		}
		if (rotacion instanceof Cuaternion) {
			rotacionEsferica = ((Cuaternion) rotacion).aRotacionEsferica();

		}
		return rotacionEsferica.rotar(vector);

	}

}
