package Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import Error.InvalidExceptions;
import gamelogic.Board;
import gamelogic.King;
import gamelogic.Location;

public class KingTest {
	
	@Test
	public void newBoard() throws Exception{
		Board board = new Board();
		
        King king = new King(board, new Location(1, 0), true);
     
        board.newPiece(king);
        
        assertEquals(king, board.getPiece(1, 0));   
	}
	
	@Test(expected = InvalidExceptions.class)
	public void invalidInit() throws Exception {
		Board board = new Board();
		
		King king1 = new King(board, new Location(9,10), true);
		King king2 = new King(board, new Location(-1, -9), false);
	}
	
	@Test
    public void validMovements() {
        Board board = new Board();
        
        King king = new King(board, new Location(3, 2), true);
        
        board.newPiece(king);
        
        board.movePiece(king, new Location(3, 1)); // left
        assertTrue(king.equals(board.getPiece(3, 1)));
        
        board.movePiece(king, new Location(2, 1)); // up 
        assertTrue(king.equals(board.getPiece(2, 1)));
        
        board.movePiece(king, new Location(2, 2)); // right 
        assertTrue(king.equals(board.getPiece(2, 2)));
        
        board.movePiece(king, new Location(3, 2)); // down 
        assertTrue(king.equals(board.getPiece(3, 2)));
        
        board.movePiece(king, new Location(2, 1)); // up left 
        assertTrue(king.equals(board.getPiece(2, 1)));
        
        board.movePiece(king, new Location(3, 2)); // down right 
        assertTrue(king.equals(board.getPiece(3, 2)));
        
        board.movePiece(king, new Location(4, 1)); // down left 
        assertTrue(king.equals(board.getPiece(4, 1)));
        
        board.movePiece(king, new Location(3, 2)); // up right 
        assertTrue(king.equals(board.getPiece(3, 2)));
    }
	
	@Test
	public void invalidMovementsMoreThan1() throws Exception {
		Board board = new Board();
		
		King king = new King(board, new Location(3, 2), true);        
		
		board.newPiece(king);
		
		board.movePiece(king, new Location(1, 2)); // up 
	    assertFalse(king.equals(board.getPiece(1, 2)));
	}
}
