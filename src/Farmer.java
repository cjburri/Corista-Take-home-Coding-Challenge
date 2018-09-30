import java.util.Random;
public class Farmer implements Players {
	
	Random random = new Random();

	int[] secretNumberArray = new int[5];
	int secretNumber;
	
	public void setNumber() {
		for(int i = 0; i < secretNumberArray.length; i++ ) {
			secretNumberArray[i] = random.nextInt();
		}
		secretNumber = (10000 * secretNumberArray[0])
			  +	(1000 * secretNumberArray[1])	
			  +	(100 * secretNumberArray[2])	
			  +	(10 * secretNumberArray[3])	
			  +	(1 * secretNumberArray[4]);
	}

	public int returnNumber() {
		return secretNumber;
	}

}
