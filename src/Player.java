/* Author: Connor Burri
 * Date: 9/28/18
 * Project: Corista Take Home Coding Challenge
 */

import java.util.ArrayList;

public class Player{
	
	Farmer farmer; //an object instance of the farmer to have knowledge on some of the farmers values

	private int guess; //the integer representation of the guess
	private int unusedNumber; //a number that is not in the answer
	private int index; //a global index for iterating through the guess
	private int totalGoats = 0; //used to prematurely stop known values if necessary

	
	ArrayList<Integer> finalGuessArray = new ArrayList<Integer>(); //an array where known digits will be put into the correct position
	ArrayList<Integer> guessArrayList = new ArrayList<Integer>(); //the representation of a guess so each digit is individual
	ArrayList<Integer> knownValues = new ArrayList<Integer>(); //a list of values that are definitely in the farmers secret number
	ArrayList<Integer> valuesIndexList = new ArrayList<Integer>(); //a list of indexs that are still available in the finalGuessArray
	ArrayList<Integer> indexList = new ArrayList<Integer>(); //a list of indexs from 0-4 which allow for custom traversal through a list
	
	private boolean answerFound = false; //boolean expression to represent if the player has found the answer
	private int testIndex = 0; //number to iterate through for first part of the algorithm
	private boolean _switch = true; //specific logic switch for knowing when to hit certain indexs in the guessArrayList
	
	//setting default values
	public Player() {
		unusedNumber = -1;
		index = 0;
	}
	
	/*Author: Connor Burri
	 *Purpose: setting the farmer as something that can be accesssed in this class
	 *Parameters: none
	 *Return:void
	 */
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	
	/*Author: Connor Burri
	 *Purpose: given a test value, this method makes the guess 5 of the same digit as the first part of the algorithm
	 *Parameters: an integer that is iterated for the first part of the algorithm (int testValue)
	 *Return: void
	 */
	public void setNumber(int testValue) {
		for(int i = 0; i < this.guessArrayList.size(); i++ ) {
			this.guessArrayList.set(i, testValue);
		}
		this.guess = (10000 * testValue) 
			  + (1000 * testValue)
			  + (100 * testValue)
			  + (10 * testValue)
			  + (1 * testValue);
	}

	/*Author: Connor Burri
	 *Purpose: returns the guess in the form of an integer
	 *Parameters: none
	 *Return: integer representation of the guess
	 */
	public int returnGuess() {
		this.guess = (10000 * this.guessArrayList.get(0)) 
				  + (1000 * this.guessArrayList.get(1))
				  + (100 * this.guessArrayList.get(2))
				  + (10 * this.guessArrayList.get(3))
				  + (1 * this.guessArrayList.get(4));
		return this.guess;
	}
	
	/*Author: Connor Burri
	 *Purpose: prints out the guessArray for formatting purposes 
	 *Parameters: none
	 *Return: void
	 */
	public void printGuessArray() {
		for(int i = 0; i < this.guessArrayList.size(); i++) {
			System.out.print(this.guessArrayList.get(i));
		}
	}
	
	/*Author: Connor Burri
	 *Purpose: returns how many goats have been accumulated overall (once we hit 5, the first part of the algorithm is over)
	 *Parameters: none
	 *Return: the number of goats
	 */
	public int returnTotalGoats() {
		return this.totalGoats;
	}
	
	/*Author: Connor Burri
	 *Purpose: sets the number of total goats - for testing purposes
	 *Parameters: number to set goats to
	 *Return: void
	 */
	public void setGoats(int num) {
		this.totalGoats = num;
	}
	
	/*Author: Connor Burri
	 *Purpose: returns the array representation of the players guess
	 *Parameters: none
	 *Return: the array representation of the players guess
	 */
	public ArrayList<Integer> returnNumberArray() {
		return this.guessArrayList;
	}
	
	/*Author: Connor Burri
	 *Purpose: sets the number array which is represented by a guess - for testing purposes
	 *Parameters: array to be set
	 *Return: void
	 */
	public void setNumberArray(ArrayList<Integer> numArray) {
		this.guessArrayList = numArray;
	}

	/*Author: Connor Burri
	 *Purpose: this is the first part of the algorithm where the player goes through the numbers 00000,11111,...,99999
	 *and uses the goats as a way to figure out which digits are in the final answer
	 *Parameters: the iteration of this algorithm, there is at most 10 iterations (int iteration)
	 *Return: void
	 */
	public void getKnownNumbers(int iteration) {
		setNumber(iteration);
		farmer.calculateResponse(this.guessArrayList);
		totalGoats = totalGoats + farmer.returnGoats();
		this.updateKnownValues(iteration);
	}
	
	/*Author: Connor Burri
	 *Purpose: when a known value is found, this is the method which stores it into the list of known values
	 *Parameters: the digit that has been found, position unknown (int value)
	 *Return: void
	 */
	public void updateKnownValues(int value) {
		if(farmer.returnGoats() > 0 || farmer.returnChickens() > 0) {
			for(int i = 0; i < (farmer.returnGoats()); i++) {
				this.knownValues.add(value);
			}
		}
	}
	
	/*Author: Connor Burri
	 *Purpose: prints out the known values which is as an array list, this was mainly for testing
	 *Parameters: none
	 *Return: void
	 */
	public void printKnownValues() {
		System.out.print("Known values: ");
		for(int i = 0; i < this.knownValues.size(); i++) {
			System.out.print(this.knownValues.get(i));
		}
	}

	/*Author: Connor Burri
	 *Purpose: to find a "filler" number to make guesses based off of the known numbers
	 *Parameters: none
	 *Return: void
	 */
	public void findAnUnusedNumber() {
		int count = 0;
		for(int i = 0; i < 10; i++) {
			if(this.unusedNumber == -1) {
				for(int j = 0; j < this.knownValues.size(); j++) {
					if(knownValues.get(j) != i) {
						count++;
					}
				}
				if(count == knownValues.size()) {
					this.unusedNumber = i;
				}
				count = 0;
			}
		}
	}
	
	/*Author: Connor Burri
	 *Purpose: prints the unused number, mainly for testing
	 *Parameters: none
	 *Return: void
	 */
	public void printUnusedNumber() {
		System.out.println("unused number: " + this.unusedNumber);
	}

	/*Author: Connor Burri
	 *Purpose: this is the second part of the algorithm which isolates the known values and subsequently
	 *puts those known value into the correct position
	 *Parameters: boolean value that lets player know that it is the first iteration which has special cases
	 *Return: void
	 */
	public void logicGuess(boolean firstIteration) {
		if(answerFound) {
			this.guessArrayList = this.finalGuessArray;
		}
		else {
			if(!firstIteration && _switch) {
				index++;
			}
			_switch = true;
			if(index == indexList.size()) {
				index = 0;
			}
			if(testIndex == valuesIndexList.size()) {
				testIndex = 0;
			}
			this.fillWithUnusedNumber();
			this.guessArrayList.set(indexList.get(index), this.knownValues.get(valuesIndexList.get(testIndex)));
		}
	}

	/*Author: Connor Burri
	 *Purpose: fills the guess array with the unused number so we can isolate the position of a known number
	 *Parameters: none
	 *Return: void
	 */
	private void fillWithUnusedNumber() {
		for(int i = 0; i < this.guessArrayList.size(); i++) {
			guessArrayList.set(i,this.unusedNumber);
		}
	}

	/*Author: Connor Burri
	 *Purpose: puts the known number into the correct position of the finalGuessArray
	 *Parameters: none
	 *Return: void
	 */
	public void setPositionOfAnswer() {
		int finalIndex = this.guessArrayList.indexOf(this.knownValues.get(valuesIndexList.get(testIndex)));
		this.finalGuessArray.set(finalIndex, this.knownValues.get(valuesIndexList.get(testIndex)));
		this.valuesIndexList.remove(testIndex);
		this.indexList.remove(indexList.indexOf(indexList.get(index)));
		testIndex = 0;
		index = 0;
		_switch = false;
	}
	
	/*Author: Connor Burri
	 *Purpose: checks to see if all of the positions of the final guess array are filled so it can be submitted
	 *Parameters: none
	 *Return: void
	 */
	public void checkForCompleteAnswer() {
		if(!this.finalGuessArray.contains(-1)) {
			answerFound  = true;
		}
	}
	
	/*Author: Connor Burri
	 *Purpose: checks to see if a known value has already been placed in the correct spot
	 *Parameters: none
	 *Return: boolean representation of the purpose
	 */
	public boolean notAlreadyAccountedFor() {
		boolean notAlreadyAccountedFor = true;
		if(finalGuessArray.get(index) == this.guessArrayList.get(index)) {
			notAlreadyAccountedFor = false;
		}
		return notAlreadyAccountedFor;
	}

	/*Author: Connor Burri
	 *Purpose: gives all of the necessary arrays initial populations
	 *Parameters: none
	 *Return: void
	 */
	public void populateArrays() {
		for(int i = 0; i < 5; i++) {
			this.guessArrayList.add(-1);
		}
		for(int i = 0; i < 5; i++) {
			this.finalGuessArray.add(-1);
		}
		for(int i = 0; i < 5; i++) {
			this.valuesIndexList.add(i);
			this.indexList.add(i);
		}
	}
}