/*
 * Leetcode 259: https://leetcode.com/problems/3sum-smaller/
 * Given an array of n integers nums and a target, find the number of index
 * triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] +
 * nums[j] + nums[k] < target.
 * 
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * 
 * Return 2. Because there are two triplets which sums are less than 2:
 * 
 * [-2, 0, 1] [-2, 0, 3]
 */
import java.util.*;

public class Three_Sum_Smaller {
	public int threeSumSmaller(int[] nums, int target) {
		if (nums == null || nums.length < 3)
			return 0;
		Arrays.sort(nums);
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			int lo = i + 1, hi = nums.length - 1;
			while (lo < hi) {
				int sum = nums[i] + nums[lo] + nums[hi];
				if (sum < target) {
					res += hi - lo;
					lo++;
				} else
					hi--;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		Three_Sum_Smaller solution = new Three_Sum_Smaller();
		int[] nums = {-2, 0, 1, 3};
		System.out.println(solution.threeSumSmaller(nums, 2)); //2
	}
}
