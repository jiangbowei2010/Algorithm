/*
 * Imagine you have a special keyboard with the following keys: Key 1: Prints
 * 'A' on screen Key 2: (Ctrl-A): Select screen Key 3: (Ctrl-C): Copy selection
 * to buffer Key 4: (Ctrl-V): Print buffer on screen appending it after what has
 * already been printed.
 * 
 * If you can only press the keyboard for N times (with the above four keys),
 * write a program to produce maximum numbers of A's. That is to say, the input
 * parameter is N (No. of keys that you can press), the output is M (No. of As
 * that you can produce). Examples:
 * 
 * Input: N = 3 Output: 3 We can at most get 3 A's on screen by pressing
 * following key sequence. A, A, A
 * 
 * Input: N = 7 Output: 9 We can at most get 9 A's on screen by pressing
 * following key sequence. A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 */

public class Maximum_number_of_A_using_given_four_keys {
	public int maxNumberA(int n) {
		if (n <= 6)
			return n;
		int[] dp = new int[n + 1];
		// when n <= 6, dp[n] = n;
		for (int i = 0; i <= 6; i++) {
			dp[i] = i;
		}
		// when n >=7, dp[i] depends on
		// dp[i - 3] * 2: ctrl A -> ctrl C -> ctrl V
		// dp[i - 4] * 3: ctrl A -> ctrl C -> ctrl V -> ctrl V
		// dp[i - 5] * 4: ctrl A -> ctrl C -> ctrl V -> ctrl V -> ctrl V
		// can not be dp[i - 6] * 5, think about why?? dp[i - 5] * 4 >= dp[i -6]
		// * 4 / 3 * 4 > 5x
		for (int i = 7; i <= n; i++) {
			dp[i] = Math.max(2 * dp[i - 3], Math.max(3 * dp[i - 4], 4 * dp[i - 5]));
		}
		return dp[n];
	}

	public static void main(String[] args) {
		Maximum_number_of_A_using_given_four_keys solution = new Maximum_number_of_A_using_given_four_keys();
		for (int i = 1; i < 20; i++) {
			System.out.println("Max Number of " + String.valueOf(i) + " is " + String.valueOf(solution.maxNumberA(i)));
		}
	}
}
