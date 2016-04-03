/*
 * http://www.geeksforgeeks.org/collect-maximum-points-in-a-grid-using-two-traversals/
 * 
 * Given a matrix where every cell represents points. How to collect maximum
 * points using two traversals under following conditions?
 * 
 * Let the dimensions of given grid be R x C.
 * 
 * 1) The first traversal starts from top left corner, i.e., (0, 0) and should
 * reach left bottom corner, i.e., (R-1, 0). The second traversal starts from
 * top right corner, i.e., (0, C-1) and should reach bottom right corner, i.e.,
 * (R-1, C-1)/
 * 
 * 2) From a point (i, j), we can move to (i+1, j+1) or (i+1, j-1) or (i+1, j)
 * 
 * 3) A traversal gets all points of a particular cell through which it passes.
 * If one traversal has already collected points of a cell, then the other
 * traversal gets no points if goes through that cell again.
 * 
 * Input : int arr[R][C] = {{3, 6, 8, 2}, {5, 2, 4, 3}, {1, 1, 20, 10}, {1, 1,
 * 20, 10}, {1, 1, 20, 10}, };
 * 
 * Output: 73
 */
public class Maximum_points_in_a_grid_using_two_traversals {
	public int maxPoints(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int m = matrix.length, n = matrix[0].length;
		// dp[row][left][right] left,right are 2 traversal end point
		int[][][] dp = new int[m][n][n]; // can be optimized to 2-D
		for (int row = 0; row < m; row++) {
			for (int left = 0; left < n; left++) {
				for (int right = 0; right < n; right++) {
					if (row == 0) {
						if (left == 0 && right == n - 1) {
							dp[row][left][right] = left == right ? matrix[row][left]
									: matrix[row][left] + matrix[row][right];
						}
					} else {
						for (int i = left - 1; i <= left + 1; i++) {
							for (int j = right - 1; j <= right + 1; j++) {
								if (i < 0 || i >= n || j < 0 || j >= n)
									continue;
								dp[row][left][right] = Math.max(dp[row][left][right], dp[row - 1][i][j]);
							}
						}
						dp[row][left][right] += left == right ? matrix[row][left]
								: matrix[row][left] + matrix[row][right];
					}
				}
			}
		}
		return dp[m - 1][0][n - 1];
	}

	public static void main(String[] args) {
		Maximum_points_in_a_grid_using_two_traversals solution = new Maximum_points_in_a_grid_using_two_traversals();
		int[][] matrix = { { 3, 6, 8, 2 }, { 5, 2, 4, 3 }, { 1, 1, 20, 10 }, { 1, 1, 20, 10 }, { 1, 1, 20, 10 } };
		System.out.println(solution.maxPoints(matrix));
	}
}
