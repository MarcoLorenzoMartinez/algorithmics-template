package lab04_alg;

/* This class measures times for the Selection method
for the 3 assumptions: (already ordered, reverse ordered and random ordered) */
public class QuicksortInsertionTimes {
	static int[] v;

	public static void main(String arg[]) {
		long t1, t2;
		String opcion = arg[0];
		
		int[] kArray = {5,10,20,30,50,100,200,500,1000};
		
		for (int i = 0; i < kArray.length; i++) {
			v = new int[16000000];

			if (opcion.compareTo("ordered") == 0)
				Vector.sorted(v);
			else if (opcion.compareTo("reverse") == 0)
				Vector.reverseSorted(v);
			else
				Vector.randomSorted(v);

			t1 = System.currentTimeMillis();

			QuicksortInsertion.quicksortInsert(v, kArray[i]);

			t2 = System.currentTimeMillis();

			System.out.println("k=" + kArray[i] + "\t" + (t2 - t1));
		}
	}
}
