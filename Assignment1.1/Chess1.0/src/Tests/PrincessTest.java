package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gamelogic.*;

public class PrincessTest {
	public void newStart() throws Exception{
		Start start = new Start();
		Player white = new Player(true); // white
        Princess princess = new Princess(new Location(1, 0), white);
     
        start.board.newPiece(princess);
        
        assertEquals(princess, start.board.getPiece(1, 0));   
	}

	
	@Test
    public void validMovements() {
        Start start = new Start();
        Player white = new Player(true); // white
        Princess princess = new Princess(new Location(0, 0), white);
        
        start.board.newPiece(princess);
        
        start.board.movePiece(princess, new Location(0, 1), start, white); 
        assertTrue(princess.equals(start.board.getPiece(0, 1)));
        
        start.board.movePiece(princess, new Location(1, 1), start, white); 
        assertTrue(princess.equals(start.board.getPiece(1, 1)));
    }
	
	@Test
	public void capture() throws Exception {
		Start start = new Start();
		Player black = new Player(false); // black
		Player white = new Player(true); // white
		
		Princess princess = new Princess(new Location(3, 2), black);        
		Pawn pawn = new Pawn(new Location(3,1), white);
		
		start.board.newPiece(princess);
		start.board.newPiece(pawn);
		
		start.board.movePiece(princess, new Location(3, 1), start, black); // up 
	    assertTrue(princess.equals(start.board.getPiece(3, 1)));
	    assertFalse(princess.equals(start.board.getPiece(3, 2)));
	}
}
