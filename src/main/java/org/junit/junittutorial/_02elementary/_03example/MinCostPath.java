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
		
		if(start.equals(target))
			return matrix[start.getRow()][start.getColumn()];
		
		Cell currentCell = start;
		int cost = 0;
		while(!currentCell.equals(target)) {
			cost += matrix[currentCell.getRow()][currentCell.getColumn()];
			currentCell = new Cell(currentCell.getRow(), currentCell.getColumn() + 1);
		}
		cost += matrix[currentCell.getRow()][currentCell.getColumn()];
		
		return cost;
	}

	private void validateIfTheCellIsOutOfMatrixBound(int[][] matrix, Cell cell) {
		if(cell.getRow() >= matrix.length || cell.getRow() < 0)
			throw new IllegalArgumentException();
		if(cell.getColumn() >= matrix[0].length || cell.getColumn() < 0)
			throw new IllegalArgumentException();
	}

}
