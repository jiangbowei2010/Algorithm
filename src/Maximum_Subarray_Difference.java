/*
 * lintcode 45: http://www.lintcode.com/en/problem/maximum-subarray-difference/#
 * Given an array with integers.
 * Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the
 * largest.
 * 
 * Return the largest difference.
 * The subarray should contain at least one number
 * 
 * Example For [1, 2, -3, 1], return 6.
 */
public class Maximum_Subarray_Difference {
	public int maxDiffSubArrays(int[] nums) {
		if (nums == null || nums.length < 2)
			return 0;
		int n = nums.length;
		int[] minSum = new int[n];
		int[] maxSum = new int[n];
		int min = nums[0], max = nums[0];
		minSum[0] = nums[0];
		maxSum[0] = nums[0];
		for (int i = 1; i < n; i++) {
			min = Math.min(nums[i], min + nums[i]);
			max = Math.max(nums[i], max + nums[i]);
			minSum[i] = Math.min(min, minSum[i - 1]);
			maxSum[i] = Math.max(max, maxSum[i - 1]);
		}
		min = nums[n - 1];
		max = nums[n - 1];
		int globalMin = nums[n - 1], globalMax = nums[n - 1];
		int res = 0;
		for (int i = n - 2; i >= 0; i--) {
			res = Math.max(res, Math.max(Math.abs(globalMax - minSum[i]), Math.abs(globalMin - maxSum[i])));
			min = Math.min(nums[i], min + nums[i]);
			max = Math.max(nums[i], max + nums[i]);
			globalMin = Math.min(min, globalMin);
			globalMax = Math.max(max, globalMax);
		}
		return res;
	}
	
	public static void main(String[] args) {
		Maximum_Subarray_Difference solution = new Maximum_Subarray_Difference();
		int[] nums = {1, 2, -3, 1};
		System.out.println(solution.maxDiffSubArrays(nums));
	}
}
