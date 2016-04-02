import java.util.Arrays;

/*
 * http://www.geeksforgeeks.org/mobile-numeric-keypad-problem/ Given the mobile
 * numeric keypad. You can only press buttons that are up, left, right or down
 * to the current button. You are not allowed to press bottom row corner buttons
 * (i.e. * and # ). Given a number N, find out the number of possible numbers of
 * given length.
 * 
 * Examples: For N=1, number of possible numbers would be 10 (0, 1, 2, 3, …., 9)
 * For N=2, number of possible numbers would be 36 Possible numbers: 00,08
 * 11,12,14 22,21,23,25 and so on.
 */

public class Mobile_Numeric_Keypad_Problem {
	public int numKeypad(int n) {
		if (n <= 0)
			return 0;
		int[][] adj = { { 0, 8 }, { 1, 2, 4 }, { 1, 2, 3, 5 }, { 2, 3, 6 }, { 1, 4, 5, 7 }, { 2, 4, 5, 6, 8 },
				{ 3, 5, 6, 9 }, { 4, 7, 8 }, { 5, 7, 8, 9, 0 }, { 6, 8, 9 } };
		int[] nums = new int[10];
		Arrays.fill(nums, 1);
		for (int i = 1; i < n; i++) {
			int[] nextNum = new int[10];
			for (int k = 0; k < 10; k++) {
				for (int t : adj[k]) {
					nextNum[k] += nums[t];
				}
			}
			nums = nextNum;
		}
		int sum = 0;
		for (int k = 0; k < 10; k++)
			sum += nums[k];
		return sum;
	}
	
	public static void main(String[] args) {
		Mobile_Numeric_Keypad_Problem solution = new Mobile_Numeric_Keypad_Problem();
		System.out.println(solution.numKeypad(1)); //10
		System.out.println(solution.numKeypad(2)); //36
		System.out.println(solution.numKeypad(3)); //138
		System.out.println(solution.numKeypad(4)); //532
		System.out.println(solution.numKeypad(5)); //2062
	}
}
