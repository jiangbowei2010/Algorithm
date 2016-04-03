/*
 * http://www.geeksforgeeks.org/shortest-common-supersequence/
 * Given two strings str1 and str2, find the shortest string that has both str1
 * and str2 as subsequences.
 * 
 * Examples:
 * Input: str1 = "geek", str2 = "eke" Output: "geeke"
 * Input: str1 = "AGGTAB", str2 = "GXTXAYB" Output: "AGXGTXAYB"
 */
public class Shortest_Common_Supersequence {
	public String shortestSub(String str1, String str2) {
		int m = str1.length(), n = str2.length();
		String[] dp = new String[n + 1]; // optimized to 1D
		for (int i = 0; i <= m; i++) {
			String pre = "";
			for (int j = 0; j <= n; j++) {
				String temp = dp[j];
				if (i == 0)
					dp[j] = str2.substring(0, j);
				else if (j == 0)
					dp[j] = str1.substring(0, i);
				else if (str1.charAt(i - 1) == str2.charAt(j - 1))
					dp[j] = pre + str1.charAt(i - 1);
				else
					dp[j] = dp[j].length() < dp[j - 1].length() ? dp[j] + str1.charAt(i - 1)
							: dp[j - 1] + str2.charAt(j - 1);
				pre = temp;
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		Shortest_Common_Supersequence solution = new Shortest_Common_Supersequence();
		System.out.println(solution.shortestSub("geek", "eke")); // "geeke"
		System.out.println(solution.shortestSub("AGGTAB", "GXTXAYB")); // "AGXGTXAYB"
	}
}
