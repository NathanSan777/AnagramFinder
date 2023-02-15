import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;

/**
 * This is a class that takes a String as user input, and returns all
 * of the possible anagrams from that word.
 * @author Nathan Sanchez
 *
 */
public class AnagramFinderMain {
	
	/**
	 * A method that takes in two Strings, a base and a word to check. 
	 * Returns a boolean value that determines if they are anagrams.
	 * @param base: the String from user input.
	 * @param toCheck:the String read in from the text file.
	 * @return: a boolean value
	 */
	public static boolean isAnagram(String base, String toCheck) {
		//Two strings cannot be anagrams if they are not the same length.
		if (base.length() != toCheck.length()) {
			return false;
		}
		//Transforms the two inputs into char arrays using the Arrays package.
		char[] baseChar = base.toCharArray();
		char[] checkChar = toCheck.toCharArray();
		//Sorts the arrays for the next conditional
		Arrays.sort(baseChar);
		Arrays.sort(checkChar);
		//If both the arrays are equivalent to each other, then they are anagrams.
		//Return true in this case.
		if (Arrays.equals(baseChar, checkChar)) {
			return true;
		}
		//Otherwise, return false.
		else {
			return false;
		}
	}
	
	/**
	 * A simple method that prints out the content of an ArrayList.
	 * @param list: The ArrayList to print out.
	 */
	public static void printArrayList(ArrayList<String> list) {
		for (String s : list) {
			System.out.print(s + " ");
		}
	}

	
	public static void main(String[] args) {
		//Create an ArrayList of Strings that contains any anagrams we find.
		ArrayList<String> anagrams = new ArrayList<String>();
		
		Scanner toScan = new Scanner(System.in);
		System.out.println("Hello!! Welcome to the Anagram Finder. Please enter a word.");
		//Set the user input to lowercase, as all of the dictionary's words
		//are lowercase. Otherwise, the ASCII value of the word will differ 
		//when I compare them.
		String toFind = toScan.nextLine().toLowerCase();
		//Try-catch statement to load in the dictionary file.
		try {
			File wordList = new File("enable.txt");
			Scanner reader = new Scanner(wordList);
			//So long as there are words to be read in the dictionary,
			//grab them line-by-line and compare them to the user's input.
			while (reader.hasNextLine()) {
				//Gets next word in the dictionary
				String newWord = reader.nextLine();
				//If an anagram is found, put it in the ArrayList of anagrams.
				if (isAnagram(toFind, newWord)) {
					anagrams.add(newWord);
				}
			}
			//Close the scanner for the dictionary to prevent issues from occurring 
			reader.close();
		}
		//If no file is found, catch the error to prevent the program from crashing.
		catch (FileNotFoundException e){
			System.out.println("An error has occurred. Did you put in a file?");
		}
		//At the end, print out all of the anagrams for the word and close
		//the scanner.
		System.out.println("The anagrams for the word " + toFind + " are: ");
		if (anagrams.isEmpty()) {
			System.out.println("Hmmm... There doesn't see to be any anagrams in this word.");
		}
		else {
			printArrayList(anagrams);
		}
		toScan.close();
	}
}
