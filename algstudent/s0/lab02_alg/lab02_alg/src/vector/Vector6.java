package vector;

/**
 * This program serves to measure times automatically increasing 
 * the size of the problem. In addition, we use a repetition value 
 * determined by nTimes, an argument of the program
 */
public class Vector6 {
	static int[]v1;
	static int[]v2;
	
	public static void main(String arg []) {
		System.out.println("Vector 6 executing");
		System.out.println("-------------------------------");
		int repetitions = Integer.parseInt(arg[0]);
		long t1,t2;
		int match = 0;
		
		for (int n=10000; n<=Integer.MAX_VALUE; n*=2){ //n is increased *5   
			  v1 = new int[n];
			  v2 = new int[n];
			  Vector1.fillIn(v1);
			  Vector1.fillIn(v2);
			  
			  t1 = System.currentTimeMillis();
			  //We have to repeat the whole process to be measured
			  for (int repetition=1; repetition<=repetitions; repetition++){    	
			     match = Vector1.matches1(v1, v2);
			  }
			  t2 = System.currentTimeMillis();
			  System.out.printf("SIZE=%d TIME=%d milliseconds SUM=%d NTIMES=%d\n", n, t2-t1, match, repetitions);	
		}//for 
		
	}//main

}
