import java.util.Arrays;
import java.util.Random;
public class Player implements Players {
	
	Random random = new Random();
	Farmer farmer;

	private int[] guessArray = new int[5];
	private int[] knownValues = {};
	private int guess;
	
	int testNumber = 0;
	int unusedNumber = -1;
	
	private int totalGoats = 0; //used to prematurely stop known values if necessary
	
	
	
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
}
