/*
 * Leetcode 124: https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * Given a binary tree, find the maximum path sum.
 * 
 * For this problem, a path is defined as any sequence of nodes from some
 * starting node to any node in the tree along the parent-child connections. The
 * path does not need to go through the root.
 * 
 * For example: Given the below binary tree,
 * 
 * 1 / \ 2 3 Return 6.
 */
public class Binary_Tree_Maximum_Path_Sum {
	public int maxPathSum(TreeNode root) {
		int[] res = new int[1];
		res[0] = Integer.MIN_VALUE;
		dfs(root, res);
		return res[0];
	}

	private int dfs(TreeNode x, int[] res) {
		if (x == null)
			return 0;
		int left = dfs(x.left, res);
		int right = dfs(x.right, res);
		res[0] = Math.max(res[0], left + right + x.val);
		int max = Math.max(left, right) + x.val;
		return max > 0 ? max : 0;
	}

	public static void main(String[] args) {
		Binary_Tree_Maximum_Path_Sum solution = new Binary_Tree_Maximum_Path_Sum();
		Serialize_and_Deserialize_Binary_Tree treeBuild = new Serialize_and_Deserialize_Binary_Tree();
		TreeNode root = treeBuild.deserializeBFS("1 2 3 # # # #");
		System.out.println(solution.maxPathSum(root)); // 6
	}
}
