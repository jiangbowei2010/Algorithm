/*
 * http://www.geeksforgeeks.org/find-length-of-the-longest-consecutive-path-in-a-character-matrix/
 * 
 * Given a matrix of characters. Find length of the longest path from a given
 * character, such that all characters in the path are consecutive to each
 * other, i.e., every character in path is next to previous in alphabetical
 * order. It is allowed to move in all 8 directions from a cell. Example
 * 
 * Input: mat[][] = { {a, c, d}, {h, b, e}, {i, g, f}} Starting Point = 'e'
 * 
 * Output: 5 If starting point is 'e', then longest path with consecutive
 * characters is "e f g h i".
 * 
 * Input: mat[R][C] = { {b, e, f}, {h, d, a}, {i, c, a}}; Starting Point = 'b'
 * 
 * Output: 1 'c' is not present in all adjacent cells of 'b'
 */

public class Longest_consecutive_path_from_a_given_starting_character {

	int[][] dirs = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	public int longestPath(char[][] matrix, char c) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int m = matrix.length, n = matrix[0].length;
		int[][] dp = new int[m][n];
		int res = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dfs(matrix, dp, i, j, m, n);
				if (matrix[i][j] == c)
					res = Math.max(res, dp[i][j]);
			}
		}
		return res;
	}

	private void dfs(char[][] matrix, int[][] dp, int i, int j, int m, int n) {
		if (dp[i][j] > 0)
			return;
		dp[i][j] = 1;
		for (int[] dir : dirs) {
			int x = i + dir[0], y = j + dir[1];
			if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j])
				continue;
			dfs(matrix, dp, x, y, m, n);
			dp[i][j] = Math.max(dp[i][j], 1 + dp[x][y]);
		}
	}

	public static void main(String[] args) {
		Longest_consecutive_path_from_a_given_starting_character solution = new Longest_consecutive_path_from_a_given_starting_character();
		char[][] matrix = { { 'a', 'c', 'd' }, { 'h', 'b', 'e' }, { 'i', 'g', 'f' } };
		System.out.println(solution.longestPath(matrix, 'e')); // 5
		System.out.println(solution.longestPath(matrix, 'a')); // 9
	}
}
