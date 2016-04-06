
/*
 * Leetcode 133: https://leetcode.com/problems/clone-graph/
 * Clone an undirected graph. Each node in the graph contains a label and a list
 * of its neighbors.
 * it works for both directed and un-directed Graph
 */

import java.util.*;

class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}

	void printGraph() {
		Set<UndirectedGraphNode> visited = new HashSet<>();
		dfs(this, visited);
	}

	void dfs(UndirectedGraphNode v, Set<UndirectedGraphNode> visited) {
		visited.add(v);
		for (UndirectedGraphNode w : v.neighbors) {
			System.out.println(String.valueOf(v.label) + " -> " + String.valueOf(w.label));
			if (!visited.contains(w))
				dfs(w, visited);
		}
	}
}

public class Clone_Graph {
	public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node) {
		if (node == null)
			return null;
		Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
		dfs(node, map);
		return map.get(node);
	}

	private void dfs(UndirectedGraphNode v, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
		map.put(v, new UndirectedGraphNode(v.label));
		for (UndirectedGraphNode w : v.neighbors) {
			if (!map.containsKey(w))
				dfs(w, map);
			map.get(v).neighbors.add(map.get(w));
		}
	}

	public UndirectedGraphNode cloneGraphBFS(UndirectedGraphNode node) {
		if (node == null)
			return null;
		Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
		Queue<UndirectedGraphNode> q = new LinkedList<>();
		map.put(node, new UndirectedGraphNode(node.label));
		q.offer(node);
		while (!q.isEmpty()) {
			UndirectedGraphNode v = q.poll();
			for (UndirectedGraphNode w : v.neighbors) {
				if (!map.containsKey(w)) {
					map.put(w, new UndirectedGraphNode(w.label));
					q.offer(w);
				}
				map.get(v).neighbors.add(map.get(w));
			}
		}
		return map.get(node);
	}

	public static void main(String[] args) {
		Clone_Graph solution = new Clone_Graph();
		UndirectedGraphNode s0 = new UndirectedGraphNode(0);
		UndirectedGraphNode s1 = new UndirectedGraphNode(1);
		UndirectedGraphNode s2 = new UndirectedGraphNode(2);
		UndirectedGraphNode s3 = new UndirectedGraphNode(3);
		s0.neighbors.add(s1);
		s0.neighbors.add(s2);
		s1.neighbors.add(s2);
		s2.neighbors.add(s0);
		s2.neighbors.add(s3);
		s3.neighbors.add(s3);
		UndirectedGraphNode clone1 = solution.cloneGraphBFS(s0);
		UndirectedGraphNode clone2 = solution.cloneGraphBFS(s0);
		s0.printGraph();
		System.out.println("");
		clone1.printGraph();
		System.out.println("");
		clone2.printGraph();
	}
}
