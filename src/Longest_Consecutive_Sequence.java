/*
 * Leetcode 128: https://leetcode.com/problems/longest-consecutive-sequence/
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * 
 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive elements
 * sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 */
import java.util.*;

public class Longest_Consecutive_Sequence {
	public int longestConsecutive(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>(); // num: len
		int res = 0;
		for (int num : nums) {
			if (map.containsKey(num))
				continue;
			map.put(num, 1);
			int left = map.containsKey(num - 1) ? map.get(num - 1) : 0;
			int right = map.containsKey(num + 1) ? map.get(num + 1) : 0;
			int len = left + right + 1;
			res = Math.max(res, len);
			map.put(num - left, len);
			map.put(num + right, len);
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] nums = {100, 4, 200, 1, 3, 2};
		Longest_Consecutive_Sequence solution = new Longest_Consecutive_Sequence();
		System.out.println(solution.longestConsecutive(nums));
	}
}
