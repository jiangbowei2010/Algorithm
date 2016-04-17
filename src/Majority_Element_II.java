
/*
 * Leetcode 229: https://leetcode.com/problems/majority-element-ii/
 * Given an integer array of size n, find all elements that appear more than 
 * n/3  times. The algorithm should run in linear time and in O(1) space.
 */
import java.util.*;

public class Majority_Element_II {
	public List<Integer> majorityElement(int[] nums) {
		int num1 = 0, num2 = 0;
		int c1 = 0, c2 = 0;
		for (int num : nums) {
			if (c1 > 0 && num == num1)
				c1++;
			else if (c2 > 0 && num == num2)
				c2++;
			else if (c1 == 0) {
				num1 = num;
				c1++;
			} else if (c2 == 0) {
				num2 = num;
				c2++;
			} else {
				c1--;
				c2--;
			}
		}
		c1 = 0;
		c2 = 0;
		for (int num : nums) {
			if (num == num1)
				c1++;
			else if (num == num2)
				c2++;
		}
		List<Integer> res = new ArrayList<>();
		if (c1 * 3 > nums.length)
			res.add(num1);
		if (c2 * 3 > nums.length)
			res.add(num2);
		return res;
	}

	public static void main(String[] args) {
		int[] nums = { 2, 2, 3, 3, 2, 2, 4, 7, 2, 2, 3 };
		Majority_Element_II solution = new Majority_Element_II();
		System.out.println(solution.majorityElement(nums));
		nums = new int[] {};
		System.out.println(solution.majorityElement(nums));
	}
}
