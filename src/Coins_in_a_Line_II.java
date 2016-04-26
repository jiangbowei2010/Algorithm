/*
 * lintcode 395: http://www.lintcode.com/en/problem/coins-in-a-line-ii/#
 * There are n coins with different value in a line. Two players take turns to
 * take one or two coins from left side until there are no more coins left. The
 * player who take the coins with the most value wins.
 * 
 * Could you please decide the first player will win or lose? Example Given
 * values array A = [1,2,2], return true.
 * 
 * Given A = [1,2,4], return false.
 */
public class Coins_in_a_Line_II {
	public boolean firstWillWin(int[] values) {
		int n = values.length;
		if (n <= 2)
			return true;
		int pre = values[n - 1];
		int curr = values[n - 1] + values[n - 2];
		int sum = curr;
		for (int i = n - 3; i >= 0; i--) {
			sum += values[i];
			int nextCurr = sum - Math.min(curr, pre);
			pre = curr;
			curr = nextCurr;
		}
		return curr * 2 > sum;
	}

	public static void main(String[] args) {
		Coins_in_a_Line_II solution = new Coins_in_a_Line_II();
		int[] values = { 1, 2, 2 };
		System.out.println(solution.firstWillWin(values));
		values = new int[] { 1, 2, 4 };
		System.out.println(solution.firstWillWin(values));
	}
}
