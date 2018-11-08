package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Error.InvalidExceptions;
import gamelogic.Start;
import gamelogic.Location;
import gamelogic.Pawn;
import gamelogic.Player;
import gamelogic.Princess;
import gamelogic.Rook;

public class PawnTest {
	
	@Test
	public void newStart() throws Exception{
		Start start = new Start();
		Player white = new Player(true); // white
		
        Pawn pawn= new Pawn(new Location(7, 0), white);
     
        start.board.newPiece(pawn);
        
        assertEquals(pawn, start.board.getPiece(7, 0));   
	} 
	
	@Test
    public void validMovementsUpPlayer() {
        Start start = new Start();
        Player white = new Player(true); // white
        
        Pawn pawn = new Pawn(new Location(1, 3), white);
        
        start.board.newPiece(pawn);
        
        start.board.movePiece(pawn, new Location(2, 3), start, white); // Up
        assertEquals(pawn, start.board.getPiece(2, 3));
          
    }
	
	@Test
    public void validMovementsUp2Player() {
        Start start = new Start();
        Player white = new Player(true); // white
        
        
        Pawn pawn = new Pawn(new Location(1, 3), white);
        
        start.board.newPiece(pawn);
        
        start.board.movePiece(pawn, new Location(3, 3), start, white); // Up
        assertTrue(pawn.equals(start.board.getPiece(3, 3)));
          
    }
	
	@Test
    public void invalidMovementsUp2Player() {
        Start start = new Start();
        Player white = new Player(true); // white
        
        Pawn pawn = new Pawn(new Location(3, 2), new Player (true));
        
        start.board.newPiece(pawn);
        
        start.board.movePiece(pawn, new Location(5, 2), start, white); // Up
        assertFalse(pawn.equals(start.board.getPiece(5, 2)));
          
    }
	
	@Test
    public void validMovementsDiagPlayer() {
        Start start = new Start();
        Player white = new Player(true); // white
        
        Pawn pawn = new Pawn(new Location(2, 3), white);
        
        start.board.newPiece(pawn);
        
        start.board.movePiece(pawn, new Location(3, 3), start, white); // diagonal left
        assertTrue(pawn.equals(start.board.getPiece(3, 3)));
          
    }
	
	@Test
    public void invalidMovementsDiagPlayer() {
        Start start = new Start();
        Player white = new Player(true); // white
        
        Pawn pawn = new Pawn(new Location(1, 3), white);
        
        start.board.newPiece(pawn);
        
        start.board.movePiece(pawn, new Location(3, 1), start, white); // diagonal left
        assertFalse(pawn.equals(start.board.getPiece(3, 1)));
          
    }
	
	@Test
	public void capture() throws Exception {
		Start start = new Start();
		Player black = new Player(false); // black
		Player white = new Player(true); // white
		
		Princess princess = new Princess(new Location(4, 1), black);    
		Princess princess2 = new Princess(new Location(5, 2), white);       
		Pawn pawn = new Pawn(new Location(6,1), black);
		Pawn pawn2 = new Pawn(new Location(6,3), black);
		
		start.board.newPiece(princess2);
		start.board.newPiece(pawn2);
		start.board.newPiece(princess);
		start.board.newPiece(pawn);
		
		start.board.movePiece(pawn, new Location(4, 1), start, black); // can't eat
	    assertTrue(princess.equals(start.board.getPiece(4, 1))); 
	   
	    start.board.movePiece(pawn2, new Location(5, 2), start, black); // eats
	    assertTrue(pawn2.equals(start.board.getPiece(5, 2))); 
	    
	}
	
}
