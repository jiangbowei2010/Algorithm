/*
 * Leetcode 200: https://leetcode.com/problems/number-of-islands/
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of
 * the grid are all surrounded by water.
 * 
 * Example 1:
 * 
 * 11110 11010 11000 00000 Answer: 1
 * 
 * Example 2:
 * 
 * 11000 11000 00100 00011 Answer: 3
 */

public class Number_of_Islands {
	
	int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	
	// Method 1: use Union Find
	public int numIslandsUF(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;
		int m = grid.length, n = grid[0].length;
		int[] nums = new int[m * n];
		int[] size = new int[m * n];
		nums[0] = -1;
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					int index = i * n + j;
					nums[index] = index;
					count++;
					if (i > 0 && grid[i - 1][j] == '1' && union(nums, size, index, index - n))
						count--;
					if (j > 0 && grid[i][j - 1] == '1' && union(nums, size, index, index - 1))
						count--;
				}
			}
		}
		return count;
	}

	private boolean union(int[] nums, int[] size, int i, int j) {
		while (i != nums[i])
			i = nums[i];
		while (j != nums[j])
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
    
	// Method 2: use DFS
    public int numIslandsDFS(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    dfs(grid, visited, i, j, m, n);
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] grid, boolean[][] visited, int i, int j, int m, int n) {
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n) continue;
            if (grid[x][y] != '1' || visited[x][y]) continue;
            dfs(grid, visited, x, y, m, n);
        }
    }
	
	public static void main(String[] args) {
		Number_of_Islands solution = new Number_of_Islands();
		char[][] grid= {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'0', '0', '1', '0', '0'}};
		System.out.println(solution.numIslandsUF(grid)); //2
		System.out.println(solution.numIslandsDFS(grid)); //2
		grid = new char[][]{{}};
		System.out.println(solution.numIslandsUF(grid)); //0
		System.out.println(solution.numIslandsDFS(grid)); //0
	}
}

