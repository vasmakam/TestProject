package testProject;

import java.util.*;

public class UniqueWords {
	private ArrayList<Token> sortedWords;

	/*
	 * UniqueWords constructor takes the input parameter which is a paragraph of text and parses it into word tokens
	 * Then it looks for unique words and arranges all of them alphabetically. It also counts the repeated words
	 * The information is stored in an array of tokens (sorted).
	 * Assumptions: The input text may have delimiters such as ".", ",", ";" and "\n" which are stripped and not part of words counted
	 *              The sorting assumes the lexicographic ascending order of numbers, other ASCII characters, and then the alphanumeric words
	 *              The upper case letters are same as lower case letters for the purpose of this project
	 */
	public UniqueWords(String paragraph) {

		// remove all delimiters		
		String text = paragraph.replaceAll("[,]+", "").replaceAll("[;]+", "").replaceAll("[\\.]+","").replaceAll("[\\n]+","");


		// Extract words from a text paragraph
		String [] words = (text.split("\\s+"));

		// find unique words in the text
		List<Token> wordList = new LinkedList<Token>();

		// traverse the input words to find matching words
		for(int i=0; i<words.length; i++) {
			Token token = new Token(words[i], 1);
			if(wordList.isEmpty()) {
				wordList.add(token);
				continue;
			}

			// traverse the newly built wordList linked list to find duplicate word or to insert the new word keeping the list sorted
			boolean found = false;
			for(int j=0; j<wordList.size(); j++) {
				// if a matching word is found, increment the count for that word in the list
				if(token.getWord().toLowerCase().equals(wordList.get(j).getWord().toLowerCase())) {
					token.setCount(token.getCount()+1);
					wordList.set(j, token);
					found = true;
					break;
				}	
			}
			if(!found) {
				for(int j=0; j<wordList.size(); j++) {
					// if the input word is alphabetically less than the word in the list insert the new word before the current list element
					String temp = wordList.get(j).getWord().toLowerCase();
					int cmpVal = token.getWord().toLowerCase().compareTo(temp);
					if (cmpVal < 0) {
						wordList.add(j, token);
						found = true;
						break;
					}

				}
			}
			if(!found) {
				wordList.add(token);
			}

		}

		sortedWords =  new ArrayList<Token>(wordList);

	}

	/*
	 * This method just returns the sorted list of words and counts to the controller
	 */
	public ArrayList<Token> getSortedWords() {
		return this.sortedWords;
	}

}
