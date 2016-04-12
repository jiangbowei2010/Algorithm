
/*
 * Lintcode 205: http://www.lintcode.com/en/problem/interval-minimum-number/#
 * Given an integer array (index from 0 to n-1, where n is the size of this
 * array), and an query list. Each query has two integers [start, end]. For each
 * query, calculate the minimum number between index start and end in the given
 * array, return the result list.
 */
import java.util.*;

class Interval {
	int start, end;

	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

public class Interval_Minimum_Number {
	private class SegmentTreeNode {
		private int start, end;
		private int min;
		private SegmentTreeNode left, right;

		private SegmentTreeNode(int start, int end, int min) {
			this.start = start;
			this.end = end;
			this.min = min;
		}
	}

	public List<Integer> intervalMinNumber(int[] nums, List<Interval> queries) {

		SegmentTreeNode root = build(nums, 0, nums.length - 1);
		List<Integer> res = new ArrayList<>();
		for (Interval query : queries) {
			res.add(query(root, query.start, query.end));
		}
		return res;
	}

	private SegmentTreeNode build(int[] nums, int lo, int hi) {
		if (lo > hi)
			return null;
		if (lo == hi)
			return new SegmentTreeNode(lo, hi, nums[lo]);
		int mid = lo + (hi - lo) / 2;
		SegmentTreeNode left = build(nums, lo, mid);
		SegmentTreeNode right = build(nums, mid + 1, hi);
		SegmentTreeNode x = new SegmentTreeNode(lo, hi, Math.min(left.min, right.min));
		x.left = left;
		x.right = right;
		return x;
	}

	private int query(SegmentTreeNode x, int lo, int hi) {
		if (x == null || lo > x.end || hi < x.start || lo > hi)
			return Integer.MAX_VALUE;
		if (lo <= x.start && hi >= x.end)
			return x.min;
		int mid = x.start + (x.end - x.start) / 2;
		if (hi <= mid)
			return query(x.left, lo, hi);
		if (lo > mid)
			return query(x.right, lo, hi);
		return Math.min(query(x.left, lo, mid), query(x.right, mid + 1, hi));
	}
	
	public static void main(String[] args) {
		Interval_Minimum_Number solution = new Interval_Minimum_Number();
		int[] nums = {1, 2, 7, 8, 5};
		List<Interval> queries = new ArrayList<>();
		queries.add(new Interval(1, 2));
		queries.add(new Interval(0, 4));
		queries.add(new Interval(2, 4));
		System.out.println(solution.intervalMinNumber(nums, queries)); //2 1 5
	}
}
