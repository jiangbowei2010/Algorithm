/*
 * http://www.geeksforgeeks.org/total-number-of-non-decreasing-numbers-with-n-digits/
 * 
 * A number is non-decreasing if every digit (except the first one) is greater
 * than or equal to previous digit. For example, 223, 4455567, 899, are
 * non-decreasing numbers.
 * 
 * So, given the number of digits n, you are required to find the count of total
 * non-decreasing numbers with n digits.
 * 
 * Examples:
 * Input: n = 1 Output: count = 10
 * Input: n = 2 Output: count = 55
 * Input: n = 3 Output: count = 220
 */
public class NonDecreasing_numbers_with_n_digits {
	public int numNonDecreasing(int n) {
		if (n <= 0)
			return 0;
		int[][] dp = new int[n][10];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 || j == 0)
					dp[i][j] = 1;
				else {
					for (int k = 0; k <= j; k++) {
						dp[i][j] += dp[i - 1][k];
					}
				}
			}
		}
		int res = 0;
		for (int i = 0; i < 10; i++)
			res += dp[n - 1][i];
		return res;
	}

	public static void main(String[] args) {
		NonDecreasing_numbers_with_n_digits solution = new NonDecreasing_numbers_with_n_digits();
		System.out.println(solution.numNonDecreasing(1)); // 10
		System.out.println(solution.numNonDecreasing(2)); // 55
		System.out.println(solution.numNonDecreasing(3)); // 220
	}
}
