import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Player implements Players {
	
	Random random = new Random();
	Farmer farmer;

	private int[] guessArray = new int[5];
	private int[] knownValues = {};
	private int guess;
	
	int testNumber = 0;
	int unusedNumber = -1;
	int baseGuessNumber = -1;
	int index = 0;
	
	private int totalGoats = 0; //used to prematurely stop known values if necessary
	private int knownValueIterationCount = 0;
	private int knownValueIteration = 0;
	private int[] finalGuessArray = {-1,-1,-1,-1,-1};
	private int[] possibleIndexs = {0,1,2,3,4};
	private int knownValueIndex = 0;
	private int index2 = 0;
	
	ArrayList<Integer> guessArrayList = new ArrayList<Integer>();
	
	
	public Player() {
		
	}
	
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	
	public void setNumber(int testValue) {
		for(int i = 0; i < guessArray.length; i++ ) {
			this.guessArray[i] = testValue;
		}
		this.guess = (10000 * testValue) 
			  + (1000 * testValue)
			  + (100 * testValue)
			  + (10 * testValue)
			  + (1 * testValue);
	}

	public int returnGuess() {
		return guess;
	}
	
	public void printGuessArray() {
		for(int i = 0; i < this.guessArray.length; i++) {
			System.out.print(this.guessArray[i]);
		}
	}
	
	public int returnTotalGoats() {
		return this.totalGoats;
	}
	
	public int[] returnNumberArray() {
		return this.guessArray;
	}

	public void getKnownNumbers(int iteration) {
		setNumber(iteration);
		farmer.calculateResponse(this.guessArray);
		totalGoats = totalGoats + farmer.returnGoats();
		this.updateKnownValues(iteration);
	}
	
	public void updateKnownValues(int value) {
		if(farmer.returnGoats() > 0 || farmer.returnChickens() > 0) {
			for(int i = 0; i < (farmer.returnGoats()); i++) {
				int[] temp = new int[knownValues.length + 1];
				for(int j = 0; j < knownValues.length; j++) {
					temp[j] = knownValues[j];
				}
				temp[temp.length - 1] = value;
				this.knownValues = temp;
			}
		}
	}
	
	public void printKnownValues() {
		System.out.print("Known values: ");
		for(int i = 0; i < this.knownValues.length; i++) {
			System.out.print(this.knownValues[i]);
		}
	}

	public void findAnUnusedNumber() {
		int count = 0;
		for(int i = 0; i < 10; i++) {
			if(this.unusedNumber == -1) {
				for(int j = 0; j < this.knownValues.length; j++) {
					if(knownValues[j] != i) {
						count++;
					}
				}
				if(count == knownValues.length) {
					this.unusedNumber = i;
				}
				count = 0;
			}
		}
	}
	
	public void printUnusedNumber() {
		System.out.println("unused number: " + this.unusedNumber);
	}

	public void logicGuess(int guessNumber) {
		
		
		//		if(finalGuessIsComplete()) {
//			guessArray = finalGuessArray;
//		}
//		else {
//			if(index == guessArray.length) {
//				index = 0;
//			}
//			if(index2 == possibleIndexs.length - 1) {
//				index2  = 0;
//			}
//			this.fillWithUnusedNumber();
//			guessArray[index] = knownValues[possibleIndexs[index2]];
//			index++;
//					
//		}
	}

	private boolean finalGuessIsComplete() {
		boolean complete = true;
		for(int i = 0; i < finalGuessArray.length; i++) {
			if(finalGuessArray[i] == -1) {
				complete = false;
			}
		}
		return complete;
	}

	private void fillWithUnusedNumber() {
		for(int i = 0; i < this.guessArray.length; i++) {
			guessArray[i] = this.unusedNumber;
		}
	}

	public void setPositionOfAnswer() {
		int finalIndex = getFinalIndex();
		this.removeFromPossibleIndexs(finalIndex);
		finalGuessArray[finalIndex] = this.isolateNumberFromGuess();
	}

	private int isolateNumberFromGuess() {
		int returnInt = 0;
		for(int i = 0; i < guessArray.length; i++) {
			if(guessArray[i] != this.unusedNumber) {
				returnInt = guessArray[i];
			}
		}
		return returnInt;
	}

	private void removeFromPossibleIndexs(int finalIndex) {
		int[] temp = new int[possibleIndexs.length - 1];
		int tempIndex = 0;
		for(int i = 0; i < possibleIndexs.length - 1; i++) {
			if(finalIndex != possibleIndexs[i]) {
				temp[tempIndex] = possibleIndexs[i];
				tempIndex++;
			}
		}
		possibleIndexs = temp;
		index2 = 0;
	}

	private int getFinalIndex() {
		int finalIndex = -1;
		for(int i = 0; i < guessArray.length; i++) {
			if(guessArray[i] != this.unusedNumber) {
				finalIndex = i;
			}
		}
		return finalIndex;
	}

	public void goToNextKnownNumber() {
		index = 0;
		knownValueIndex++;
	}
	
	public void printFinalGuessArray() {
		System.out.print("\nFinal Guess:");
		for(int i =0; i < finalGuessArray.length; i++) {
			System.out.print(finalGuessArray[i]);
		}
		System.out.print("\n");
	}
	
	public boolean notAlreadyAccountedFor() {
		boolean notAlreadyAccountedFor = true;
		if(index == 5) {
			index = 4;
		}
		if(finalGuessArray[index] == guessArray[index]) {
			notAlreadyAccountedFor = false;
		}
		return notAlreadyAccountedFor;
	}
}
