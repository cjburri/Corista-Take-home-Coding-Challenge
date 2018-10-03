/* Author: Connor Burri
 * Date: 9/28/18
 * Project: Corista Take Home Coding Challenge
 */

import java.util.ArrayList;
import java.util.Random;
public class Farmer{
	
	Random random = new Random(); //random object for creating random secret number
	Player player; //player object to know some insights about the player

	private int[] secretNumberArray = new int[5]; //array representation of the secret number
	private int secretNumber; //secret number in int form
	
	private int goats; //number of goats going towards a players guess
	public int chickens; //number of chickens going towards a players guess
	
	
	public Farmer(Player player) {
		this.setNumber();
		this.player = player;
	}
	
	/*Author: Connor Burri
	 *Purpose: if the user chooses to input their own number as a secret answer then this is where it occurs
	 *Parameters: the array representation of the inputted number (int[] inputtedSecretNumber)
	 *Return: void
	 */
	public void setSecretNumber(int[] inputtedSecretNumber) {
		secretNumberArray = inputtedSecretNumber;
		this.arrayToSecretInt();
	}
	
	/*Author: Connor Burri
	 *Purpose: changes the array to an integer form of the secret number
	 *Parameters: none
	 *Return: void
	 */
	private void arrayToSecretInt() {
		this.secretNumber = (10000 * this.secretNumberArray[0])
				  +	(1000 * this.secretNumberArray[1])	
				  +	(100 * this.secretNumberArray[2])	
				  +	(10 * this.secretNumberArray[3])	
				  +	(1 * this.secretNumberArray[4]);
	}

	/*Author: Connor Burri
	 *Purpose: sets the secret number to a random variable that is overridden by the .txt file if user inputs it
	 *Parameters: none
	 *Return: void
	 */
	private void setNumber() {
		for(int i = 0; i < this.secretNumberArray.length; i++ ) {
			this.secretNumberArray[i] = random.nextInt(10);
		}
		this.secretNumber = (10000 * this.secretNumberArray[0])
			  +	(1000 * this.secretNumberArray[1])	
			  +	(100 * this.secretNumberArray[2])	
			  +	(10 * this.secretNumberArray[3])	
			  +	(1 * this.secretNumberArray[4]);
	}

	/*Author: Connor Burri
	 *Purpose: returns the secret number in int form
	 *Parameters: none
	 *Return: integer representation of secret number
	 */
	public int returnSecretNumber() {
		return this.secretNumber;
	}
	
	/*Author: Connor Burri
	 *Purpose: prints the secret number using the array so leading 0's arent cut out
	 *Parameters: none
	 *Return: void
	 */
	public void printSecretNumberArray() {
		System.out.print("secret number: ");
		for(int i = 0; i < secretNumberArray.length; i++) {
			System.out.print(secretNumberArray[i]);
		}
	}
	
	/*Author: Connor Burri
	 *Purpose: given a guess, this goes through the process of figuring out how many chickens and 
	 *goats should be distributed back to the player
	 *Parameters: the array representation of a guess from the player(ArrayList<Integer> guessArrayList)
	 *Return: void
	 */
	public void calculateResponse(ArrayList<Integer> guessArrayList) {
		int[] noDuplicateArray = this.makeArrayNoDuplicates(guessArrayList);
		this.goats = countGoats(guessArrayList);
		this.chickens = countChickens(noDuplicateArray, guessArrayList);
		
		
	}

	/*Author: Connor Burri
	 *Purpose: turns the players array into a smaller array that does not have duplicating values to easily
	 *calculate the number of chickens and goats
	 *Parameters: the array form of a guess
	 *Return: a non-duplicated form of the parameter
	 */
	public int[] makeArrayNoDuplicates(ArrayList<Integer> guessArrayList) {
		int[] duplicates = {};
		for(int i = 0; i < guessArrayList.size(); i++) {
			for(int j = i + 1; j < guessArrayList.size(); j++) {
				if(guessArrayList.get(i) == guessArrayList.get(j) && (numNotInDuplicates(duplicates, guessArrayList.get(j)))) {
					int[] temp = new int[duplicates.length + 1];
					for(int k = 0; k < duplicates.length; k++) {
						temp[k] = duplicates[k];
					}
					temp[temp.length - 1] = guessArrayList.get(j);
					duplicates = temp;
				}
			}
		}
		return duplicates;
	}

	/*Author: Connor Burri
	 *Purpose: checks to see if the number is not already in the duplicates array
	 *Parameters: the duplicates array and the index we are checking
	 *Return: returns a boolean representation of the purpose
	 */
	public boolean numNotInDuplicates(int[] duplicates, int i) {
		boolean notInDuplicates = true;
		for(int j = 0; j < duplicates.length; j++) {
			if(duplicates[j] == i) {
				notInDuplicates = false;
			}
		}
		return notInDuplicates;
	}

	/*Author: Connor Burri
	 *Purpose: counts the number of goats earned given the guess
	 *Parameters: the guess in array form
	 *Return: the number of goats
	 */
	public int countGoats(ArrayList<Integer> guessArrayList) {
		int returnGoats = 0;
		
		for(int i = 0; i < 5; i++) {
			if(guessArrayList.get(i) == this.secretNumberArray[i]) {
				returnGoats++;
			}
		}
		
		return returnGoats;
	}
	
	/*Author: Connor Burri
	 *Purpose: counts the number of chickens earned given the guess
	 *Parameters: the guess in array form
	 *Return: the number of chickens
	 */
	public int countChickens(int[] nonDuplicatedGuessArray, ArrayList<Integer> guessArrayList) {
		int returnChickens = 0;
	
		for(int i = 0; i < nonDuplicatedGuessArray.length; i++) {
			for(int j = 0; j < this.secretNumberArray.length; j++) {
				if(nonDuplicatedGuessArray[i] == this.secretNumberArray[j]) {
					returnChickens++;
					if(guessArrayList.get(j) == this.secretNumberArray[j]) {
						returnChickens--;
					}
				}
			}
		}
		return returnChickens;
	}
	
	/*Author: Connor Burri
	 *Purpose: manually sets the goats for testing purposes
	 *Parameters: number to set goats to (int setNumber)
	 *Return: void
	 */
	public void setGoats(int setNumber) {
		this.goats = setNumber;
	}
	
	/*Author: Connor Burri
	 *Purpose: manually sets the chickens for testing purposes
	 *Parameters: number to set chickens to (int setNumber)
	 *Return: void
	 */
	public void setChickens(int setNumber) {
		this.chickens = setNumber;
	}
	
	
	/*Author: Connor Burri
	 *Purpose: returns the number of goats earned
	 *Parameters: none
	 *Return: int number of goats
	 */
	public int returnGoats() {
		return this.goats;
	}
	
	/*Author: Connor Burri
	 *Purpose: returns the number of chickens earned from the guess
	 *Parameters: none
	 *Return: the int representation of chickens earned
	 */
	public int returnChickens() {
		return this.chickens;
	}

	
	/*Author: Connor Burri
	 *Purpose: resets the number of goats and chickens earned
	 *Parameters: none
	 *Return: void
	 */
	public void resetValues() {
		goats = 0;
		chickens = 0;
	}

	
	/*Author: Connor Burri
	 *Purpose: returns the length of the secret number array - mainly for testing purposes
	 *Parameters: none
	 *Return: the length of the secret number array
	 */
	public int returnSecretNumberArrayLength() {
		return secretNumberArray.length;
	}
}
