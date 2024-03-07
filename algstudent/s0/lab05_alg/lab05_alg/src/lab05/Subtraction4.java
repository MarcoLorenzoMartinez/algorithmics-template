package lab05;

/* Class that models T(n)=T(n-1)+O(n)
 * The time complexity is quadratic O(n^2) 
 * and the waste of stack is O(n)
 * In this case => the stack overflows 
 */
public class Subtraction4 {
	
	static long cont;
	
	public static void rec4(int n) {
		if (n <= 0)
			cont++;
		else {
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					cont++;
			rec4(n - 1);
			for (int i = 0; i < n; i++)
				cont++; // O(n)
		}
	}

	public static void main(String arg[]) {
		long t1, t2 = 0;
		int nTimes = Integer.parseInt (arg [0]);
		
		for (int n = 100; n <= 100000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int reps=1; reps<=nTimes;reps++)
			{ 
				cont = 0;
				rec4(n);
			} 			

			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main
} // class