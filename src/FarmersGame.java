import java.util.Scanner;

public class FarmersGame {
	
	public static void main(String[] args) {
		
		//Scanner input = new Scanner(System.in);
		
		Game game = new Game();
		
		game.printSecretNumber();
		System.out.print("\n" + game.returnSecretNumber());

	}

}
