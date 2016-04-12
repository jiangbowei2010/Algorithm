public class Range_Sum_Query_Mutable_SegmentTree {

	private class SegmentTreeNode {
		int start, end;
		int sum;
		SegmentTreeNode left, right;

		public SegmentTreeNode(int start, int end, int sum) {
			this.start = start;
			this.end = end;
			this.sum = sum;
		}
	}

	private SegmentTreeNode root = null;

	public Range_Sum_Query_Mutable_SegmentTree(int[] nums) {
		root = build(nums, 0, nums.length - 1);
	}

	private SegmentTreeNode build(int[] nums, int lo, int hi) {
		if (lo > hi)
			return null;
		if (lo == hi)
			return new SegmentTreeNode(lo, hi, nums[lo]);
		int mid = lo + (hi - lo) / 2;
		SegmentTreeNode left = build(nums, lo, mid);
		SegmentTreeNode right = build(nums, mid + 1, hi);
		SegmentTreeNode x = new SegmentTreeNode(lo, hi, left.sum + right.sum);
		x.left = left;
		x.right = right;
		return x;
	}

	void update(int i, int val) {
		update(root, i, val);
	}

	private void update(SegmentTreeNode x, int i, int val) {
		if (x == null || i < x.start || i > x.end)
			return;
		if (i == x.start && i == x.end) {
			x.sum = val;
			return;
		}
		int mid = x.start + (x.end - x.start) / 2;
		if (i <= mid)
			update(x.left, i, val);
		else
			update(x.right, i, val);
		x.sum = x.left.sum + x.right.sum;
	}

	public int sumRange(int i, int j) {
		return sumRange(root, i, j);
	}

	private int sumRange(SegmentTreeNode x, int i, int j) {
		if (x == null || j < x.start || i > x.end || i > j)
			return 0;
		if (i <= x.start && j >= x.end)
			return x.sum;
		int mid = x.start + (x.end - x.start) / 2;
		if (j <= mid)
			return sumRange(x.left, i, j);
		if (i > mid)
			return sumRange(x.right, i, j);
		return sumRange(x.left, i, mid) + sumRange(x.right, mid + 1, j);
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, 5 };
		Range_Sum_Query_Mutable_SegmentTree solution = new Range_Sum_Query_Mutable_SegmentTree(nums);
		System.out.println(solution.sumRange(0, 2)); // 9
		solution.update(1, 2);
		System.out.println(solution.sumRange(0, 2)); // 8
	}
}
