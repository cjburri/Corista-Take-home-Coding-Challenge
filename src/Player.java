import java.util.Random;
public class Player implements Players {
	
	Random random = new Random();

	int[] guessArray = new int[5];
	int guess;
	
	public Player() {
		setNumber();
	}
	
	public void setNumber() {
		for(int i = 0; i < guessArray.length; i++ ) {
			guessArray[i] = random.nextInt();
		}
		guess = (10000 * guessArray[0])
			  +	(1000 * guessArray[1])	
			  +	(100 * guessArray[2])	
			  +	(10 * guessArray[3])	
			  +	(1 * guessArray[4]);	
	}

	public int returnNumber() {
		return guess;
	}

}
