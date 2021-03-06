package org.junit.junittutorial._02elementary._03example;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class Cell {

	private final int row;
	private final int column;

	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public boolean equals(Object obj) {
		if(!Cell.class.isInstance(obj))
			return false;
		
		Cell cell = (Cell) obj;
		
		return (cell.getRow() == this.getRow() && cell.getColumn() == this.getColumn());
	}

}
