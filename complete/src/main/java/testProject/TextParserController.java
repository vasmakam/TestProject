package testProject;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextParserController {

	/*
	 * This REST controller gets a paragraph of text from the client and returns an array of tokens representing unique words and their count
	 * found in the input text. The list is sorted alphabetically
	 */
	@RequestMapping (value="/uniqueWords", method=RequestMethod.POST)
	public ResponseEntity<ArrayList<Token>> parseText (@RequestBody Paragraph para) {

		ArrayList<Token> wordList = (new UniqueWords(para.getPara())).getSortedWords();
		ResponseEntity<ArrayList<Token>> resp = new ResponseEntity<ArrayList<Token>> (wordList, HttpStatus.OK);
	
		return resp;
	}


}
