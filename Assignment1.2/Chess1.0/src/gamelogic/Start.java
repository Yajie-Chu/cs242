package gamelogic;
 
public class Start{
	public Board board;
	public Player playerWhite, playerBlack;
	
	public Start() {
		setNewGame();
	}
	
	public void setNewGame() {
		board = new Board();
		 
	}
	
	public Board getBoard(){
		return board;
	}
	
}
