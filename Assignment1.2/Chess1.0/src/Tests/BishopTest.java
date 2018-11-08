package Tests;

import gamelogic.*;
import static org.junit.Assert.*;
import org.junit.Test;
import Error.InvalidExceptions;
 
public class BishopTest {
	
	@Test
	public void newBoard() throws Exception{
		Start start = new Start();
		Player white = new Player(true); // white
        Bishop bishop = new Bishop(new Location(0, 2), white);
     
        start.board.newPiece(bishop);
        
        assertEquals(bishop, start.board.getPiece(0, 2));   
	}
	
	@Test
    public void validMovements() {
        Start start = new Start();
        Player white = new Player(true); // white
        Player black = new Player(false); // black
        
        Bishop bishop = new Bishop(new Location(5, 5), white);
        Bishop b1 = new Bishop(new Location(4,4), black);
        start.board.newPiece(bishop);
        start.board.newPiece(b1);
        
        start.board.movePiece(b1, new Location(5, 5), start, black); // up left
        assertTrue(b1.equals(start.board.getPiece(5, 5)));
        
        
    }
	
	@Test
	public void invalidMovementsUp() throws Exception {
		Start start = new Start();
		Player white = new Player(true); // white
		Bishop bishop = new Bishop( new Location(5, 5), white);
		
		start.board.newPiece(bishop);
		
		start.board.movePiece(bishop, new Location(5, 6), start, white); // move up
		assertFalse(bishop.equals(start.board.getPiece(5, 6)));
	}
	
	@Test
	public void invalidMovementsDown() throws Exception {
		Start start = new Start();
		Player white = new Player(true); // white
		Bishop bishop = new Bishop( new Location(5, 5), white);
		
		start.board.newPiece(bishop);
		
		start.board.movePiece(bishop, new Location(5, 4), start, white); // move down
		assertFalse(bishop.equals(start.board.getPiece(5, 4)));
	}
	
	@Test
	public void invalidMovementsLeft() throws Exception {
		Start start = new Start();
		Player white = new Player(true); // white
		Bishop bishop = new Bishop( new Location(5, 5), white);
		
		start.board.newPiece(bishop);
		
		start.board.movePiece(bishop, new Location(4, 5), start,white); // move left
		assertFalse(bishop.equals(start.board.getPiece(4, 5)));
	}
	
	@Test
	public void invalidMovementsRight() throws Exception {
		Start start = new Start();
		Player white = new Player(true); // white
		Bishop bishop = new Bishop( new Location(5, 5), white);
		
		start.board.newPiece(bishop);
		
		start.board.movePiece(bishop, new Location(6, 5), start, white); // move right
		assertFalse(bishop.equals(start.board.getPiece(6, 5)));
	}
	
	@Test
	public void capture() throws Exception {
		Start start = new Start();
		Player black = new Player(false); // black
		Player white = new Player(true); // white
		
		Bishop bishop = new Bishop( new Location(5, 5), black);
		//Pawn pawn = new Pawn(new Location(4,5), white);
		Pawn pawn2 = new Pawn(new Location(3,3), black);
		
		start.board.newPiece(bishop);
		//start.board.newPiece(pawn);
		start.board.newPiece(pawn2);
		
		//start.board.movePiece(bishop, new Location(4, 5), start, black); // fail
		start.board.movePiece(bishop, new Location(3, 3), start, black); // diag
	    assertTrue(bishop.equals(start.board.getPiece(5,5)));
	   
	}
	
	
}
