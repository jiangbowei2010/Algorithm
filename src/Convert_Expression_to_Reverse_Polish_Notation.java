
/*
 * Lintcode 370: http://www.lintcode.com/en/problem/convert-expression-to-reverse-polish-notation/#
 * Given an expression string array, return the Reverse Polish notation of this
 * expression. (remove the parentheses)
 * 
 * Example For the expression [3 - 4 + 5] (which denote by ["3", "-", "4", "+",
 * "5"]), return [3 4 - 5 +] (which denote by ["3", "4", "-", "5", "+"])
 */
import java.util.*;

public class Convert_Expression_to_Reverse_Polish_Notation {
	public List<String> convertToRPN(String[] expression) {
		List<String> res = new ArrayList<>();
		Deque<String> stack = new LinkedList<>();
		for (String exp : expression) {
			switch (exp) {
			case "(":
				stack.push(exp);
				break;
			case ")":
				while (!stack.peek().equals("("))
					res.add(stack.pop());
				stack.pop();
				break;
			case "*":
			case "/":
				if (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/")))
					res.add(stack.pop());
				stack.push(exp);
				break;
			case "+":
			case "-":
				if (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/")))
					res.add(stack.pop());
				if (!stack.isEmpty() && (stack.peek().equals("+") || stack.peek().equals("-")))
					res.add(stack.pop());
				stack.push(exp);
				break;
			default:
				res.add(exp);
			}
		}
		while (!stack.isEmpty())
			res.add(stack.pop());
		return res;
	}

	public static void main(String[] args) {
		Convert_Expression_to_Reverse_Polish_Notation solution = new Convert_Expression_to_Reverse_Polish_Notation();
		String[] expression = { "3", "-", "4", "+", "5" };
		System.out.println(solution.convertToRPN(expression));
		expression = new String[]{ "3", "-", "4", "*", "(", "5", "+", "7", ")"};
		System.out.println(solution.convertToRPN(expression));
	}
}
