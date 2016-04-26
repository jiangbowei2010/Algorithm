/*
 * Lintcode 43: http://www.lintcode.com/en/problem/maximum-subarray-iii/#
 * 
 * Given an array of integers and a number k, find k non-overlapping subarrays
 * which have the largest sum.
 * 
 * The number in each subarray should be contiguous.
 * 
 * Return the largest sum.
 */
public class Maximum_Subarray_III {
	public int maxSubArray(int[] nums, int k) {
		int n = nums.length;
		if (k <= 0 || n < k)
			return 0;
		int[][] local = new int[k + 1][n + 1];
		int[][] global = new int[k + 1][n + 1];
		for (int i = 1; i <= k; i++) {
			for (int j = 1; j <= n; j++) {
				if (j == i) {
					local[i][j] = global[i - 1][j - 1] + nums[j - 1];
					global[i][j] = local[i][j];
				} else if (j > i) {
					local[i][j] = Math.max(global[i - 1][j - 1], local[i][j - 1]) + nums[j - 1];
					global[i][j] = Math.max(local[i][j], global[i][j - 1]);
				}
			}
		}
		return global[k][n];
	}
	
	public static void main(String[] args) {
		Maximum_Subarray_III solution = new Maximum_Subarray_III();
		int[] nums = {-1,4,-2,3,-2,3};
		System.out.println(solution.maxSubArray(nums, 2)); //8
	}
}
