package gamelogic;

public class Bishop extends Piece{
	
	Type type;
	
	/**
     * Constructor for Bishop.
     * @param board   the board object of the current Board
     * @param location   the location object of the Board
     * @param owner the Owner object associated with the Piece
     */
	public Bishop(Board board, Location location, boolean player) {
		super(board, location, player);
		type = Type.BISHOP;
	}

	/**
	 * A function that gets the Piece type.
	 * @return  an integer indicating the Piece type
	 */
	public Type getType()
	{
		return Type.BISHOP;
	}
	
	/**
     * check Validmove for Bishop.
     * @param x, designated row's location
     * @param y, designated col's location
     * @return boolean = true if it's valid otherwise false
     */
	@Override
	public boolean legalMovement(int toI, int toJ) {
		
		// check if designated array out-of-bound
		if(isOutOfBound(toI, toJ)) {
			return false;
		}
		
		int fromI = this.getLocation().getRow();
		int fromJ = this.getLocation().getCol();
		
		// check if movement is horizontal or vertical 
		if(fromI == toI || fromJ == toJ ) {
			return false;
		}
		
		if(Math.abs((fromI - toI)) != Math.abs((fromJ - toI))) {
			return false;
		}
		
		return isPathDiagonalClear(toI, toJ, fromI, fromJ);
	}
}
