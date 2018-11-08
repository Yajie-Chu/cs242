package gamelogic;

public class Board {
	
	// ChessBoard variable container; tiles
	private Piece [][] tiles;
	private Piece undoPiece;
	public Piece secondPiece;
	private int curI, curJ;
	private Location checkLoc, secondLoc;
	
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
	
	public void undo(Piece piece, Location location, Location location2) {
		tiles[location.getRow()][location.getCol()] = piece;
		//System.out.println(piece.getType() + " at " + location.getRow() + ", " + location.getCol() );
		if(secondPiece != null) {
			tiles[curI][curJ] = secondPiece;
			tiles[curI][curJ].setLocation(location2);
			System.out.println(secondPiece.getType() + " at " + location2.getRow() + ", " + location2.getCol() );
		}else {
			removePiece(piece);
		}
		piece.setLocation(location);
		System.out.println(piece.getType() + " at " + piece.getLocation().getRow() + ", " + piece.getLocation().getCol() );
	}
	
	/**
     * Function to move piece 
     * @param Piece the Piece object
     * @param Location the location object
     */
	public boolean movePiece(Piece piece, Location location, Start start, Player player) {
		if (piece.checkMovement(location.getRow(), location.getCol(), piece, start, player) == true) {
			checkLoc = piece.getLocation();
			
			undoPiece = piece;
			curI = location.getRow();
			curJ = location.getCol();
		    secondPiece = tiles[curI][curJ];
			secondLoc = location;
			addPiece(piece, location);
			removePiece(piece);
			piece.setLocation(location);
			if(piece.getType() == Type.KING) {
				if(ischecked(piece, start, player, 0)) {
					 
					undo(piece, checkLoc,location);
					return false;
				}
			}
			else {
				Piece k = findKing(start, player, 0);
				if(ischecked(k, start, player, 0)) {
					undo(piece, checkLoc, location);
					return false;
				}
			}
			return true;
		}
		return false;
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
	
	/**
     * Function to check if king is in checked by Knight
     * @param P the king
     * @param Start the board
     * @param player the player
     * @return true if in checked o/w false
     */
	public boolean checkedKnight(Piece p, Start start, Player player) {
		int fromI = p.getLocation().getRow();
		int fromJ = p.getLocation().getCol();
		
		Piece p1 = start.board.getPiece(fromI + 1, fromJ + 2);
		if(p1 != null && p1.getType() == Type.KNIGHT && p1.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p2 = start.board.getPiece(fromI + 1, fromJ - 2);
		if(p2 != null &&p2.getType() == Type.KNIGHT && p2.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p3 = start.board.getPiece(fromI - 1, fromJ - 2);
		if(p3 != null &&p3.getType() == Type.KNIGHT && p3.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p4 = start.board.getPiece(fromI - 1, fromJ + 2);
		if(p4 != null &&p4.getType() == Type.KNIGHT && p4.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p5 = start.board.getPiece(fromI + 2, fromJ + 1);
		if(p5 != null && p5.getType() == Type.KNIGHT && p5.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p6 = start.board.getPiece(fromI - 2, fromJ - 1);
		if(p6 != null && p6.getType() == Type.KNIGHT && p6.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p7 = start.board.getPiece(fromI + 2, fromJ - 1);
		if(p7 != null && p7.getType() == Type.KNIGHT && p7.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p8 = start.board.getPiece(fromI - 2, fromJ + 1);
		if(p8 != null && p8.getType() == Type.KNIGHT && p8.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		return false;
	}
	
	/**
     * Function to check if king is in checked by Prince
     * @param P the king
     * @param Start the board
     * @param player the player
     * @return true if in checked o/w false
     */
	public boolean checkedPrince(Piece p, Start start, Player player) {
		int fromI = p.getLocation().getRow();
		int fromJ = p.getLocation().getCol();
		
		Piece p1 = start.board.getPiece(fromI, fromJ + 2);
		if(p1 != null && p1.getType() == Type.PRINCE && p1.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p2 = start.board.getPiece(fromI , fromJ - 2);
		if(p2 != null &&p2.getType() == Type.PRINCE && p2.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p3 = start.board.getPiece(fromI - 2, fromJ);
		if(p3 != null &&p3.getType() == Type.PRINCE && p3.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p4 = start.board.getPiece(fromI + 2, fromJ);
		if(p4 != null &&p4.getType() == Type.PRINCE && p4.getPlayer().getPlayerType() != player.getPlayerType()) {
			 
			return true;
		}
		
		return false;
	}
	
	/**
     * Function to check if king is in checked by Prince
     * @param P the king
     * @param Start the board
     * @param player the player
     * @return true if in checked o/w false
     */
	public boolean checkedPrincess(Piece p, Start start, Player player) {
		int fromI = p.getLocation().getRow();
		int fromJ = p.getLocation().getCol();
		
		Piece p1 = start.board.getPiece(fromI, fromJ + 1);
		if(p1 != null && p1.getType() == Type.PRINCESS && p1.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p2 = start.board.getPiece(fromI , fromJ - 1);
		if(p2 != null &&p2.getType() == Type.PRINCESS && p2.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p3 = start.board.getPiece(fromI - 1, fromJ);
		if(p3 != null &&p3.getType() == Type.PRINCESS && p3.getPlayer().getPlayerType() != player.getPlayerType()) {
			return true;
		}
		
		Piece p4 = start.board.getPiece(fromI + 1, fromJ);
		if(p4 != null &&p4.getType() == Type.PRINCESS && p4.getPlayer().getPlayerType() != player.getPlayerType()) {
			 
			return true;
		}
		
		return false;
	}
	
	/**
     * Function to check if king is in checked diagonally
     * @param P the king
     * @param Start the board
     * @param player the player
     * @return true if in checked o/w false
     */
	public boolean checkedDiagonal(Piece p, Start start, Player player) {
		int fromI = p.getLocation().getRow();
		int fromJ = p.getLocation().getCol();
		int countJ = fromJ;
		int countJ2 = fromJ;
		
		for(int i = fromI-1; i >= 0; i--) {
			countJ = countJ - 1;
			Piece p1 = start.board.getPiece(i, countJ);
			
			if(p1 != null  && countJ == fromJ - 1) {
				if((p1.getType() == Type.PAWN || p1.getType() == Type.BISHOP || p1.getType() == Type.QUEEN) && p1.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}else {break;}
			}
			if(p1 != null) {
				if((p1.getType() == Type.BISHOP || p1.getType() == Type.QUEEN) && p1.getPlayer().getPlayerType() != player.getPlayerType()) 
					return true;
				else break;
			}
		}
		for(int i = fromI-1; i >= 0; i--) {
			countJ2 = countJ2 + 1;
			
			Piece p2 = start.board.getPiece(i, countJ2);
			
			if(p2 != null  && countJ2 == fromJ + 1) {
				if((p2.getType() == Type.PAWN || p2.getType() == Type.BISHOP || p2.getType() == Type.QUEEN) && p2.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}else break;
			}
				
			if(p2 != null) {
				if((p2.getType() == Type.BISHOP || p2.getType() == Type.QUEEN) && p2.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}else break;
			}
				
				
		}
		
		countJ = fromJ;
		countJ2 = fromJ;
		for(int i = fromI+1; i <= 7; i++) {
			countJ = countJ - 1;
			Piece p1 = start.board.getPiece(i, countJ);
			if(p1 != null  && countJ == fromJ - 1) {
				
				if((p1.getType() == Type.PAWN || p1.getType() == Type.BISHOP || p1.getType() == Type.QUEEN) && p1.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}else break;
			}
			if(p1 != null) {
				if((p1.getType() == Type.BISHOP || p1.getType() == Type.QUEEN) && p1.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}else break;
			}
		}
		for(int i = fromI+1; i <= 7; i++) {
			
			countJ2 = countJ2 + 1;
			
			Piece p2 = start.board.getPiece(i, countJ2);
			
			if(p2 != null  && countJ2 == fromJ + 1) {
				if((p2.getType() == Type.PAWN || p2.getType() == Type.BISHOP || p2.getType() == Type.QUEEN) && p2.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}else break;
			}
			if(p2 != null) {
				if((p2.getType() == Type.BISHOP || p2.getType() == Type.QUEEN) && p2.getPlayer().getPlayerType() != player.getPlayerType()) {
					return true;
				}else break;
			}
		}
		
		return false;
	}
	
	/**
     * Function to check if king is in checked by Straight path
     * @param P the king
     * @param Start the board
     * @param player the player
     * @return true if in checked o/w false
     */
	public boolean checkedStraight(Piece piece, Start start, Player player) {
		int fromI = piece.getLocation().getRow();
		int fromJ = piece.getLocation().getCol();
		//System.out.println(fromI +"" + fromJ);
		for(int i = (fromI - 1); i >= 0; i--) {
			//System.out.println(i);
			Piece p = start.board.getPiece(i, fromJ);
			if(p != null && (p.getType() != Type.ROOK) && (p.getType() != Type.QUEEN)) {break;}
			if(p != null && (p.getType() == Type.ROOK || p.getType() == Type.QUEEN) && p.getPlayer().getPlayerType() != player.getPlayerType()){
				//System.out.println("i is" + i +"fromJj is" + fromJ);
				//System.out.println(p.getType()+""+p.getPlayer().getPlayerType()+"---------"+player.getPlayerType());
				return true;
			}
		}
		
		for(int i = fromI + 1; i <= 7; i++) {
			Piece p = start.board.getPiece(i, fromJ);
			if(p != null && (p.getType() != Type.ROOK) && (p.getType() != Type.QUEEN)) {break;}
			if(p != null && (p.getType() == Type.ROOK || p.getType() == Type.QUEEN) && p.getPlayer().getPlayerType() != player.getPlayerType()){
				//System.out.println("2");
				return true;
			}
		}
		
		for(int j = fromJ - 1; j >= 0; j--) {
			Piece p = start.board.getPiece(fromI, j);
			if(p != null && (p.getType() != Type.ROOK) && (p.getType() != Type.QUEEN)) {break;}
			if(p != null && (p.getType() == Type.ROOK || p.getType() == Type.QUEEN) && p.getPlayer().getPlayerType() != player.getPlayerType()){
				//System.out.println(p.getPlayer().getPlayerType()+"---------"+player.getPlayerType());
				//System.out.println(j);
				return true;
			}
		}
		
		for(int j = fromJ + 1; j <= 7; j++) {
			Piece p = start.board.getPiece(fromI, j);
			if(p != null && (p.getType() != Type.ROOK) && (p.getType() != Type.QUEEN)) {break;}
			if(p != null && (p.getType() == Type.ROOK || p.getType() == Type.QUEEN) && p.getPlayer().getPlayerType() != player.getPlayerType()){
				//System.out.println("4");
				return true;
			}
		}
		
		return false;
	}
	
	/**
     * Function to get the King
     * @param Start the board
     * @param player the player
     * @return piece King o/w null
     */
	public Piece findKing(Start start, Player player, int flag) {
		boolean color = player.getPlayerType();
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				
				if(tiles[i][j] == null) {continue;}
				//System.out.println(tiles[i][j].getType() + "--------" + color);
				if(flag != 0) {
				if((tiles[i][j].getType() == Type.KING) && (tiles[i][j].getPlayer().getPlayerType() == !color))
					return tiles[i][j];
				}
				else {
					if((tiles[i][j].getType() == Type.KING) && (tiles[i][j].getPlayer().getPlayerType() == color))
						return tiles[i][j];
					}
				}
			}
		
		return null;
	}
	
	/**
     * Function to check if king is in checked from all different path
     * @param P the king
     * @param Start the board
     * @param player the player
     * @return true if in checked o/w false
     */
	public boolean ischecked(Piece p, Start start, Player player, int i) {
		
		if(i == 1) {
		p = findKing(start, player, 1);
		if(p == null) {
			System.out.println("can't find king");
			return false;
		}
		if(checkedStraight(p, start, p.getPlayer()) == true) {
			System.out.println("checked straight");
			return true;
			 
		}
		if(checkedKnight(p, start, p.getPlayer()) == true) {
			System.out.println("checked knight");
			return true;
		}
		if(checkedDiagonal(p, start, p.getPlayer()) == true) {
			System.out.println("checked diag");
			return true;
		}
		
		if(checkedPrince(p, start, p.getPlayer()) == true || checkedPrincess(p, start, p.getPlayer()) == true) {
			System.out.println("checked prince");
			return true;
		}
		
		}
		
		if(i == 0){
			if(checkedStraight(p, start, player) == true) {
				System.out.println("checked straight");
				return true;
				 
			}
			if(checkedKnight(p, start, player) == true) {
				System.out.println("checked knight");
				return true;
			}
			if(checkedDiagonal(p, start, player) == true) {
				System.out.println("checked diag");
				return true;
			}
			if(checkedPrince(p, start, player) == true || checkedPrincess(p, start, player) == true) {
				System.out.println("checked prince");
				return true;
			}
		}
		return false;
	}
	
	/**
     * Function to check if king is in stalemate
     * @param P the king
     * @param Start the board
     * @param player the player
     * @return true if in stalemate o/w false
     */
	public boolean isStaleMate(Piece p, Start start, Player player) {
		int fromI = p.getLocation().getRow();
		int fromJ = p.getLocation().getCol();
		
		
		if(p.getType() == Type.KING) {
			 
				if(start.board.movePiece(p, new Location(fromI + 1, fromJ), start, player)) {
					return false;
				}
				 
				
				if(start.board.movePiece(p, new Location(fromI - 1, fromJ), start, player)) {
					return false;
				}
				 
				
				if(start.board.movePiece(p, new Location(fromI , fromJ - 1), start, player)) {
					return false;
				}
				
				if(start.board.movePiece(p, new Location(fromI, fromJ + 1), start, player)) {
					return false;
				}
				 
				
				if(start.board.movePiece(p, new Location(fromI + 1, fromJ + 1), start, player)) {
					return false;
				}
				 
				
				if(start.board.movePiece(p, new Location(fromI - 1, fromJ - 1), start, player)){
			 
					return false;
				}
				
				if(start.board.movePiece(p, new Location(fromI + 1, fromJ - 1), start, player)) {
					return false;
				}
				
				
				if(start.board.movePiece(p, new Location(fromI - 1, fromJ + 1), start, player)) {
				 	return false;
				}
				
				return true;
		}
		else {
			return false;
		}
		 
	}
}
