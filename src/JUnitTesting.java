/* Author: Connor Burri
 * Date: 10/2/18
 * Project: Corista Take Home Coding Challenge
 */

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class JUnitTesting {
	
	//setup for constant tests
	
	int[] globalSecretNumberArray = {2,3,4,8,6}; //the array representation of the test secret number
	int globalSecretNumber = 23486; //integer representation of the secretNumber used for testing
	Game globalGame = new Game(globalSecretNumberArray);
	
	//------------------------------------------------------------------------------------------------------------
	
	//testing Game class methods
	//There is only one method to test in this class, others are just formatting output methods
	
	/*This is testing for the ability for the computer to eventually guess
	 *the correct number using the programmed logic  
	 */
	@Test
	public void testingNumberGuessingAbility() {
		int[] testNumber = {1,6,4,8,5}; //random test number that player will guess
		Game game = new Game(testNumber);
		game.playGame(); //game is played first which switches whether or not it has been guessed
		assertFalse(game.returnNumberNotGuessed()); //double negative, so if false then it did guess it
	}
	
	
	//------------------------------------------------------------------------------------------------------------
	
	//testing FarmersGame methods
	//there is only one method aside from the main method
	
	//This tests the stringToArray method that simply turns a string into an array
	@Test
	public void testingStringToArrayMethod() {
		String input = "12345"; //standard input (5 digits)
		int[] expectedOutput = {1,2,3,4,5}; //what should be outputted
		assertArrayEquals(FarmersGame.stringToArray(input), expectedOutput);
			
	}
	
	//------------------------------------------------------------------------------------------------------------
	
	//testing Farmer methods
	
	
	/*This tests to make sure that when given an array, the method can convert it
	 *to the corresponding int and return it
	 */
	@Test
	public void testingReturnSecretNumberMethod() {
		assertEquals(globalGame.farmer.returnSecretNumber(), globalSecretNumber);
	}
	

	/* tests to make sure that an element isnt already part of the duplicates array
	 */
	@Test
	public void testingNumNotInDuplicatesMethod() {
		int[] globalSecretNumberArray = {2,3,4,8,6}; //the array representation of the test secret number
		Game globalGame = new Game(globalSecretNumberArray);
		int[] duplicates = {1,2,3};
		int testNumber = 4;
		assertTrue(globalGame.farmer.numNotInDuplicates(duplicates, testNumber));
	}
	
	/*Thus tests if the count of goats given a guess is as expected
	 * in this case it is 0 because none of the common digits hold the same index
	 */
	@Test
	public void testingCountGoatsMethod() {
		ArrayList<Integer> guess = new ArrayList<Integer>();
		guess.add(1);
		guess.add(2);
		guess.add(3);
		guess.add(4);
		guess.add(5);
		int output = globalGame.farmer.countGoats(guess);
		assertEquals(output, 0);
	}
	
	/* Testing to make sure that the farmer outputs the correct number of chickens
	 * when the method is invoked, in this case, they should output 2
	 */
	@Test
	public void testingCountChickensMethod() {
		ArrayList<Integer> guess = new ArrayList<Integer>();
		guess.add(1);
		guess.add(2);
		guess.add(3);
		guess.add(4);
		guess.add(5);
		int[] nonDuplicated = {1,2,3,4,5};
		int output = globalGame.farmer.countChickens(nonDuplicated,guess);
		assertEquals(output, 3);
	}
	
	/* this tests that the return goats method is returning the number
	 * of goats when explicitly specified (overriding the variable)
	 */
	@Test
	public void testingReturnGoatsMethod() {
		int[] globalSecretNumberArray = {2,3,4,8,6}; //the array representation of the test secret number
		Game globalGame = new Game(globalSecretNumberArray);
		globalGame.farmer.setGoats(1);
		assertEquals(1, globalGame.farmer.returnGoats());
	}
	
	/* this tests that the return chickens method is returning the number
	 * of chickens when explicitly specified (overriding the variable)
	 */
	@Test
	public void testingReturnChickensMethod() {
		int[] globalSecretNumberArray = {2,3,4,8,6}; //the array representation of the test secret number
		Game globalGame = new Game(globalSecretNumberArray);
		globalGame.farmer.setChickens(1);
		assertEquals(1, globalGame.farmer.returnChickens());
	}
	
	/* makes sure that the method is returning the correct length of the secret
	 * variable which in this case is always 5 as the test suggests
	 */
	@Test
	public void testingReturnSecretNumberArrayLengthMethod() {
		int[] globalSecretNumberArray = {2,3,4,8,6}; //the array representation of the test secret number
		Game globalGame = new Game(globalSecretNumberArray);
		assertEquals(5, globalGame.farmer.returnSecretNumberArrayLength());
	}
	
	//------------------------------------------------------------------------------------------------------------

	//Testing Player methods
	
	Player player1 = new Player();
	
	/*This method is used to make sure that we can properly set the number to
	 * the 5 digit, same digit value
	 * 
	 * This simultaneously tests the returnGuess() method
	 */
	@Test
	public void TestingIfSetNumberWorks() {
		player1.populateArrays();
		player1.setNumber(5);
		assertEquals(55555,player1.returnGuess());
	}
	
	/*This tests if the returnGoats method is returning the correct goats
	 * this test makes use of the setGoats() method to override the goat number
	 */
	@Test
	public void TestingIfReturnTotalGoatsWorks() {
		player1.setGoats(3);
		assertEquals(3, player1.returnTotalGoats());
	}
	
	/* This test makes sure that the returnNumArray() function is working
	 * I used a setter to manually override and see if the function was doing as told 
	 * 
	 */
	@Test
	public void TestingIfReturnnumArrayWorks() {
		ArrayList<Integer> numArray = new ArrayList<Integer>();
		numArray.add(1);
		numArray.add(2);
		numArray.add(3);
		numArray.add(4);
		numArray.add(5);
		player1.setNumberArray(numArray);
		assertEquals(numArray, player1.returnNumberArray());
	}
	
	
	
}
