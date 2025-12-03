package word_tracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import implementations.BSTree;
import implementations.BSTreeNode;
import utilities.Iterator;
public class WordTracker {
/**
 * Entry point of the program
 * @param args commandline arguments
 * @author Youssif Al-Halawche
 * @version 1.0
 */
	
	/**
	 * this program will only have 3 output modes
	 * this enum is used to set the global/general output mode of the program
	 * @author Youssif Al-Halawche
	 */
	private enum OutputMode{
		PF,
		PL,
		PO
	}
	
	//outputmode, defaulted to PF
	static OutputMode outputmode = OutputMode.PF;
	//output file path, incase user used the third optional argument
	static String outputFile = null;
	
	public static void main(String[] args) throws IOException {
		
			//safety check
			if (args.length < 2) {
				System.out.println("program crashed because you did not supply valid/enough arguments \n \n");
				throw new IllegalArgumentException("usage java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f<output.txt>]");
			}
			//1-parse arguments 			
			File f = new File(args[0]);
			if(!f.exists() || f.isDirectory()) {
				throw new NullPointerException("File does not exist or is a directory!");
			}
			switch (args[1].toString()) {
				case "pf":
					outputmode = OutputMode.PF;
					System.out.print("pf");
					break;
				case "pl":
					outputmode = OutputMode.PL;
					System.out.print("pl");
					break;
				case "po":
					outputmode = OutputMode.PO;
					System.out.print("po");
					break;
				default:
					throw new IllegalArgumentException("Invalid Option: " + args[1].toString());
			}
			
			//optional third arguement handling
			if(args.length == 3) {
				String opt = args[2];
				if (opt.startsWith("-f")) {
					if(opt.length() > 2) {
						outputFile = opt.substring(2);
					}
				}
			} 
			//2-deserialize
			//deserialized tree
			BSTree tree = deserialize();
			
			//3-process input file
			processInputFile(tree, f);
			
			
			//5-generate report
			String report = generateReport(tree, outputmode);
			//output report to file if outputFile is specified, otherwise print to console
			if(outputFile == null) {
				System.out.print(report);
			}else {
				writeToFile(outputFile, report);
			}
			
			
			//4-serialize
			boolean isSaved = serialize(tree);
			//if file fails to save infrom user of failure
			if (!isSaved) System.err.println("FILE FAILED TO SAVE!!");
			
	}
	
	/**
	 * Generated a formatted rport of all WordRecord objects stored in the binary search tree
	 * 
	 * @param tree the binary search tree containing all WordRecord objects
	 * @param outputmode an enum value representing the desired formatting mode
	 * @return returns the formatted string representing the full report
	 */
	private static String generateReport(BSTree<WordRecord> tree, OutputMode outputmode) {
		
		StringBuilder sb = new StringBuilder();
		
		Iterator<WordRecord> it = tree.inorderIterator();
		while (it.hasNext()) {
			WordRecord r = it.next();
			switch(outputmode) {
				
			case PF:
				sb.append(WordTrackerUtility.formatPF(r));
				break;
			case PL:
				sb.append(WordTrackerUtility.formatPL(r));
				break;
			case PO:
				sb.append(WordTrackerUtility.formatPO(r));
				break;
				
			}
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
	
	
	/**
	 * writes the report string geenrated to a file if an output file is specified
	 * @param outputPath output path...
	 * @param content content..
	 * @throws IOException in case of an IO error during the writing process
	 * 
	 * @author Youssif Al-Halawche
	 */
	private static void writeToFile(String outputPath, String content) throws IOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))){
			bw.write(content);
		}
	}
	/**
	 * Reads the input file line by line 
	 * while reading the file uses non-word characters to seperate words
	 * then updates the BST with WordRecord objects and locations
	 * @param tree BSTree to be updated
	 * @param file input text file
	 * 
	 * @author Youssif Al-Halawche
	 */
	private static void processInputFile(BSTree<WordRecord> tree, File file) {
		String fileNameOnly = file.getName();
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			int lineNumber = 0;
			
			while ((line = br.readLine()) != null) {
				lineNumber++;
				
				//split line into words, use non-words as seperators
				String[] words = line.toLowerCase().split("[ ,.;:!?()\"'-]+");
				
				for (String s : words) {
					if (s.isEmpty()) {
						continue;
					}
					
					WordRecord keyRecord = new WordRecord(s);
					
					BSTreeNode<WordRecord> node = tree.search(keyRecord);
					
					if(node == null) {
						WordRecord newRecord = new WordRecord(s);
						newRecord.addLocation(fileNameOnly, lineNumber);
						tree.add(newRecord);
					}else {
						//in this case the word exists 
						WordRecord existingWord = node.getElement();
						existingWord.addLocation(fileNameOnly, lineNumber);
					}
				}
			}
		}catch(Exception e) {
			System.err.print("ERROR: " + e);
		}
	}
	/**
	 * loads repository.ser if exists, if it does not then it returns a new empty tree
	 * @return BSTree<WordRecord> of deserialized WordRecord objects from repository.ser 
	 * @throws IOException throws this exception in case of an io error
	 */
	@SuppressWarnings("unchecked")
	public static BSTree<WordRecord> deserialize() {
		File repo = new File("repository.ser");
		if(!repo.exists()) {
			return new BSTree<>();
		}
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(repo))){
			return (BSTree<WordRecord>) ois.readObject();
		}catch(Exception e) {
			System.err.println("Error loading repository.ser :" + e);
			return new BSTree<>();
		}
	}
	
	/**
	 * saves tree to repository.ser file
	 * 
	 * @param tree BSTree of Wordrecord objects to be saved
	 * @return returns true if successful, false otherwise
	 * @throws IOException incase of an io error
	 */
	public static boolean serialize(BSTree<WordRecord> tree) throws IOException {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("repository.ser"))){
			oos.writeObject(tree);
			oos.close();
			return true;
		}catch(Exception e ) {
			System.err.print("ERROR SAVING YOUR FILE : " + e);
			return false;
		}

	}
}
