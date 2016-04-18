/*
 * Leetcode 41: https://leetcode.com/problems/first-missing-positive/
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 * 
 * Your algorithm should run in O(n) time and uses constant space.
 */
public class First_Missing_Positive {
	public int firstMissingPositive(int[] nums) {
		if (nums == null || nums.length == 0)
			return 1;
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			while (nums[i] != i + 1 && nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i])
				swap(nums, i, nums[i] - 1);
		}
		for (int i = 0; i < n; i++) {
			if (nums[i] != i + 1)
				return i + 1;
		}
		return n + 1;
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		First_Missing_Positive solution = new First_Missing_Positive();
		int[] nums = { 3, 4, -1, 1 };
		System.out.println(solution.firstMissingPositive(nums)); // 2
	}
}
