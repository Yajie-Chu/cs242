package Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import Error.InvalidExceptions;
import gamelogic.Start;
import gamelogic.King;
import gamelogic.Location;
import gamelogic.*;

public class KingTest {
	
	@Test
	public void newStart() throws Exception{
		Start start = new Start();
		Player black = new Player(false); // black
		Player white = new Player(true); // whites
        King king = new King(new Location(1, 0), black);
     
        start.board.newPiece(king);
        
        assertEquals(king, start.board.getPiece(1, 0));   
	}
	
	 
	
	@Test
    public void validMovements() {
        Start start = new Start();
        Player black = new Player(false); // black
        Player white = new Player(true); // whites
        King king = new King(new Location(3, 2), black);
        
        start.board.newPiece(king);
        
        start.board.movePiece(king, new Location(3, 1), start, white); // left
        assertTrue(king.equals(start.board.getPiece(3, 1)));
        
        start.board.movePiece(king, new Location(2, 1), start, black); // up 
        assertTrue(king.equals(start.board.getPiece(2, 1)));
        
        start.board.movePiece(king, new Location(2, 2), start, black); // right 
        assertTrue(king.equals(start.board.getPiece(2, 2)));
        
        start.board.movePiece(king, new Location(3, 2), start, black); // down 
        assertTrue(king.equals(start.board.getPiece(3, 2)));
        
        start.board.movePiece(king, new Location(2, 1), start, black); // up left 
        assertTrue(king.equals(start.board.getPiece(2, 1)));
        
        start.board.movePiece(king, new Location(3, 2), start, black); // down right 
        assertTrue(king.equals(start.board.getPiece(3, 2)));
        
        start.board.movePiece(king, new Location(4, 1), start, black); // down left 
        assertTrue(king.equals(start.board.getPiece(4, 1)));
        
        start.board.movePiece(king, new Location(3, 2), start, black); // up right 
        assertTrue(king.equals(start.board.getPiece(3, 2)));
    }
	
	@Test
	public void invalidMovementsMoreThan1() throws Exception {
		Start start = new Start();
		Player black = new Player(false); // black
		King king = new King(new Location(3, 2), black);        
		
		start.board.newPiece(king);
		
		start.board.movePiece(king, new Location(1, 2), start, black); // up 
	    assertFalse(king.equals(start.board.getPiece(1, 2)));
	}
	
	@Test
	public void capture() throws Exception {
		Start start = new Start();
		Player black = new Player(false); // black
		Player white = new Player(true); // white
		King king = new King(new Location(3, 2), black);        
		Pawn pawn = new Pawn(new Location(4,2), white);
		start.board.newPiece(king);
		start.board.newPiece(pawn);
		
		start.board.movePiece(king, new Location(4, 2), start, black); // up 
	    assertTrue(king.equals(start.board.getPiece(4, 2)));
	    //assertTrue(king.equals(start.board.getPiece(3, 2)));
	}
}
