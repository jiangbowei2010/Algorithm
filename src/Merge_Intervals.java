
/*
 * Leetcode 56: https://leetcode.com/problems/merge-intervals/
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 */
import java.util.*;

public class Merge_Intervals {
	private class IntervalComparator implements Comparator<Interval> {
		@Override
		public int compare(Interval a, Interval b) {
			return a.start - b.start;
		}
	}

	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> res = new ArrayList<>();
		Collections.sort(intervals, new IntervalComparator());
		for (Interval interval : intervals) {
			if (res.size() == 0 || interval.start > res.get(res.size() - 1).end)
				res.add(new Interval(interval.start, interval.end));
			else
				res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, interval.end);
		}
		return res;
	}

	public static void main(String[] args) {
		Merge_Intervals solution = new Merge_Intervals();
		List<Interval> intervals = Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10),
				new Interval(15, 18));
		List<Interval> res = solution.merge(intervals);
		for (Interval i : res) {
			System.out.println(String.valueOf(i.start) + " " + String.valueOf(i.end));
		} 
	}
}
