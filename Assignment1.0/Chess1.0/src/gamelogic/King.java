package gamelogic;

public class King extends Piece{
	
	Type type;
	
	/**
     * Constructor for King.
     * @param board   the board object of the current Board
     * @param location   the location object of the Board
     * @param owner the Owner object associated with the Piece
     */
	public King(Board board, Location location, boolean player){
        super(board, location, player);
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
	public boolean legalMovement(int toX, int toY) {
		
		if(isOutOfBound(toX, toY)) {
			return false;
		}
		
		int fromY = this.getLocation().getCol();
		int fromX = this.getLocation().getRow();
		
		int check_1x = Math.abs(fromX - toX);
		int check_1y = Math.abs(fromY - toY);
		
		if(check_1x < 2 && check_1y < 2) {
			return true;
		}
		
		return false;
	}
	
	
}
