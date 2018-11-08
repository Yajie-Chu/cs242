package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Error.InvalidExceptions;
import gamelogic.Queen;
import gamelogic.Board;
import gamelogic.Location;

public class QueenTest {
	
	@Test
	public void newBoard() throws Exception{
		Board board = new Board();
		
        Queen queen = new Queen(board, new Location(0, 2), true);
     
        board.newPiece(queen);
        
        assertEquals(queen, board.getPiece(0, 2));   
	}
	
	@Test(expected = InvalidExceptions.class)
	public void invalidInit() throws Exception {
		Board board = new Board();
		
		Queen queen1 = new Queen(board, new Location(9,10), true);
		Queen queen2 = new Queen(board, new Location(-1, -9), false);
	}
	
	@Test
    public void validMovements() {
        Board board = new Board();
        
        Queen queen = new Queen(board, new Location(5, 5), true);
        
        board.newPiece(queen);
        
        board.movePiece(queen, new Location(4, 4)); // down left
        assertTrue(queen.equals(board.getPiece(4, 4)));
        
        board.movePiece(queen, new Location(5, 5)); // up right
        assertTrue(queen.equals(board.getPiece(5, 5)));
        
        board.movePiece(queen, new Location(6, 4)); // down right 
        assertTrue(queen.equals(board.getPiece(6, 4)));
        
        board.movePiece(queen, new Location(4, 6)); // up left
        assertTrue(queen.equals(board.getPiece(4, 6)));
    }
	
	@Test
    public void validMovementsUp() {
        Board board = new Board();
        
        Queen queen = new Queen(board, new Location(7, 0), true);
        
        board.newPiece(queen);
        
        board.movePiece(queen, new Location(7, 5)); // Up
        assertTrue(queen.equals(board.getPiece(7, 5)));
          
    }
	
	@Test
    public void validMovementsDown() {
        Board board = new Board();
        
        Queen queen = new Queen(board, new Location(7, 5), true);
        
        board.newPiece(queen);
        
        board.movePiece(queen, new Location(7, 1)); // Down
        assertTrue(queen.equals(board.getPiece(7, 1)));
          
    }
}
