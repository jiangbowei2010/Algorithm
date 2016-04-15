
/*
 * Leetcode 224 https://leetcode.com/problems/basic-calculator/
 * mplement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string may contain open ( and closing parentheses ), the plus
 * + or minus sign -, non-negative integers and empty spaces .
 * 
 * You may assume that the given expression is always valid.
 * 
 * Some examples: "1 + 1" = 2 " 2-1 + 2 " = 3 "(1+(4+5+2)-3)+(6+8)" = 23 Note:
 * Do not use the eval built-in library function.
 */
import java.util.*;

public class Basic_Calculator {

	// Method 1: add dummy curr & sign
	public int calculate(String s) {
		int curr = 0, sign = 1, num = 0;
		Deque<Integer> stack = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '(':
				stack.push(curr);
				stack.push(sign);
				sign = 1;
				curr = 0;
				break;
			case ')':
				num = curr + sign * num;
				sign = stack.pop();
				curr = stack.pop();
				break;
			case '+':
			case '-':
				curr = curr + sign * num;
				sign = c == '+' ? 1 : -1;
				num = 0;
				break;
			case ' ':
				break;
			default:
				num = num * 10 + c - '0';
			}
		}
		return curr + sign * num;
	}

	// Methold 2: Use value expression method
	public int calculateII(String s) {
		Deque<Character> stackOP = new LinkedList<>();
		Deque<Integer> stackNum = new LinkedList<>();
		int num = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '(':
				stackOP.push(c);
				break;
			case ')':
				while (stackOP.peek() != '(') {
					char op = stackOP.pop();
					int left = stackNum.pop();
					num = op == '+' ? left + num : left - num;
				}
				stackOP.pop();
				break;
			case '+':
			case '-':
				if (!stackOP.isEmpty() && (stackOP.peek() == '+' || stackOP.peek() == '-')) {
					int left = stackNum.pop();
					char op = stackOP.pop();
					num = op == '+' ? left + num : left - num;
				}
				stackOP.push(c);
				stackNum.push(num);
				num = 0;
				break;
			case ' ':
				break;
			default:
				num = num * 10 + c - '0';
			}
		}
		if (!stackOP.isEmpty()) {
			int left = stackNum.pop();
			char op = stackOP.pop();
			num = op == '+' ? left + num : left - num;
		}
		return num;
	}

	public static void main(String[] args) {
		Basic_Calculator solution = new Basic_Calculator();
		System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
		System.out.println(solution.calculateII("(1+(4+5+2)-3)+(6+8)"));
	}
}
