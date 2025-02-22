
public class Problem1 {
	static double actualRoot = 0.641583; // Root of function f

	// f(x), f'(x), and slope formula
	public static double f(double x) {
		return Math.exp(-Math.pow(x, 3)) - Math.pow(x, 4) - Math.sin(x);
	}
	public static double fPrime(double x) {
		return -3 * Math.pow(x, 2) * Math.exp(-Math.pow(x, 3)) - 4 * Math.pow(x, 3) - Math.cos(x);
	}
	public static double slope(double x1, double x2) {
		return (f(x2) - f(x1)) / (x2 - x1);
	}
	
	// Method 1: Bisection method
	public static void bisection() {
		double a = -1, b = 1; // Interval [-1, 1]
		double estimatedRoot = (a + b) / 2;
		
		int i = 0; // # of iterations
		int numOps = 2; // # of floating point operations
		while (Math.abs(estimatedRoot - actualRoot) >= 0.00005) {
			if (f(a) * f(estimatedRoot) < 0)
				b = estimatedRoot;
			else
				a = estimatedRoot;
			
			estimatedRoot = (a + b) / 2;
			i++;
			numOps += 4;
		}
		
		System.out.println("Method 1 (Bisection Method):");
		System.out.println("Estimated Root: " + estimatedRoot);
		System.out.println("# of Iterations: " + i);
		System.out.println("# of Floating Point Operations: " + numOps);
	}
	
	// Method 2: Newton's method
	public static void newton() {
		double x = 0; // Estimated root
		
		int i = 0; // # of iterations
		while (Math.abs(x - actualRoot) >= 0.00005) {
			x = x - f(x) / fPrime(x);
			i++;
		}
		
		System.out.println("Method 2 (Newton Method):");
		System.out.println("Estimated Root: " + x);
		System.out.println("# of Iterations: " + i);
	}
	
	// Method 3: Secant method
	public static void secant() {
		double firstRoot = -1;
		double secondRoot = 1; // Estimated root
		
		int i = 0; // # of iterations
		while (Math.abs(secondRoot - actualRoot) >= 0.00005) {
			double thirdRoot = secondRoot - f(secondRoot) / slope(firstRoot, secondRoot);
			firstRoot = secondRoot;
			secondRoot = thirdRoot;
			
			i++;
		}
		
		System.out.println("Method 3 (Secant Method):");
		System.out.println("Estimated Root: " + secondRoot);
		System.out.println("# of Iterations: " + i);
	}
	
	// Method 4: Monte Carlo method
	public static void monteCarlo() {
		double randomRoot = 0.5 + Math.random() * 0.25; // Random floating point value on [0.5, 0.75]
		
		int i = 0; // # of iterations
		while (Math.abs(randomRoot - actualRoot) >= 0.00005) {
			// Generate random floating point value on the interval [0.5, 0.75]
			randomRoot = 0.5 + Math.random() * 0.25;
			i++;
		}
		
		System.out.println("Method 4 (Monte Carlo Method):");
		System.out.println("Estimated Root: " + randomRoot);
		System.out.println("# of Iterations: " + i);
	}
	
	public static void main(String[] args) {
		bisection();
		System.out.println();
		newton();
		System.out.println();
		secant();
		System.out.println();
		monteCarlo();
	}

}
