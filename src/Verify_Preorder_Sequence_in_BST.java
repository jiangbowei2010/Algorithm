
/*
 * https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/
 * Leetcode 255
 * Given an array of numbers, verify whether it is the correct preorder
 * traversal sequence of a binary search tree.
 * You may assume each number in the sequence is unique.
 * Follow up: Could you do it using only constant space complexity?
 */
import java.util.*;

public class Verify_Preorder_Sequence_in_BST {
	// method 1 use tree preorder traversal
	public boolean verifyPreorder1(int[] preorder) {
		if (preorder == null || preorder.length == 0)
			return true;
		Deque<Integer> stack = new LinkedList<>();
		int i = 0;
		Integer lo = null;
		while (i < preorder.length) {
			if (stack.isEmpty() || preorder[i] < stack.peek()) {
				if (lo != null && preorder[i] <= lo)
					return false;
				stack.push(preorder[i++]);
			} else if (preorder[i] == stack.peek())
				return false;
			else
				lo = stack.pop();
		}
		return true;
	}

	// method 2, using dec stack, when pop from stack, it must be in order
	public boolean verifyPreorder2(int[] preorder) {
		Deque<Integer> stack = new LinkedList<>();
		Integer pre = null;
		for (int num : preorder) {
			while (!stack.isEmpty() && stack.peek() <= num) {
				if (pre != null && stack.peek() <= pre)
					return false;
				pre = stack.pop();
			}
			stack.push(num);
		}
		if (pre != null && !stack.isEmpty() && stack.peek() <= pre)
			return false;
		return true;
	}

	public static void main(String[] args) {
		Verify_Preorder_Sequence_in_BST solution = new Verify_Preorder_Sequence_in_BST();
		int[] preorder = { 2, 3, 1 }; // false
		System.out.println(solution.verifyPreorder1(preorder));
		System.out.println(solution.verifyPreorder2(preorder));
		preorder = new int[]{ 2, 4, 3 }; // true
		System.out.println(solution.verifyPreorder1(preorder));
		System.out.println(solution.verifyPreorder2(preorder));
	}
}
