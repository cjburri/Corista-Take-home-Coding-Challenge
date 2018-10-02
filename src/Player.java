import java.util.Random;
import java.util.ArrayList;

public class Player implements Players {
	
	Random random = new Random();
	Farmer farmer;

	private int guess;
	
	int testNumber = 0;
	int unusedNumber = -1;
	int baseGuessNumber = -1;
	int index = 0;
	
	private int totalGoats = 0; //used to prematurely stop known values if necessary

	
	ArrayList<Integer> finalGuessArray = new ArrayList<Integer>();
	ArrayList<Integer> guessArrayList = new ArrayList<Integer>();
	ArrayList<Integer> knownValues = new ArrayList<Integer>();
	ArrayList<Integer> valuesIndexList = new ArrayList<Integer>();
	ArrayList<Integer> indexList = new ArrayList<Integer>();
	private int valuesIndex = 0;
	private boolean answerFound = false;
	private int testIndex = 0;
	private boolean _switch = true;
	
	
	public Player() {
		
	}
	
	//all set
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	
	//all set
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

	//all set
	public int returnGuess() {
		this.guess = (10000 * this.guessArrayList.get(0)) 
				  + (1000 * this.guessArrayList.get(1))
				  + (100 * this.guessArrayList.get(2))
				  + (10 * this.guessArrayList.get(3))
				  + (1 * this.guessArrayList.get(4));
		return this.guess;
	}
	
	//all set
	public void printGuessArray() {
		for(int i = 0; i < this.guessArrayList.size(); i++) {
			System.out.print(this.guessArrayList.get(i));
		}
	}
	
	//all set
	public int returnTotalGoats() {
		return this.totalGoats;
	}
	
	//all set
	public ArrayList<Integer> returnNumberArray() {
		return this.guessArrayList;
	}

	//all set
	public void getKnownNumbers(int iteration) {
		setNumber(iteration);
		farmer.calculateResponse(this.guessArrayList);
		totalGoats = totalGoats + farmer.returnGoats();
		this.updateKnownValues(iteration);
	}
	
	//all set
	public void updateKnownValues(int value) {
		if(farmer.returnGoats() > 0 || farmer.returnChickens() > 0) {
			for(int i = 0; i < (farmer.returnGoats()); i++) {
				this.knownValues.add(value);
			}
		}
	}
	
	//all set
	public void printKnownValues() {
		System.out.print("Known values: ");
		for(int i = 0; i < this.knownValues.size(); i++) {
			System.out.print(this.knownValues.get(i));
		}
	}

	//all set
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
	
	//all set
	public void printUnusedNumber() {
		System.out.println("unused number: " + this.unusedNumber);
	}

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

	/* Fills the entirety of the arraylist with a number that is not
	 * in the farmer's answer as a base case to test
	 */
	private void fillWithUnusedNumber() {
		for(int i = 0; i < this.guessArrayList.size(); i++) {
			guessArrayList.set(i,this.unusedNumber);
		}
	}

	public void setPositionOfAnswer() {
		int finalIndex = this.guessArrayList.indexOf(this.knownValues.get(valuesIndexList.get(testIndex)));
		this.finalGuessArray.set(finalIndex, this.knownValues.get(valuesIndexList.get(testIndex)));
		this.valuesIndexList.remove(testIndex);
		this.indexList.remove(indexList.indexOf(indexList.get(index)));
		testIndex = 0;
		index = 0;
		_switch = false;
	}
	
	public void checkForCompleteAnswer() {
		if(!this.finalGuessArray.contains(-1)) {
			answerFound  = true;
		}
	}
	
	//all set
	public boolean notAlreadyAccountedFor() {
		boolean notAlreadyAccountedFor = true;
		if(finalGuessArray.get(index) == this.guessArrayList.get(index)) {
			notAlreadyAccountedFor = false;
		}
		return notAlreadyAccountedFor;
	}

	//all set
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