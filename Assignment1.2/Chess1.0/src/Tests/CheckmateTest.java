package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import gamelogic.Bishop;
import gamelogic.Board;
import gamelogic.Rook;
import gamelogic.*;

public class CheckmateTest {
	
	@Test
	public void checkmate() {
		Start start = new Start();
		Player white = new Player(true);
		Player black = new Player(false);
		
		King king = new King(new Location(2, 1), white);
		Rook rook = new Rook(new Location(7, 1), black);
		Rook rook2 = new Rook(new Location(2, 7), black);
		Bishop bishop = new Bishop(new Location(0, 3), black);
		Bishop bishop2 = new Bishop(new Location(4, 3), black);
		
		start.board.newPiece(king);
		start.board.newPiece(rook);
		start.board.newPiece(rook2);
		start.board.newPiece(bishop);
		start.board.newPiece(bishop2);
		
		assertEquals(true, start.board.isStaleMate(king, start, white));
	}
}
