
/*
 * Leetcode 164: https://leetcode.com/problems/maximum-gap/
 * Given an unsorted array, find the maximum difference between the successive
 * elements in its sorted form.
 * 
 * Try to solve it in linear time/space.
 * 
 * Return 0 if the array contains less than 2 elements.
 * 
 * You may assume all elements in the array are non-negative integers and fit in
 * the 32-bit signed integer range.
 */
import java.util.*;

public class Maximum_Gap {
	public int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2)
			return 0;
		int n = nums.length;
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for (int num : nums) {
			max = Math.max(max, num);
			min = Math.min(min, num);
		}
		int gap = (max - min) / (n - 1);
		if (gap * (n - 1) < max - min)
			gap++;
		if (gap == 0)
			return 0;
		int[] minBacket = new int[n];
		int[] maxBacket = new int[n];
		Arrays.fill(minBacket, Integer.MAX_VALUE);
		Arrays.fill(maxBacket, Integer.MIN_VALUE);
		for (int num : nums) {
			int index = (num - min) / gap;
			minBacket[index] = Math.min(minBacket[index], num);
			maxBacket[index] = Math.max(maxBacket[index], num);
		}
		int res = 0;
		int pre = maxBacket[0];
		for (int i = 1; i < n; i++) {
			if (minBacket[i] <= maxBacket[i]) {
				res = Math.max(res, minBacket[i] - pre);
				pre = maxBacket[i];
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 4, 9, 10, 3};
		Maximum_Gap solution = new Maximum_Gap();
		System.out.println(solution.maximumGap(nums));
	}
}
