import java.util.Random;

public class Game {
	Players player = new Player();
	Players farmer = new Farmer();
	
	int guessNumber;
	int goats;
	int chickens;
	
	boolean numberNotGuessed;
	
	public Game() {
		guessNumber = 1;
		goats = 0;
		chickens = 0;
		
		numberNotGuessed = false;
	}
	
	
    public void returnFormat(int guessNumber, int goats, int chickens) {
    	System.out.print("Guess: " + guess + "\nNumber of guesses: " + guessNumber + "\nGoats: " + goats + "\nChickens: " + chickens + "\n");
    }

}
