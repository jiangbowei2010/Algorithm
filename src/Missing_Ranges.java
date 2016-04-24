
/*
 * Leetcode 163: https://leetcode.com/problems/missing-ranges/
 * Given a sorted integer array where the range of elements are [lower, upper]
 * inclusive, return its missing ranges.
 * 
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2",
 * "4->49", "51->74", "76->99"].
 */
import java.util.*;

public class Missing_Ranges {
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> res = new ArrayList<>();
		for (int num : nums) {
			if (lower > upper)
				break;
			int hi = Math.min(num - 1, upper);
			if (lower < hi)
				res.add(String.valueOf(lower) + "->" + String.valueOf(hi));
			else if (lower == hi)
				res.add(String.valueOf(lower));
			lower = Math.max(lower, num + 1);
		}
		if (lower < upper)
			res.add(String.valueOf(lower) + "->" + String.valueOf(upper));
		else if (lower == upper)
			res.add(String.valueOf(lower));
		return res;
	}

	public static void main(String[] args) {
		Missing_Ranges solution = new Missing_Ranges();
		int[] nums = { 0, 1, 3, 50, 75 };
		System.out.println(solution.findMissingRanges(nums, 0, 99));
	}
}
