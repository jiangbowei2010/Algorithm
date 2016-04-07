
/*
 * Leetcode 305 https://leetcode.com/problems/number-of-islands-ii/
 * 
 * A 2d grid map of m rows and n columns is initially filled with water. We may
 * perform an addLand operation which turns the water at position (row, col)
 * into a land. Given a list of positions to operate, count the number of
 * islands after each addLand operation. An island is surrounded by water and is
 * formed by connecting adjacent lands horizontally or vertically. You may
 * assume all four edges of the grid are all surrounded by water.
 * 
 * Example:
 * 
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]. Initially, the
 * 2d grid grid is filled with water. (Assume 0 represents water and 1
 * represents land).
 * 
 * 0 0 0 0 0 0 0 0 0 Operation #1: addLand(0, 0) turns the water at grid[0][0]
 * into a land.
 * 
 * 1 0 0 0 0 0 Number of islands = 1 0 0 0 Operation #2: addLand(0, 1) turns the
 * water at grid[0][1] into a land.
 * 
 * 1 1 0 0 0 0 Number of islands = 1 0 0 0 Operation #3: addLand(1, 2) turns the
 * water at grid[1][2] into a land.
 * 
 * 1 1 0 0 0 1 Number of islands = 2 0 0 0 Operation #4: addLand(2, 1) turns the
 * water at grid[2][1] into a land.
 * 
 * 1 1 0 0 0 1 Number of islands = 3 0 1 0 We return the result as an array: [1,
 * 1, 2, 3]
 */
import java.util.*;

public class Number_of_Islands_II {
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> res = new ArrayList<>();
		if (m <= 0 || n <= 0 || positions == null || positions.length == 0)
			return res;
		int[][] grid = new int[m][n];
		int[] nums = new int[m * n];
		// nums[0] = -1;
		int[] size = new int[m * n];
		int count = 0;
		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int[] position : positions) {
			if (grid[position[0]][position[1]] == 1)
				continue;
			grid[position[0]][position[1]] = 1;
			count++;
			int index = position[0] * n + position[1];
			nums[index] = index;
			for (int[] dir : dirs) {
				int x = position[0] + dir[0], y = position[1] + dir[1];
				if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != 1)
					continue;
				if (union(nums, size, index, x * n + y))
					count--;
			}
			res.add(count);
		}
		return res;
	}

	private boolean union(int[] nums, int[] size, int i, int j) {
		while (nums[i] != i)
			i = nums[i];
		while (nums[j] != j)
			j = nums[j];
		if (i == j)
			return false;
		if (size[i] > size[j]) {
			nums[j] = i;
			size[i] += size[j];
		} else {
			nums[i] = j;
			size[j] += size[i];
		}
		return true;
	}
	
	public static void main(String[] args) {
		Number_of_Islands_II solution = new Number_of_Islands_II();
		int[][] positions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}};
		System.out.println(solution.numIslands2(3, 3, positions)); //1 1 2 3
	}
}
