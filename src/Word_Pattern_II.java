
/*
 * Leetcode 291: https://leetcode.com/problems/word-pattern-ii/
 * Given a pattern and a string str, find if str follows the same pattern.
 * 
 * Here follow means a full match, such that there is a bijection between a
 * letter in pattern and a non-empty substring in str.
 * 
 * Examples: pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true. pattern = "aabb",
 * str = "xyzabcxzyabc" should return false. Notes: You may assume both pattern
 * and str contains only lowercase letters.
 */
import java.util.*;

public class Word_Pattern_II {
	public boolean wordPatternMatch(String pattern, String str) {
		if (pattern.length() > str.length())
			return false;
		Map<Character, String> map = new HashMap<>();
		Set<String> set = new HashSet<>();
		return dfs(pattern, str, map, set);
	}

	private boolean dfs(String pattern, String str, Map<Character, String> map, Set<String> set) {
		int m = pattern.length(), n = str.length();
		if (m == 0 && n == 0)
			return true;
		if (m == 0 || n == 0 || m > n)
			return false;
		char c = pattern.charAt(0);
		if (map.containsKey(c)) {
			String s = map.get(c);
			int len = s.length();
			if (len > n)
				return false;
			if (!str.substring(0, len).equals(s))
				return false;
			return dfs(pattern.substring(1), str.substring(len), map, set);
		}
		String nextPat = pattern.substring(1);
		for (int i = 0; i < n; i++) {
			String s = str.substring(0, i + 1);
			if (set.contains(s))
				continue;
			map.put(c, s);
			set.add(s);
			if (dfs(nextPat, str.substring(i + 1), map, set))
				return true;
			set.remove(s);
			map.remove(c);
		}
		return false;
	}
	
	public static void main(String[] args) {
		Word_Pattern_II solution = new Word_Pattern_II();
		System.out.println(solution.wordPatternMatch("abab", "redblueredblue")); //true
		System.out.println(solution.wordPatternMatch("aabb", "xyzabcxzyabc")); //false
		System.out.println(solution.wordPatternMatch("aabb", "")); //false
		
	}
}
