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
	public void movePiece(Piece piece, Location location) {
		if (piece.checkMovement(location.getRow(), location.getCol(), piece) == true) {
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
	
}
