
/*
 * Leetcode 212: https://leetcode.com/problems/word-search-ii/
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once in a word.
 * 
 * For example, Given words = ["oath","pea","eat","rain"] and board =
 * 
 * [ ['o','a','a','n'], ['e','t','a','e'], ['i','h','k','r'], ['i','f','l','v']
 * ] Return ["eat","oath"]. Note: You may assume that all inputs are consist of
 * lowercase letters a-z.
 */
import java.util.*;

public class Word_Search_II {

	private class TrieNode {
		private boolean val = false;
		private TrieNode[] next = new TrieNode[26];
	}

	int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		if (board == null || board.length == 0 || board[0].length == 0)
			return res;
		if (words == null || words.length == 0)
			return res;
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
		int m = board.length, n = board[0].length;
		Set<String> set = new HashSet<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dfs(board, i, j, m, n, root, "", set);
			}
		}
		for (String s : set)
			res.add(s);
		return res;
	}

	private void dfs(char[][] board, int i, int j, int m, int n, TrieNode x, String path, Set<String> set) {
		int index = board[i][j] - 'a';
		if (x.next[index] == null)
			return;
		x = x.next[index];
		path = path + board[i][j];
		if (x.val)
			set.add(path);
		board[i][j] = '.';
		for (int[] dir : dirs) {
			int r = i + dir[0], c = j + dir[1];
			if (r < 0 || c < 0 || r >= m || c >= n || board[r][c] == '.')
				continue;
			dfs(board, r, c, m, n, x, path, set);
		}
		board[i][j] = (char) ('a' + index);
	}
	
	public static void main(String[] args) {
		Word_Search_II solution = new Word_Search_II();
		char[][] board = {
				  			{'o','a','a','n'},
				  			{'e','t','a','e'},
				  			{'i','h','k','r'},
				  			{'i','f','l','v'}
						};
		String[] words = {"oath","pea","eat","rain"};
		System.out.println(solution.findWords(board, words));
	}
}
