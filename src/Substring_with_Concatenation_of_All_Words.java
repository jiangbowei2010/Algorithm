
/*
 * Leetcode 30: https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 * You are given a string, s, and a list of words, words, that are all of the
 * same length. Find all starting indices of substring(s) in s that is a
 * concatenation of each word in words exactly once and without any intervening
 * characters.
 * 
 * For example, given: s: "barfoothefoobarman" words: ["foo", "bar"]
 * 
 * You should return the indices: [0,9]. (order does not matter).
 */
import java.util.*;

public class Substring_with_Concatenation_of_All_Words {
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		if (words == null || words.length == 0)
			return res;
		int n = s.length(), m = words.length, len = words[0].length();
		Map<String, Integer> mapCount = new HashMap<>();
		int numWords = 0;
		for (String word : words) {
			if (mapCount.containsKey(word))
				mapCount.put(word, mapCount.get(word) - 1);
			else {
				mapCount.put(word, -1);
				numWords++;
			}
		}
		for (int i = 0; i < len; i++) {
			if (i + m * len > n)
				break;
			Map<String, Integer> map = new HashMap<>(mapCount);
			int num = numWords, lo = i;
			for (int j = i; j <= n - len; j += len) {
				String word = s.substring(j, j + len);
				if (!map.containsKey(word))
					map.put(word, 1);
				else
					map.put(word, map.get(word) + 1);
				if (map.get(word) == 1)
					num++;
				else if (map.get(word) == 0)
					num--;
				if (j - lo == m * len) {
					String preWord = s.substring(lo, lo + len);
					map.put(preWord, map.get(preWord) - 1);
					lo += len;
					if (map.get(preWord) == 0)
						num--;
					else if (map.get(preWord) == -1)
						num++;
				}
				if (num == 0)
					res.add(lo);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Substring_with_Concatenation_of_All_Words solution = new Substring_with_Concatenation_of_All_Words();
		String[] words = { "foo", "bar" };
		String s = "barfoothefoobarman";
		System.out.println(solution.findSubstring(s, words));
	}
}
