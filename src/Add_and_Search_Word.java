/*
 * Leetcode 211 https://leetcode.com/problems/add-and-search-word-data-structure-design/
 * Design a data structure that supports the following two operations:
 * 
 * void addWord(word) bool search(word) search(word) can search a literal word
 * or a regular expression string containing only letters a-z or .. A . means it
 * can represent any one letter.
 * 
 * For example:
 * 
 * addWord("bad") addWord("dad") addWord("mad") search("pad") -> false
 * search("bad") -> true search(".ad") -> true search("b..") -> true Note: You
 * may assume that all words are consist of lowercase letters a-z.
 */
public class Add_and_Search_Word {

	private class TrieNode {
		private boolean val = false;
		private TrieNode[] next = new TrieNode[26];
	}

	private TrieNode root = new TrieNode();

	// Adds a word into the data structure.
	public void addWord(String word) {
		TrieNode x = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (x.next[index] == null)
				x.next[index] = new TrieNode();
			x = x.next[index];
		}
		x.val = true;
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		return search(root, word, 0);
	}

	private boolean search(TrieNode x, String word, int d) {
		if (x == null)
			return false;
		if (d == word.length())
			return x.val;
		if (word.charAt(d) != '.')
			return search(x.next[word.charAt(d) - 'a'], word, d + 1);
		for (int i = 0; i < 26; i++) {
			if (search(x.next[i], word, d + 1))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Add_and_Search_Word solution = new Add_and_Search_Word();
		solution.addWord("bad");
		solution.addWord("mad");
		solution.addWord("dad");
		System.out.println(solution.search("pad")); //false
		System.out.println(solution.search("mad")); //true
		System.out.println(solution.search(".ad")); //true
	}
}
