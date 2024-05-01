package lab09_alg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Helper {
	/** 
	 * Method to read the data of the file and return an array of strings
	 * in which each line is an array element
	 */
	public static String[] readLinesToArray(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }
	
	public static List<String> readLinesToList(String filename) throws IOException{
		BufferedReader bufReader = new BufferedReader(new FileReader(filename));
	    ArrayList<String> listOfLines = new ArrayList<>();

	    String line = bufReader.readLine();
	    while (line != null) {
	      listOfLines.add(line);
	      line = bufReader.readLine();
	    }

	    bufReader.close();
	    
	    return listOfLines;
	}
	
	// ----------My methods----------
	public static boolean isFilledMatrix(String[][] matr) {
		for (int row = 0; row < matr.length-2; row+=2) {
			if (!isFilledRow(matr, row))
				return false;
		}
		return true;
	}

	public static boolean checkMatchingMatrix(String[][] matr) {
		if (!checkNoUnknowns(matr)) {
			return false;
		}
		for (int row = 0; row < matr.length-2; row+=2) {
			if (!checkMatchingRow(matr, row))
				return false;
		}
		for (int col = 0; col < matr.length-2; col+=2) {
			if (!checkMatchingColumn(matr, col))
				return false;
		}
		return true;
	}

	private static boolean checkNoUnknowns(String[][] matr) {
		for (int i = 0; i < matr.length-2; i+=2) {
			for (int j = 0; j < matr.length-2; j+=2) {
				if (matr[i][j].equals("?")) {
					return false;
				}
			}
		}
		return true;
	}

	public static List<int[]> getVariablePositions(String[][] matr) {
		List<int[]> list = new ArrayList<int[]>();
		int[] pos = new int[2];
		
		for (int i = 0; i < matr.length-2; i+=2) {
			for (int j = 0; j < matr[0].length-2; j+=2) {
				if (matr[i][j].equals("?")) {
					pos = new int[]{i, j};
					list.add(pos);
				}
			}
		}
		
		return list;
	}

	public static boolean isFilledRow(String[][] matr, int i) {
		for (int j = 0; j < matr.length-2; j++) {
			if (matr[i][j].equals("?"))
				return false;
		}
		return true;
	}
	
	public static boolean isFilledColumn(String[][] matr, int j) {
		for (int i = 0; i < matr.length-2; i++) {
			if (matr[i][j].equals("?"))
				return false;
		}
		return true;
	}	
	
	public static boolean checkMatchingRow(String[][] matr, int index) {
		long rowResult = 0;
		int i = 1;
		while (!matr[index][i].equals("=")) {
			if (matr[index][i-1] == "?" || matr[index][i+1] == "?") {
				return false;
			}
			
			int right = Integer.parseInt(matr[index][i+1]);		
			
			if (i == 1) {
				int left = Integer.parseInt(matr[index][i-1]);
				
				switch (matr[index][i]) {
					case "+":
						rowResult = (left + right);
						break;
					case "-":
						rowResult = (left - right);
						break;
					case "*":
						rowResult = (left * right);
						break;
					case "/":
						if (right == 0 || left%right != 0) return false;
						rowResult = (left / right);
						break;
				}
			}
			else {
				switch (matr[index][i]) {
				case "+":
					rowResult += right;
					break;
				case "-":
					rowResult -= right;
					break;
				case "*":
					rowResult *= right;
					break;
				case "/":
					if (right == 0 || rowResult%right != 0) return false;
					rowResult /= right;
					break;
				}
			}
			i += 2;
		}
		
		long expectedResult = Long.parseLong(matr[index][matr[index].length-1]);
		return rowResult == expectedResult;
	}
	
	public static boolean checkMatchingColumn(String[][] matr, int index) {
		long columnResult = 0;
		int i = 1;
		while (!matr[i][index].equals("=")) {
			if (matr[i-1][index] == "?" || matr[i+1][index] == "?") {
				return false;
			}
			
			int down = Integer.parseInt(matr[i+1][index]);		
			
			if (i == 1) {
				int up = Integer.parseInt(matr[i-1][index]);
				
				switch (matr[i][index]) {
					case "+":
						columnResult = (up + down);
						break;
					case "-":
						columnResult = (up - down);
						break;
					case "*":
						columnResult = (up * down);
						break;
					case "/":
						if (down == 0 || up%down != 0) return false;
						columnResult = (up / down);
						break;
				}
			}
			else {
				switch (matr[i][index]) {
				case "+":
					columnResult += down;
					break;
				case "-":
					columnResult -= down;
					break;
				case "*":
					columnResult *= down;
					break;
				case "/":
					if (down == 0 || columnResult%down != 0) return false;
					columnResult /= down;
					break;
				}
			}
			i += 2;
		}
		
		long expectedResult = Long.parseLong(matr[matr[index].length-1][index]);
		return columnResult == expectedResult;
	}
	
	
}
