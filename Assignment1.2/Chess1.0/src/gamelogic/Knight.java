package gamelogic;

public class Knight extends Piece{
	Type type;
	
	/**
     * Constructor for Knight.
     * @param board   the board object of the current Board
     * @param location   the location object of the Board
     * @param owner the Owner object associated with the Piece
     */
	public Knight( Location location, Player player) {
		super( location, player);
		type = Type.KNIGHT;
	}

	/**
	 * A function that gets the Piece type.
	 * @return  an integer indicating the Piece type
	 */
	public Type getType()
	{
		return Type.KNIGHT;
	}
	/**
     * check Validmove for Knight.
     * @param toX, designated row's location
     * @param toY, designated col's location
     * @return boolean = true if it's valid otherwise false
     */
	@Override
	public boolean legalMovement(int toI, int toJ, Start start, Player player) {
		
		// check if designated array out-of-bound
		if(isOutOfBound(toI, toJ)) {
			return false;
		}
		
		int fromJ = this.getLocation().getCol();
		int fromI = this.getLocation().getRow();
		
		int check_1x = Math.abs(fromI - toI);
		int check_2y = Math.abs(fromJ - toJ);
		
		int check_2x = Math.abs(fromI - toI);
		int check_1y = Math.abs(fromJ - toJ);
		
		if(check_1x == 1 && check_2y == 2 || (check_2x == 2 && check_1y == 1)) {
			if(start.board.getPiece(toI, toJ) == null) {
				return true;
			}
			
			if(player.getPlayerType() == start.board.getPiece(toI, toJ).getPlayer().getPlayerType()) {
				return false;
			}
			else {
				return true;
			}
		}
	
		
		return false;
	}
	
	
}
