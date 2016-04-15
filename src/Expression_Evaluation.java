
/*
 * Lint code 368: http://www.lintcode.com/en/problem/expression-evaluation/
 * For the expression 2*6-(23+7)/(1+2), input is
 * 
 * [ "2", "*", "6", "-", "(", "23", "+", "7", ")", "/", (", "1", "+", "2", ")"
 * ], return 2
 */
import java.util.*;

public class Expression_Evaluation {
	public int evaluateExpression(String[] expression) {
		Deque<Integer> stackNum = new LinkedList<>(); // store left operate num
		Deque<String> stackOP = new LinkedList<>(); // store op
		int num = 0;
		for (String exp : expression) {
			if (exp.equals("("))
				stackOP.push(exp);
			else if (exp.equals(")")) {
				while (!stackOP.peek().equals("("))
					num = calculate(stackNum.pop(), stackOP.pop(), num);
				stackOP.pop();
			} else if (exp.equals("*") || exp.equals("/")) {
				if (!stackOP.isEmpty() && (stackOP.peek().equals("*") || stackOP.peek().equals("/"))) {
					num = calculate(stackNum.pop(), stackOP.pop(), num);
				}
				stackOP.push(exp);
				stackNum.push(num);
			} else if (exp.equals("+") || exp.equals("-")) {
				if (!stackOP.isEmpty() && (stackOP.peek().equals("*") || stackOP.peek().equals("/")))
					num = calculate(stackNum.pop(), stackOP.pop(), num);
				if (!stackOP.isEmpty() && (stackOP.peek().equals("+") || stackOP.peek().equals("-")))
					num = calculate(stackNum.pop(), stackOP.pop(), num);
				stackOP.push(exp);
				stackNum.push(num);
			} else
				num = Integer.valueOf(exp);
		}
		while (!stackOP.isEmpty())
			num = calculate(stackNum.pop(), stackOP.pop(), num);
		return num;
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
		Expression_Evaluation solution = new Expression_Evaluation();
		String[] expressions = { "2", "*", "6", "-", "(", "23", "+", "7", ")", "/", "(", "1", "+", "2", ")" };
		System.out.println(solution.evaluateExpression(expressions));
	}
}
