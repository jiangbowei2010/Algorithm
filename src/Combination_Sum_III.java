/*
 * Leetcode 216: Combination Sum III
 * Find all possible combinations of k numbers that add up to a number n, given
 * that only numbers from 1 to 9 can be used and each combination should be a
 * unique set of numbers.
 * Ensure that numbers within the set are sorted in ascending order.
 * 
 * Example 1:
 * Input: k = 3, n = 7
 * Output:
 * [[1,2,4]]
 * 
 * Example 2:
 * Input: k = 3, n = 9
 * Output:
 * [[1,2,6], [1,3,5], [2,3,4]]
 */
import java.util.*;

public class Combination_Sum_III {
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<>();
		if (k <= 0 || k > 9 || n <= 0 || n > 45)
			return res;
		List<Integer> path = new ArrayList<>();
		dfs(1, k, n, path, res);
		return res;
	}

	private void dfs(int num, int k, int n, List<Integer> path, List<List<Integer>> res) {
		if (k == 0 && n == 0)
			res.add(new ArrayList<>(path));
		else if (n > 0 && k > 0 && 10 - num >= k) {
			dfs(num + 1, k, n, path, res);
			path.add(num);
			dfs(num + 1, k - 1, n - num, path, res);
			path.remove(path.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		Combination_Sum_III solution = new Combination_Sum_III();
		System.out.println(solution.combinationSum3(3, 7)); // [1 2 4]
		System.out.println(solution.combinationSum3(3, 9)); //[1 2 6] [1 3 5] [2 3 4]
	}
}
