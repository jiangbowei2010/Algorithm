/*
 * Leetcode 150: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * 
 * Valid operators are +, -, *, /. Each operand may be an integer or another
 * expression.
 * 
 * Some examples: ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9 ["4", "13",
 * "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
import java.util.*;

public class Evaluate_Reverse_Polish_Notation {
	public int evalRPN(String[] tokens) {
		Deque<Integer> stack = new LinkedList<>();
		for (String t : tokens) {
			switch (t) {
			case "+":
			case "-":
			case "*":
			case "/":
				int right = stack.pop(), left = stack.pop();
				stack.push(calculate(left, t, right));
				break;
			default:
				stack.push(Integer.valueOf(t));
			}
		}
		if (stack.isEmpty())
			return 0;
		return stack.pop();
	}

	private int calculate(int left, String op, int right) {
		switch (op) {
		case "+":
			return left + right;
		case "-":
			return left - right;
		case "*":
			return left * right;
		case "/":
			return left / right;
		default:
			return 0;
		}
	}
	
	public static void main(String[] args) {
		Evaluate_Reverse_Polish_Notation solution = new Evaluate_Reverse_Polish_Notation();
		String[] tokens = {"2", "1", "+", "3", "*"};
		System.out.println(solution.evalRPN(tokens)); //9
		tokens = new String[]{"4", "13", "5", "/", "+"};
		System.out.println(solution.evalRPN(tokens)); //6
	}
}
