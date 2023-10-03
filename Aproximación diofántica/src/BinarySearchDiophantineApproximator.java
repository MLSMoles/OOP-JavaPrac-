

import es.uma.eda.problem.algebra.diophantine.RationalNumber;
import es.uma.eda.problem.algebra.diophantine.approximation.DiophantineApproximator;

/**
 * Generic procedure for conducting binary search to find the rational approximation
 * of a floating-point number. The actual computation of the middle point is delegated
 * to derived classes.
 * @author ccottap
 *
 */
public abstract class BinarySearchDiophantineApproximator extends DiophantineApproximator {		
	/**
	 * rational number 0
	 */
	protected static final RationalNumber zero = new RationalNumber(0,1);
	/**
	 * rational number 1
	 */
	protected static final RationalNumber one = new RationalNumber(1,1);

	@Override
	protected RationalNumber _approximate(double x, double epsilon) {
		RationalNumber L, R; // rational numbers that enclose x
		RationalNumber M; // the middle-ish point (a rational number between L and R).

		if (x < epsilon) // Optional: to have integers being approximated
			M = zero; // in an exact way, and save computations in boundary
		else if (x > (1 - epsilon)) // cases (x being too close to 0 or to 1).
			M = one;
		else {
			L = zero;
			R = one;
			M = getMiddlePoint(L, R);
			//System.out.println("HELLO");

			if (Math.abs((x - M.asDouble())) < epsilon) {
				return M;
			} else {
				while (!(Math.abs(x - M.asDouble()) < epsilon)) {
					//System.out.println(M.numerator());
					if (M.asDouble() < x) {
						L = M;
					} else {
						R = M;
					}
					M = getMiddlePoint(L, R);
				}
			}
		}
		return M;
	}
	
	/**
	 * Returns a rational number in between l and r
	 * @param l the left rational number
	 * @param r the right rational number
	 * @return a rational number between l and r
	 */
	protected abstract RationalNumber getMiddlePoint(RationalNumber l, RationalNumber r);


}
