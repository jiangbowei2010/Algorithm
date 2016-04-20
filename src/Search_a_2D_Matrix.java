/*
 * Leetcode 74: https://leetcode.com/problems/search-a-2d-matrix/
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right. The first integer of each
 * row is greater than the last integer of the previous row. For example,
 * 
 * Consider the following matrix:
 * 
 * [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] Given target = 3, return
 * true.
 */
public class Search_a_2D_Matrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;
		int m = matrix.length, n = matrix[0].length;
		int lo = 0, hi = m - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (matrix[mid][0] == target)
				return true;
			if (matrix[mid][0] < target)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		if (hi == -1)
			return false;
		int row = hi;
		lo = 0;
		hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (matrix[row][mid] == target)
				return true;
			if (matrix[row][mid] < target)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50} };
		Search_a_2D_Matrix solution = new Search_a_2D_Matrix();
		System.out.println(solution.searchMatrix(matrix, 3)); //true
		System.out.println(solution.searchMatrix(matrix, 4)); //false
	}
}
