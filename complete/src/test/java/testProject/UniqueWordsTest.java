package testProject;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class UniqueWordsTest {


	@Test
	public void testGetSortedWords() {
		
		String testPara = "Bye; bye.";
		UniqueWords uniq = new UniqueWords(testPara);
		ArrayList <Token> result;

		result = uniq.getSortedWords();
		assertTrue(result.size()==1);
		
		assertTrue(result.get(0).getWord().equals("bye"));
		assertTrue(result.get(0).getCount()==2);
	}



}
