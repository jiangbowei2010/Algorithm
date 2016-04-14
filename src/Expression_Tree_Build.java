/*
 * 
 * Clarification See wiki: Expression Tree
 * 
 * Example For the expression (2*6-(23+7)/(1+2)) (which can be represented by
 * ["2" "*" "6" "-" "(" "23" "+" "7" ")" "/" "(" "1" "+" "2" ")"]). The
 * expression tree will be like
 * 
 * [ - ] / \ [ * ] [ / ] / \ / \ [ 2 ] [ 6 ] [ + ] [ + ] / \ / \ [ 23 ][ 7 ] [ 1
 * ] [ 2 ] . After building the tree, you just need to return root node [-].
 */
import java.util.*;

class ExpressionTreeNode {
	public String symbol;
	public ExpressionTreeNode left, right;

	public ExpressionTreeNode(String symbol) {
		this.symbol = symbol;
		this.left = this.right = null;
	}
}

public class Expression_Tree_Build {

	public ExpressionTreeNode build(String[] expression) {
		Deque<ExpressionTreeNode> stackNode = new LinkedList<>();
		Deque<Integer> stackPriority = new LinkedList<>();
		int base = 0;
		for (String exp : expression) {
			int currPriority = base;
			if (exp.equals("(")) {
				base += 10;
				continue;
			} else if (exp.equals(")")) {
				base -= 10;
				continue;
			} else if (exp.equals("+") || exp.equals("-"))
				currPriority += 1;
			else if (exp.equals("*") || exp.equals("/"))
				currPriority += 2;
			else
				currPriority = Integer.MAX_VALUE;
			ExpressionTreeNode x = new ExpressionTreeNode(exp);
			ExpressionTreeNode right = null;
			while (!stackPriority.isEmpty() && stackPriority.peek() >= currPriority) {
				ExpressionTreeNode y = stackNode.pop();
				y.right = right;
				right = y;
				stackPriority.pop();
			}
			x.left = right;
			stackNode.push(x);
			stackPriority.push(currPriority);
		}
		ExpressionTreeNode right = null;
		while (!stackNode.isEmpty()) {
			ExpressionTreeNode x = stackNode.pop();
			x.right = right;
			right = x;
		}
		return right;
	}
}
