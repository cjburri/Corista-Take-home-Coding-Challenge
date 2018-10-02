import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FarmersGame {
	public static void main(String[] args) throws Exception{
		String filePath = new File("").getAbsolutePath();
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the name of the test file to use as the farmers secret number\nor leave blank to have the secret number randomly generated\n");
		System.out.print("Enter file name: ");
		String fileName = input.next();
		
		
		
		FileReader file = new FileReader(filePath + "/src/testFiles/" + fileName);
		BufferedReader reader = new BufferedReader(file);
		
		String text = "";
		String line = reader.readLine();
		while(line != null) {
			text+= line;
			line = reader.readLine();
		}
		int[] secretNumber = stringToArray(text);
		
		
		Game game = new Game(secretNumber);
		game.playGame();
	}

	private static int[] stringToArray(String text) {
		int[] returnArray = new int[5];
		for(int i = 0; i < text.length(); i++) {
			returnArray[i] = Integer.parseInt(String.valueOf(text.charAt(i)));
		}
		return returnArray;
	}
}
