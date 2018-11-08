package gamelogic;

public class King extends Piece{
	
	Type type;
	
	/**
     * Constructor for King.
     * @param board   the board object of the current Board
     * @param location   the location object of the Board
     * @param owner the Owner object associated with the Piece
     */
	public King(Location location, Player player){
        super(location, player);
        type = Type.KING;
	}

	/**
	 * A function that gets the Piece type.
	 * @return  an integer indicating the Piece type
	 */
	public Type getType()
	{
		return Type.KING;
	}

	
	/**
     * check Validmove for King.
     * @param toX, designated row's location
     * @param toY, designated col's location
     * @return boolean = true if it's valid otherwise false
     */
	@Override
	public boolean legalMovement(int toI, int toJ, Start start, Player player) {
		
		if(isOutOfBound(toI, toJ)) {
			return false;
		}
		
		int fromJ = this.getLocation().getCol();
		int fromI = this.getLocation().getRow();
		
		int check_1x = Math.abs(fromI - toI);
		int check_1y = Math.abs(fromJ - toJ);
		if(fromJ == toJ && fromI == toI) return false;
		if(check_1x < 2 && check_1y < 2) {
			if(start.board.getPiece(toI, toJ) == null) {
				return true;
			}
			
			if(player.getPlayerType() != start.board.getPiece(toI, toJ).getPlayer().getPlayerType()) 
				return true;		
		}
		
		return false;
	}
	
	
}
