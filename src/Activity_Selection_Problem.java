
/*
 * http://www.geeksforgeeks.org/greedy-algorithms-set-1-activity-selection-problem/
 * You are given n activities with their start and finish times. Select the
 * maximum number of activities that can be performed by a single person,
 * assuming that a person can only work on a single activity at a time.
 */
import java.util.*;

public class Activity_Selection_Problem {

	private class IntComparator implements Comparator<Interval> {
		@Override
		public int compare(Interval a, Interval b) {
			return a.end - b.end;
		}
	}

	public int maxActivity(Interval[] intervals) {
		Arrays.sort(intervals, new IntComparator());
		List<Interval> res = new ArrayList<>();
		for (Interval interval : intervals) {
			if (res.size() == 0 || interval.start >= res.get(res.size() - 1).end)
				res.add(interval);
		}
		return res.size();
	}

	public static void main(String[] args) {
		Interval[] intervals = { new Interval(1, 2), new Interval(3, 4), new Interval(0, 6), new Interval(5, 7),
				new Interval(8, 9), new Interval(5, 9) };
		Activity_Selection_Problem solution = new Activity_Selection_Problem();
		System.out.println(solution.maxActivity(intervals));
	}

}
