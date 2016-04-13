/*
 * Leetcode 327 https://leetcode.com/problems/count-of-range-sum/
 * Given an integer array nums, return the number of range sums that lie in
 * [lower, upper] inclusive. Range sum S(i, j) is defined as the sum of the
 * elements in nums between indices i and j (i <= j), inclusive.
 * 
 * Note: A naive algorithm of O(n2) is trivial. You MUST do better than that.
 * 
 * Example: Given nums = [-2, 5, -1], lower = -2, upper = 2, Return 3. The three
 * ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 */
public class Count_of_Range_Sum {

	// Method 1: divide and conquer -- use merge sort O(nlogn)
	public int countRangeSum(int[] nums, int lower, int upper) {
		if (nums == null || nums.length == 0 || lower > upper)
			return 0;
		int n = nums.length;
		long[] sum = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			sum[i] = sum[i - 1] + nums[i - 1];
		}
		long[] cache = new long[n + 1];
		return mergeSort(sum, 0, n, cache, lower, upper);
	}

	private int mergeSort(long[] sum, int lo, int hi, long[] cache, int lower, int upper) {
		if (lo >= hi)
			return 0;
		int mid = lo + (hi - lo) / 2;
		int res = mergeSort(sum, lo, mid, cache, lower, upper) + mergeSort(sum, mid + 1, hi, cache, lower, upper);
		// [t, k) is the right half node that sum[right] - sum[i] meet
		// requirement
		int t = mid + 1, k = mid + 1;
		for (int i = lo; i <= mid; i++) {
			while (t <= hi && sum[t] < sum[i] + lower)
				t++;
			while (k <= hi && sum[k] <= sum[i] + upper)
				k++;
			res += k - t;
		}
		// merge
		int i = lo, j = mid + 1;
		k = lo;
		while (k <= hi) {
			if (i > mid)
				cache[k++] = sum[j++];
			else if (j > hi)
				cache[k++] = sum[i++];
			else if (sum[i] < sum[j])
				cache[k++] = sum[i++];
			else
				cache[k++] = sum[j++];
		}
		for (i = lo; i <= hi; i++)
			sum[i] = cache[i];
		return res;
	}

	// Method 2: Use tree(better to be balanced tree, other all positive array
	// will go to O(n2)
	private class TreeNode {
		private long val;
		private int size;
		private TreeNode left, right;

		public TreeNode(long val) {
			this.val = val;
			this.size = 1;
		}
	}

	public int countRangeSumTree(int[] nums, int lower, int upper) {
		if (nums == null || nums.length <= 0 || lower > upper)
			return 0;
		int n = nums.length;
		long[] sum = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			sum[i] = nums[i - 1] + sum[i - 1];
		}
		TreeNode root = null;
		int res = 0;
		for (int i = 0; i <= n; i++) {
			long lo = sum[i] - upper;
			long hi = sum[i] - lower;
			res += query(root, lo, hi);
			root = add(root, sum[i]);
		}
		return res;
	}

	private TreeNode add(TreeNode x, long val) {
		if (x == null)
			return new TreeNode(val);
		if (val > x.val)
			x.right = add(x.right, val);
		if (val < x.val)
			x.left = add(x.left, val);
		x.size++;
		return x;
	}

	private int query(TreeNode x, long lo, long hi) {
		if (x == null)
			return 0;
		if (lo > x.val)
			return query(x.right, lo, hi);
		if (hi < x.val)
			return query(x.left, lo, hi);
		return query(x.left, lo, hi) + query(x.right, lo, hi) + x.size - size(x.left) - size(x.right);
	}

	private int size(TreeNode x) {
		if (x == null)
			return 0;
		return x.size;
	}
	
	public static void main(String[] args) {
		Count_of_Range_Sum solution = new Count_of_Range_Sum();
		int[] nums = {-2, 5, -1};
		System.out.println(solution.countRangeSum(nums, -2, 2)); //3
		System.out.println(solution.countRangeSumTree(nums, -2, 2)); //3
	}
}
