/*
 * Lintcode 182: http://www.lintcode.com/en/problem/delete-digits/#
 * Given string A representative a positive integer which has N digits, remove
 * any k digits of the number, the remaining digits are arranged according to
 * the original order to become a new positive integer.
 * 
 * Find the smallest integer after remove k digits.
 * 
 * N <= 240 and k <= N,
 */
public class Delete_Digits {
	public String DeleteDigits(String s, int k) {
		int n = s.length();
		if (k > n)
			return "";
		StringBuilder sb = new StringBuilder();
		int lo = 0, hi = k;
		for (int i = 0; i < n - k; i++) {
			char c = s.charAt(lo);
			int index = lo;
			for (int j = lo + 1; j <= hi; j++) {
				if (s.charAt(j) < c) {
					c = s.charAt(j);
					index = j;
				}
			}
			sb.append(c);
			lo = index + 1;
			hi++;
		}
		int i = 0;
		while (i < sb.length() - 1 && sb.charAt(i) == '0')
			i++;
		return sb.substring(i);
	}
	
	public static void main(String[] args) {
		Delete_Digits solution = new Delete_Digits();
		System.out.println(solution.DeleteDigits("178542", 4)); //12
	}
}
