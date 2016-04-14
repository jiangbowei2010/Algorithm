/*
 * http://www.geeksforgeeks.org/longest-prefix-matching-a-trie-based-solution-in-java/
 * 
 * Given a dictionary of words and an input string, find the longest prefix of
 * the string which is also a word in dictionary.
 * 
 * Examples:
 * 
 * Let the dictionary contains the following words: {are, area, base, cat,
 * cater, children, basement}
 * 
 * Below are some input/output examples: --------------------------------------
 * Input String Output -------------------------------------- caterer cater
 * basemexy base child < Empty >
 */
public class Longest_prefix_matching {
	private class TrieNode {
		private boolean val = false;
		private TrieNode[] next = new TrieNode[26];
	}

	public String longestPrefix(String[] words, String s) {
		if (words == null || words.length == 0 || s == null || s.length() == 0)
			return "";
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode x = root;
			for (int i = 0; i < word.length(); i++) {
				int index = word.charAt(i) - 'a';
				if (x.next[index] == null)
					x.next[index] = new TrieNode();
				x = x.next[index];
			}
			x.val = true;
		}
		int len = 0;
		TrieNode x = root;
		for (int i = 0; i < s.length() && x != null; i++) {
			if (x.val)
				len = i;
			int index = s.charAt(i) - 'a';
			x = x.next[index];
		}
		if (x != null && x.val)
			len = s.length();
		return s.substring(0, len);
	}

	public static void main(String[] args) {
		Longest_prefix_matching solution = new Longest_prefix_matching();
		String[] words = { "are", "area", "base", "cat", "cater", "children", "basement" };
		System.out.println(solution.longestPrefix(words, "caterer")); // cater
		System.out.println(solution.longestPrefix(words, "basemexy")); // base
		System.out.println(solution.longestPrefix(words, "child")); // blank
	}
}
