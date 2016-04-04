
/*
 * Leetcode 297: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 */
import java.util.*;

public class Serialize_and_Deserialize_Binary_Tree {

	// Method 1 ---- Use DFS iterative pre-order traversal
	public String serialize(TreeNode root) {
		if (root == null)
			return "";
		StringBuilder sb = new StringBuilder();
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode x = root;
		while (x != null || !stack.isEmpty()) {
			if (x != null) {
				stack.push(x);
				sb.append(String.valueOf(x.val));
				sb.append(" ");
				x = x.left;
			} else {
				sb.append("# ");
				x = stack.pop().right;
			}
		}
		return sb.toString();
	}

	public TreeNode deserialize(String data) {
		if (data == null || data.length() == 0)
			return null;
		String[] node = data.split(" ");
		TreeNode root = new TreeNode(Integer.valueOf(node[0]));
		TreeNode x = root;
		Deque<TreeNode> stack = new LinkedList<>();
		stack.push(root);
		int i = 1;
		boolean left = true;
		while (i < node.length) {
			if (node[i].equals("#")) {
				x = stack.pop();
				left = false;
				i++;
			} else if (left) {
				x.left = new TreeNode(Integer.valueOf(node[i++]));
				x = x.left;
				stack.push(x);
			} else {
				x.right = new TreeNode(Integer.valueOf(node[i++]));
				x = x.right;
				stack.push(x);
				left = true;
			}
		}
		return root;
	}

	public String serializeBFS(TreeNode root) {
		if (root == null)
			return "";
		StringBuilder sb = new StringBuilder();
		Queue<TreeNode> q = new LinkedList<>();
		sb.append(String.valueOf(root.val));
		sb.append(" ");
		q.offer(root);
		while (!q.isEmpty()) {
			TreeNode x = q.poll();
			if (x.left == null) {
				sb.append("# ");
			} else {
				sb.append(String.valueOf(x.left.val));
				sb.append(" ");
				q.offer(x.left);
			}
			if (x.right == null) {
				sb.append("# ");
			} else {
				sb.append(String.valueOf(x.right.val));
				sb.append(" ");
				q.offer(x.right);
			}
		}
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserializeBFS(String data) {
		if (data == null || data.length() == 0)
			return null;
		String[] node = data.split(" ");
		TreeNode root = new TreeNode(Integer.valueOf(node[0]));
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		int i = 1;
		while (i < node.length) {
			TreeNode x = q.poll();
			if (node[i].equals("#"))
				i++;
			else {
				x.left = new TreeNode(Integer.valueOf(node[i++]));
				q.offer(x.left);
			}
			if (node[i].equals("#"))
				i++;
			else {
				x.right = new TreeNode(Integer.valueOf(node[i++]));
				q.offer(x.right);
			}
		}
		return root;
	}

	public static void main(String[] args) {
		Serialize_and_Deserialize_Binary_Tree solution = new Serialize_and_Deserialize_Binary_Tree();
		String preOrder = "1 # 3 2 # # 7 #";
		TreeNode root = solution.deserialize(preOrder);
		System.out.println(solution.serialize(root));
		System.out.println(solution.serializeBFS(root));

		String levelOrder = "1 # 3 2 7 # # # #";
		root = solution.deserializeBFS(levelOrder);
		System.out.println(solution.serialize(root));
		System.out.println(solution.serializeBFS(root));
	}
}
