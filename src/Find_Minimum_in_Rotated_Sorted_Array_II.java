/*
 * Leetcode 154: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * The array may contain duplicates
 */
public class Find_Minimum_in_Rotated_Sorted_Array_II {
	public int findMin(int[] nums) {
		int lo = 0, hi = nums.length - 1;
		while (lo < hi) {
			if (nums[lo] < nums[hi])
				return nums[lo];
			else if (nums[lo] > nums[hi]) {
				int mid = lo + (hi - lo) / 2;
				if (nums[mid] >= nums[lo])
					lo = mid + 1;
				else
					hi = mid;
			} else
				lo++;
		}
		return nums[lo];
	}
	
	public static void main(String[] args) {
		Find_Minimum_in_Rotated_Sorted_Array_II solution = new Find_Minimum_in_Rotated_Sorted_Array_II();
		int[] nums = {3, 1, 1};
		System.out.println(solution.findMin(nums)); //1
		nums = new int[]{1};
		System.out.println(solution.findMin(nums)); //1
	}
}
