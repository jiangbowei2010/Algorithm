
/*
 * Leetcode 228: https://leetcode.com/problems/summary-ranges/
 * Given a sorted integer array without duplicates, return the summary of its
 * ranges.
 * 
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
import java.util.*;

public class Summary_Ranges {
	public List<String> summaryRanges(int[] nums) {
		List<String> res = new ArrayList<>();
		int i = 0;
		while (i < nums.length) {
			int j = i;
			while (j + 1 < nums.length && nums[j + 1] == nums[j] + 1)
				j++;
			if (i == j)
				res.add(String.valueOf(nums[i]));
			else
				res.add(String.valueOf(nums[i]) + "->" + String.valueOf(nums[j]));
			i = j + 1;
		}
		return res;
	}

	public static void main(String[] args) {
		Summary_Ranges solution = new Summary_Ranges();
		int[] nums = { 0, 1, 2, 4, 5, 7 };
		System.out.println(solution.summaryRanges(nums));
	}
}
