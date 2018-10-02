import java.util.Scanner;

public class Game {
	//Objects
	Player player = new Player(); //player object
	Farmer farmer = new Farmer(player); //player is an argument in instantiating farmer so farmer can "communicate" with the player
	Scanner input = new Scanner(System.in); //simply to allow advancement to next turn at players leisure
	
	//fields
	private int guessNumber;
	private boolean numberNotGuessed;
	private boolean firstLogicIteration; //a break case for the logical section of the code
	
	public Game() {
		//allows the player and farmer to "communicate"
		player.setFarmer(farmer);
		guessNumber = 0;
		numberNotGuessed = true;
		firstLogicIteration = true;
	}
    
    /* The intention of this method is to have a pass of responses between
     * the farmer and the player. essentially using this class as an interface between
     * the two players of the game */
	public void playGame() {
		//gives the arrays for the player to guess some non-null values
		player.populateArrays(); 
		
		//Quick but kind of costly algorithm to acquire the number of each digit in the answer
		for(int i = 0; i < 10; i++) {
			//this if statement allows for the player to not "overguess" (as much as this algorithm allows)
			if(player.returnTotalGoats() != farmer.returnSecretNumberArrayLength()) {
				player.getKnownNumbers(i); //This will be at most 10 guesses to figure out what the digits are of the secret number
				guessNumber++;
				//if by chance the secret number is something like '11111', this accounts for it
				if(player.returnGuess() == farmer.returnSecretNumber()){
					System.out.println("The player has guessed the correct sequence in " + guessNumber + " day(s)!");
					i = 10;
				}
				else {
					this.formatOutput();
					farmer.resetValues();
				}
			}
		}
		
		//This merely finds a filler number that the player will use to make calculated guesses
		player.findAnUnusedNumber();
		
		//while the player hasn't guessed the secret number, the player will continue to guess
		while(numberNotGuessed) {
			//the player then uses logic to find the exact position of the known numbers with the help of the farmers response
			if(firstLogicIteration) {
				player.logicGuess(true);
				firstLogicIteration = false;
			}
			else {
				player.logicGuess(false);
			}
			farmer.calculateResponse(player.returnNumberArray());
			
			//this sets the answer up when the player has decided it is in the right position
			if(farmer.returnGoats() > 0 && player.notAlreadyAccountedFor()) {
				player.setPositionOfAnswer();
				player.checkForCompleteAnswer();
			}
			
			//section to format output
			this.formatOutput();
			if(player.returnGuess() == farmer.returnSecretNumber()) {
				this.numberNotGuessed = false;
			}
			else {
				//setting things up for the next iteration
				guessNumber++;
				farmer.resetValues();
			}
		}
		
		//END OF GAME
		System.out.println("you have found the farmers secret number in " + guessNumber + " days!");
	}

	/* outputs all of the data according to the prompt in an organized way
	 */
	private void formatOutput() {
		this.returnString(guessNumber, farmer.returnGoats(), farmer.returnChickens());
		System.out.print("(Press Enter To Continue)\n");
		@SuppressWarnings("unused")
		String continueString = input.next();
		
	}
	/* Just the outputted data
	 */
	 public void returnString(int guessNumber, int goats, int chickens) {
	    	System.out.print("Guess: ");
	    	player.printGuessArray();
	    	System.out.print("\nAnswer: "); //was using this for testing purposes
	    	farmer.printSecretNumberArray(); //this too
	    	System.out.print("\nNumber of guesses: " + guessNumber + "\nGoats: " + goats + "\nChickens: " + chickens + "\n\n");
	    }
}
