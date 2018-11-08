package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Error.InvalidExceptions;
import gamelogic.Rook;
import gamelogic.Bishop;
import gamelogic.Board;
import gamelogic.Location;

public class RookTest {
	
	@Test
	public void newBoard() throws Exception{
		Board board = new Board();
		
        Rook rook = new Rook(board, new Location(7, 0), true);
     
        board.newPiece(rook);
        
        assertEquals(rook, board.getPiece(7, 0));   
	}
	
	@Test(expected = InvalidExceptions.class)
	public void invalidInit() throws Exception {
		Board board = new Board();
		
		Rook rook1 = new Rook(board, new Location(9,10), true);
		Rook rook2 = new Rook(board, new Location(-1, -9), false);
	}
	
	@Test
    public void validMovementsRight() {
        Board board = new Board();
        
        Rook rook = new Rook(board, new Location(7, 0), true);
        
        board.newPiece(rook);
        
        board.movePiece(rook, new Location(7, 5)); // Right
        assertTrue(rook.equals(board.getPiece(7, 5)));
          
    }
	
	@Test
    public void validMovementsUp() {
        Board board = new Board();
        
        Rook rook = new Rook(board, new Location(7, 5), true);
        
        board.newPiece(rook);
        
        board.movePiece(rook, new Location(2, 5)); // Up
        assertTrue(rook.equals(board.getPiece(2, 5)));
          
    }
	
	@Test
    public void invalidMovementsDiagonal() {
        Board board = new Board();
        
        Rook rook = new Rook(board, new Location(7, 0), true);
        
        board.newPiece(rook);
        
        board.movePiece(rook, new Location(5, 2)); // Diagonal
        assertFalse(rook.equals(board.getPiece(5, 2)));
          
    }
	
}
