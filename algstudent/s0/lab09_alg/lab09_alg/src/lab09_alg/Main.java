package lab09_alg;

public class Main {
	
	public static void main(String[] args) {
		long t1, t2;		
		
		
		for (int i = 0; i <= 7; i++) {
			NumericSquareBaB bab = new NumericSquareBaB();
			
			t1 = System.currentTimeMillis();
			
			bab.computeBaB("src/testFiles/test0"  + i + ".txt");

			t2 = System.currentTimeMillis();

			System.out.println("test0"  + i + ":\t" + (t2 - t1));
			System.out.println("Developed nodes -> " + bab.getDevelopedNodes());
			System.out.println();
			bab.printResultNode();
			System.out.println("-----------------------------");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		NumericSquareBaB bab = new NumericSquareBaB();
		bab.computeBaB("src/testFiles/test0"  + 1 + ".txt");
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("SOLUTION:");
		bab.printResultNode();
		*/

		
		
		
		
		
		
		/*
		String[][] matrix = 
			{{"1","+","?","=","3"},
			 {"*","","-","=",""},
			 {"?","+","1","=","2"},
			 {"=","","=","",""},
			 {"1","","1","",""}
			};
		
		Node node = new Node(matrix,0,0);
		
		System.out.println("Row: " + node.getRow() + "   Column: " + node.getColumn());
		node.goToNextUnknown();
		System.out.print("Row: " + node.getRow() + "   Column: " + node.getColumn());
		*/
	}
	
}