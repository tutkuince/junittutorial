package org.junit.junittutorial._02elementary._03example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class MinCostPathTest {

	/**
	 * matrix, int[][], start cell, target cell, Cell(row, column)
	 * start cell or target cell is out of matrix bound		--- DONE
	 * start cell equals target cell then return cost of start cell
	 * find minimum cost path for one row matrix
	 * find minimum cost path for multiple row matrix
	 * right path cost, down path cost, diagonal path cost
	 *  
	 */
	
	private Cell cell(int row, int column) {
		return new Cell(row, column);
	}
	
	private MinCostPath minCostPath;
	
	@BeforeEach
	void setUp() {
		minCostPath = new MinCostPath();
	}
	
	@Test
	@DisplayName("Throws IllegalArgumentException when the start or target cell is out of matrix bound")
	void throwsIllegalArgumentExceptionWhenTheCellIsOutOfMatrixBound() throws Exception {
		
		final int[][] matrix = { {4, 5, 6}, {7, 8, 1} };
		
		assertAll("Start cell must be in matrix",
				() -> assertThrows(IllegalArgumentException.class, () -> minCostPath.find(matrix, cell(2, 1), cell(0, 2))),
				() -> assertThrows(IllegalArgumentException.class,() -> minCostPath.find(matrix, cell(-1, 1), cell(0, 2)))
				);
		
		assertAll("Target cell must be in matrix",
				() -> assertThrows(IllegalArgumentException.class, () -> minCostPath.find(matrix, cell(0, 0), cell(2, 2))),
				() -> assertThrows(IllegalArgumentException.class, () -> minCostPath.find(matrix, cell(0, 0), cell(-1, 2)))
				);
	}
	
	@Test
	@DisplayName("Return the cost of start cell when the start cell equals to target cell")
	void returnTheCostStartCellWhenTheStartCellEqualToTargetCell() throws Exception {
		final int[][] matrix = { { 3, 5, 7, 9 } };
		
		assertEquals(3, minCostPath.find(matrix, cell(0, 0), cell(0, 0)));
		assertEquals(7, minCostPath.find(matrix, cell(0, 2), cell(0, 2)));
	}
	
	
}
