

import es.uma.eda.problem.algebra.diophantine.RationalNumber;

/**
 * Approximates a real number to an irreducible rational fraction using
 * the mediant approximation - see: Farey sequence, Stern-Brocot tree 
 * @author ccottap
 *
 */
public class MediantApproximation extends BinarySearchDiophantineApproximator {

	@Override
	public String getName() {
		return "Mediant-Approximation";
	}

	@Override
	protected RationalNumber getMiddlePoint(RationalNumber l, RationalNumber r) {
//		if (l.numerator() != 0 && (r.numerator() * l.denominator() - l.numerator() * r.denominator() == 1)) {
//			if (r.denominator() > l.denominator()) return l;
//			else return r;
//		} else {
//			RationalNumber mediant = new RationalNumber(l.numerator() + r.numerator(),
//					l.denominator() + r.denominator());
//			return mediant;
//		}
//	}
		RationalNumber mediant = new RationalNumber(l.numerator() + r.numerator(),
				l.denominator() + r.denominator());
		return mediant;
	}
}
