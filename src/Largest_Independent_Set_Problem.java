/*
 * from http://www.geeksforgeeks.org/largest-independent-set-problem/ Given a
 * Binary Tree, find size of the Largest Independent Set(LIS) in it. A subset of
 * all tree nodes is an independent set if there is no edge between any two
 * nodes of the subset.
 */

public class Largest_Independent_Set_Problem {

	public int largestSet(TreeNode root) {
		int[] res = dfs(root);
		return Math.max(res[0], res[1]);
	}

	// res[0] include this node, res[1] exclude this node
	private int[] dfs(TreeNode x) {
		int[] res = new int[2];
		if (x == null)
			return res;
		int[] left = dfs(x.left);
		int[] right = dfs(x.right);
		res[0] = left[1] + right[1] + 1;
		res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		return res;
	}

	public static void main(String[] args) {
		Largest_Independent_Set_Problem solution = new Largest_Independent_Set_Problem();
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(1);
		root.right = new TreeNode(2);
		System.out.println(solution.largestSet(root));
	}
}
