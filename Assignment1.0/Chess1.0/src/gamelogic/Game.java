package gamelogic;

public class Game {
	
	private Board board = new Board();
	
	public Game() {
		
		 
	}
	
	public Piece findKing(boolean isOwner) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Piece p = board.getPiece(i, j);
				if(p.getType() == Type.KING && p.getPlayer() == isOwner) {
					return p;
				}
			}
		}
		
		return null;
	}
	
	
	public boolean checkedKnight(Piece p) {
		int fromI = p.getLocation().getRow();
		int fromJ = p.getLocation().getRow();
		
		Piece p1 = board.getPiece(fromI + 1, fromJ + 2);
		if(p1.getType() == Type.KNIGHT && !p1.getPlayer()) {
			return true;
		}
		
		Piece p2 = board.getPiece(fromI + 1, fromJ - 2);
		if(p2.getType() == Type.KNIGHT && !p2.getPlayer()) {
			return true;
		}
		
		Piece p3 = board.getPiece(fromI - 1, fromJ - 2);
		if(p3.getType() == Type.KNIGHT && !p3.getPlayer()) {
			return true;
		}
		
		Piece p4 = board.getPiece(fromI - 1, fromJ + 2);
		if(p4.getType() == Type.KNIGHT && !p4.getPlayer()) {
			return true;
		}
		
		Piece p5 = board.getPiece(fromI + 2, fromJ + 1);
		if(p5.getType() == Type.KNIGHT && !p5.getPlayer()) {
			return true;
		}
		
		Piece p6 = board.getPiece(fromI - 2, fromJ - 1);
		if(p6.getType() == Type.KNIGHT && !p6.getPlayer()) {
			return true;
		}
		
		Piece p7 = board.getPiece(fromI + 2, fromJ - 1);
		if(p7.getType() == Type.KNIGHT && !p7.getPlayer()) {
			return true;
		}
		
		Piece p8 = board.getPiece(fromI - 2, fromJ + 1);
		if(p8.getType() == Type.KNIGHT && !p8.getPlayer()) {
			return true;
		}
		
		return false;
	}
	
	public boolean checkedDiagonal(Piece p) {
		int fromI = p.getLocation().getRow();
		int fromJ = p.getLocation().getCol();
		int countJ = fromJ;
		int countJ2 = fromJ;
		
		for(int i = fromI - 1; i > 0; i--) {
			countJ = countJ - 1;
			countJ2 = countJ2 + 1;
			Piece p1 = board.getPiece(i, countJ);
			Piece p2 = board.getPiece(i, countJ2);
			if(p1 != null  && countJ == fromJ - 1) {
				if((p1.getType() == Type.PAWN || p1.getType() == Type.BISHOP || p1.getType() == Type.QUEEN) && !p1.getPlayer()) {
					return true;
				}
			}
			if(p2 != null  && countJ == fromJ - 1) {
				if((p2.getType() == Type.PAWN || p2.getType() == Type.BISHOP || p2.getType() == Type.QUEEN) && !p2.getPlayer()) {
					return true;
				}
			}
			
			if(p1 != null) {
				if((p1.getType() == Type.BISHOP || p1.getType() == Type.QUEEN) && p1.getPlayer() != p.getPlayer()) {
					return true;
				}
			if(p2 != null) {
				if((p2.getType() == Type.BISHOP || p2.getType() == Type.QUEEN) && p2.getPlayer() != p.getPlayer()) {
					return true;
				}
			}
				
			}
		}
		
		countJ = fromJ;
		countJ2 = fromJ;
		for(int i = fromI; i < 8; i++) {
			countJ = countJ - 1;
			countJ2 = countJ2 + 1;
			Piece p1 = board.getPiece(i, countJ);
			Piece p2 = board.getPiece(i, countJ2);
			if(p1 != null  && countJ == fromJ - 1) {
				if((p1.getType() == Type.PAWN || p1.getType() == Type.BISHOP || p1.getType() == Type.QUEEN) && !p1.getPlayer()) {
					return true;
				}
			}
			if(p2 != null  && countJ == fromJ - 1) {
				if((p2.getType() == Type.PAWN || p2.getType() == Type.BISHOP || p2.getType() == Type.QUEEN) && !p2.getPlayer()) {
					return true;
				}
			}
			
			if(p1 != null) {
				if((p1.getType() == Type.BISHOP || p1.getType() == Type.QUEEN) && p1.getPlayer() != p.getPlayer()) {
					return true;
				}
			}
			if(p2 != null) {
				if((p2.getType() == Type.BISHOP || p2.getType() == Type.QUEEN) && p2.getPlayer() != p.getPlayer()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean checkedStraight(Piece p) {
		int fromI = p.getLocation().getRow();
		int fromJ = p.getLocation().getCol();
		
		for(int i = fromI-1; i > 0; i--) {
			Piece p1 = board.getPiece(i, fromJ);
			if(p1 != null && (p1.getType() == Type.ROOK || p1.getType() == Type.QUEEN) && p1.getPlayer() != p.getPlayer()){
				return true;
			}
		}
		
		for(int i = fromI + 1; i < 7; i++) {
			Piece p1 = board.getPiece(i, fromJ);
			if(p1 != null && (p1.getType() == Type.ROOK || p1.getType() == Type.QUEEN) && p1.getPlayer() != p.getPlayer()){
				return true;
			}
		}
		
		for(int j = fromJ - 1; j > 0; j--) {
			Piece p1 = board.getPiece(fromI, j);
			if(p1 != null && (p1.getType() == Type.ROOK || p1.getType() == Type.QUEEN) && p1.getPlayer() != p.getPlayer()){
				return true;
			}
		}
		
		for(int j = fromJ + 1; j < 7; j++) {
			Piece p1 = board.getPiece(fromI, j);
			if(p1 != null && (p1.getType() == Type.ROOK || p1.getType() == Type.QUEEN) && p1.getPlayer() != p.getPlayer()){
				return true;
			}
		}
		
		return false;
	}
	
	
	public boolean ischecked(Piece p) {
		if(checkedStraight(p) == true || checkedDiagonal(p) == true || checkedKnight(p) == true) {
			return true;
		}
		
		return false;
	}
	
	public boolean isCheckedMate(Piece p) {
		int fromI = p.getLocation().getRow();
		int fromJ = p.getLocation().getCol();
		
		Piece p1 = new King(board, new Location(fromI, fromJ), true);
		board.newPiece(p1);
		
		if(ischecked(p) == true) {
			board.movePiece(p1, new Location(fromI + 1, fromJ));
			if(ischecked(p1) == false) {
				board.removePiece(p1);
				return false;
			}
			
			board.movePiece(p1, new Location(fromI - 1, fromJ));
			if(ischecked(p1) == false) {
				board.removePiece(p1);
				return false;
			}
			
			board.movePiece(p1, new Location(fromI , fromJ - 1));
			if(ischecked(p1) == false) {
				board.removePiece(p1);
				return false;
			}
			
			board.movePiece(p1, new Location(fromI, fromJ + 1));
			if(ischecked(p1) == false) {
				board.removePiece(p1);
				return false;
			}
			
			board.movePiece(p1, new Location(fromI + 1, fromJ + 1));
			if(ischecked(p1) == false) {
				board.removePiece(p1);
				return false;
			}
			
			board.movePiece(p1, new Location(fromI - 1, fromJ - 1));
			if(ischecked(p1) == false) {
				board.removePiece(p1);
				return false;
			}
			
			board.movePiece(p1, new Location(fromI + 1, fromJ - 1));
			if(ischecked(p1) == false) {
				board.removePiece(p1);
				return false;
			}
			
			
			board.movePiece(p1, new Location(fromI - 1, fromJ + 1));
			if(ischecked(p1) == false) {
				board.removePiece(p1);
				return false;
			}
			
			
		}
		
		return true;
	}
}
