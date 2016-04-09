/*
 * Leetcode 93: https://leetcode.com/problems/restore-ip-addresses/
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 * 
 * For example: Given "25525511135",
 * 
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
import java.util.*;

public class Restore_IP_Addresses {
	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<>();
		dfs(s, 0, "", res);
		return res;
	}

	private void dfs(String s, int d, String path, List<String> res) {
		if (d == 4)
			res.add(path.substring(0, path.length() - 1));
		else {
			for (int i = 0; i < 3; i++) {
				if (s.length() - i - 1 < 3 - d || s.length() - i - 1 > (3 - d) * 3)
					continue;
				if (i > 0 && s.charAt(0) == '0')
					break;
				if (i == 2 && s.substring(0, 3).compareTo("255") > 0)
					break;
				dfs(s.substring(i + 1), d + 1, path + s.substring(0, i + 1) + '.', res);
			}
		}
	}
	
	public static void main(String[] args) {
		Restore_IP_Addresses solution = new Restore_IP_Addresses();
		System.out.println(solution.restoreIpAddresses("0000"));
		System.out.println(solution.restoreIpAddresses("25525511135"));
	}
}

