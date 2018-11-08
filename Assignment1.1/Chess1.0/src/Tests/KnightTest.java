package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Error.InvalidExceptions;
import gamelogic.Knight;
import gamelogic.Start;
import gamelogic.Location;
import gamelogic.Pawn;
import gamelogic.Player;
import gamelogic.Princess;

public class KnightTest {
	@Test
	public void newStart() throws Exception{
		Start start = new Start();
		Player white = new Player(true); // white
        Knight knight = new Knight(new Location(1, 0), white);
     
        start.board.newPiece(knight);
        
        assertEquals(knight, start.board.getPiece(1, 0));   
	}

	
	@Test
    public void validMovementsBottomLeft() {
        Start start = new Start();
        Player white = new Player(true); // white
        Knight knight = new Knight(new Location(5, 2), white);
        
        start.board.newPiece(knight);
        
        start.board.movePiece(knight, new Location(7, 1), start, white); // bottom left
        assertTrue(knight.equals(start.board.getPiece(7, 1)));
    }
	
	@Test
    public void validMovementsDownLeft() {
        Start start = new Start();
        Player white = new Player(true); // white
        Knight knight = new Knight(new Location(5, 2), white);
        
        start.board.newPiece(knight);
        
        start.board.movePiece(knight, new Location(6, 0), start, white); // down left
        assertTrue(knight.equals(start.board.getPiece(6, 0)));
    }
	
	@Test
    public void validMovementsUpperLeft() {
        Start start = new Start();
        Player white = new Player(true); // white
        Knight knight = new Knight(new Location(5, 2), white);
        
        start.board.newPiece(knight);
        
        start.board.movePiece(knight, new Location(4, 0), start, white); // upper left
        assertTrue(knight.equals(start.board.getPiece(4, 0)));
    }
	
	@Test
    public void validMovementsTopLeft() {
        Start start = new Start();
        Player white = new Player(true); // white
        Knight knight = new Knight(new Location(5, 2), white);
        
        start.board.newPiece(knight);
        
        start.board.movePiece(knight, new Location(3, 1), start, white); // top left
        assertTrue(knight.equals(start.board.getPiece(3, 1)));
    }
	
	@Test
    public void validMovementsTopRight() {
        Start start = new Start();
        Player white = new Player(true); // white
        Knight knight = new Knight(new Location(5, 2), white);
        
        start.board.newPiece(knight);
        
        start.board.movePiece(knight, new Location(3, 3), start, white); // top right
        assertTrue(knight.equals(start.board.getPiece(3, 3)));
    }
	
	@Test
    public void validMovementsUpperRight() {
        Start start = new Start();
        Player white = new Player(true); // white
        Knight knight = new Knight(new Location(5, 2), white);
        
        start.board.newPiece(knight);
        
        start.board.movePiece(knight, new Location(4, 4), start, white); // upper right
        assertTrue(knight.equals(start.board.getPiece(4, 4)));
    }
	
	@Test
    public void validMovementsDownRight() {
        Start start = new Start();
        Player white = new Player(true); // white
        Knight knight = new Knight(new Location(5, 2), white);
        
        start.board.newPiece(knight);
        
        start.board.movePiece(knight, new Location(6, 4), start, white); // down right
        assertTrue(knight.equals(start.board.getPiece(6, 4)));
    }
	
	@Test
    public void validMovementsBottomRight() {
        Start start = new Start();
        Player white = new Player(true); // white
        Knight knight = new Knight(new Location(5, 2), white);
        
        start.board.newPiece(knight);
        
        start.board.movePiece(knight, new Location(7, 3), start, white); // bottom right
        assertTrue(knight.equals(start.board.getPiece(7, 3)));
    }
	
	@Test
    public void invalidMovementsUp() {
        Start start = new Start();
        Player white = new Player(true); // white
        Knight knight = new Knight(new Location(5, 2), white);
        
        start.board.newPiece(knight);
        
        start.board.movePiece(knight, new Location(5, 4), start, white); // up
        assertFalse(knight.equals(start.board.getPiece(5, 4)));
    }
	
	@Test
    public void invalidMovementsDown() {
        Start start = new Start();
        Player white = new Player(true); // white
        Knight knight = new Knight(new Location(5, 2), white);
        
        start.board.newPiece(knight);
        
        start.board.movePiece(knight, new Location(5, 1), start, white); // down
        assertFalse(knight.equals(start.board.getPiece(5, 1)));
    }
	
	@Test
    public void invalidMovementsLeft() {
        Start start = new Start();
        Player white = new Player(true); // white
        Knight knight = new Knight(new Location(5, 2), white);
        
        start.board.newPiece(knight);
        
        start.board.movePiece(knight, new Location(4, 2), start, white); // left
        assertFalse(knight.equals(start.board.getPiece(4, 2)));
    }
	
	@Test
    public void invalidMovementsRight() {
        Start start = new Start();
        Player white = new Player(true); // white
        Knight knight = new Knight(new Location(5, 2), white);
        
        start.board.newPiece(knight);
        
        start.board.movePiece(knight, new Location(6, 2), start, white); 
        assertFalse(knight.equals(start.board.getPiece(6, 2))); // right
    }
	
	@Test
	public void capture() throws Exception {
		Start start = new Start();
		Player black = new Player(false); // black
		Player white = new Player(true); // white
		
		Knight knight = new Knight(new Location(3, 2), black);        
		Pawn pawn = new Pawn(new Location(2,0), white);
		
		start.board.newPiece(knight);
		start.board.newPiece(pawn);
		
		start.board.movePiece(knight, new Location(2, 0), start, black); 
	    assertTrue(knight.equals(start.board.getPiece(2, 0)));
	    assertFalse(knight.equals(start.board.getPiece(3, 2)));
	}
}
