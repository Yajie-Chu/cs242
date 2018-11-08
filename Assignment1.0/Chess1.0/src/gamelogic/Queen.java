package gamelogic;

public class Queen extends Piece{
	Type type;
	
	/**
     * Constructor for Queen.
     * @param board   the board object of the current Board
     * @param location   the location object of the Board
     * @param owner the Owner object associated with the Piece
     */
	public Queen(Board board, Location location, boolean player) {
		super(board, location, player);
		type = Type.QUEEN;
	}

	/**
	 * A function that gets the Piece type.
	 * @return  an integer indicating the Piece type
	 */
	public Type getType()
	{
		return Type.QUEEN;
	}
	
	/**
     * check Validmove for Queen.
     * @param toX, designated row's location
     * @param toY, designated col's location
     * @return boolean = true if it's valid otherwise false
     */
	@Override
	public boolean legalMovement(int toI, int toJ) {
		
		// call (row, col) (x,y)
		int fromI = this.getLocation().getRow();
		int fromJ = this.getLocation().getCol();
		
		if(isOutOfBound(toI,toJ)) {
			return false;
		}
	
		// rook movement
		if((fromI == toI) || (fromJ == toJ)) {
			return true;
		}
		
		// bishop movement
		if(Math.abs((fromI - toI)) != Math.abs((fromJ - toJ))) {
			return false;
		}
		
		return true;
	}
	

}
