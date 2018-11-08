package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Error.InvalidExceptions;
import gamelogic.Queen;
import gamelogic.Start;
import gamelogic.Location;
import gamelogic.Pawn;
import gamelogic.Player;
import gamelogic.Princess;

public class QueenTest {
	
	@Test
	public void newStart() throws Exception{
		Start start = new Start();
		Player white = new Player(true); // white
        Queen queen = new Queen( new Location(0, 2), white);
     
        start.board.newPiece(queen);
        
        assertEquals(queen, start.board.getPiece(0, 2));   
	}
	
	
	@Test
    public void validMovements() {
        Start start = new Start();
        Player white = new Player(true); // white
        
        Queen queen = new Queen( new Location(5, 5), white);
        
        start.board.newPiece(queen);
        
        start.board.movePiece(queen, new Location(4, 4), start, white); // down left
        assertTrue(queen.equals(start.board.getPiece(4, 4)));
        
        start.board.movePiece(queen, new Location(5, 5), start, white); // up right
        assertTrue(queen.equals(start.board.getPiece(5, 5)));
        
        start.board.movePiece(queen, new Location(6, 4), start, white); // down right 
        assertTrue(queen.equals(start.board.getPiece(6, 4)));
        
        start.board.movePiece(queen, new Location(4, 6), start, white); // up left
        assertTrue(queen.equals(start.board.getPiece(4, 6)));
    }
	
	@Test
    public void validMovementsUp() {
        Start start = new Start();
        Player white = new Player(true); // white
        
        Queen queen = new Queen( new Location(7, 0), white);
        
        start.board.newPiece(queen);
        
        start.board.movePiece(queen, new Location(7, 5), start, white); // Up
        assertTrue(queen.equals(start.board.getPiece(7, 5)));
          
    }
	
	@Test
    public void validMovementsDown() {
        Start start = new Start();
        Player white = new Player(true); // white
        
        Queen queen = new Queen( new Location(7, 5), white);
        
        start.board.newPiece(queen);
        
        start.board.movePiece(queen, new Location(7, 1), start, white); // Down
        assertTrue(queen.equals(start.board.getPiece(7, 1)));
          
    }
	
	@Test
	public void capture() throws Exception {
		Start start = new Start();
		Player black = new Player(false); // black
		Player white = new Player(true); // white
		
		Queen queen = new Queen( new Location(7, 5), white);  
		Pawn pawn = new Pawn(new Location(4,5), white);
		Pawn pawn2 = new Pawn(new Location(3,4), white);
		
		start.board.newPiece(queen);
		start.board.newPiece(pawn);
		start.board.newPiece(pawn2);
		
		start.board.movePiece(queen, new Location(4, 5), start, black); // up 
		start.board.movePiece(queen, new Location(3, 4), start, black); // diag
	    assertTrue(queen.equals(start.board.getPiece(3, 4)));
	   
	}
}
