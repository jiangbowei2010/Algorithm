/*
 * Leetcode 332: https://leetcode.com/problems/reconstruct-itinerary/
 * Given a list of airline tickets represented by pairs of departure and arrival
 * airports [from, to], reconstruct the itinerary in order. All of the tickets
 * belong to a man who departs from JFK. Thus, the itinerary must begin with
 * JFK.
 * 
 * Note: If there are multiple valid itineraries, you should return the
 * itinerary that has the smallest lexical order when read as a single string.
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
 * ["JFK", "LGB"]. All airports are represented by three capital letters (IATA
 * code). You may assume all tickets form at least one valid itinerary. Example
 * 1: tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"]. Example 2: tickets =
 * [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"]. Another possible reconstruction
 * is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 */
import java.util.*;

public class Reconstruct_Itinerary {
	public List<String> findItinerary(String[][] tickets) {
		Map<String, Queue<String>> map = new HashMap<>();
		for (String[] ticket : tickets) {
			if (!map.containsKey(ticket[0]))
				map.put(ticket[0], new PriorityQueue<>());
			map.get(ticket[0]).offer(ticket[1]);
		}
		LinkedList<String> res = new LinkedList<>();
		dfs(map, "JFK", res);
		return res;
	}

	private void dfs(Map<String, Queue<String>> map, String s, LinkedList<String> res) {
		if (map.containsKey(s)) {
			while (!map.get(s).isEmpty()) {
				dfs(map, map.get(s).poll(), res);
			}
		}
		res.offerFirst(s);
	}
	
	public static void main(String[] args) {
		Reconstruct_Itinerary solution = new Reconstruct_Itinerary();
		String[][] tickets = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
		System.out.println(solution.findItinerary(tickets));//["JFK", "MUC", "LHR", "SFO", "SJC"].
		tickets = new String[][]{{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		System.out.println(solution.findItinerary(tickets));//["JFK","ATL","JFK","SFO","ATL","SFO"].
	}
}
