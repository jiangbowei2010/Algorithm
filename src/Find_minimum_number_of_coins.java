/*
 * http://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
 * 
 * Given a value V, if we want to make change for V cents, and we have infinite
 * supply of each of C = { C1, C2, .. , Cm} valued coins, what is the minimum
 * number of coins to make the change?
 * 
 * Examples:
 * 
 * Input: coins[] = {25, 10, 5}, V = 30 Output: Minimum 2 coins required We can
 * use one coin of 25 cents and one of 5 cents
 * 
 * Input: coins[] = {9, 6, 5, 1}, V = 11 Output: Minimum 2 coins required We can
 * use one coin of 6 cents and 1 coin of 5 cents
 */
public class Find_minimum_number_of_coins {
	public int minNumCoins(int[] coins, int val) {
		if (val < 0)
			return -1;
		int n = coins.length;
		int[][] dp = new int[n + 1][val + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= val; j++) {
				if (j == 0)
					dp[i][j] = 0;
				else if (i == 0)
					dp[i][j] = -1;
				else {
					dp[i][j] = dp[i - 1][j];
					if (j >= coins[i - 1] && dp[i - 1][j - coins[i - 1]] >= 0) {
						if (dp[i][j] == -1 || 1 + dp[i - 1][j - coins[i - 1]] < dp[i][j])
							dp[i][j] = 1 + dp[i - 1][j - coins[i - 1]];
					}
				}
			}
		}
		return dp[n][val];
	}

	public static void main(String[] args) {
		Find_minimum_number_of_coins solution = new Find_minimum_number_of_coins();
		int[] coins = { 9, 6, 5, 2 };
		System.out.println(solution.minNumCoins(coins, 11)); // 2
		System.out.println(solution.minNumCoins(coins, 1)); // -1
		coins = new int[] { 9, 6, 5, 5, 5, 2 };
		System.out.println(solution.minNumCoins(coins, 11)); // 2
	}
}
