/*
 * Lintcode 183: http://www.lintcode.com/en/problem/wood-cut/#
 * Given n pieces of wood with length L[i] (integer array). Cut them into small
 * pieces to guarantee you could have equal or more than k pieces with the same
 * length. What is the longest length you can get from the n pieces of wood?
 * Given L & k, return the maximum length of the small pieces.
 */
public class Wood_Cut {
	public int woodCut(int[] L, int k) {
		int max = 0;
		for (int len : L)
			max = Math.max(max, len);
		int lo = 1, hi = max;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int count = 0;
			for (int len : L) {
				count += len / mid;
			}
			if (count >= k)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return hi;
	}
	
	public static void main(String[] args) {
		Wood_Cut solution = new Wood_Cut();
		int[] L = {232, 124, 456};
		System.out.println(solution.woodCut(L, 7)); //114
	}
}
