/*
 * Leetcode 329: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * Given an integer matrix, find the length of the longest increasing path.
 * 
 * From each cell, you can either move to four directions: left, right, up or
 * down. You may NOT move diagonally or move outside of the boundary (i.e.
 * wrap-around is not allowed).
 * 
 * Example 1:
 * 
 * nums = [ [9,9,4], [6,6,8], [2,1,1] ] Return 4 The longest increasing path is
 * [1, 2, 6, 9].
 * 
 * Example 2:
 * 
 * nums = [ [3,4,5], [3,2,6], [2,2,1] ] Return 4 The longest increasing path is
 * [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class Longest_Increasing_Path_in_a_Matrix {
	int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public int longestIncreasingPath(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int m = matrix.length, n = matrix[0].length;
		int res = 0;
		int[][] dp = new int[m][n]; // memory max length of the cell
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dfs(matrix, dp, i, j, m, n);
				res = Math.max(res, dp[i][j]);
			}
		}
		return res;
	}

	private void dfs(int[][] matrix, int[][] dp, int i, int j, int m, int n) {
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
		Longest_Increasing_Path_in_a_Matrix solution = new Longest_Increasing_Path_in_a_Matrix();
		int[][] nums = {
				  {9,9,4},
				  {6,6,8},
				  {2,1,1}
				};
		System.out.println(solution.longestIncreasingPath(nums));
	}
}
