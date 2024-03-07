package lab05;

public class MergeSortTimes {
	static int[] v;

	public static void main(String arg[]) {
		long t1, t2;
		String opcion = arg[0];
		
		while(true) {
			for (int n = 31250; n <= 1600000000; n *= 2) {
				v = new int[n];
	
				if (opcion.compareTo("ordered") == 0)
					Vector.sorted(v);
				else if (opcion.compareTo("reverse") == 0)
					Vector.reverseSorted(v);
				else
					Vector.randomSorted(v);
	
				t1 = System.currentTimeMillis();
	
				MergeSort.mergeSort(v, 0, v.length - 1);
	
				t2 = System.currentTimeMillis();
	
				System.out.println(n + "\t" + (t2 - t1));
			}
		}
	}
}
