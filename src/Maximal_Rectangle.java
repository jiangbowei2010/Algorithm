/*
 * Leetcode 85: https://leetcode.com/problems/maximal-rectangle/
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing all ones and return its area.
 */
import java.util.*;

public class Maximal_Rectangle {
	public int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int m = matrix.length, n = matrix[0].length, res = 0;
		int[] left = new int[n];
		int[] right = new int[n];
		int[] height = new int[n];
		Arrays.fill(right, n - 1);
		for (int i = 0; i < m; i++) {
			int currLeft = 0;
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == '0') {
					height[j] = 0;
					left[j] = 0;
					currLeft = j + 1;
				} else {
					height[j] += 1;
					left[j] = Math.max(left[j], currLeft);
				}
			}
			int currRight = n - 1;
			for (int j = n - 1; j >= 0; j--) {
				if (matrix[i][j] == '0') {
					right[j] = n - 1;
					currRight = j - 1;
				} else
					right[j] = Math.min(right[j], currRight);
				res = Math.max(res, (right[j] - left[j] + 1) * height[j]);
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		Maximal_Rectangle solution = new Maximal_Rectangle();
		char[][] matrix = {{'0', '1', '1'}, {'1', '1', '0'}};
		System.out.println(solution.maximalRectangle(matrix));//2
	}
}
