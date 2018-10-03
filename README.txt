Name: Connor Burri
Version: 1.0.0
Contact Info:
	-email: cjburri@wpi.edu
	-phone: 774-293-0250


Description:
This program is designed to decipher a secret 5 digit number. The deciphering takes place in the form of a guessing game
in which the computer makes a calculated guess based on the information it has thus far. Those guesses are then
given to the farmer which also acts on the computer. The farmer gives the guesser a chicken if they have a digit
that is in the number but not in the right position and a goat if the guesser guesses the right digit in the right
position. the program keeps track of the days (guesses) that it takes the guesser to get the correct answer and
receive five goats. Once the guesser guesses correctly, the game is terminated.

Prerequisites:
- have Java installed
- have JUnit4 Library

Install:
1. Unzip the files to an empty directory of your choice

Uninstall:
1. Delete the directory which you installed the files

Configuration:
1. To test your own number, modify "myNumber.txt" with a five digit number that you would like to check

Operating Instructions:
1. enter a five digit number you would like to use as the secret number in "src\testFiles\myNumber.txt" and save
2. press Windows+R
3. type "cmd" and click "ok"
4. change the directory to the directory where you installed the files 
5. open windows explorer and navigate to "C:Program Files\Java\jdkx.x.x_xxx" (or wherever java is installed) and take not of the version number of the jdk
6. in the command line enter "set path=%path%;C:\Program Files\Java\jdkx.x.x_xxx\bin" entering your version number where the x's are
7. change directory to "src"
8. type "javac FarmersGame.java" and press enter
9. type "java FarmersGame" and press enter
10. enter the name of a text file you want to be the secret number 
	- (IMPORTANT: THE .TXT FILE MUST BE IN THE FOLDER "src\testFiles")
	- (Simply enter "myNumber.txt" into the program to use your number that you wrote in step 1)

Test Instructions:
- I've included some premade .txt files that can give a bit of a test on how my program handles obscure inputs
	- To check them out, look in the \src\testFiles and try a few others out
- On top of that theres a JUnit class (which was admittadly rushed(more on that in extra notes)) which can be run by adding the JUnit library to the project in an IDE (preferrably eclipse) and simply running it


Included Files:
Within the src folder you should find:
	-Farmer.java
	-FarmersGame.java
	-Game.java
	-Player.java
	-README.txt
	-testFiles directory which by default contains:
		-all_same_digit.txt
		-myNumber.txt
		-not_enough_digits.txt
		-prearranged-random.txt
		-too_many_digits.txt

Problems:
- the main problem i had with this project was the traversal of an array that had an increasingly shorter index over time to reduce guesses
- another issue at one point was I was occasionally getting negative chickens which doesn't make sense

Solutions:
- ArrayLists helped a lot in this project, I was traversing arrays before I used the .get() and .set() operations of an ArrayList

Extra Notes:
- I have to apologize for the test cases, they were incredibly rushed... I had a lot on my plate in terms of school these past few days
- This was fun, thanks for giving me the oppurtunity to think like this