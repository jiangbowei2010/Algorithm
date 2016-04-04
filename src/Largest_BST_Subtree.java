/*
 * Leetcode 333: https://leetcode.com/problems/largest-bst-subtree/
 * Given a binary tree, find the largest subtree which is a Binary Search Tree
 * (BST), where largest means subtree with largest number of nodes in it.
 */
public class Largest_BST_Subtree {
	public int largestBSTSubtree(TreeNode root) {
		int[] res = dfs(root);
		return res[1];
	}

	private int[] dfs(TreeNode x) {
		int[] res = new int[4];
		if (x == null) {
			res[0] = 1;
			return res;
		}
		int[] left = dfs(x.left);
		int[] right = dfs(x.right);
		if (left[0] == 1 && right[0] == 1) {
			if ((left[1] == 0 || x.val > left[3]) && (right[1] == 0 || x.val < right[2])) {
				res[0] = 1;
				res[1] = left[1] + right[1] + 1;
				res[2] = left[1] == 0 ? x.val : left[2];
				res[3] = right[1] == 0 ? x.val : right[3];
				return res;
			}
		}
		res[1] = Math.max(left[1], right[1]);
		return res;
	}
	
	public static void main(String[] args) {
		Largest_BST_Subtree solution = new Largest_BST_Subtree();
		Serialize_and_Deserialize_Binary_Tree treeBuild = new Serialize_and_Deserialize_Binary_Tree();
		TreeNode root = treeBuild.deserializeBFS("10 5 15 1 8 # 7 # # # # # #");
		System.out.println(solution.largestBSTSubtree(root)); //3
	}
}
