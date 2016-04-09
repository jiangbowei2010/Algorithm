
/*
 * Leetcode 126: https://leetcode.com/problems/word-ladder-ii/
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * all shortest transformation sequence(s) from beginWord to endWord, such that:
 * 
 * Only one letter can be changed at a time Each intermediate word must exist in
 * the word list For example,
 * 
 * Given: beginWord = "hit" endWord = "cog" wordList =
 * ["hot","dot","dog","lot","log"] Return [ ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"] ] Note: All words have the same length. All
 * words contain only lowercase alphabetic characters
 */
import java.util.*;

public class Word_Ladder_II {
	public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		Map<String, List<String>> map = new HashMap<>();
		Set<String> visited = new HashSet<>();
		set1.add(beginWord);
		set2.add(endWord);
		visited.add(beginWord);
		visited.add(endWord);
		boolean dir = true, found = false;
		while (!set1.isEmpty() && !set2.isEmpty()) {
			if (set1.size() > set2.size()) {
				Set<String> temp = set1;
				set1 = set2;
				set2 = temp;
				dir = !dir;
			}
			Set<String> nextSet = new HashSet<>();
			for (String word : set1) {
				char[] c = word.toCharArray();
				for (int i = 0; i < c.length; i++) {
					char org = c[i];
					for (char r = 'a'; r <= 'z'; r++) {
						c[i] = r;
						String s = new String(c);
						String key = dir ? word : s;
						String val = dir ? s : word;
						if (set2.contains(s)) {
							found = true;
							if (!map.containsKey(key))
								map.put(key, new LinkedList<String>());
							map.get(key).add(val);
						} else if (!found && !visited.contains(s) && wordList.contains(s)) {
							if (!map.containsKey(key))
								map.put(key, new LinkedList<String>());
							map.get(key).add(val);
							nextSet.add(s);
						}
					}
					c[i] = org;
				}
			}
			visited.addAll(nextSet);
			set1 = nextSet;
			if (found)
				break;
		}
		List<String> path = new ArrayList<>();
		List<List<String>> res = new ArrayList<>();
		dfs(map, beginWord, endWord, path, res);
		return res;
	}

	private void dfs(Map<String, List<String>> map, String word, String endWord, List<String> path,
			List<List<String>> res) {
		path.add(word);
		if (word.equals(endWord))
			res.add(new ArrayList<String>(path));
		else if (map.containsKey(word)) {
			for (String s : map.get(word))
				dfs(map, s, endWord, path, res);
		}
		path.remove(path.size() - 1);
	}

	public static void main(String[] args) {
		Word_Ladder_II solution = new Word_Ladder_II();
		Set<String> wordList = new HashSet<>();
		String[] strs = { "hot", "dot", "dog", "lot", "log" };
		for (String word : strs)
			wordList.add(word);
		System.out.println(solution.findLadders("hit", "cog", wordList));
	}
}
