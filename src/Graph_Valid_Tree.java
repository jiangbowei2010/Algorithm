/*
 * Leetcode 261: https://leetcode.com/problems/graph-valid-tree/
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to check whether these edges make
 * up a valid tree.
 * 
 * For example:
 * 
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * 
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return
 * false.
 */
import java.util.*;

public class Graph_Valid_Tree {
	
	//Method 1: Use DFS
    public boolean validTreeDFS(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) 
            adj[i] = new LinkedList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        boolean[] hasCycle = new boolean[1];
        dfs(adj, 0, -1, visited, hasCycle);
        if (hasCycle[0]) return false;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }
    
    private void dfs(List<Integer>[] adj, int v, int parent, boolean[] visited, boolean[] hasCycle) {
        visited[v] = true;
        for (int w : adj[v]) {
            if (hasCycle[0]) break;
            else if (!visited[w]) dfs(adj, w, v, visited, hasCycle);
            else if (w != parent) hasCycle[0] = true;
        }
    }
  
    //Method 2: use Union Find
    public boolean validTreeUF(int n, int[][] edges) {
        if (n <= 0) return true;
        int[] nums = new int[n];
        int[] size = new int[n];
        int count = n;
        for (int i = 0; i < n; i++) nums[i] = i;
        for (int[] edge : edges) {
            int i = edge[0], j = edge[1];
            while (nums[i] != i) i = nums[i];
            while (nums[j] != j) j = nums[j];
            if (i == j) return false;
            count--;
            if (size[i] > size[j]) {
                nums[j] = i;
                size[i] += size[j];
            }
            else {
                nums[i] = j;
                size[j] += size[i];
            }
        }
        return count == 1;
    }
    
    public static void main(String[] args) {
    	Graph_Valid_Tree solution = new Graph_Valid_Tree();
    	int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
    	System.out.println(solution.validTreeDFS(5, edges)); //true
    	System.out.println(solution.validTreeUF(5, edges)); //true
    	edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
    	System.out.println(solution.validTreeDFS(5, edges)); //false
    	System.out.println(solution.validTreeUF(5, edges)); //false
    }
}


