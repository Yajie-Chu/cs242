package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Error.InvalidExceptions;
import gamelogic.Rook;
import gamelogic.Start;
import gamelogic.Location;
import gamelogic.Pawn;
import gamelogic.Player;

public class RookTest {
	
	@Test
	public void newStart() throws Exception{
		Start start = new Start();
		Player white = new Player(true); // white
        Rook rook = new Rook( new Location(7, 0), white);
     
        start.board.newPiece(rook);
        
        assertEquals(rook, start.board.getPiece(7, 0));   
	}
	
	@Test
    public void validMovementsRight() {
        Start start = new Start();
        Player white = new Player(true); // white
        Rook rook = new Rook( new Location(7, 0), white);
        
        start.board.newPiece(rook);
        
        start.board.movePiece(rook, new Location(7, 5), start, white); // Right
        assertTrue(rook.equals(start.board.getPiece(7, 5)));
          
    }
	
	@Test
    public void validMovementsUp() {
        Start start = new Start();
        Player white = new Player(true); // white
        Rook rook = new Rook( new Location(7, 5), white);
        
        start.board.newPiece(rook);
        
        start.board.movePiece(rook, new Location(2, 5), start, white); // Up
        assertTrue(rook.equals(start.board.getPiece(2, 5)));
          
    }
	
	@Test
    public void invalidMovementsDiagonal() {
        Start start = new Start();
        Player white = new Player(true); // white
        Rook rook = new Rook( new Location(7, 0), white);
        
        start.board.newPiece(rook);
        
        start.board.movePiece(rook, new Location(5, 2), start, white); // Diagonal
        assertFalse(rook.equals(start.board.getPiece(5, 2)));
          
    }
	
	@Test
	public void capturePieces() throws Exception {
		Start start = new Start();
        Player white = new Player(true); // white
        Player black = new Player(false); // black
        
        Rook rook = new Rook( new Location(7, 0), black);
        Pawn pawn = new Pawn( new Location(4, 0), white);
        Pawn pawn2 = new Pawn( new Location(3,0), white);
        
        // rook2 eats rook3 and rook4
        Rook rook2 = new Rook( new Location(0, 4), black);
        Rook rook3 = new Rook( new Location(0, 7), white);
        Rook rook4 = new Rook( new Location(0, 0), black);
        
        start.board.newPiece(rook);
       // start.board.newPiece(pawn);
        start.board.newPiece(pawn2);
        
        start.board.newPiece(rook2);
        start.board.newPiece(rook3);
        start.board.newPiece(rook4);
        
        start.board.movePiece(rook, new Location(3, 0), start, black); // eat pawn
        assertTrue(rook.equals(start.board.getPiece(3, 0)));
        
        start.board.movePiece(rook2, new Location(0, 7), start, black); // eat pawn
        start.board.movePiece(rook2, new Location(0, 0), start, black); // eat pawn
        assertTrue(rook2.equals(start.board.getPiece(0, 7)));
	}
	
	
}
