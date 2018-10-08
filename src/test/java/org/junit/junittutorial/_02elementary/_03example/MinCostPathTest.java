package org.junit.junittutorial._02elementary._03example;

import static org.junit.jupiter.api.Assertions.*;

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
	
	@Test
	@DisplayName("Throws IllegalArgumentException when the start or target cell is out of matrix bound")
	void throwsIllegalArgumentExceptionWhenTheCellIsOutOfMatrixBound() throws Exception {
		
		MinCostPath minCostPath = new MinCostPath();
		final int[][] matrix = { {4, 5, 6}, {7, 8, 1} };
		
		assertAll("Start cell must be in matrix",
				() -> assertThrows(IllegalArgumentException.class, () -> minCostPath.find(matrix, new Cell(2, 1), new Cell(0, 2))),
				() -> assertThrows(IllegalArgumentException.class,() -> minCostPath.find(matrix, new Cell(-1, 1), new Cell(0, 2)))
				);
		
		assertAll("Target cell must be in matrix",
				() -> assertThrows(IllegalArgumentException.class, () -> minCostPath.find(matrix, new Cell(0, 0), new Cell(2, 2))),
				() -> assertThrows(IllegalArgumentException.class, () -> minCostPath.find(matrix, new Cell(0, 0), new Cell(-1, 2)))
				);
	}
}
