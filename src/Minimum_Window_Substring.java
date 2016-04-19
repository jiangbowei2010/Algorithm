/*
 * Leetcode 76: https://leetcode.com/problems/minimum-window-substring/
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * For example, S = "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
 * 
 * Note: If there is no such window in S that covers all characters in T, return
 * the empty string "".
 * 
 * If there are multiple such windows, you are guaranteed that there will always
 * be only one unique minimum window in S.
 */
public class Minimum_Window_Substring {
	public String minWindow(String s, String t) {
		int[] count = new int[256];
		int num = 0;
		for (int i = 0; i < t.length(); i++) {
			if (count[t.charAt(i)]-- == 0)
				num++;
		}
		int lo = 0, index = -1, len = Integer.MAX_VALUE;
		for (int j = 0; j < s.length(); j++) {
			if (++count[s.charAt(j)] == 0)
				num--;
			if (num == 0) {
				while (count[s.charAt(lo)] > 0)
					count[s.charAt(lo++)]--;
				if (j - lo + 1 < len) {
					len = j - lo + 1;
					index = lo;
				}
			}
		}
		if (index == -1)
			return "";
		return s.substring(index, index + len);
	}

	public static void main(String[] args) {
		Minimum_Window_Substring solution = new Minimum_Window_Substring();
		System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
	}
}
