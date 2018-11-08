package Tests;

import gamelogic.*;
import static org.junit.Assert.*;
import org.junit.Test;
import Error.InvalidExceptions;
 
public class BishopTest {
	
	@Test
	public void newBoard() throws Exception{
		Board board = new Board();
		
        Bishop bishop = new Bishop(board, new Location(0, 2), true);
     
        board.newPiece(bishop);
        
        assertEquals(bishop, board.getPiece(0, 2));   
	}
	
	@Test(expected = InvalidExceptions.class)
	public void invalidInit() throws Exception {
		Board board = new Board();
		
		Bishop bishop1 = new Bishop(board, new Location(9,10), true);
		Bishop bishop2 = new Bishop(board, new Location(-1, -9), false);
	}
	
	@Test
    public void validMovements() {
        Board board = new Board();
        
        Bishop bishop = new Bishop(board, new Location(5, 5), true);
        
        board.newPiece(bishop);
        
        board.movePiece(bishop, new Location(4, 4)); // up left
        assertTrue(bishop.equals(board.getPiece(4, 4)));
        
        board.movePiece(bishop, new Location(5, 5)); // down right
        assertTrue(bishop.equals(board.getPiece(5, 5)));
        
        board.movePiece(bishop, new Location(6, 4)); // up right 
        assertTrue(bishop.equals(board.getPiece(6, 4)));
        
        board.movePiece(bishop, new Location(5, 5)); // down left
        assertTrue(bishop.equals(board.getPiece(5, 5)));
    }
	
	@Test
	public void invalidMovementsUp() throws Exception {
		Board board = new Board();
		
		Bishop bishop = new Bishop(board, new Location(5, 5), true);
		
		board.newPiece(bishop);
		
		board.movePiece(bishop, new Location(5, 6)); // move up
		assertFalse(bishop.equals(board.getPiece(5, 6)));
	}
	
	@Test
	public void invalidMovementsDown() throws Exception {
		Board board = new Board();
		
		Bishop bishop = new Bishop(board, new Location(5, 5), true);
		
		board.newPiece(bishop);
		
		board.movePiece(bishop, new Location(5, 4)); // move down
		assertFalse(bishop.equals(board.getPiece(5, 4)));
	}
	
	@Test
	public void invalidMovementsLeft() throws Exception {
		Board board = new Board();
		
		Bishop bishop = new Bishop(board, new Location(5, 5), true);
		
		board.newPiece(bishop);
		
		board.movePiece(bishop, new Location(4, 5)); // move left
		assertFalse(bishop.equals(board.getPiece(4, 5)));
	}
	
	@Test
	public void invalidMovementsRight() throws Exception {
		Board board = new Board();
		
		Bishop bishop = new Bishop(board, new Location(5, 5), true);
		
		board.newPiece(bishop);
		
		board.movePiece(bishop, new Location(6, 5)); // move right
		assertFalse(bishop.equals(board.getPiece(6, 5)));
	}
	
	
}
