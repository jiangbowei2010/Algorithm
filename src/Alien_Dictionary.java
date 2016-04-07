/*
 * Leetcode 269 https://leetcode.com/problems/alien-dictionary/
 * There is a new alien language which uses the latin alphabet. However, the
 * order among letters are unknown to you. You receive a list of words from the
 * dictionary, where words are sorted lexicographically by the rules of this new
 * language. Derive the order of letters in this language.
 * 
 * For example, Given the following words in dictionary,
 * 
 * [ "wrt", "wrf", "er", "ett", "rftt" ] The correct order is: "wertf".
 */
import java.util.*;

public class Alien_Dictionary {
	public String alienOrder(String[] words) {
		Map<Character, Set<Character>> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			for (int k = 0; k < words[i].length(); k++) {
				if (!map.containsKey(words[i].charAt(k))) {
					map.put(words[i].charAt(k), new HashSet<>());
				}
			}
			if (i > 0) {
				for (int k = 0; k < words[i - 1].length() && k < words[i].length(); k++) {
					if (words[i - 1].charAt(k) != words[i].charAt(k)) {
						map.get(words[i - 1].charAt(k)).add(words[i].charAt(k));
						break;
					}
				}
			}
		}
		Set<Character> visited = new HashSet<>();
		Set<Character> onStack = new HashSet<>();
		boolean[] hasCycle = new boolean[1];
		StringBuilder sb = new StringBuilder();
		for (char v : map.keySet()) {
			if (hasCycle[0])
				return "";
			if (!visited.contains(v))
				dfs(map, v, visited, onStack, hasCycle, sb);
		}
		return sb.reverse().toString();
	}

	private void dfs(Map<Character, Set<Character>> map, char v, Set<Character> visited, Set<Character> onStack,
			boolean[] hasCycle, StringBuilder sb) {
		visited.add(v);
		onStack.add(v);
		for (char w : map.get(v)) {
			if (hasCycle[0])
				break;
			else if (!visited.contains(w))
				dfs(map, w, visited, onStack, hasCycle, sb);
			else if (onStack.contains(w))
				hasCycle[0] = true;
		}
		onStack.remove(v);
		sb.append(v);
	}
	
	public static void main(String[] args) {
		Alien_Dictionary solution = new Alien_Dictionary();
		String[] words = {"wrt","wrf","er","ett","rftt"};
		System.out.println(solution.alienOrder(words)); //wertf
		words = new String[]{};
		System.out.println(solution.alienOrder(words)); //""
		words = new String[]{"wrt","wrf","er","ett","rftt", "rt"};
		System.out.println(solution.alienOrder(words)); //""
	}
}
