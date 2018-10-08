package org.junit.junittutorial._02elementary._03example;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class MinCostPath {

	public int find(int[][] matrix, Cell start, Cell target) {
		
		validateIfTheCellIsOutOfMatrixBound(matrix, start);
		
		validateIfTheCellIsOutOfMatrixBound(matrix, target);
		
		return 0;
	}

	private void validateIfTheCellIsOutOfMatrixBound(int[][] matrix, Cell cell) {
		if(cell.getRow() >= matrix.length || cell.getRow() < 0)
			throw new IllegalArgumentException();
		if(cell.getColumn() >= matrix[0].length || cell.getColumn() < 0)
			throw new IllegalArgumentException();
	}

}
