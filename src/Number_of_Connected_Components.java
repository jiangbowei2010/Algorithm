/*
 * Leetcode 323 https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to find the number of connected
 * components in an undirected graph.
 * 
 * Example 1: 0 3 | | 1 --- 2 4 Given n = 5 and edges = [[0, 1], [1, 2], [3,
 * 4]], return 2.
 * 
 * Example 2: 0 4 | | 1 --- 2 --- 3 Given n = 5 and edges = [[0, 1], [1, 2], [2,
 * 3], [3, 4]], return 1.
 */

public class Number_of_Connected_Components {
	public int countComponents(int n, int[][] edges) {
		if (n <= 0)
			return 0;
		int[] nums = new int[n];
		int[] size = new int[n];
		int count = n;
		if (edges == null || edges.length == 0)
			return n;
		for (int i = 0; i < n; i++)
			nums[i] = i;
		for (int[] edge : edges) {
			int i = edge[0], j = edge[1];
			while (nums[i] != i)
				i = nums[i];
			while (nums[j] != j)
				j = nums[j];
			if (i == j)
				continue;
			count--;
			if (size[i] > size[j]) {
				nums[j] = i;
				size[i] += size[j];
			} else {
				nums[i] = j;
				size[j] += size[i];
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Number_of_Connected_Components solution = new Number_of_Connected_Components();
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 3, 4 } };
		System.out.println(solution.countComponents(5, edges)); // 2
		edges = new int[][] {};
		System.out.println(solution.countComponents(1, edges)); // 1
	}
}
