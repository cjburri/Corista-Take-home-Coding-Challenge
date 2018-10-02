import java.util.ArrayList;
import java.util.Random;
public class Farmer implements Players {
	
	Random random = new Random();
	Player player;

	private int[] secretNumberArray = new int[5];
	private int secretNumber;
	
	private int goats;
	private int chickens;
	
	public Farmer(Player player) {
		this.setNumber();
		this.player = player;
	}
	
	public void setSecretNumber(int[] inputtedSecretNumber) {
		secretNumberArray = inputtedSecretNumber;
	}
	
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

	public int returnSecretNumber() {
		return this.secretNumber;
	}
	
	public void printSecretNumberArray() {
		System.out.print("secret number: ");
		for(int i = 0; i < secretNumberArray.length; i++) {
			System.out.print(secretNumberArray[i]);
		}
	}
	
	public void calculateResponse(ArrayList<Integer> guessArrayList) {
		int[] noDuplicateArray = this.makeArrayNoDuplicates(guessArrayList);
		this.goats = countGoats(guessArrayList);
		this.chickens = countChickens(noDuplicateArray, guessArrayList);
		
		
	}

	private int[] makeArrayNoDuplicates(ArrayList<Integer> guessArrayList) {
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

	private boolean numNotInDuplicates(int[] duplicates, int i) {
		boolean notInDuplicates = true;
		for(int j = 0; j < duplicates.length; j++) {
			if(duplicates[j] == i) {
				notInDuplicates = false;
			}
		}
		return notInDuplicates;
	}

	private int countGoats(ArrayList<Integer> guessArrayList) {
		int returnGoats = 0;
		
		for(int i = 0; i < 5; i++) {
			if(guessArrayList.get(i) == this.secretNumberArray[i]) {
				returnGoats++;
			}
		}
		
		return returnGoats;
	}
	
	private int countChickens(int[] nonDuplicatedGuessArray, ArrayList<Integer> guessArrayList) {
		int returnChickens = 0;
		
		for(int i = 0; i < secretNumberArray.length; i++) {
			for(int j = 0; j < nonDuplicatedGuessArray.length; j++) {
				if(secretNumberArray[i] == nonDuplicatedGuessArray[j]) {
					returnChickens++;
				}
			}
			if(guessArrayList.get(i) == secretNumberArray[i]) {
				returnChickens--;
			}
		}
		
		return returnChickens ;
	}
	
	public int returnGoats() {
		return this.goats;
	}
	
	public int returnChickens() {
		return this.chickens;
	}

	
	public void resetValues() {
		goats = 0;
		chickens = 0;
	}

	
	public int returnSecretNumberArrayLength() {
		return secretNumberArray.length;
	}
}
