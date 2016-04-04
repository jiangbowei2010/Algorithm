/*
 * Leetcode 105 :https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 */

import java.util.*;

public class Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder == null || preorder.length == 0 || preorder.length != inorder.length)
			return null;
		TreeNode root = new TreeNode(preorder[0]);
		TreeNode x = root;
		Deque<TreeNode> stack = new LinkedList<>();
		stack.push(x);
		boolean left = true;
		int i = 1, j = 0;
		while (i < preorder.length) {
			if (!stack.isEmpty() && stack.peek().val == inorder[j]) {
				x = stack.pop();
				left = false;
				j++;
			} else if (left) {
				x.left = new TreeNode(preorder[i++]);
				x = x.left;
				stack.push(x);
			} else {
				x.right = new TreeNode(preorder[i++]);
				x = x.right;
				stack.push(x);
				left = true;
			}
		}
		return root;
	}
	
	public static void main(String[] args) {
		Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal solution = new Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal();
		int[] preorder = {1, 2, 4, 3};
		int[] inorder = {4, 2, 1, 3};
		TreeNode root = solution.buildTree(preorder, inorder);
		Serialize_and_Deserialize_Binary_Tree code = new Serialize_and_Deserialize_Binary_Tree();
		System.out.println(code.serializeBFS(root)); // 1 2 3 4 # # # # #
	}
}
