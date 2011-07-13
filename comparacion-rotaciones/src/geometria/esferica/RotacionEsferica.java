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
			coordenadaEsferica = ((Cuaternion) v).aCoordenadaEsferica();
			return rotar(coordenadaEsferica).aCuaternion();
		}
		if (v instanceof CoordenadaEsferica) {
			coordenadaEsferica = (CoordenadaEsferica) v;
			return rotar(coordenadaEsferica);
		}

		return null;
	}

	public CoordenadaEsferica rotar(CoordenadaEsferica p) {
		// eje de la rotacion u
		// vector a rotar p
		CoordenadaEsferica u = eje;
		double difLambda = p.getLambda() - u.getLambda();

		double cosenoDifLambda = Math.cos(difLambda);
		double senoDifLambda = Math.sin(difLambda);
		//double senoDifLambda = Math.sqrt(1 - cosenoDifLambda * cosenoDifLambda);
		double cosenoPhiP = Math.cos(p.getPhi());
		double senoPhiP = Math.sin(p.getPhi());
		//double senoPhiP = Math.sqrt(1 - cosenoPhiP * cosenoPhiP);
		double cosenoPhiU = Math.cos(u.getPhi());
		double senoPhiU = Math.sin(u.getPhi());
		//double senoPhiU = Math.sqrt(1 - cosenoPhiU * cosenoPhiU);
		double cosenoAngulo = Math.cos(angulo);
		double senoAngulo = Math.sin(angulo);
		//double senoAngulo = Math.sqrt(1 - cosenoAngulo * cosenoAngulo);

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

		// With coseno PUZ, seno PUZ, coseno Angulo and seno Angulo they can be
		// calculated y using the classical formulas of the trigonometry
		// (cosines and sines of the addition of angles).
		// Calculation of the cosine of P'UZ
		double cosenoP1UZ = (cosenoPUZ * cosenoAngulo) - (senoPUZ * senoAngulo);

		// Calculation of the cosine of phi_P'
		double cosenoPhiP1 = (cosenoUP * cosenoPhiU)
				+ (senoUP * senoPhiU * cosenoP1UZ);

		// Calculation of the sine of phi_P
		double senoPhiP1 = Math.sqrt(1 - cosenoPhiP1 * cosenoPhiP1);

		// Calculation of the cosine of P'ZU
		double cosenoP1ZU = (cosenoUP - cosenoPhiU * cosenoPhiP1)
				/ (senoPhiU * senoPhiP1);

		if (cosenoP1ZU > 1)
			cosenoP1ZU = 1; // correcciones por decimales
		if (cosenoP1ZU < -1)
			cosenoP1ZU = -1;

		if (cosenoPhiP1 > 1)
			cosenoPhiP1 = 1; // correcciones por decimales
		if (cosenoPhiP1 < -1)
			cosenoPhiP1 = -1;

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

	@Override
	public RotacionEsferica aRotacionEsferica() {// no paso el radio ya que de
													// un eje deberia ser 1
		return new RotacionEsferica(new CoordenadaEsferica(eje.getLambda(),
				eje.getPhi()), angulo);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return eje.toString() + " angulo: " + angulo;
	}
}
