
/*
 * Given a non-empty binary search tree and a target value, find k values in the
 * BST that are closest to the target.
 * 
 * Note: Given target value is a floating point. You may assume k is always
 * valid, that is: k <= total nodes. You are guaranteed to have only one unique
 * set of k values in the BST that are closest to the target. Follow up: Assume
 * that the BST is balanced, could you solve it in less than O(n) runtime (where
 * n = total nodes)?
 */

import java.util.*;

public class Closest_Binary_Search_Tree_Value_II {
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		List<Integer> res = new ArrayList<>();
		if (root == null || k <= 0)
			return res;
		TreeNode x = root;
		Deque<TreeNode> leftStack = new LinkedList<>();
		Deque<TreeNode> rightStack = new LinkedList<>();
		while (x != null) {
			if (x.val > target) {
				rightStack.push(x);
				x = x.left;
			} else {
				leftStack.push(x);
				x = x.right;
			}
		}
		for (int i = 0; i < k; i++) {
			if (leftStack.isEmpty())
				res.add(nextRight(rightStack));
			else if (rightStack.isEmpty())
				res.add(nextLeft(leftStack));
			else if (target - leftStack.peek().val < rightStack.peek().val - target)
				res.add(nextLeft(leftStack));
			else
				res.add(nextRight(rightStack));
		}
		return res;
	}

	private int nextLeft(Deque<TreeNode> leftStack) {
		TreeNode x = leftStack.pop();
		int res = x.val;
		x = x.left;
		while (x != null) {
			leftStack.push(x);
			x = x.right;
		}
		return res;
	}

	private int nextRight(Deque<TreeNode> rightStack) {
		TreeNode x = rightStack.pop();
		int res = x.val;
		x = x.right;
		while (x != null) {
			rightStack.push(x);
			x = x.left;
		}
		return res;
	}

	public static void main(String[] args) {
		Closest_Binary_Search_Tree_Value_II solution = new Closest_Binary_Search_Tree_Value_II();
		Serialize_and_Deserialize_Binary_Tree buildTree = new Serialize_and_Deserialize_Binary_Tree();
		TreeNode root = buildTree.deserializeBFS("3 1 4 # 2 # # # #");
		System.out.println(solution.closestKValues(root, 5, 2)); // [4 3]
		System.out.println(solution.closestKValues(root, 2.1, 2)); // [2 3]
		System.out.println(solution.closestKValues(null, 2.1, 0)); // []
	}
}
