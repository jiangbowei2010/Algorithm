/*
 * Leetcode 227: https://leetcode.com/problems/basic-calculator-ii/
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string contains only non-negative integers, +, -, *, /
 * operators and empty spaces . The integer division should truncate toward
 * zero.
 * 
 * You may assume that the given expression is always valid.
 * 
 * Some examples: "3+2*2" = 7 " 3/2 " = 1 " 3+5 / 2 " = 5 Note: Do not use the
 * eval built-in library function.
 */
public class Basic_Calculator_II {
	public int calculate(String s) {
		int pre = 0, curr = 1, num = 0; // pre + curr * num
		boolean preSign = true, currSign = true;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '*':
			case '/':
				curr = currSign ? curr * num : curr / num;
				currSign = c == '*';
				num = 0;
				break;
			case '+':
			case '-':
				curr = currSign ? curr * num : curr / num;
				pre = preSign ? pre + curr : pre - curr;
				preSign = c == '+';
				currSign = true;
				curr = 1;
				num = 0;
				break;
			case ' ':
				break;
			default:
				num = num * 10 + c - '0';
			}
		}
		curr = currSign ? curr * num : curr / num;
		pre = preSign ? pre + curr : pre - curr;
		return pre;
	}

	public static void main(String[] args) {
		Basic_Calculator_II solution = new Basic_Calculator_II();
		System.out.println(solution.calculate("3+2*2")); // 7
		System.out.println(solution.calculate("0")); // 0
		System.out.println(solution.calculate(" 3+5 / 2 ")); // 5
	}
}
