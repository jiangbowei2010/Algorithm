
/*
 * http://www.geeksforgeeks.org/weighted-job-scheduling/
 * Given N jobs where every job is represented by following three elements of
 * it.
 * 
 * Start Time Finish Time Profit or Value Associated Find the maximum profit
 * subset of jobs such that no two jobs in the subset overlap.
 * 
 * Example:
 * Input: Number of Jobs n = 4 Job Details {Start Time, Finish Time, Profit} Job
 * 1: {1, 2, 50} Job 2: {3, 5, 20} Job 3: {6, 19, 100} Job 4: {2, 100, 200}
 * Output: The maximum profit is 250. We can get the maximum profit by
 * scheduling jobs 1 and 4. Note that there is longer schedules possible Jobs 1,
 * 2 and 3 but the profit with this schedule is 20+50+100 which is less than
 * 250.
 */
import java.util.*;

class Job {
	int start, end;
	int val;

	public Job(int start, int end, int val) {
		this.start = start;
		this.end = end;
		this.val = val;
	}
}

public class Weighted_Job_Scheduling {

	private class JobComparator implements Comparator<Job> {
		@Override
		public int compare(Job a, Job b) {
			return a.end - b.end;
		}
	}

	public int maxProfit(Job[] jobs) {
		if (jobs == null || jobs.length == 0)
			return 0;
		Arrays.sort(jobs, new JobComparator());
		int n = jobs.length;
		int[] select = new int[n];
		int[] notSel = new int[n];
		select[0] = jobs[0].val;
		for (int i = 1; i < n; i++) {
			notSel[i] = Math.max(select[i - 1], notSel[i - 1]);
			int lo = 0, hi = i - 1;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (jobs[mid].end <= jobs[i].start)
					lo = mid + 1;
				else
					hi = mid - 1;
			}
			select[i] = jobs[i].val;
			if (hi >= 0) {
				select[i] += Math.max(select[hi], notSel[hi]);
			}
		}
		return Math.max(select[n - 1], notSel[n - 1]);
	}

	public static void main(String[] args) {
		Weighted_Job_Scheduling solution = new Weighted_Job_Scheduling();
		Job[] jobs = new Job[4];
		jobs[0] = new Job(1, 2, 50);
		jobs[1] = new Job(3, 5, 20);
		jobs[2] = new Job(6, 19, 100);
		jobs[3] = new Job(2, 100, 200);
		System.out.println(solution.maxProfit(jobs)); // 250
	}
}
