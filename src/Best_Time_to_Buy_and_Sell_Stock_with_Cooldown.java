/*
 * Leetcode 309: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times) with the following restrictions:
 * 
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again). After you sell your stock, you cannot
 * buy stock on next day. (ie, cooldown 1 day) Example:
 * 
 * prices = [1, 2, 3, 0, 2] maxProfit = 3 transactions = [buy, sell, cooldown,
 * buy, sell]
 */
public class Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {
	public int maxProfit(int[] prices) {
		int local = 0, global = 0, preGlobal = 0;
		for (int i = 1; i < prices.length; i++) {
			local = Math.max(local + prices[i] - prices[i - 1], preGlobal);
			preGlobal = global;
			global = Math.max(global, local);
		}
		return global;
	}
	
	public static void main(String[] args) {
		int[] prices = {1, 2, 3, 0, 2};
		Best_Time_to_Buy_and_Sell_Stock_with_Cooldown solution = new Best_Time_to_Buy_and_Sell_Stock_with_Cooldown();
		System.out.println(solution.maxProfit(prices)); //3
	}
}
