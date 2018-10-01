import java.util.Scanner;

public class Game {
	Player player = new Player();
	Farmer farmer = new Farmer(player);
	
	Scanner input = new Scanner(System.in);
	
	int guessNumber;
	
	boolean numberNotGuessed;
	
	public Game() {
		player.setFarmer(farmer);
		
		guessNumber = 1;
		
		numberNotGuessed = false;
	}
	
    public void returnString(int guessNumber, int goats, int chickens) {
    	System.out.print("Guess: ");
    	player.printGuessArray();
    	System.out.print("\nNumber of guesses: " + guessNumber + "\nGoats: " + goats + "\nChickens: " + chickens + "\n\n");
    }
    
    /* The intention of this method is to have a pass of responses between
     * the farmer and the player. essentially using this class as an interface between
     * the two players of the game */
	public void playGame() {
		//farmer.printSecretNumberArray();
		for(int i = 0; i < 10; i++) {
			if(player.returnTotalGoats() != farmer.returnSecretNumberArrayLength()) {
				player.getKnownNumbers(i); //This will be at most 10 guesses to figure out what the digits are of the secret number
				this.returnString(guessNumber, farmer.returnGoats(), farmer.returnChickens());
				guessNumber++;
				farmer.resetValues();
			}
		}
//		while(player.returnGuess() != farmer.returnSecretNumber()) {
//			farmer.calculateResponse(player.logicGuess());
//			this.guessNumber++;
//		}
		player.printKnownValues();
	}
}
