package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Test;

import gamelogic.*;

public class PrinceTest {
	public void newStart() throws Exception{
		Start start = new Start();
		Player white = new Player(true); // white
        Prince prince = new Prince(new Location(1, 0), white);
     
        start.board.newPiece(prince);
        
        assertEquals(prince, start.board.getPiece(1, 0));   
	}

	
	@Test
    public void validMovements() {
        Start start = new Start();
        Player white = new Player(true); // white
        Prince prince = new Prince(new Location(0, 0), white);
        
        start.board.newPiece(prince);
        
        start.board.movePiece(prince, new Location(0, 2), start, white); 
        assertTrue(prince.equals(start.board.getPiece(0, 2)));
        
        start.board.movePiece(prince, new Location(2, 2), start, white); 
        assertTrue(prince.equals(start.board.getPiece(2, 2)));
    }
	
	@Test
	public void capture() throws Exception {
		Start start = new Start();
		Player black = new Player(false); // black
		Player white = new Player(true); // white
		
		Prince prince = new Prince(new Location(3, 2), black);        
		Pawn pawn = new Pawn(new Location(3,0), white);
		
		start.board.newPiece(prince);
		start.board.newPiece(pawn);
		
		start.board.movePiece(prince, new Location(3, 0), start, black); // up 
	    assertTrue(prince.equals(start.board.getPiece(3, 0)));
	    assertFalse(prince.equals(start.board.getPiece(3, 2)));
	}
}
