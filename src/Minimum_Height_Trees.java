
/*
 * Leetcode 301: https://leetcode.com/problems/minimum-height-trees/
 * 
 * For a undirected graph with tree characteristics, we can choose any node as
 * the root. The result graph is then a rooted tree. Among all possible rooted
 * trees, those with minimum height are called minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a list
 * of their root labels.
 * 
 * Format The graph contains n nodes which are labeled from 0 to n - 1. You will
 * be given the number n and a list of undirected edges (each edge is a pair of
 * labels).
 * 
 * You can assume that no duplicate edges will appear in edges. Since all edges
 * are undirected, [0, 1] is the same as [1, 0] and thus will not appear
 * together in edges.
 * 
 * Example 1:
 * 
 * Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 * 
 * 0 | 1 / \ 2 3 return [1]
 * 
 * Example 2:
 * 
 * Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 * 
 * 0 1 2 \ | / 3 | 4 | 5 return [3, 4]
 */
import java.util.*;

public class Minimum_Height_Trees {

	// Build the graph, BFS from the ourside where the leaf only have one degree
	// after removing the leaf, add new leaf (one degree by removing node of pre-level)
	// when only one or two node left, find the answer

	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		Set<Integer>[] adj = new Set[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new HashSet<>();
		}
		for (int[] edge : edges) {
			adj[edge[0]].add(edge[1]);
			adj[edge[1]].add(edge[0]);
		}
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (adj[i].size() == 1)
				list.add(i);
		}
		int num = n;
		while (num > 2) {
			num -= list.size();
			List<Integer> nextList = new ArrayList<>();
			for (int v : list) {
				for (int w : adj[v]) {
					adj[w].remove(v);
					if (adj[w].size() == 1)
						nextList.add(w);
				}
			}
			list = nextList;
		}
		if (n == 1)
			list.add(0);
		return list;
	}
	
	public static void main(String[] args) {
		Minimum_Height_Trees solution = new Minimum_Height_Trees();
		int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
		System.out.println(solution.findMinHeightTrees(4, edges)); //1
		edges = new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
		System.out.println(solution.findMinHeightTrees(6, edges)); //3 4
		edges = new int[][]{};
		System.out.println(solution.findMinHeightTrees(0, edges)); //[]
		System.out.println(solution.findMinHeightTrees(1, edges)); //0
	}
}
