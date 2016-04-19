/*
 * Leetcode 28: https://leetcode.com/problems/implement-strstr/
 * 
 * Implement strStr().
 * 
 * Returns the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 */

public class Implement_strStr {

	// Method 1: use KMP
	public int strStr(String haystack, String needle) {
		int n = haystack.length(), m = needle.length();
		if (m == 0)
			return 0;
		if (n == 0)
			return -1;
		int[] next = new int[m];
		next[0] = -1;
		int k = -1, j = 0;
		while (j < m - 1) {
			if (k == -1 || needle.charAt(j) == needle.charAt(k)) {
				k++;
				j++;
				next[j] = needle.charAt(j) == needle.charAt(k) ? next[k] : k;
			} else
				k = next[k];
		}
		j = 0;
		int i = 0;
		while (i < n && j < m) {
			if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
				i++;
				j++;
			} else
				j = next[j];
		}
		if (j == m)
			return i - j;
		return -1;
	}

	// Method 2: brute force
	public int strStrII(String haystack, String needle) {
		int n = haystack.length(), m = needle.length();
		int i = 0, j = 0;
		while (i < n && j < m) {
			if (haystack.charAt(i) == needle.charAt(j)) {
				i++;
				j++;
			} else {
				i = i - j + 1;
				j = 0;
			}
		}
		if (j == m)
			return i - j;
		return -1;
	}

	public static void main(String[] args) {
		Implement_strStr solution = new Implement_strStr();
		System.out.println(solution.strStr("", "")); // 0
		System.out.println(solution.strStr("dfdsabcfdf", "abc")); // 4
		System.out.println(solution.strStrII("", "")); // 0
		System.out.println(solution.strStrII("dfdsabcfdf", "abc")); // 4
	}
}
