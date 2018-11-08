package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Error.InvalidExceptions;
import gamelogic.Board;
import gamelogic.Location;
import gamelogic.Pawn;
import gamelogic.Rook;

public class PawnTest {
	
	@Test
	public void newBoard() throws Exception{
		Board board = new Board();
		
        Pawn pawn= new Pawn(board, new Location(7, 0), true);
     
        board.newPiece(pawn);
        
        assertEquals(pawn, board.getPiece(7, 0));   
	}
	
	@Test(expected = InvalidExceptions.class)
	public void invalidInit() throws Exception {
		Board board = new Board();
		
		Pawn pawn1 = new Pawn(board, new Location(9,10), true);
		Pawn pawn2 = new Pawn(board, new Location(-1, -9), false);
		
		board.newPiece(pawn1);
		board.newPiece(pawn2);
		
	}
	
	@Test
    public void validMovementsUpPlayer() {
        Board board = new Board();
        
        Pawn pawn = new Pawn(board, new Location(1, 3), true);
        
        board.newPiece(pawn);
        
        board.movePiece(pawn, new Location(2, 3)); // Up
        assertEquals(pawn, board.getPiece(2, 3));
          
    }
	
	@Test
    public void validMovementsUp2Player() {
        Board board = new Board();
        
        Pawn pawn = new Pawn(board, new Location(1, 3), true);
        
        board.newPiece(pawn);
        
        board.movePiece(pawn, new Location(3, 3)); // Up
        assertTrue(pawn.equals(board.getPiece(3, 3)));
          
    }
	
	@Test
    public void invalidMovementsUp2Player() {
        Board board = new Board();
        
        Pawn pawn = new Pawn(board, new Location(3, 2), true);
        
        board.newPiece(pawn);
        
        board.movePiece(pawn, new Location(5, 2)); // Up
        assertFalse(pawn.equals(board.getPiece(5, 2)));
          
    }
	
	@Test
    public void validMovementsDiagPlayer() {
        Board board = new Board();
        
        Pawn pawn = new Pawn(board, new Location(1, 3), true);
        
        board.newPiece(pawn);
        
        board.movePiece(pawn, new Location(2, 2)); // diagonal left
        assertTrue(pawn.equals(board.getPiece(2, 2)));
          
    }
	
	@Test
    public void invalidMovementsDiagPlayer() {
        Board board = new Board();
        
        Pawn pawn = new Pawn(board, new Location(1, 3), true);
        
        board.newPiece(pawn);
        
        board.movePiece(pawn, new Location(3, 1)); // diagonal left
        assertFalse(pawn.equals(board.getPiece(3, 1)));
          
    }
	
}
