/*
 * Leetcode 57: https://leetcode.com/problems/insert-interval/
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their
 * start times.
 * 
 * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as
 * [1,5],[6,9].
 * 
 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in
 * as [1,2],[3,10],[12,16].
 * 
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
import java.util.*;

public class Insert_Interval {
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> res = new ArrayList<>();
		int i = 0;
		while (i < intervals.size() && intervals.get(i).end < newInterval.start)
			res.add(intervals.get(i++));
		while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
			newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
			newInterval.end = Math.max(newInterval.end, intervals.get(i++).end);
		}
		res.add(newInterval);
		while (i < intervals.size())
			res.add(intervals.get(i++));
		return res;
	}
	
	public static void main(String[] args) {
		List<Interval> intervals = Arrays.asList(new Interval(1, 3), new Interval(6, 9));
		Interval newInterval = new Interval(2, 5);
		Insert_Interval solution = new Insert_Interval();
		List<Interval> res = solution.insert(intervals, newInterval);
		for (Interval i : res) {
			System.out.println(String.valueOf(i.start) + " " + String.valueOf(i.end));
		} 		
	}
}
