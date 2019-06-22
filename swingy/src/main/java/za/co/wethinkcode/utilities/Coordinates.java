package za.co.wethinkcode.utilities;

public class Coordinates {
	private int row;
	private int column;
	
	public Coordinates(int row, int col) {
		this.row = row;
		this.column = col;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.column;
	}
}
