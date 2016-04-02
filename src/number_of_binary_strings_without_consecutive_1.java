/*
 * http://www.geeksforgeeks.org/count-number-binary-strings-without-
 * consecutive-1s/ Given a positive integer N, count all possible distinct
 * binary strings of length N such that there are no consecutive 1’s.
 * Examples:
 * Input: N = 2 Output: 3 // The 3 strings are 00, 01, 10
 * Input: N = 3 Output: 5 // The 5 strings are 000, 001, 010, 100, 101
 */

public class number_of_binary_strings_without_consecutive_1 {
	public int numBinaryString(int n) {
		if (n <= 0)
			return 0;
		int endOne = 1, endZero = 1;
		for (int i = 1; i < n; i++) {
			int nextEndOne = endZero;
			endZero += endOne;
			endOne = nextEndOne;
		}
		return endOne + endZero;
	}

	public static void main(String[] args) {
		number_of_binary_strings_without_consecutive_1 solution = new number_of_binary_strings_without_consecutive_1();
		System.out.println(solution.numBinaryString(2));
		System.out.println(solution.numBinaryString(3));
	}
}
