/*
 * Leetcode 336: https://leetcode.com/problems/palindrome-pairs/
 * Given a list of unique words. Find all pairs of distinct indices (i, j) in
 * the given list, so that the concatenation of the two words, i.e. words[i] +
 * words[j] is a palindrome.
 * 
 * Example 1: Given words = ["bat", "tab", "cat"] Return [[0, 1], [1, 0]] The
 * palindromes are ["battab", "tabbat"] Example 2: Given words = ["abcd",
 * "dcba", "lls", "s", "sssll"] Return [[0, 1], [1, 0], [3, 2], [2, 4]] The
 * palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
import java.util.*;

public class Palindrome_Pairs {

	private class TrieNode {
		private int val = -1;
		private TrieNode[] next = new TrieNode[26];
	}

	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> res = new ArrayList<>();
		if (words == null || words.length == 0)
			return res;
		TrieNode root = new TrieNode();
		for (int i = 0; i < words.length; i++) {
			TrieNode x = root;
			for (int k = words[i].length() - 1; k >= 0; k--) {
				int index = words[i].charAt(k) - 'a';
				if (x.next[index] == null)
					x.next[index] = new TrieNode();
				x = x.next[index];
			}
			x.val = i;
		}
		for (int i = 0; i < words.length; i++) {
			TrieNode x = root;
			for (int k = 0; k < words[i].length() && x != null; k++) {
				if (x.val >= 0 && i != x.val && isPalindrome(words[i].substring(k)))
					res.add(Arrays.asList(i, x.val));
				int index = words[i].charAt(k) - 'a';
				x = x.next[index];
			}
			if (x == null)
				continue;
			dfs(x, "", i, res);
		}
		return res;
	}

	private void dfs(TrieNode x, String path, int i, List<List<Integer>> res) {
		if (x == null)
			return;
		if (x.val >= 0 && i != x.val && isPalindrome(path))
			res.add(Arrays.asList(i, x.val));
		for (int k = 0; k < 26; k++) {
			if (x.next[k] != null)
				dfs(x.next[k], path + (char) ('a' + k), i, res);
		}
	}

	private boolean isPalindrome(String word) {
		int i = 0, j = word.length() - 1;
		while (i < j) {
			if (word.charAt(i++) != word.charAt(j--))
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Palindrome_Pairs solution = new Palindrome_Pairs();
		String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
		System.out.println(solution.palindromePairs(words));
	}
}

