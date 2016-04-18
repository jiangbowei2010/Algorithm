
/*
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms
 * required.
 * 
 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 */

import java.util.*;

public class Meeting_Rooms_II {
	public int minMeetingRooms(Interval[] intervals) {
		int n = intervals.length;
		int[] start = new int[n];
		int[] end = new int[n];
		for (int i = 0; i < n; i++) {
			start[i] = intervals[i].start;
			end[i] = intervals[i].end;
		}
		Arrays.sort(start);
		Arrays.sort(end);
		int j = 0, res = 0;
		for (int i = 0; i < n; i++) {
			while (end[j] <= start[i])
				j++;
			res = Math.max(res, i - j + 1);
		}
		return res;
	}

	public static void main(String[] args) {
		Meeting_Rooms_II solution = new Meeting_Rooms_II();
		Interval[] intervals = { new Interval(0, 30), new Interval(5, 10), new Interval(15, 20) };
		System.out.println(solution.minMeetingRooms(intervals));
	}
}
