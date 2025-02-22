import Jama.Matrix;

public class Problem2 {
	static int[] t = {5, 4, 3, 2, 1};
	static int[] y = {417, 398, 397, 407, 412};

	// 4th order polynomial function that interpolates data
	public static double P(double x) {
		double output = 0;
		
		// Compute and sum each term together
		for (int i = 0; i < 5; i++) {
			// Determine numerator of term
			double numerator = y[i];
			for (int j = 0; j < 5; j++) {
				if (j != i)
					numerator *= x - t[j];
			}
			
			// Determine denominator of term
			double denominator = 1;
			for (int j = 0; j < 5; j++) {
				if (j != i)
					denominator *= t[i] - t[j];
			}
			
			output += numerator / denominator;
		}
		
		return output;
	}
	
	// Quadratic function that represents fit of data
	public static double Q(double x) {
		// Create matrix A (5x3)
		Matrix A = new Matrix(5, 3);
		for (int i = 0; i < 5; i++) {
			A.set(i, 0, 1);
			A.set(i, 1, t[i]);
			A.set(i, 2, Math.pow(t[i], 2));
		}
		
		// Create transpose of A
		Matrix transposeA = A.transpose();
		
		// Create vector b (5x1)
		Matrix b = new Matrix(new double[] {417, 398, 397, 407, 412}, 5);
		
		// Solve for vector c (3x1) in matrix equation (Transpose of A) * A * c = (Transpose of A) * b
		Matrix c = transposeA.times(A).solve(transposeA.times(b)); // Vector of coefficients [c1, c2, c3]
		
		return c.get(0, 0) + c.get(1, 0) * x + c.get(2, 0) * Math.pow(x, 2);
	}
	
	public static void main(String[] args) {
		System.out.println("(1) P(t = 6) = " + P(6));
		System.out.println("(2) Q(t = 6) = " + Q(6));
	}

}
