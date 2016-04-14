
/*
 * Leetcode 239: https://leetcode.com/problems/sliding-window-maximum/
 * Given an array nums, there is a sliding window of size k which is moving from
 * the very left of the array to the very right. You can only see the k numbers
 * in the window. Each time the sliding window moves right by one position.
 * 
 * For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * 
 * Window position Max --------------- ----- [1 3 -1] -3 5 3 6 7 3 1 [3 -1 -3] 5
 * 3 6 7 3 1 3 [-1 -3 5] 3 6 7 5 1 3 -1 [-3 5 3] 6 7 5 1 3 -1 -3 [5 3 6] 7 6 1 3
 * -1 -3 5 [3 6 7] 7 Therefore, return the max sliding window as [3,3,5,5,6,7].
 */
import java.util.*;

public class Sliding_Window_Maximum {
	public int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length;
		if (n < k || k <= 0)
			return new int[0];
		Deque<Integer> q = new LinkedList<>();
		int[] res = new int[n - k + 1];
		for (int i = 0; i < n; i++) {
			while (!q.isEmpty() && nums[q.peekLast()] <= nums[i])
				q.pollLast();
			q.offer(i);
			if (q.peekFirst() == i - k)
				q.pollFirst();
			if (i >= k - 1)
				res[i - k + 1] = nums[q.peekFirst()];
		}
		return res;
	}

	public static void main(String[] args) {
		Sliding_Window_Maximum solution = new Sliding_Window_Maximum();
		int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int[] res = solution.maxSlidingWindow(nums, 3);
		for (int num : res) {
			System.out.print(num); // 3 3 5 5 6 7
			System.out.print(" ");
		}
	}
}
