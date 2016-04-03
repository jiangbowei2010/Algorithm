/*
 * http://www.geeksforgeeks.org/minimum-number-of-squares-whose-sum-equals-to-given-number-n/
 * A number can always be represented as a sum of squares of other numbers. Note
 * that 1 is a square and we can always break a number as (1*1 + 1*1 + 1*1 + …).
 * Given a number n, find the minimum number of squares that sum to X.
 * 
 * Examples:
 * 
 * Input: n = 100 Output: 1 100 can be written as 102. Note that 100 can also be
 * written as 52 + 52 + 52 + 52, but this representation requires 4 squares.
 * 
 * Input: n = 6 Output: 3
 */
public class Minimum_number_of_squares_with_sum_equals_n {
	public int numSquares(int n) {
		if (n <= 0)
			return 0;
		int[] dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = 1; j <= i / j; j++) {
				dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		Minimum_number_of_squares_with_sum_equals_n solution = new Minimum_number_of_squares_with_sum_equals_n();
		System.out.println(solution.numSquares(6));
		System.out.println(solution.numSquares(10));
	}
}
