/*
 * Leetcode 300: https://leetcode.com/problems/longest-increasing-subsequence/
 * Given an unsorted array of integers, find the length of longest increasing
 * subsequence.
 * 
 * For example, Given [10, 9, 2, 5, 3, 7, 101, 18], The longest increasing
 * subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may
 * be more than one LIS combination, it is only necessary for you to return the
 * length.
 * 
 * Your algorithm should run in O(n2) complexity.
 * 
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class Longest_Increasing_Subsequence {
	public int lengthOfLIS(int[] nums) {
		int[] arr = new int[nums.length];
		int len = 0;
		for (int num : nums) {
			if (len == 0 || num > arr[len - 1])
				arr[len++] = num;
			else {
				int lo = 0, hi = len - 1;
				while (lo <= hi) {
					int mid = lo + (hi - lo) / 2;
					if (arr[mid] < num)
						lo = mid + 1;
					else
						hi = mid - 1;
				}
				arr[lo] = num;
			}
		}
		return len;
	}
	
	public static void main(String[] args) {
		Longest_Increasing_Subsequence solution = new Longest_Increasing_Subsequence();
		int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
		System.out.println(solution.lengthOfLIS(nums));
	}
}
