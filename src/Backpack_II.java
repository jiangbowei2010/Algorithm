/*
 * Lintcode 125: http://www.lintcode.com/en/problem/backpack-ii/#
 * Given n items with size Ai and value Vi, and a backpack with size m. What's
 * the maximum value can you put into the backpack? Example Given 4 items with
 * size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The
 * maximum value is 9.
 */
public class Backpack_II {
	public int backPackII(int m, int[] A, int V[]) {
		int n = A.length;
		if (m <= 0 || n <= 0)
			return 0;
		int[][] dp = new int[m + 1][n + 1];
		for (int j = 1; j <= n; j++) {
			for (int i = 1; i <= m; i++) {
				dp[i][j] = dp[i][j - 1];
				if (i >= A[j - 1]) {
					int val = dp[i - A[j - 1]][j - 1] + V[j - 1];
					if (val > dp[i][j - 1])
						dp[i][j] = val;
				}
			}
		}
		return dp[m][n];
	}
	
	public static void main(String[] args) {
		int[] A = {2, 3, 5, 7};
		int[] V = {1, 5, 2, 4};
		Backpack_II solution = new Backpack_II();
		System.out.println(solution.backPackII(10, A, V)); //9
	}
}
