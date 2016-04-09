
/*
 * http://www.geeksforgeeks.org/backtracking-set-8-solving-cryptarithmetic-puzzles/
 * 
 * Newspapers and magazines often have crypt-arithmetic puzzles of the form:
 * 
 * SEND + MORE -------- MONEY --------
 * 
 * The goal here is to assign each letter a digit from 0 to 9 so that the
 * arithmetic works out correctly. The rules are that all occurrences of a
 * letter must be assigned the same digit, and no digit can be assigned to more
 * than one letter.
 */
import java.util.*;

public class Solving_Cryptarithmetic_Puzzles {
	public Map<Character, Integer> solvePuzzle(String s1, String s2, String s3) {
		Map<Character, Integer> map = new HashMap<>();
		Set<Integer> set = new HashSet<>();
		dfs(s1, s2, s3, s1.length() - 1, s2.length() - 1, s3.length() - 1, 0, map, set);
		return map;
	}

	private boolean dfs(String s1, String s2, String s3, int p1, int p2, int p3, int carry, Map<Character, Integer> map,
			Set<Integer> set) {
		if (p1 >= 0 || p2 >= 0 || carry > 0) {
			if (p3 < 0)
				return false;
			if (p1 >= 0 && !map.containsKey(s1.charAt(p1))) {
				for (int i = 0; i <= 9; i++) {
					if (!set.contains(i)) {
						map.put(s1.charAt(p1), i);
						set.add(i);
						if (dfs(s1, s2, s3, p1, p2, p3, carry, map, set))
							return true;
						set.remove(i);
						map.remove(s1.charAt(p1));
					}
				}
				return false;
			}
			if (p2 >= 0 && !map.containsKey(s2.charAt(p2))) {
				for (int i = 0; i <= 9; i++) {
					if (!set.contains(i)) {
						map.put(s2.charAt(p2), i);
						set.add(i);
						if (dfs(s1, s2, s3, p1, p2, p3, carry, map, set))
							return true;
						set.remove(i);
						map.remove(s2.charAt(p2));
					}
				}
				return false;
			}
			int num1 = p1 >= 0 ? map.get(s1.charAt(p1)) : 0;
			int num2 = p2 >= 0 ? map.get(s2.charAt(p2)) : 0;
			int sum = (num1 + num2 + carry) % 10;
			carry = (num1 + num2 + carry) / 10;
			if (map.containsKey(s3.charAt(p3))) {
				if (map.get(s3.charAt(p3)) == sum)
					return dfs(s1, s2, s3, p1 - 1, p2 - 1, p3 - 1, carry, map, set);
				else
					return false;
			} else if (set.contains(sum))
				return false;
			else {
				map.put(s3.charAt(p3), sum);
				set.add(sum);
				if (dfs(s1, s2, s3, p1 - 1, p2 - 1, p3 - 1, carry, map, set))
					return true;
				set.remove(sum);
				map.remove(s3.charAt(p3));
			}
			return false;
		} else if (p3 < 0)
			return true;
		return false;
	}

	public static void main(String[] args) {
		Solving_Cryptarithmetic_Puzzles solution = new Solving_Cryptarithmetic_Puzzles();
		Map<Character, Integer> map = solution.solvePuzzle("SEND", "MORE", "MONEY");
		for (char c : map.keySet()) {
			System.out.println("" + c + ": " + String.valueOf(map.get(c))); // 9567
																			// +
																			// 1085
																			// =
																			// 10652
		}
	}
}
