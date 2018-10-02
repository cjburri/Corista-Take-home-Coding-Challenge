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
    
    /* The intention of this method is to have a pass of responses between
     * the farmer and the player. essentially using this class as an interface between
     * the two players of the game */
	public void playGame() {
		//gives the arrays for the player to guess some non-null values
		player.populateArrays(); 
		
		//Quick but kind of costly algorithm to acquire the number of each digit in the answer
		for(int i = 0; i < 10; i++) {
			if(player.returnTotalGoats() != farmer.returnSecretNumberArrayLength()) {
				player.getKnownNumbers(i); //This will be at most 10 guesses to figure out what the digits are of the secret number
				this.formatOutput();
				guessNumber++;
				farmer.resetValues();
			}
			else {
				System.out.println("The player has guessed the correct sequence in " + guessNumber + " day(s)!");
			}
		}
		
		//This merely finds a filler number that the player will use to make calculated guesses
		player.findAnUnusedNumber();
		
		while(player.returnGuess() != farmer.returnSecretNumber()) {
			//the player then uses logic to find the exact position of the known numbers with the help of the farmers response
			player.logicGuess(guessNumber);
			farmer.calculateResponse(player.returnNumberArray());
			
			//this sets the answer up when the player has decided it is in the right position
			if(farmer.returnGoats() > 0 && player.notAlreadyAccountedFor()) {
				player.setPositionOfAnswer();
				player.checkForCompleteAnswer();
			}
			
			//section to format output
			this.formatOutput();
			
			//setting things up for the next iteration
			guessNumber++;
			farmer.resetValues();
		}
		System.out.println("you have found the farmers secret number in " + guessNumber + " days!");
	}

	private void formatOutput() {
		this.returnString(guessNumber, farmer.returnGoats(), farmer.returnChickens());
		System.out.println("(Press Enter To Continue)");
		@SuppressWarnings("unused")
		String continueString = input.next();
		
	}
	
	 public void returnString(int guessNumber, int goats, int chickens) {
	    	System.out.print("Guess: ");
	    	player.printGuessArray();
	    	System.out.print("\nAnswer: " + farmer.returnSecretNumber());
	    	System.out.print("\nNumber of guesses: " + guessNumber + "\nGoats: " + goats + "\nChickens: " + chickens + "\n\n");
	    }
}
