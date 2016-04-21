/*
 * Leetcode 4: https://leetcode.com/problems/median-of-two-sorted-arrays/
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)).
 */
public class Median_of_Two_Sorted_Arrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length;
		if (m + n == 0)
			return 0;
		int len = (m + n) / 2;
		int lo = 0, hi = Math.min(len, m);
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int t = len - mid;
			if (get(nums1, mid) >= get(nums2, t - 1)) {
				if (get(nums2, t) >= get(nums1, mid - 1)) {
					lo = mid;
					hi = mid - 1;
				} else
					hi = mid - 1;
			} else
				lo = mid + 1;
		}
		int len1 = lo, len2 = len - len1;
		if ((m + n) % 2 == 1)
			return Math.min(get(nums1, len1), get(nums2, len2));
		return (double) (Math.min(get(nums1, len1), get(nums2, len2))
				+ Math.max(get(nums1, len1 - 1), get(nums2, len2 - 1))) / 2;
	}

	private int get(int[] nums, int i) {
		if (i < 0)
			return Integer.MIN_VALUE;
		if (i >= nums.length)
			return Integer.MAX_VALUE;
		return nums[i];
	}
	
	public static void main(String[] args) {
		int[] nums1 = {1};
		int[] nums2 = {};
		Median_of_Two_Sorted_Arrays solution = new Median_of_Two_Sorted_Arrays();
		System.out.println(solution.findMedianSortedArrays(nums1, nums2));
	}
}
