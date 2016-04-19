/*
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * Examples:
 * 
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
 * answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class Longest_Substring_Without_Repeating_Characters {
    public int lengthOfLongestSubstring(String s) {
        int[] count = new int[256];
        int lo = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (++count[s.charAt(i)] == 2) {
                while (--count[s.charAt(lo++)] == 0);
            }
            res = Math.max(res, i - lo + 1);
        }
        return res;
    }
    
    public static void main(String[] args) {
    	Longest_Substring_Without_Repeating_Characters solution = new Longest_Substring_Without_Repeating_Characters();
    	System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
    	System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
    }
}
