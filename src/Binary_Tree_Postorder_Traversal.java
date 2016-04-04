
/*
 * Leetcode 145 https://leetcode.com/problems/binary-tree-postorder-traversal/
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * 
 * For example: Given binary tree {1,#,2,3}, 1 \ 2 / 3 return [3,2,1].
 */
import java.util.*;

public class Binary_Tree_Postorder_Traversal {
	public List<Integer> postorderTraversal(TreeNode root) {
		TreeNode x = root, lastVisit = null;
		Deque<TreeNode> stack = new LinkedList<>();
		List<Integer> res = new ArrayList<>();
		while (x != null || !stack.isEmpty()) {
			if (x != null) {
				stack.push(x);
				x = x.left;
			} else {
				TreeNode right = stack.peek().right;
				if (right == null || (lastVisit != null && right == lastVisit)) {
					lastVisit = stack.pop();
					res.add(lastVisit.val);
				} else
					x = right;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Binary_Tree_Postorder_Traversal solution = new Binary_Tree_Postorder_Traversal();
		Serialize_and_Deserialize_Binary_Tree treeBuild = new Serialize_and_Deserialize_Binary_Tree();
		TreeNode root = treeBuild.deserializeBFS("1 # 2 3 #");
		System.out.println(solution.postorderTraversal(root));
	}
}
