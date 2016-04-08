/*
 * http://www.geeksforgeeks.org/backtracking-set-7-hamiltonian-cycle/
 * 
 * Hamiltonian Path in an undirected graph is a path that visits each vertex
 * exactly once. A Hamiltonian cycle (or Hamiltonian circuit) is a Hamiltonian
 * Path such that there is an edge (in graph) from the last vertex to the first
 * vertex of the Hamiltonian Path. Determine whether a given graph contains
 * Hamiltonian Cycle or not. If it contains, then print the path. Following are
 * the input and output of the required function.
 * 
 * Input: A 2D array graph[V][V] where V is the number of vertices in graph and
 * graph[V][V] is adjacency matrix representation of the graph. A value
 * graph[i][j] is 1 if there is a direct edge from i to j, otherwise graph[i][j]
 * is 0.
 * 
 * Output: An array path[V] that should contain the Hamiltonian Path. path[i]
 * should represent the ith vertex in the Hamiltonian Path. The code should also
 * return false if there is no Hamiltonian Cycle in the graph.
 * 
 * For example, a Hamiltonian Cycle in the following graph is {0, 1, 2, 4, 3,
 * 0}. There are more Hamiltonian Cycles in the graph like {0, 3, 4, 2, 1, 0}
 * 
 * (0)--(1)--(2) | / \ | | / \ | | / \ | (3)-------(4) And the following graph
 * doesn’t contain any Hamiltonian Cycle.
 * 
 * (0)--(1)--(2) | / \ | | / \ | | / \ | (3) (4)
 */
import java.util.*;

public class Hamiltonian_Cycle {
	List<Integer> findCycle(boolean[][] graph) {
		List<Integer> res = new ArrayList<>();
		if (graph == null || graph.length == 0) return res;
		int n = graph.length;
		boolean[] visited = new boolean[n];
		dfs(graph, 0, n, visited, res);
		return res;
	}
	
	private boolean dfs(boolean[][] graph, int v, int numLeft, boolean[] visited, List<Integer> res) {
		res.add(v);
		visited[v] = true;
		numLeft--;
		if (numLeft == 0 && graph[v][0]) {
			res.add(0);
			return true;
		}
		for (int w = 0; w < graph.length; w++) {
			if (graph[w][v] && !visited[w] && dfs(graph, w, numLeft, visited, res)) return true;
		}
		visited[v] = false;
		res.remove(res.size() - 1);
		return false;
	}
	
	public static void main(String[] args) {
		Hamiltonian_Cycle solution = new Hamiltonian_Cycle();
		boolean[][] graph = {{false, true, false, true, false}
							, {true, false, true, true, true}
							, {false, true, false, false, true}
							, {true, true, false, false, true}
							, {false, true, true, true, false}};
		System.out.println(solution.findCycle(graph)); // 0 1 2 4 3 0
	}
}
