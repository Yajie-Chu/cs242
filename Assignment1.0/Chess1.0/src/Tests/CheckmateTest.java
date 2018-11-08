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
		Game game = new Game();
		Board board = new Board();
		
		King king = new King(board, new Location(2, 1), true);
		Rook rook = new Rook(board, new Location(7, 1), false);
		Rook rook2 = new Rook(board, new Location(2, 7), false);
		Bishop bishop = new Bishop(board, new Location(0, 3), false);
		Bishop bishop2 = new Bishop(board, new Location(4, 3), false);
		
		board.newPiece(king);
		board.newPiece(rook);
		board.newPiece(rook2);
		board.newPiece(bishop);
		board.newPiece(bishop2);
		
		assertEquals(true, game.isCheckedMate(king));
	}
}
