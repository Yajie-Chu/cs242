package gamelogic;

public abstract class Piece {

	// Piece class variables
	private Location location;
	private Board board;
	private boolean player;
	
	/**
     * Constructor for a Piece.
     * @param board   the board object of the Piece
     * @param location   the location object of the Piece
     * @param owner the Owner object associated with the Piece
     */
	public Piece(Board board, Location location, boolean player) {
		this.board = board;
		this.location = location;
		this.player = player;
	}
	
	/**
     * A function getter that gets the board 
     * @return  the board object
     */
	public Board getBoard() {
		return board;
	}
	
	/**
     * A function getter that gets the location
     * @return  the location object
     */
	public Location getLocation() {
		return location;
	}
	
	/**
     * A function getter that gets the player
     * @return  the player object
     */
	public boolean getPlayer() {
		return player;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
     * An abstract function that returns the type of a Piece.
     */
    public abstract Type getType();
	
	/**
     * Abstract function that is shared among the Pieces type to check if it's a valid move
     * @param toI the row's designated location
     * @param toJ the col's designated location
     * @return  a boolean indicating whether the path is valid
     */
	public abstract boolean legalMovement(int toI, int toJ);
	
	public boolean checkMovement(int toI, int toJ, Piece piece) {
		
		if(piece.getType() == Type.KING) {
			return piece.legalMovement(toI, toJ);
		}
		else if(piece.getType() == Type.PAWN) {
			return piece.legalMovement(toI, toJ);
		}
		else if(piece.getType() == Type.ROOK) {
			return piece.legalMovement(toI, toJ);
		}
		else if(piece.getType() == Type.QUEEN) {
			return piece.legalMovement(toI, toJ);
		}
		else if(piece.getType() == Type.KNIGHT) {
			return piece.legalMovement(toI, toJ);
		}
		else if(piece.getType() == Type.BISHOP) {
			return piece.legalMovement(toI, toJ);
		}
		else {
			return false;
		}
	}
	
	/**
     * Function to check out-of-boundary.
     * @param i the Row's coordinate
     * @param j the Col's coordinate
     * @return true if it is out-of-boundary, otherwise false
     */
	public boolean isOutOfBound(int i, int j) {
		if(i >= 0 && i <= 7) {
			 if(j >= 0 && j <= 7) {
				 return false;
			 }
		}
			 
		return true;
	}
	
	/**
     * Function to check Diagonal path.
     * @param toI the designated X coordinate
     * @param toJ the designated Y coordinate
     * @param fromI the current X coordinate
     * @param fromJ the current Y coordinate
     * @return true if it is out-of-boundary, otherwise false
     */
	public boolean isPathDiagonalClear(int toI, int toJ, int fromI, int fromJ) {
		int dir_x, dir_y;
		int diff = Math.abs(toI - fromI) + 1;
		int flag2 = 0;
		
		if(fromI > toI) {
			dir_x = 1;
		}
		else {
			dir_x = - 1;
		}
		
		if(fromJ > toJ) {
			dir_y = 1;
		}
		else {
			dir_y = -1;
		}
		
		for(int i = 1; i < diff; i++) {
			if(getBoard().getPiece(fromI + i * dir_x, fromJ + i * dir_y) == null) {continue;}
			else {
				flag2 = -1;
				break;
			}
		}
		
		if(flag2 == 0) return true;
		else return false;
		
	}
	
	/**
     * Function to check vertical and horizontal path.
     * @param toI the designated X coordinate
     * @param toJ the designated Y coordinate
     * @param fromI the current X coordinate
     * @param fromJ the current Y coordinate
     * @return true if it is out-of-boundary, otherwise false
     */
	public boolean isPathStraightClear(int toI, int toJ, int fromI, int fromJ) {
		int dir_x = 0;
		int dir_y = 0;
		int diff = 1;
		int flag = 0;
		int flag2 = 1;
		
		if(toI == fromI) {
			if(fromJ < toJ) { // move right horizontally
				dir_y = 1;
			}
			else { // move left horizontally
				dir_y = -1;
			}
			
			diff = diff +  Math.abs(fromJ - toJ);
			flag = 1;
		}
		else {
			if(fromI < toI) { // move up vertically 
				dir_x = -1;
			}
			else {
				dir_x = 1; // move down vertically
			}
			
			diff = diff + Math.abs(fromI - toI);
		}
		
		if(flag == 1) {
			for(int i = 1; i < diff; i++) {
				if(getBoard().getPiece(toI, fromJ + (i * dir_y)) == null) {continue;}
				else {
					flag2 = -1;
					break;
				}
			}
		}
		else {
			for(int i = 1; i < diff ; i++) {
				if(getBoard().getPiece(fromI + (i * dir_x), toJ) == null) {continue;}
				else {
					flag2 = -1;
					break;
				}
			}
		}
		
		if(flag2 == 1) return true;
		else return false;
	}

	 
	public boolean equals(Piece other) {
		if(other == null) return false;
		return(this.getLocation().getRow() == other.getLocation().getRow()) && (this.getLocation().getCol() == other.getLocation().getCol()) && (this.getPlayer() == other.getPlayer());		
	}
}
