package hr.fer.zemris.java.gui.layouts;

public class RCPosition {

	private int row;
	private int column;
	
	public RCPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public static RCPosition parse(String text) {
		return new RCPosition(Integer.parseInt(text.split(",")[0]), Integer.parseInt(text.split(",")[1]));
	}
	
	
	
}
