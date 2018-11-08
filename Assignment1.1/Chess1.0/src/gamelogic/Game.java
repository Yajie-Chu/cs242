package gamelogic;


public class Game {
	  private Start start = new Start();
	  Player white = new Player(true);
	  Player black = new Player(false);
	    public Game(){
	        for(int i = 0; i < 8; i++){
	            start.board.newPiece(new Pawn( new Location(1, i), white));
	            start.board.newPiece(new Pawn( new Location(6, i), black));
	        }
	        //player
	        start.board.newPiece(new Rook( new Location(0, 0), white));
	        start.board.newPiece(new Rook( new Location(0, 7), white));
	        start.board.newPiece(new Knight( new Location(0, 1), white));
	        start.board.newPiece(new Knight( new Location(0, 6), white));
	        start.board.newPiece(new Bishop( new Location(0, 2), white));
	        start.board.newPiece(new Bishop( new Location(0, 5), white));
	        start.board.newPiece(new Queen( new Location(0, 3), white));
	        start.board.newPiece(new King( new Location(0, 4), white));

	        //opponent
	        start.board.newPiece(new Rook( new Location(7, 0), black));
	        start.board.newPiece(new Rook( new Location(7, 7), black));
	        start.board.newPiece(new Knight( new Location(7, 1), black));
	        start.board.newPiece(new Knight( new Location(7, 6), black));
	        start.board.newPiece(new Bishop( new Location(7, 2), black));
	        start.board.newPiece(new Bishop( new Location(7, 5), black));
	        start.board.newPiece(new Queen( new Location(7, 3), black));
	        start.board.newPiece(new King( new Location(7, 4), black));
	    }
	    
	    public Piece getPiece(int i, int j){
	        if (i < 0 || i > 7 || j < 0 || j > 7){
	            return null;
	        }
	        return start.board.getPiece(i, j);
	    }
}
