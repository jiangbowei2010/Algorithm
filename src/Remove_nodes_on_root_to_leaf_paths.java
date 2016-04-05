/*
 * http://www.geeksforgeeks.org/remove-nodes-root-leaf-paths-length-k/
 * Given a Binary Tree and a number k, remove all nodes that lie only on root to
 * leaf path(s) of length smaller than k. If a node X lies on multiple
 * root-to-leaf paths and if any of the paths has path length >= k, then X is
 * not deleted from Binary Tree. In other words a node is deleted if all paths
 * going through it have lengths smaller than k.
 * 
 * Consider the following example Binary Tree
 * 
 * 1 / \ 2 3 / \ \ 4 5 6 / / 7 8 Input: Root of above Binary Tree k = 4
 * 
 * Output: The tree should be changed to following 1 / \ 2 3 / \ 4 6 / / 7 8
 */

public class Remove_nodes_on_root_to_leaf_paths {
	public void removeNode(TreeNode root, int k) {
		dfs(root, 0, k);
	}

	private int dfs(TreeNode x, int d, int k) {
		if (x == null)
			return d;
		int left = dfs(x.left, d + 1, k);
		int right = dfs(x.right, d + 1, k);
		if (left < k)
			x.left = null;
		if (right < k)
			x.right = null;
		return Math.max(left, right);
	}

	public static void main(String[] args) {
		Remove_nodes_on_root_to_leaf_paths solution = new Remove_nodes_on_root_to_leaf_paths();
		Serialize_and_Deserialize_Binary_Tree treeBuild = new Serialize_and_Deserialize_Binary_Tree();
		TreeNode root = treeBuild.deserializeBFS("1 2 3 4 5 # 6 7 # # # 8 #");
		solution.removeNode(root, 4);
		System.out.println(treeBuild.serializeBFS(root)); // 1 2 3 4 # # 6 7 # 8 # # # # #
	}
}
