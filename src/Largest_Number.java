
/*
 * Leetcode 179: https://leetcode.com/problems/largest-number/
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 * 
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * Note: The result may be very large, so you need to return a string instead of
 * an integer.
 */
import java.util.*;

public class Largest_Number {
	private class StrComparator implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
			return (a + b).compareTo(b + a);
		}
	}

	public String largestNumber(int[] nums) {
		if (nums == null || nums.length == 0)
			return "";
		String[] strs = new String[nums.length];
		for (int i = 0; i < nums.length; i++)
			strs[i] = String.valueOf(nums[i]);
		Arrays.sort(strs, new StrComparator());
		StringBuilder sb = new StringBuilder();
		for (int i = nums.length - 1; i >= 0; i--)
			sb.append(strs[i]);
		int i = 0;
		while (i < sb.length() - 1 && sb.charAt(i) == '0')
			i++;
		return sb.substring(i);
	}

	public static void main(String[] args) {
		int[] nums = {3, 30, 34, 5, 9};
		Largest_Number solution = new Largest_Number();
		System.out.println(solution.largestNumber(nums)); //9534330
	}
}
