package Error;

public class InvalidExceptions extends Exception{
	 
	// called when it is an illegal move
    public InvalidExceptions(){
        System.out.println("Oops, that's an illegal move!");
    }
}
