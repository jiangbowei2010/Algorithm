/*
 * Lint code 201/202/203
 * For an integer array (index from 0 to n-1, where n is the size of this
 * array), in the corresponding SegmentTree, each node stores an extra attribute
 * max to denote the maximum number in the interval of the array (index from
 * start to end).
 */
class SegmentTreeNode {
	public int start, end, max;
	public SegmentTreeNode left, right;

	public SegmentTreeNode(int start, int end, int max) {
		this.start = start;
		this.end = end;
		this.max = max;
		this.left = this.right = null;
	}
}

public class Segment_Tree_Build_Query_Modify {
	private SegmentTreeNode root = null;

	public void build(int[] arr) {
		root = build(arr, 0, arr.length - 1);
	}

	private SegmentTreeNode build(int[] arr, int lo, int hi) {
		if (lo > hi)
			return null;
		if (lo == hi)
			return new SegmentTreeNode(lo, hi, arr[lo]);
		int mid = lo + (hi - lo) / 2;
		SegmentTreeNode left = build(arr, lo, mid);
		SegmentTreeNode right = build(arr, mid + 1, hi);
		SegmentTreeNode x = new SegmentTreeNode(lo, hi, Math.max(left.max, right.max));
		x.left = left;
		x.right = right;
		return x;
	}
	
	public int query(int lo, int hi) {
		return query(root, lo, hi);
	}
	
	private int query(SegmentTreeNode x, int lo, int hi) {
		if (x == null || lo > x.end || hi < x.start || lo > hi) return Integer.MIN_VALUE;
		if (lo <= x.start && hi >= x.end) return x.max;
		int mid = x.start + (x.end - x.start) / 2;
		if (hi <= mid) return query(x.left, lo, hi);
		if (lo > mid) return query(x.right, lo, hi);
		return Math.max(query(x.left, lo, mid), query(x.right, mid + 1, hi));
		
	}
	
	public void modify(int index, int val) {
		modify(root, index, val);
	}
	
	private void modify(SegmentTreeNode x, int index, int val) {
		if (x == null || index > x.end || index < x.start) return;
		if (index == x.start && index == x.end) {
			x.max = val;
			return;
		}
		int mid = x.start + (x.end - x.start) / 2;
		if (index <= mid) modify(x.left, index, val);
		else modify(x.right, index, val);
		x.max = Math.max(x.left.max, x.right.max);
	}
	
	public static void main(String[] args) {
		Segment_Tree_Build_Query_Modify solution = new Segment_Tree_Build_Query_Modify();
		int[] nums = {1, 4, 2, 3};
		solution.build(nums);
		System.out.println(solution.query(1, 1)); //4
		System.out.println(solution.query(1, 2)); //4
		System.out.println(solution.query(2, 3)); //3
		System.out.println(solution.query(0, 2)); //4
		solution.modify(1, 10);
		System.out.println(solution.query(0, 2)); //10
	}
}
