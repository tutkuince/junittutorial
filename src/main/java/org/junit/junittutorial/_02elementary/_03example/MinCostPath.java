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

		if (start.equals(target))
			return matrix[start.getRow()][start.getColumn()];

		return findMinCostPath(matrix, start, target);
	}

	private void validateIfTheCellIsOutOfMatrixBound(int[][] matrix, Cell cell) {
		if (cell.getRow() >= matrix.length || cell.getRow() < 0)
			throw new IllegalArgumentException();
		if (cell.getColumn() >= matrix[0].length || cell.getColumn() < 0)
			throw new IllegalArgumentException();
	}

	private int findMinCostPath(int[][] matrix, Cell start, Cell target) {

		if (start.getRow() > target.getRow() || start.getColumn() > target.getColumn())
			return Integer.MAX_VALUE;

		if (start.equals(target))
			return matrix[start.getRow()][start.getColumn()];

		int rightPathCost = findMinCostPath(matrix, new Cell(start.getRow(), start.getColumn() + 1), target);
		int downPathCost = findMinCostPath(matrix, new Cell(start.getRow() + 1, start.getColumn()), target);
		int diagonalPathCost = findMinCostPath(matrix, new Cell(start.getRow() + 1, start.getColumn() + 1), target);

		final int min = Math.min(rightPathCost, Math.min(downPathCost, diagonalPathCost));

		return min + matrix[start.getRow()][start.getColumn()];
	}

}
