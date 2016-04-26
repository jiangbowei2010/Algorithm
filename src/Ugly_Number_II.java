/*
 * Leetcode 264: https://leetcode.com/problems/ugly-number-ii/
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10
 * ugly numbers.
 * 
 * Note that 1 is typically treated as an ugly number.
 */
public class Ugly_Number_II {
	public int nthUglyNumber(int n) {
		if (n <= 0)
			return 0;
		int[] ugly = new int[n];
		ugly[0] = 1;
		int p2 = 0, p3 = 0, p5 = 0;
		for (int i = 1; i < n; i++) {
			ugly[i] = Math.min(ugly[p2] * 2, Math.min(ugly[p3] * 3, ugly[p5] * 5));
			if (ugly[p2] * 2 == ugly[i])
				p2++;
			if (ugly[p3] * 3 == ugly[i])
				p3++;
			if (ugly[p5] * 5 == ugly[i])
				p5++;
		}
		return ugly[n - 1];
	}
	
	public static void main(String[] args) {
		Ugly_Number_II solution = new Ugly_Number_II();
		System.out.println(solution.nthUglyNumber(5));
		System.out.println(solution.nthUglyNumber(521));
	}
}
