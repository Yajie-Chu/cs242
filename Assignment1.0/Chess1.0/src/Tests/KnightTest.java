package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Error.InvalidExceptions;
import gamelogic.Knight;
import gamelogic.Board;
import gamelogic.Location;

public class KnightTest {
	@Test
	public void newBoard() throws Exception{
		Board board = new Board();
		
        Knight knight = new Knight(board, new Location(1, 0), true);
     
        board.newPiece(knight);
        
        assertEquals(knight, board.getPiece(1, 0));   
	}
	
	@Test(expected = InvalidExceptions.class)
	public void invalidInit() throws Exception {
		Board board = new Board();
		
		Knight knight1 = new Knight(board, new Location(9,10), true);
		Knight knight2 = new Knight(board, new Location(-1, -9), false);
	}
	
	@Test
    public void validMovementsBottomLeft() {
        Board board = new Board();
        
        Knight knight = new Knight(board, new Location(5, 2), true);
        
        board.newPiece(knight);
        
        board.movePiece(knight, new Location(7, 1)); // bottom left
        assertTrue(knight.equals(board.getPiece(7, 1)));
    }
	
	@Test
    public void validMovementsDownLeft() {
        Board board = new Board();
        
        Knight knight = new Knight(board, new Location(5, 2), true);
        
        board.newPiece(knight);
        
        board.movePiece(knight, new Location(6, 0)); // down left
        assertTrue(knight.equals(board.getPiece(6, 0)));
    }
	
	@Test
    public void validMovementsUpperLeft() {
        Board board = new Board();
        
        Knight knight = new Knight(board, new Location(5, 2), true);
        
        board.newPiece(knight);
        
        board.movePiece(knight, new Location(4, 0)); // upper left
        assertTrue(knight.equals(board.getPiece(4, 0)));
    }
	
	@Test
    public void validMovementsTopLeft() {
        Board board = new Board();
        
        Knight knight = new Knight(board, new Location(5, 2), true);
        
        board.newPiece(knight);
        
        board.movePiece(knight, new Location(3, 1)); // top left
        assertTrue(knight.equals(board.getPiece(3, 1)));
    }
	
	@Test
    public void validMovementsTopRight() {
        Board board = new Board();
        
        Knight knight = new Knight(board, new Location(5, 2), true);
        
        board.newPiece(knight);
        
        board.movePiece(knight, new Location(3, 3)); // top right
        assertTrue(knight.equals(board.getPiece(3, 3)));
    }
	
	@Test
    public void validMovementsUpperRight() {
        Board board = new Board();
        
        Knight knight = new Knight(board, new Location(5, 2), true);
        
        board.newPiece(knight);
        
        board.movePiece(knight, new Location(4, 4)); // upper right
        assertTrue(knight.equals(board.getPiece(4, 4)));
    }
	
	@Test
    public void validMovementsDownRight() {
        Board board = new Board();
        
        Knight knight = new Knight(board, new Location(5, 2), true);
        
        board.newPiece(knight);
        
        board.movePiece(knight, new Location(6, 4)); // down right
        assertTrue(knight.equals(board.getPiece(6, 4)));
    }
	
	@Test
    public void validMovementsBottomRight() {
        Board board = new Board();
        
        Knight knight = new Knight(board, new Location(5, 2), true);
        
        board.newPiece(knight);
        
        board.movePiece(knight, new Location(7, 3)); // bottom right
        assertTrue(knight.equals(board.getPiece(7, 3)));
    }
	
	@Test
    public void invalidMovementsUp() {
        Board board = new Board();
        
        Knight knight = new Knight(board, new Location(5, 2), true);
        
        board.newPiece(knight);
        
        board.movePiece(knight, new Location(5, 4)); // up
        assertFalse(knight.equals(board.getPiece(5, 4)));
    }
	
	@Test
    public void invalidMovementsDown() {
        Board board = new Board();
        
        Knight knight = new Knight(board, new Location(5, 2), true);
        
        board.newPiece(knight);
        
        board.movePiece(knight, new Location(5, 1)); // down
        assertFalse(knight.equals(board.getPiece(5, 1)));
    }
	
	@Test
    public void invalidMovementsLeft() {
        Board board = new Board();
        
        Knight knight = new Knight(board, new Location(5, 2), true);
        
        board.newPiece(knight);
        
        board.movePiece(knight, new Location(4, 2)); // left
        assertFalse(knight.equals(board.getPiece(4, 2)));
    }
	
	@Test
    public void invalidMovementsRight() {
        Board board = new Board();
        
        Knight knight = new Knight(board, new Location(5, 2), true);
        
        board.newPiece(knight);
        
        board.movePiece(knight, new Location(6, 2)); 
        assertFalse(knight.equals(board.getPiece(6, 2))); // right
    }
}
