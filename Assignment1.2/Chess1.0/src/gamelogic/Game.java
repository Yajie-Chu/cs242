package gamelogic;


public class Game {
	  public Start start = new Start();
	  Player white = new Player(true);
	  Player black = new Player(false);
	    public Game(int custom){
	        
	        if(custom == 0) {
	        	for(int j = 1; j <= 6 ; j++){
	        		start.board.newPiece(new Pawn( new Location(1, j), white));
	        		start.board.newPiece(new Pawn( new Location(6, j), black));
	        	}
	        	start.board.newPiece(new Prince( new Location(1, 0), white));
	        	start.board.newPiece(new Princess( new Location(1, 7), white));
	        	start.board.newPiece(new Prince( new Location(6, 0), black));
        		start.board.newPiece(new Princess( new Location(6, 7), black));
	        }
	        else{
	        	for(int j = 0; j < 8; j++){
	        		start.board.newPiece(new Pawn( new Location(1, j), white));
	        		start.board.newPiece(new Pawn( new Location(6, j), black));
	        	}
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
