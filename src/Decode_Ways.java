/*
 * Leetcode 91: https://leetcode.com/problems/decode-ways/
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing digits,
 * determine the total number of ways to decode it.
 * 
 * For example, Given encoded message "12", it could be decoded as "AB" (1 2) or
 * "L" (12).
 * 
 * The number of ways decoding "12" is 2.
 */
public class Decode_Ways {
	public int numDecodings(String s) {
		if (s == null || s.length() == 0)
			return 0;
		int pre = 0, curr = 1;
		for (int i = 0; i < s.length(); i++) {
			int nextCurr = 0;
			if (s.charAt(i) != '0')
				nextCurr += curr;
			if (i > 0) {
				int num = (s.charAt(i - 1) - '0') * 10 + s.charAt(i) - '0';
				if (num >= 10 && num <= 26)
					nextCurr += pre;
			}
			pre = curr;
			curr = nextCurr;
		}
		return curr;
	}
	public static void main(String[] args) {
		Decode_Ways solution = new Decode_Ways();
		System.out.println(solution.numDecodings("123"));
		System.out.println(solution.numDecodings("112001"));
	}
}
