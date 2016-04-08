
/*
 * Leetcode 139: https://leetcode.com/problems/word-break/\
 * 
 * Given a string s and a dictionary of words dict, determine if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 * 
 * For example, given s = "leetcode", dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 */
import java.util.*;

public class Word_Break {
	// Method 1, backtracking and market node visited, slightly faster than DP-- 6ms
	public boolean wordBreak(String s, Set<String> wordDict) {
		if (s == null || s.length() == 0)
			return true;
		int n = s.length();
		boolean[] visited = new boolean[n + 1];
		return dfs(s, 0, visited, wordDict);
	}

	private boolean dfs(String s, int d, boolean[] visited, Set<String> wordDict) {
		if (d == s.length())
			return true;
		visited[d] = true;
		for (int i = d; i < s.length(); i++) {
			if (!visited[i + 1] && wordDict.contains(s.substring(d, i + 1)) && dfs(s, i + 1, visited, wordDict))
				return true;
		}
		return false;
	}
	
	// Method 2, DP -- 12ms
    public boolean wordBreakDP(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0) return true;
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

	public static void main(String[] args) {
		Word_Break solution = new Word_Break();
		String s = "leetcode";
		Set<String> wordDict = new HashSet<>();
		wordDict.add("leet");
		wordDict.add("code");
		System.out.println(solution.wordBreak(s, wordDict));
		System.out.println(solution.wordBreakDP(s, wordDict));
	}
}
