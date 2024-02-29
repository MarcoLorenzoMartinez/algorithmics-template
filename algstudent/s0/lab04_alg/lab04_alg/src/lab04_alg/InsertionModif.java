package lab04_alg;

/* This program is used to sort n elements with a quadratic algorithm
except if the vector is initially ordered or nearly ordered,
which is of linear complexity (INSERTION) */
public class InsertionModif {
	static int[] v;

	/* Sorting by the Insertion method */
	public static void insertionModif(int[] a, int left, int right) {
		int j;
		int pivot;
		
		for (int i = left; i <= right; i++) {
			pivot = a[i];
			j = i-1;
			
			while (j >= left && pivot < a[j]) {
				a[j+1] = a[j];
				j--;
			}
			
			a[j+1] = pivot;
		}
	}

	public static void main(String arg[]) {
		int n = Integer.parseInt(arg[0]); //size of the problem
		v = new int[n];

		Vector.sorted(v);
		System.out.println("VECTOR TO BE SORTED");
		Vector.print(v);
		insertionModif(v,0,v.length-1);
		System.out.println("SORTED VECTOR");
		Vector.print(v);

		Vector.reverseSorted(v);
		System.out.println("VECTOR TO BE SORTED");
		Vector.print(v);
		insertionModif(v,0,v.length-1);
		System.out.println("SORTED VECTOR");
		Vector.print(v);

		Vector.randomSorted(v);
		System.out.println("VECTOR TO BE SORTED");
		Vector.print(v);
		insertionModif(v,0,v.length-1);
		System.out.println("SORTED VECTOR");
		Vector.print(v);
	} 

}
