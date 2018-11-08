package gamelogic;

public class Location {
	
	// local variables
    int row;
	int col;
	
	/**
     * The constructor for class Location
     * @param x the Row's coordinate
     * @param y the Col's coordinate
     */
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
     * A function that gets the row
     * @return  an integer of row
     */
	public int getRow() {
		return row;
	}
	
	/**
     * A function that gets the column
     * @return an integer of row
     */
	public int getCol() {
		return col;
	}
	
}
