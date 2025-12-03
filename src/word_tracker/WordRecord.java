package word_tracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * WordRecord is used to store record(s) of a word
 * since in this application we are trying to "track" words
 * not just plain words as strings but data with it
 * so We will use this class and store its instances in the Nodes of the binary tree
 * 
 * @author Youssif Al-Halawche
 * @version 1.0
 */
public class WordRecord implements Comparable<WordRecord>, Serializable{

	
	//serialization id 
	private static final long serialVersionUID = 144L;

	
	
	
	//the word we are storing
	private String word;
	//word data.
	//Key:using filename is the key,
	//Value: an ArrayList<Integer> of the line numbers the word occurred in
	private HashMap<String, ArrayList<Integer>> references;
	
	/**
	 * Word Record constructor
	 * Checks whether the word value is null or the word string is empty/contains only white space if true
	 * throws a {@code NullPointerException} with a related message
	 * Takes the text of the word that we are trying to keep track of as a parameter
	 * creates a WordRecord object by adding a HashMap that keeps track of references
	 * @param wordText the text of the word to keep track of.
	 * @author Youssif Al-Halawche 
	 */
	public WordRecord(String wordText) {
		
		if (wordText == null || wordText.isBlank()){ 
		throw new NullPointerException("Word text cannot be null");
		}
		
		this.word = wordText;
		this.references = new HashMap<>();
		
	}
	/**
	 * adds a new location where a particular word was found
	 * 
	 * 
	 * @author Youssif Al-Halawche
	 * @param filename file name used as key in the references HashMap
	 * @param lineNumber The lineNumber to be added to the ArrayList of linenumbers
	 * @return void
	 * @author Youssif Al-Halawche 
	 */
	public void addLocation(String filename, int lineNumber) {
		if(!references.containsKey(filename)) {
			references.put(filename, new ArrayList<>());
		}
		references.get(filename).add(lineNumber);
		
	}
	/**
	 * returns the word string (actual word)
	 * @return String representing the word
	 * @author Youssif Al-Halawche 
	 */
	public String getWord() {
		return word;
	}
	/**
	 * @return HashMap containing all references to the lines numbers the word occurred in
	 * @author Youssif Al-Halawche 
	 */
	public HashMap<String, ArrayList<Integer>> getReferences(){
		return references;
	}
	/**
	 * compares the strings of two words
	 * @author Youssif Al-Halawche
	 */
	@Override
	public int compareTo(WordRecord o) {
		return this.word.compareTo(o.word);
	}

	@Override
	public String toString() {
		return "WordRecord [word=" + word + ", references=" + references + "]";
	}


	

}
