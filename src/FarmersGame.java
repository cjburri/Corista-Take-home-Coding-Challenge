/* Author: Connor Burri
 * Date: 9/27/18
 * Project: Corista Take Home Coding Challenge
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FarmersGame {
	public static void main(String[] args) throws Exception{
		String filePath = new File("").getAbsolutePath();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int[] secretNumber;
		
		System.out.print("Enter the name of the test file to use as the farmers secret number\nor leave blank to have the secret number randomly generated\n");
		System.out.print("Enter file name: ");
		String fileName = input.nextLine();
		if(fileName.length() == 0) {
			Game game = new Game();
			game.playGame();
		}
		else {
			FileReader file = new FileReader(filePath + "/testFiles/" + fileName);
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(file);
			
			String text = "";
			String line = reader.readLine();
			while(line != null) {
				text+= line;
				line = reader.readLine();
			}
			if(text.length() != 5) {
				System.out.println("This file contains the incorrect number of digits, run the program again with a file with the correct number of digits");
			}
			else {
				secretNumber = stringToArray(text);
				Game game = new Game(secretNumber);
				game.playGame();
			}
		}
	}

	public static int[] stringToArray(String text) {
		int[] returnArray = new int[5];
		for(int i = 0; i < text.length(); i++) {
			returnArray[i] = Integer.parseInt(String.valueOf(text.charAt(i)));
		}
		return returnArray;
	}
}
