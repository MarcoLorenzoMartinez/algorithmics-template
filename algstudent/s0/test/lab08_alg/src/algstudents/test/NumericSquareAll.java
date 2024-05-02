package algstudents.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumericSquareAll {
	int size;
	int matrixSize;
	String[][] matrix;
	String[][] result;
	List<String[][]> results;
	int solutionCounter = 0;
	
	public void compute() {
		List<int[]> positions = getVariablePositions(matrix);
		results = new ArrayList<String[][]>();
		computeRec(positions, 0);
	}
	
	/**
	 * 
	 * @param positions --> list of positions with "?" in the matrix
	 * @param iter --> the index iterating over the list of positions
	 */
	public void computeRec(List<int[]> positions, int iter) {
		if (iter >= positions.size()) return;
		
		int row = positions.get(iter)[0];
		int col = positions.get(iter)[1];
				
		for (int i = -5; i <= 5; i++) { // Test
			result[row][col] = String.valueOf(i);
			if (isFilledRow(result, row) && !checkMatchingRow(result, row))
				continue;
			if (isFilledColumn(result, col) && !checkMatchingColumn(result, col))
				continue;
			
			if (isFilledMatrix(result) && checkMatchingMatrix(result)) {
				String[][] res = deepCopy(result);
				results.add(res);
				solutionCounter++;
				result[row][col] = "?";
				return;
			}
			
			computeRec(positions, iter+1);
		}
		
		result[row][col] = "?";
	}
	
	private String[][] deepCopy(String[][] orig) {
        if (orig == null) 
        	return null;
        
        final String[][] result = new String[orig.length][];
        for (int i = 0; i < orig.length; i++) {
            result[i] = Arrays.copyOf(orig[i], orig[i].length);
        }
        return result;
    }
	
	private boolean isFilledMatrix(String[][] matr) {
		for (int row = 0; row < matr.length-2; row+=2) {
			if (!isFilledRow(matr, row))
				return false;
		}
		return true;
	}

	public boolean checkMatchingMatrix(String[][] matr) {
		// ----------Test-------------------------------------------
		int diagonal = 0;
		for (int i = 0; i < matr.length-2; i+=2) {
			for (int j = 0; j < matr.length-2; j+=2) {
				if (i == j) {
					diagonal += Integer.parseInt(matr[i][j]);
				}
					
			}
		}
		if (diagonal != 0) {
			return false;
		}
		// ---------------------------------------------------------
		
		
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

	public List<int[]> getVariablePositions(String[][] matr) {
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

	public boolean isFilledRow(String[][] matr, int i) {
		for (int j = 0; j < matr.length-2; j++) {
			if (matr[i][j].equals("?"))
				return false;
		}
		return true;
	}
	
	public boolean isFilledColumn(String[][] matr, int j) {
		for (int i = 0; i < matr.length-2; i++) {
			if (matr[i][j].equals("?"))
				return false;
		}
		return true;
	}	
	
	public boolean checkMatchingRow(String[][] matr, int index) {
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
	
	public boolean checkMatchingColumn(String[][] matr, int index) {
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
	
	/**
	 * Reading the numeric square from a file
	 * 
	 * @param filename the name of the file where the numeric square is
	 */
	public void fileToMatrix(String filename) {
		List<String> lines = new ArrayList<String>();
		
		try {
			lines = Helper.readLinesToList(filename);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		size = Integer.parseInt(lines.get(0));
		matrixSize = (size*2)+1;
		matrix = new String[matrixSize][matrixSize];
		lines.remove(0);
		
		for (int i = 0; i < lines.size(); i++) {
			String[] spLine = lines.get(i).split(" ");
			if (i%2 == 0 && i != matrixSize-1) { // Line with numbers and operators
				matrix[i] = spLine;
			} else { // Line with only numbers or only operators
				for (int j = 0; j < matrixSize; j++) {
					if (j%2 == 0 && j/2 < spLine.length)
						matrix[i][j] = spLine[j/2];
					else
						matrix[i][j] = null;
				}
			}
		}
		
		result = matrix;
	}
	
	public void printMatrix(String[][] matr) {
		if (matr == null) {
			System.out.print("NO RESULT");
			return;
		}	
		
		int size = matr.length;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(matr[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public int getSolutionCounter() {
		return solutionCounter;
	}

	public List<String[][]> getResults() {
		return results;
	}
}
