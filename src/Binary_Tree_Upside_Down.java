/*
 * Leetcode 156 : https://leetcode.com/problems/binary-tree-upside-down/
 * Given a binary tree where all the right nodes are either leaf nodes with a
 * sibling (a left node that shares the same parent node) or empty, flip it
 * upside down and turn it into a tree where the original right nodes turned
 * into left leaf nodes. Return the new root.
 * 
 * For example: Given a binary tree {1,2,3,4,5}, 1 / \ 2 3 / \ 4 5 return the
 * root of the binary tree [4,5,2,#,#,3,1]. 4 / \ 5 2 / \ 3 1
 */
public class Binary_Tree_Upside_Down {
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		TreeNode left = null, right = null;
		TreeNode x = root;
		while (x != null) {
			TreeNode nodeLeft = x.left;
			TreeNode nodeRight = x.right;
			x.left = left;
			x.right = right;
			right = x;
			left = nodeRight;
			x = nodeLeft;
		}
		return right;
	}

	public static void main(String[] args) {
		Binary_Tree_Upside_Down solution = new Binary_Tree_Upside_Down();
		Serialize_and_Deserialize_Binary_Tree buildTree = new Serialize_and_Deserialize_Binary_Tree();
		TreeNode root = buildTree.deserializeBFS("1 2 3 4 5");
		root = solution.upsideDownBinaryTree(root);
		System.out.println(buildTree.serializeBFS(root)); //4 5 2 # # 3 1
	}
}
