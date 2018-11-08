package gamelogic;

public class Board {
	
	// ChessBoard variable container; tiles
	private Piece [][] tiles;
	
	
	/**
     * Constructor for the chess board.
     * @param Empty create 2D array of size 8x8
     */
	public Board() {
		tiles = new Piece [8][8];
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				tiles [x][y] = null;
			}
		}
		
		
	}
	
	// board getters
	public Piece[][] getBoard(){
		return tiles;
	}
	
	/**
     * function to initialize piece
     * @param Piece get the piece types
     */
	public void newPiece(Piece piece) {
		if(piece.getLocation().getRow() < 0 || piece.getLocation().getRow() > 7 || piece.getLocation().getCol() < 0 || piece.getLocation().getRow() > 7) {
			tiles[piece.getLocation().getRow()][piece.getLocation().getCol()] = null;
		}
		tiles[piece.getLocation().getRow()][piece.getLocation().getCol()] = piece;
	}
	
	/**
     * Function to add piece to current tiles
     * @param Piece the Piece object
     * @param Location the location object
     */
	public void addPiece(Piece piece, Location location){
        tiles[location.getRow()][location.getCol()] = piece;
    }
	
	/**
     * Function to remove piece add current tiles
     * @param Piece the Piece object
     */
	public void removePiece(Piece piece) {
		tiles[piece.getLocation().getRow()][piece.getLocation().getCol()] = null;
	}
	
	/**
     * Function to move piece 
     * @param Piece the Piece object
     * @param Location the location object
     */
	public void movePiece(Piece piece, Location location, Start start, Player player) {
		if (piece.checkMovement(location.getRow(), location.getCol(), piece, start, player) == true) {
			removePiece(piece);
			addPiece(piece, location);
			piece.setLocation(location);
		}
	}
	
	/**
     * Function from Piece class to retrieve the arrays.
     * @param x the Row's coordinate
     * @param y the Col's coordinate
     * @return the tiles if it's not out-of-bound
     */
	public Piece getPiece(int x, int y) {
		if(x >= 0 && x <= 7) 
		  if(y >= 0 && y <= 7)
			return tiles[x][y];
		
		return null;
	}
	
	
	public boolean checkedKnight(Piece p, Start start, Player player) {
		int fromI = p.getLocation().getRow();
		int fromJ = p.getLocation().getRow();
		
		Piece p1 = start.board.getPiece(fromI + 1, fromJ + 2);
		if(p1.getType() == Type.KNIGHT && p1.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p2 = start.board.getPiece(fromI + 1, fromJ - 2);
		if(p2.getType() == Type.KNIGHT && p2.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p3 = start.board.getPiece(fromI - 1, fromJ - 2);
		if(p3.getType() == Type.KNIGHT && p3.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p4 = start.board.getPiece(fromI - 1, fromJ + 2);
		if(p4.getType() == Type.KNIGHT && p4.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p5 = start.board.getPiece(fromI + 2, fromJ + 1);
		if(p5.getType() == Type.KNIGHT && p5.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p6 = start.board.getPiece(fromI - 2, fromJ - 1);
		if(p6.getType() == Type.KNIGHT && p6.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p7 = start.board.getPiece(fromI + 2, fromJ - 1);
		if(p7.getType() == Type.KNIGHT && p7.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p8 = start.board.getPiece(fromI - 2, fromJ + 1);
		if(p8.getType() == Type.KNIGHT && p8.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		return false;
	}
	
	public boolean checkedDiagonal(Piece p, Start start, Player player) {
		int fromI = p.getLocation().getRow();
		int fromJ = p.getLocation().getCol();
		int countJ = fromJ;
		int countJ2 = fromJ;
		
		for(int i = fromI-1; i >= 0; i--) {
			countJ = countJ - 1;
			countJ2 = countJ2 + 1;
			Piece p1 = start.board.getPiece(i, countJ);
			Piece p2 = start.board.getPiece(i, countJ2);
			if(p1 != null  && countJ == fromJ - 1) {
				if((p1.getType() == Type.PAWN || p1.getType() == Type.BISHOP || p1.getType() == Type.QUEEN) && p1.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}
			}
			if(p2 != null  && countJ == fromJ - 1) {
				if((p2.getType() == Type.PAWN || p2.getType() == Type.BISHOP || p2.getType() == Type.QUEEN) && p2.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}
			}
			
			if(p1 != null) {
				if((p1.getType() == Type.BISHOP || p1.getType() == Type.QUEEN) && p.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}
			if(p2 != null) {
				if((p2.getType() == Type.BISHOP || p2.getType() == Type.QUEEN) && p2.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}
			}
				
			}
		}
		
		countJ = fromJ;
		countJ2 = fromJ;
		for(int i = fromI+1; i <= 7; i++) {
			countJ = countJ - 1;
			countJ2 = countJ2 + 1;
			Piece p1 = start.board.getPiece(i, countJ);
			Piece p2 = start.board.getPiece(i, countJ2);
			if(p1 != null  && countJ == fromJ - 1) {
				if((p1.getType() == Type.PAWN || p1.getType() == Type.BISHOP || p.getType() == Type.QUEEN) && p1.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}
			}
			if(p2 != null  && countJ == fromJ - 1) {
				if((p2.getType() == Type.PAWN || p2.getType() == Type.BISHOP || p2.getType() == Type.QUEEN) && p2.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}
			}
			
			if(p1 != null) {
				if((p1.getType() == Type.BISHOP || p1.getType() == Type.QUEEN) && p1.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}
			}
			if(p2 != null) {
				if((p2.getType() == Type.BISHOP || p2.getType() == Type.QUEEN) && p2.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean checkedStraight(Piece piece, Start start, Player player) {
		int fromI = piece.getLocation().getRow();
		int fromJ = piece.getLocation().getCol();
		
		for(int i = fromI-1; i >= 0; i--) {
			Piece p = start.board.getPiece(i, fromJ);
			if(p != null && (p.getType() == Type.ROOK || p.getType() == Type.QUEEN) && p.getPlayer().getPlayerType() != player.getPlayerType()){
				return true;
			}
		}
		
		for(int i = fromI + 1; i <= 7; i++) {
			Piece p = start.board.getPiece(i, fromJ);
			if(p != null && (p.getType() == Type.ROOK || p.getType() == Type.QUEEN) && p.getPlayer().getPlayerType() != player.getPlayerType()){
				return true;
			}
		}
		
		for(int j = fromJ - 1; j >= 0; j--) {
			Piece p = start.board.getPiece(fromI, j);
			if(p != null && (p.getType() == Type.ROOK || p.getType() == Type.QUEEN) && p.getPlayer().getPlayerType() != player.getPlayerType()){
				return true;
			}
		}
		
		for(int j = fromJ + 1; j <= 7; j++) {
			Piece p = start.board.getPiece(fromI, j);
			if(p != null && (p.getType() == Type.ROOK || p.getType() == Type.QUEEN) && p.getPlayer().getPlayerType() != player.getPlayerType()){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean ischecked(Piece p, Start start, Player player) {
		if(checkedStraight(p, start, player) == true || checkedDiagonal(p, start, player) == true || checkedKnight(p, start, player) == true) {
			return true;
		}
		
		return false;
	}
	
	public boolean isCheckedMate(Piece p, Start start, Player player) {
		int fromI = p.getLocation().getRow();
		int fromJ = p.getLocation().getCol();
		
		if(p.getType() == Type.KING) {
			 
				start.board.movePiece(p, new Location(fromI + 1, fromJ), start, player);
				if(ischecked(p, start, player) == false) {
					start.board.removePiece(p);
					return false;
				}
				
				start.board.movePiece(p, new Location(fromI - 1, fromJ), start, player);
				if(ischecked(p, start, player) == false) {
					start.board.removePiece(p);
					return false;
				}
				
				start.board.movePiece(p, new Location(fromI , fromJ - 1), start, player);
				if(ischecked(p, start, player) == false) {
					start.board.removePiece(p);
					return false;
				}
				
				start.board.movePiece(p, new Location(fromI, fromJ + 1), start, player);
				if(ischecked(p, start, player) == false) {
					start.board.removePiece(p);
					return false;
				}
				
				start.board.movePiece(p, new Location(fromI + 1, fromJ + 1), start, player);
				if(ischecked(p, start, player) == false) {
					start.board.removePiece(p);
					return false;
				}
				
				start.board.movePiece(p, new Location(fromI - 1, fromJ - 1), start, player);
				if(ischecked(p, start, player) == false) {
					start.board.removePiece(p);
					return false;
				}
				
				start.board.movePiece(p, new Location(fromI + 1, fromJ - 1), start, player);
				if(ischecked(p, start, player) == false) {
					start.board.removePiece(p);
					return false;
				}
				
				
				start.board.movePiece(p, new Location(fromI - 1, fromJ + 1), start, player);
				if(ischecked(p, start, player) == false) {
					start.board.removePiece(p);
					return false;
				}
				
				return true;
		}
		else {
			return false;
		}
		 
	}
}
