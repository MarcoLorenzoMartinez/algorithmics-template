package lab04_alg;

import java.util.Random;

/* This program is used to order n elements with Quicksort */
public class QuicksortInsertion {
	static int[] v;
	/*get the position of the median of the three (left, right and 
	 the element which position is in the center between them, and
	 move the elements to order them */
	public static int median_of_three(int[] a, int left, int right) { 
		int center = (left + right) / 2;
		if (a[left] > a[center])
			Vector.interchange(a, left, center);
		if (a[left] > a[right])
			Vector.interchange(a, left, right);
		if (a[center] > a[right])
			Vector.interchange(a, center, right);
		return center;
	}
	
	public static void quicksortInsert(int[] a, int k) {
		quicksortInsert(a, 0, a.length-1, k);
	}
	
	public static void quicksortInsert(int[] a, int left, int right, int k) {
		int i = left;
		int j = right - 1;
		int pivot;
		
		if (right - left < k) {
			InsertionModif.insertionModif(a, left, right);
		}
		else if (left < right){ //if there is one element it is not necessary
			int center = median_of_three(a, left, right);
			//if there are less than or equal to 3 elements, there are just ordered
			if ((right - left) >= 3){ 
				pivot = a[center]; //choose the pivot
				Vector.interchange(a, center, right); //hide the pivot

				do {         
			    	while (a[i] <= pivot && i < right) i++; //first element > pivot
			    	while (a[j] >= pivot && j > left) j--; //first element < pivot
			        if (i < j) Vector.interchange(a, i, j);
			    } while (i < j);   //end while
				
				//we set the position of the pivot
				Vector.interchange(a, i, right);
				quicksortInsert(a, left, i-1, k);
				quicksortInsert(a, i+1, right, k);		
			} //if
		} //if
	}


	public static void main(String arg[]) {
//		int n = Integer.parseInt(arg[0]); //size of the problem
//		v = new int[n];
//
//		Vector.sorted(v);
//		System.out.println("VECTOR TO BE SORTED");
//		Vector.print(v);
//		quicksortInsert(v);
//		System.out.println("SORTED VECTOR");
//		Vector.print(v);
//
//		Vector.reverseSorted(v);
//		System.out.println("VECTOR TO BE SORTED");
//		Vector.print(v);
//		quicksortInsert(v);
//		System.out.println("SORTED VECTOR");
//		Vector.print(v);
//
//		Vector.randomSorted(v);
//		System.out.println("VECTOR TO BE SORTED");
//		Vector.print(v);
//		quicksortInsert(v);
//		System.out.println("SORTED VECTOR");
//		Vector.print(v);
		
		int[] array = new int[20];
		for (int i = 0; i < array.length; i++) {
			Random r = new Random();
			array[i] = r.nextInt(100);
		}
		
		System.out.println("Vector to sort: ");
		System.out.print("(");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " , ");
		}
		System.out.println();
		
		QuicksortInsertion.quicksortInsert(array,30);
		
		System.out.println("Vector sorted: ");
		System.out.print("(");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " , ");
		}
	}

}
