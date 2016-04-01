/*
 * http://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem Given
 * a set of non-negative integers, and a value sum, determine if there is a
 * subset of the given set with sum equal to given sum. Examples: set[] =
 * {3, 34, 4, 12, 5, 2}, sum = 9 Output: True //There is a subset (4, 5)
 * with sum 9.
 */
public class Subset_Sum_Problem {
	public boolean existSum(int[] nums, int sum) {
		int n = nums.length;
		boolean[][] dp = new boolean[n + 1][sum + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= sum; j++) {
				if (j == 0)
					dp[i][j] = true;
				else if (i == 0)
					dp[i][j] = false;
				else
					dp[i][j] = dp[i - 1][j] || (j - nums[i - 1] >= 0 && dp[i - 1][j - nums[i - 1]]);
			}
		}
		return dp[n][sum];
	}

	public static void main(String[] args) {
		Subset_Sum_Problem solution = new Subset_Sum_Problem();

		int[] nums = { 3, 34, 4, 12, 5, 2 };
		int sum = 9;
		System.out.println(solution.existSum(nums, sum));

		sum = 1;
		System.out.println(solution.existSum(nums, sum));
	}
}
