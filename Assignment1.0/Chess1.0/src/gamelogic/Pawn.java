package gamelogic;

public class Pawn extends Piece {
	Type type;
	
	/**
     * Constructor for Pawn.
     * @param board   the board object of the current Board
     * @param location   the location object of the Board
     * @param owner the Owner object associated with the Piece
     */
	public Pawn(Board board, Location location, boolean player) {
		super(board, location, player);
		type = Type.PAWN;
	}

	/**
	 * A function that gets the Piece type.
	 * @return  an integer indicating the Piece type
	 */
	public Type getType()
	{
		return Type.PAWN;
	}
	

	/**
     * check Validmove for Pawn.
     * @param toI, designated row's location
     * @param toJ, designated col's location
     * @return boolean = true if it's valid otherwise false
     */
	@Override
	public boolean legalMovement(int toI, int toJ) {
		 
		if(isOutOfBound(toI, toJ)) {
			return false;
		}
		
		// (row,col) 
		int fromJ = this.getLocation().getCol();
		int fromI = this.getLocation().getRow();
		
		// check for 1 or 2 steps for Pawn
		
		// player
		if(getPlayer() == true) {
			if(fromI == 1) {
				if(Math.abs(toI - fromI) == 2 && fromJ == toJ && (fromI < toI)) {
					return true;
				}
				if(Math.abs(toI - fromI) == 1) {
					if(Math.abs(fromJ - toJ) < 2 && fromI < toI) {
						return true;
					}
				}
			}
			else {
				if(Math.abs(fromI- toI) == 1 && Math.abs(fromJ - toJ) < 2 && (fromI < toI)) {
					return true;
			}
			}
		}
		
		// opponent
		if(getPlayer() == false) {
			
			if(fromI == 6) {
				if(Math.abs(fromI - toI) == 2 && fromJ == toJ && (fromI > toI)) {
					return true;
				}
				if(Math.abs(toI - fromI) == 1) {
					if(Math.abs(fromJ - toJ) < 2 && fromI < toI) {
						return true;
					}
				}
			}
			else {
				if(Math.abs(fromI- toI) == 1 && Math.abs(fromJ - toJ) < 2 && (fromI > toI)) {
					return true;
				}
			}
		}
		 

		return false;
	}
	
}
