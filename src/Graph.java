
/*
 * http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
 * http://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/
 */
import java.util.*;

public class Graph {
	private int V;
	private List<Integer>[] adj;

	public Graph(int V) {
		this.V = V;
		this.adj = new List[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
	}

	public List<Integer> bfs(int s) {
		List<Integer> res = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		q.offer(s);
		boolean[] visited = new boolean[V];
		visited[s] = true;
		while (!q.isEmpty()) {
			int v = q.poll();
			res.add(v);
			for (int w : adj[v]) {
				if (!visited[w]) {
					visited[w] = true;
					q.offer(w);
				}
			}
		}
		return res;
	}

	public List<Integer> dfs(int s) {
		boolean[] visited = new boolean[V];
		List<Integer> res = new ArrayList<>();
		dfs(s, visited, res);
		return res;
	}

	private void dfs(int v, boolean[] visited, List<Integer> res) {
		visited[v] = true;
		res.add(v);
		for (int w : adj[v]) {
			if (!visited[w])
				dfs(w, visited, res);
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		List<Integer> bfs = g.bfs(2);
		System.out.println(bfs); // 2 0 3 1
		List<Integer> dfs = g.dfs(2);
		System.out.println(dfs); // 2 0 1 3
	}
}
