package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gamelogic.*;

public class UndoTest {
	
	@Test
    public void undoTest() {
        Start start = new Start();
        Player white = new Player(true); // white
        Rook rook = new Rook( new Location(7, 0), white);
        
        start.board.newPiece(rook);
        
        start.board.movePiece(rook, new Location(7, 5), start, white); // Right
        start.board.undo(rook, new Location(7,0), new Location(7,5));
        assertTrue(rook.equals(start.board.getPiece(7, 0)));
          
    }
}
