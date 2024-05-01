package lab09_alg;

import java.util.ArrayList;
import java.util.Arrays;

public class Node implements Comparable<Node>{
	private String[][] matrix;
	private int heuristicValue = -1;
	private int row;
	private int column;
	
	public Node(String[][] matrix, int row, int column) {
		this.matrix = matrix;
		this.row = row;
		this.column = column;
	}
	
	public void calculateHeuristicValue() {
		int heuristic = 0;
		
		for (int i = 0; i < matrix.length-2; i+=2) { // If a row or a column is wrong, we won't get to a solution, so the heuristic value is the worst possible
			if (Helper.isFilledRow(matrix,i) && !Helper.checkMatchingRow(matrix, i)) {
				heuristicValue = Integer.MAX_VALUE;
				return;
			}
			if (Helper.isFilledColumn(matrix,i) && !Helper.checkMatchingColumn(matrix, i)) {
				heuristicValue = Integer.MAX_VALUE;
				return;
			}
		}
		
		for (int i = 0; i < matrix.length-2; i+=2) { // The more filled rows and columns, the closer we are to the solution
			if (!Helper.isFilledRow(matrix,i) || !Helper.isFilledColumn(matrix, i)) {
				heuristic++;
			}
		}
		
		heuristicValue = heuristic;
	}
	
	
	public boolean isSolution() {
		return Helper.checkMatchingMatrix(matrix);
	}
	
	public ArrayList<Node> expand(){
		goToNextUnknown();
		ArrayList<Node> childs = new ArrayList<Node>();
		
		for (int i = 0; i <= 9; i++) {
			String[][] child = deepCopy(matrix);
			child[row][column] = "" + i;
			Node childNode = new Node(child,0,0);
			childs.add(childNode);
		}
		
		return childs;
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
	
	public void goToNextUnknown() {
		int i = row;
		int j = column;
		
		int len = matrix.length;
		
		while (i < len && j < len && !matrix[i][j].equals("?")) {
			if (i < len-2 && j < len-2) {
				j += 2;
			} else if (j >= len-2) {
				i += 2;
				j = 0;
			}
		}
		
		if (i < len && j < len){ // Question mark found
			row = i;
			column = j;
		} else { // No question mark
			row = -1;
			column = -1;
		}
	}

	public String[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(String[][] matrix) {
		this.matrix = matrix;
	}

	public int getHeuristicValue() {
		if (heuristicValue == -1)
			calculateHeuristicValue();
		return heuristicValue;
	}

	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int initialValuePruneLimit() {
		return Integer.MAX_VALUE;
	}
	
	@Override
	public int compareTo(Node node) {
		int totalValue = heuristicValue;
	    int totalValueToBeCompared = node.getHeuristicValue();

	    if (totalValue > totalValueToBeCompared)
	      return 1;
	    else if (totalValue == totalValueToBeCompared)
	      return 0;
	    else
	      return -1;
	}
	
}
