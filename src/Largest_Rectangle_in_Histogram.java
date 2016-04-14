
/*
 * Leetcode 84 https://leetcode.com/problems/largest-rectangle-in-histogram/
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 */
import java.util.*;

public class Largest_Rectangle_in_Histogram {
	public int largestRectangleArea(int[] heights) {
		if (heights == null || heights.length == 0)
			return 0;
		Deque<Integer> stack = new LinkedList<>();
		int i = 0, res = 0;
		while (i < heights.length || !stack.isEmpty()) {
			if (i == heights.length || (!stack.isEmpty() && heights[stack.peek()] > heights[i])) {
				int h = heights[stack.pop()];
				int left = stack.isEmpty() ? 0 : stack.peek() + 1;
				res = Math.max(res, h * (i - left));
			} else
				stack.push(i++);
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] heights = {2, 1, 5, 6, 2, 3};
		Largest_Rectangle_in_Histogram solution = new Largest_Rectangle_in_Histogram();
		System.out.println(solution.largestRectangleArea(heights)); //10
	}
}
