/*
 * Leetcode 33: https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 */
public class Search_in_Rotated_Sorted_Array {
	public int search(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (nums[mid] == target)
				return mid;
			if (nums[lo] <= nums[hi]) {
				if (target < nums[lo] || target > nums[hi])
					return -1;
				if (target > nums[mid])
					lo = mid + 1;
				else
					hi = mid - 1;
			} else {
				if (nums[mid] >= nums[lo]) {
					if (target > nums[mid] || target < nums[lo])
						lo = mid + 1;
					else
						hi = mid - 1;
				} else {
					if (target > nums[mid] && target < nums[lo])
						lo = mid + 1;
					else
						hi = mid - 1;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] nums = {4, 5, 6, 7, 0, 1, 2};
		Search_in_Rotated_Sorted_Array solution = new Search_in_Rotated_Sorted_Array();
		System.out.println(solution.search(nums, 1));
		System.out.println(solution.search(nums, 10));
	}
}
