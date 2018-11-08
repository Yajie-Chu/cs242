package gamelogic;

public class Player {
	
	boolean isWhite; // White == false, black == false
	
	/**
     * The constructor for class Location
     * @param x the Row's coordinate
     * @param y the Col's coordinate
     */
	public Player(boolean isWhite) {
		this.isWhite = isWhite; 
	}
	
	/**
     * A function that gets the boolean
     * @return true if white, else false. 
     */
	public boolean getPlayerType() {
		return isWhite;
	}
}
