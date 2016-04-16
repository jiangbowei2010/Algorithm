/*
 * Given two arrays of length m and n with digits 0-9 representing two numbers.
 * Create the maximum number of length k <= m + n from digits of the two. The
 * relative order of the digits from the same array must be preserved. Return an
 * array of the k digits. You should try to optimize your time and space
 * complexity.
 * 
 * Example 1: nums1 = [3, 4, 6, 5] nums2 = [9, 1, 2, 5, 8, 3] k = 5 return [9,
 * 8, 6, 5, 3]
 * 
 * Example 2: nums1 = [6, 7] nums2 = [6, 0, 4] k = 5 return [6, 7, 6, 0, 4]
 * 
 * Example 3: nums1 = [3, 9] nums2 = [8, 9] k = 3 return [9, 8, 9]
 */
public class Create_Maximum_Number {
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int m = nums1.length, n = nums2.length;
		if (m + n < k)
			return new int[0];
		int[] res = new int[k];
		for (int len1 = 0; len1 <= m && len1 <= k; len1++) {
			int len2 = k - len1;
			if (len2 > n)
				continue;
			int[] arr1 = select(nums1, len1);
			int[] arr2 = select(nums2, len2);
			int[] arr = combine(arr1, arr2);
			if (compare(arr, 0, res, 0) > 0)
				res = arr;
		}
		return res;
	}

	private int[] select(int[] nums, int len) {
		int[] res = new int[len];
		int lo = 0;
		for (int i = 0; i < len; i++) {
			int hi = nums.length - len + i;
			res[i] = nums[lo];
			int index = lo;
			for (int k = lo + 1; k <= hi; k++) {
				if (nums[k] > res[i]) {
					res[i] = nums[k];
					index = k;
				}
			}
			lo = index + 1;
		}
		return res;
	}

	private int[] combine(int[] arr1, int[] arr2) {
		int m = arr1.length, n = arr2.length;
		int i = 0, j = 0, k = 0;
		int[] res = new int[m + n];
		while (k < m + n) {
			if (i == m)
				res[k++] = arr2[j++];
			else if (j == n)
				res[k++] = arr1[i++];
			else if (compare(arr1, i, arr2, j) > 0)
				res[k++] = arr1[i++];
			else
				res[k++] = arr2[j++];
		}
		return res;
	}

	private int compare(int[] arr1, int i, int[] arr2, int j) {
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j])
				return -1;
			else if (arr1[i] > arr2[j])
				return 1;
			i++;
			j++;
		}
		return i == arr1.length ? -1 : 1;
	}
	
	public static void main(String[] args) {
		Create_Maximum_Number solution = new Create_Maximum_Number();
		int[] nums1 = {3, 4, 6, 5};
		int[] nums2 = {9, 1, 2, 5, 8, 3};
		int[] res = solution.maxNumber(nums1, nums2, 5);
		for (int num : res) {
			System.out.print(num);
			System.out.print(" ");
		}
	}
}
