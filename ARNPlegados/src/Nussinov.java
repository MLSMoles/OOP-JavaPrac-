


import es.uma.eda.problem.combinatorial.sequence.folding.RNASecondaryStructurePredictor;

/**
 * Implementation of Nussinov algorithm for RNA secondary structure prediction by 
 * base-pair maximization using dynamic programming. 
 * @author ccottap
 *
 */
public class Nussinov extends RNASecondaryStructurePredictor {
	/**
	 * Strings describing compatible base pairs: basepairs1(i) is compatible with basepairs2(i)
	 */
	private final static String basepairs1 = "AUUCGG"; 
	private final static String basepairs2 = "UAGGCU"; 
	/**
	 * Default constructor
	 */
	public Nussinov() {
		
	}
	
	/**
	 * Returns true iff nucleotides b1 and b2 are compatible for pairing
	 * @param b1 a nucleotide (A, C, G, U)
	 * @param b2 a nucleotide (A, C, G, U)
	 * @return true iff nucleotides b1 and b2 are compatible for pairing
	 */
	protected boolean isCompatible (char b1, char b2) {
		b1 = Character.toUpperCase(b1);
		b2 = Character.toUpperCase(b2);
		for (int i=0; i<basepairs1.length(); i++)
			if ((basepairs1.charAt(i) == b1) && (basepairs2.charAt(i) == b2))
				return true;
		
		return false;
	}

	@Override
	public String getName() {
		return "Nussinov algorithm";
	}

	@Override
	protected String _run(String rnaSeq, int minLoopSize) {
		int n = rnaSeq.length();			// number of nucleotides in the sequence
		int[][] M = new int[n][n+1];		// to store costs
		int[][] D = new int[n][n];			// to store decisions
		boolean[][] B = new boolean[n][n];	// to precompute which base pairs match
		
		for (int i=0; i<n; i++)
			for (int j=i+1; j<n; j++)
				B[i][j] = isCompatible(rnaSeq.charAt(i), rnaSeq.charAt(j));
		
		// Computation of the optimal number of base-pairs
		// M(i,j) = maximum number of base pairs when folding the RNA sequence from position i to position j (both inclusive), 0 <= i < n, -1 <= j < n).
		// The range for j must include one unit less than the minimum for i, and since we cannot have negative indices, this means that we have 
		// to store M(i,j) at position M[i][j+1].
		// This situation (i = j+1) represents the limit case in which we have an empty sequence. No decision has to be taken in that case
		// and hence the table D does not need a sentinel. Thus, D(i,j) is stored in D[i][j] (and the same for table B).
		//
		// base case: M(i,j) = 0 if j <= i+minloopSize
		for (int i=0; i<n; i++) {
			M[i][i] = 0;				// M(i,i-1) = 0
			for (int j=i, k=0; (k<=minLoopSize) && (j<n); j++, k++) {
				M[i][j+1] = 0;			// M(i,j) = 0;
				D[i][j] = -1;			// -1 => unpaired
			}
		}
		// TODO
		// general case: M(i,j) = max(M(i,j-1),
		//max (M(i, k-1) + M(k+1,j-1) + 1 |
		//i <= k < j-minloopSize,
		// s_k and s_j are complementary)) if j > i+minloopSize
//		int column = 0;
//		int fila = 0;
//		int max = 0;
//		int maxK = 0;
//		for (int i = 0; i < n; i++) {
//			for (int j = i; j < n; j++) {
//				if (j - i > minLoopSize) {
//					fila = M[i][j - 1];
//					for (int k = i + 1; k < j - minLoopSize; k++) {
//						max = (M[i][k - 1] + M[k + 1][j - 1] + 1);
//						if (column < max) {
//							column = max;
//							maxK = k;
//						}
//					}
//					M[i][j] = Math.max(fila, column);
//					D[i][j] = maxK;
//				}
//			}
//		}
		int maxK=-1;
		for(int i = n-minLoopSize-1;i>0;i--) {
			for(int j = minLoopSize +1 +i;j<=n-1;j++) {
				if(j-i<=minLoopSize) {
					int column = 0;
					int fila = 0;
					fila = M[i][j-1];
					for(int k = i; k<j-minLoopSize;k++) {
						if(B[k][j]) {
							int max =0;
							max=((M[i][k] + M[k+1][j]) +1);
							if ( column >max) {
								column = max;
								maxK=k;
							}
							M[i][j+1]=Math.max(fila, column);
							D[i][j]=maxK;
						}
					}
				}
			}
		}
		
		
		if (verbosity) {
			System.out.println("\nScore matrix:\n");

			System.out.print(" \t|");
			for (int j=0; j<=n; j++)
				System.out.print("\t"+(j-1));
			System.out.print("\n   \t|\t ");
			for (int j=1; j<=n; j++)
				System.out.print("\t"+rnaSeq.charAt(j-1));
			System.out.print("\n---- \t+");
			for (int j=0; j<=n; j++)
				System.out.print("\t----");
			for (int i=0; i<n; i++) {
				System.out.print("\n"+i+ " " + rnaSeq.charAt(i)+"\t|\t");
				for (int j=0; j<i; j++) {
					System.out.print("  \t");
				}
				for (int j=i; j<=n; j++) {
					System.out.format("%d \t", M[i][j]);
				}
			}

			System.out.println("\n\nDecision matrix:\n");

			System.out.print(" \t|");
			for (int j=0; j<n; j++)
				System.out.print("\t"+j);
			System.out.print("\n   \t|");
			for (int j=0; j<n; j++)
				System.out.print("\t"+rnaSeq.charAt(j));
			System.out.print("\n---- \t+");
			for (int j=0; j<n; j++)
				System.out.print("\t----");
			for (int i=0; i<n; i++) {
				System.out.print("\n"+i+ " " + rnaSeq.charAt(i)+"\t|\t");
				for (int j=0; j<n; j++) {
					System.out.format("%d \t", D[i][j]);
				}
			}
			System.out.println("\n");
		}
		
		// Reconstruction of the optimal solution
		return reconstructSolution(D, 0, n-1);
	}
	
	
	private String reconstructSolution(int[][] D, int i, int j) {
		/*
		 * / String result = ""; /
		 */
		String result = "";

		// TODO
		// returns the folding in dot-bracket notation.
		// use a recursive approach for simplicity
//		for (int w = i; w < D.length; w++) {
//			for (int l = j; l >= 0; l--) {
//				//System.out.println(D[w][l]);
//				if (D[w][l] < 0) {
//					//System.out.println("HELLO");
//					result += ".";
//				}
//				else if (D[w][l] > 0) {
//					int k = D[i][j];
//					result += "(" + reconstructSolution(D, w, k - 1) + ")";
//				}
//			}
//
//		}
		if (D[i][j] < 0 && j - 1 >= 0) {
			result += reconstructSolution(D,i,j-1) + ".";
		} else if (D[i][j] > 0) {
			int k = D[i][j];
			result += reconstructSolution(D,i,k-1)+ "(" + reconstructSolution(D, k+1, j-1) + ")";
		}
		return result;
	}

	@Override
	public double evaluate(String rnaSeq, String folding) {
		//System.out.println("HERE1 " + rnaSeq.length());
		//System.out.println("Here " + folding.length());
		//System.out.println(folding.toString());
		if (rnaSeq.length() != folding.length())  // error: the folding string does not match the RNA sequence
			return -1.0;
		double contacts = 0.0;
		int n = folding.length();
		int open = 0;
		
		for (int i=0; i<n; i++) 
			switch (folding.charAt(i)) {
			case '.': break;
			case '(': contacts += 1.0;
					  open++;
					  break;
			case ')': open --;
					  if (open < 0)				// error: parentheses are not balanced
						  return -1.0;
					  break;
			default:  return -1.0;				// error: unknown symbol found
			}
		if (open > 0)							// error: parentheses are not balanced
			contacts = -1.0;
		return contacts;
	}

}
