/*
 * Leetcode 214: https://leetcode.com/problems/shortest-palindrome/
 * Given a string S, you are allowed to convert it to a palindrome by adding
 * characters in front of it. Find and return the shortest palindrome you can
 * find by performing this transformation.
 * 
 * For example:
 * 
 * Given "aacecaaa", return "aaacecaaa".
 * 
 * Given "abcd", return "dcbabcd".
 */

public class Shortest_Palindrome {
	public String shortestPalindrome(String s) {
		int n = s.length();
		if (n <= 1)
			return s;
		StringBuilder sb = new StringBuilder();
		String rev = sb.append(s).reverse().toString();
		int[] next = new int[n];
		next[0] = -1;
		int k = -1, j = 0;
		while (j < n - 1) {
			if (k == -1 || s.charAt(k) == s.charAt(j)) {
				k++;
				j++;
				next[j] = s.charAt(k) == s.charAt(j) ? next[k] : k;
			} else
				k = next[k];
		}
		j = 0;
		int i = 0;
		while (i + j < n) {
			if (j == -1 || rev.charAt(i) == s.charAt(j)) {
				i++;
				j++;
			} else
				j = next[j];
		}
		return rev.substring(0, i - j) + s;
	}
	public static void main(String[] args) {
		Shortest_Palindrome solution = new Shortest_Palindrome();
		System.out.println(solution.shortestPalindrome("aacecaaa"));
		System.out.println(solution.shortestPalindrome("abcd"));
	}
}
