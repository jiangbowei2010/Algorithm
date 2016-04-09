/*
 * Leetcode 320: https://leetcode.com/problems/generalized-abbreviation/
 * Write a function to generate the generalized abbreviations of a word.
 * 
 * Example: Given word = "word", return the following list (order does not
 * matter): ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2",
 * "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */
import java.util.*;

public class Generalized_Abbreviation {
	public List<String> generateAbbreviations(String word) {
		List<String> res = new ArrayList<>();
		dfs(word, "", res);
		return res;
	}

	private void dfs(String word, String path, List<String> res) {
		if (word.length() == 0)
			res.add(path);
		else {
			dfs(word.substring(1), path + word.charAt(0), res);
			for (int i = 0; i < word.length() - 1; i++) {
				dfs(word.substring(i + 2), path + String.valueOf(i + 1) + word.charAt(i + 1), res);
			}
			res.add(path + String.valueOf(word.length()));
		}
	}
	
	public static void main(String[] args) {
		Generalized_Abbreviation solution = new Generalized_Abbreviation();
		System.out.println(solution.generateAbbreviations("word"));
	}
}
