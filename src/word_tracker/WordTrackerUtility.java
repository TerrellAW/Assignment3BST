package word_tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * utility class with static string format methods
 * 
 * @author Youssif Al-Halawche
 */
public class WordTrackerUtility {

	/*
	 * format for -pf word: file1, file2
	 * 
	 * @author Youssif Al-Halawche
	 */
	public static String formatPF(WordRecord r) {
		StringBuilder sb = new StringBuilder();
		sb.append(r.getWord()).append(": ");

		HashMap<String, ArrayList<Integer>> references = r.getReferences();
		ArrayList<String> files = new ArrayList<>(references.keySet());
		Collections.sort(files);

		for (int i = 0; i < files.size(); i++) {
			sb.append(files.get(i));
			if (i < files.size() - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	/**
	 * Formats output for the -pl argument words are returned as files containing
	 * the lines where the word happened for example {@code file1(line1,line2)}
	 * 
	 * @param r WordRecord object containing the word itself and its meta data
	 * @return formatted String
	 * 
	 * @author Youssif Al-Halawche
	 */
	public static String formatPL(WordRecord r) {
		StringBuilder sb = new StringBuilder();
		sb.append(r.getWord()).append(": ");

		HashMap<String, ArrayList<Integer>> refs = r.getReferences();
		ArrayList<String> files = new ArrayList<>(refs.keySet());
		Collections.sort(files);

		for (int i = 0; i < files.size(); i++) {
			String file = files.get(i);
			ArrayList<Integer> lines = refs.get(file);
			Collections.sort(lines);

			sb.append(file).append("(");
			for (int j = 0; j < lines.size(); j++) {
				sb.append(lines.get(j));
				if (j < lines.size() - 1) {
					sb.append(", ");
				}
			}
			sb.append(")");

			if (i < files.size() - 1) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}

	/**
	 * Formats output for the -po arguemtn
	 * 
	 * words are organizes as files containing the lines where the word happened
	 * then a count= an integer of how many occurrancies the word had
	 * 
	 * @param r wordrecord and its metadata
	 * @return formatted string
	 * 
	 * @author Youssif Al-Halawche
	 */
	public static String formatPO(WordRecord r) {
		StringBuilder sb = new StringBuilder();
		sb.append(r.getWord()).append(": ");

		HashMap<String, ArrayList<Integer>> refs = r.getReferences();
		ArrayList<String> files = new ArrayList<>(refs.keySet());
		Collections.sort(files);

		int totalCount = 0;

		for (int i = 0; i < files.size(); i++) {
			String file = files.get(i);
			ArrayList<Integer> lines = refs.get(file);
			Collections.sort(lines);

			int fileCount = lines.size();
			totalCount += fileCount;

			sb.append(file).append("(");
			for (int j = 0; j < lines.size(); j++) {
				sb.append(lines.get(j));
				if (j < lines.size() - 1) {
					sb.append(", ");
				}
			}
			sb.append(")").append(" [count=").append(fileCount).append("]");

			if (i < files.size() - 1) {
				sb.append(", ");
			}
		}

		sb.append(" (total=").append(totalCount).append(")");

		return sb.toString();
	}
}
