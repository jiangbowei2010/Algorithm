/*
 * Leetcode 188: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most k
 * transactions.
 * 
 * Note: You may not engage in multiple transactions at the same time (ie, you
 * must sell the stock before you buy again).
 */
public class Best_Time_to_Buy_and_Sell_Stock_IV {
	public int maxProfit(int k, int[] prices) {
		if (k <= 0 || prices == null || prices.length == 0)
			return 0;
		int n = prices.length;
		if (k > n / 2)
			return fastSolve(prices);
		int[][] local = new int[k + 1][n];
		int[][] global = new int[k + 1][n];
		for (int i = 1; i <= k; i++) {
			for (int j = 1; j < n; j++) {
				local[i][j] = Math.max(local[i][j - 1] + prices[j] - prices[j - 1], global[i - 1][j - 1]);
				global[i][j] = Math.max(local[i][j], global[i][j - 1]);
			}
		}
		return global[k][n - 1];
	}

	private int fastSolve(int[] prices) {
		int res = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1])
				res += prices[i] - prices[i - 1];
		}
		return res;
	}
	
	public static void main(String[] args) {
		Best_Time_to_Buy_and_Sell_Stock_IV solution = new Best_Time_to_Buy_and_Sell_Stock_IV();
		int[] prices = {3, 2, 7, 9, 5, 3, 17, 3};
		System.out.println(solution.maxProfit(2, prices));
	}
}
