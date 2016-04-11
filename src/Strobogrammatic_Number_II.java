
/*
 * Leetcode 247: https://leetcode.com/problems/strobogrammatic-number-ii/
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 * 
 * Find all strobogrammatic numbers that are of length = n.
 * 
 * For example, Given n = 2, return ["11","69","88","96"].
 */
import java.util.*;

public class Strobogrammatic_Number_II {

	char[][] pairs = { { '1', '1' }, { '0', '0' }, { '8', '8' }, { '6', '9' }, { '9', '6' } };

	// Method 1: use back tracking
	public List<String> findStrobogrammaticDFS(int n) {
		List<String> res = new ArrayList<>();
		if (n <= 0)
			return res;
		char[] c = new char[n];
		dfs(c, 0, n - 1, res);
		return res;
	}

	private void dfs(char[] c, int lo, int hi, List<String> res) {
		if (lo > hi)
			res.add(new String(c));
		else {
			for (char[] pair : pairs) {
				if (lo == 0 && hi > lo && pair[0] == '0')
					continue;
				if (lo == hi && pair[0] != pair[1])
					continue;
				c[lo] = pair[0];
				c[hi] = pair[1];
				dfs(c, lo + 1, hi - 1, res);
			}
		}
	}

	// Method 2: use recursion
	public List<String> findStrobogrammatic(int n) {
		if (n <= 0)
			return new ArrayList<>();
		return helper(n, n);
	}

	private List<String> helper(int num, int n) {
		List<String> res = new ArrayList<>();
		if (num == 0) {
			res.add("");
		} else if (num == 1) {
			res.add("0");
			res.add("1");
			res.add("8");
		} else {
			List<String> list = helper(num - 2, n);
			for (String s : list) {
				if (num < n)
					res.add("0" + s + "0");
				res.add("1" + s + "1");
				res.add("8" + s + "8");
				res.add("6" + s + "9");
				res.add("9" + s + "6");
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Strobogrammatic_Number_II solution = new Strobogrammatic_Number_II();
		System.out.println(solution.findStrobogrammatic(0));
		System.out.println(solution.findStrobogrammatic(1));
		System.out.println(solution.findStrobogrammatic(3));
		System.out.println(solution.findStrobogrammaticDFS(0));
		System.out.println(solution.findStrobogrammaticDFS(1));
		System.out.println(solution.findStrobogrammaticDFS(3));
	}
}
