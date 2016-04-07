
/*
 * http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 * A permutation, also called an “arrangement number” or “order,” is a
 * rearrangement of the elements of an ordered list S into a one-to-one
 * correspondence with S itself. A string of length n has n! permutation.
 * Source: Mathword(http://mathworld.wolfram.com/Permutation.html)
 * 
 * Below are the permutations of string ABC. ABC ACB BAC BCA CBA CAB
 */
import java.util.*;

public class Permutations_of_a_given_string {

	// Method 1: use back tracking
	public List<String> permutation(String s) {
		List<String> res = new ArrayList<>();
		if (s == null || s.length() == 0)
			return res;
		char[] c = s.toCharArray();
		Arrays.sort(c);
		boolean[] selected = new boolean[c.length];
		StringBuilder sb = new StringBuilder();
		dfs(c, 0, selected, sb, res);
		return res;
	}

	private void dfs(char[] c, int d, boolean[] selected, StringBuilder sb, List<String> res) {
		if (d == c.length)
			res.add(sb.toString());
		else {
			Character pre = null;
			for (int i = 0; i < c.length; i++) {
				if (!selected[i] && (pre == null || c[i] != pre)) {
					pre = c[i];
					selected[i] = true;
					sb.append(c[i]);
					dfs(c, d + 1, selected, sb, res);
					sb.deleteCharAt(sb.length() - 1);
					selected[i] = false;
				}
			}
		}
	}

	// Method2: use iteration
	public List<String> permutation2(String s) {
		List<String> res = new ArrayList<>();
		if (s == null || s.length() == 0)
			return res;
		Set<String> set = new HashSet<>();
		set.add("");
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			Set<String> nextSet = new HashSet<>();
			for (String word : set) {
				for (int k = 0; k <= word.length(); k++) {
					nextSet.add(word.substring(0, k) + c + word.substring(k));
				}
			}
			set = nextSet;
		}
		for (String word : set)
			res.add(word);
		return res;
	}

	public static void main(String[] args) {
		Permutations_of_a_given_string solution = new Permutations_of_a_given_string();
		System.out.println(solution.permutation("ACB"));
		System.out.println(solution.permutation("AAB"));
		System.out.println(solution.permutation(""));
		
		System.out.println(solution.permutation2("ACB"));
		System.out.println(solution.permutation2("AAB"));
		System.out.println(solution.permutation2(""));
	}
}
