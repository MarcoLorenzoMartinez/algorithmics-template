package lab01_alg;

import java.util.ArrayList;

public class JavaA1 {
	
	public static void main(String[] args){
		int n = 10000;
		for (int i = 0; i < 7; i++) {
			long t1 = System.currentTimeMillis();
			ArrayList<Integer> primes = listadoPrimos(n);
			long t2 = System.currentTimeMillis();
			System.out.println("n=" + n + "*** time=" + (t2-t1) + "milliseconds");
			n = n*2;
		}
	}
	
	private static ArrayList<Integer> listadoPrimos(int n) {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (int i = 2; i < n+1; i++) {
			if (primoA1(i)) {
				primes.add(i);
			}
		}
		return primes;
	}
	
	private static boolean primoA1(int m) {
		boolean p = true;
		for (int i = 2; i < m; i++) {
			if (m%i == 0) {
				p = false;
			}
		}
		return p;
	}
	
	
}
