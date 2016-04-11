
/*
 * 
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the
 * digits so they evaluate to the target value.
 * 
 * Examples: "123", 6 -> ["1+2+3", "1*2*3"] "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"] "00", 0 -> ["0+0", "0-0", "0*0"] "3456237490",
 * 9191 -> []
 */
import java.util.*;

public class Expression_Add_Operators {
	public List<String> addOperators(String num, int target) {
		List<String> res = new ArrayList<>();
		if (num == null || num.length() == 0)
			return res;
		long pre = 0, curr = 1; // pre +/- curr * num
		boolean sign = true; // true is "+", false is "-"
		dfs(num, pre, curr, sign, "", res, target);
		return res;
	}

	private void dfs(String s, long pre, long curr, boolean sign, String path, List<String> res, int target) {
		long num = 0;
		for (int i = 0; i < s.length(); i++) {
			if (i > 0 && s.charAt(0) == '0')
				return;
			num = num * 10 + s.charAt(i) - '0';
			if (i < s.length() - 1) {
				String left = s.substring(0, i + 1);
				String right = s.substring(i + 1);
				dfs(right, pre, curr * num, sign, path + left + '*', res, target); // *
				long val = sign ? pre + curr * num : pre - curr * num;
				dfs(right, val, 1, true, path + left + '+', res, target); // +
				dfs(right, val, 1, false, path + left + '-', res, target); // -
			}
		}
		long val = sign ? pre + curr * num : pre - curr * num;
		if (val == target)
			res.add(path + s);
	}
	
	public static void main(String[] args) {
		Expression_Add_Operators solution = new Expression_Add_Operators();
		System.out.println(solution.addOperators("123", 6));
		System.out.println(solution.addOperators("232", 8));
		System.out.println(solution.addOperators("00", 0));
		System.out.println(solution.addOperators("3456237490", 9191));
	}
}
