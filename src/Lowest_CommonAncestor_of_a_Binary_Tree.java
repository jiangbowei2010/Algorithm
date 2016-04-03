/*
 * Leetcode 236: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes
 * in the tree.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes v and w as the lowest node in T that has both v
 * and w as descendants (where we allow a node to be a descendant of itself).”
 */
public class Lowest_CommonAncestor_of_a_Binary_Tree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p) return p;
        if (root == q) return q;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) return null;
        if (left != null && right != null) return root;
        return left == null? right : left;
    }
    
    public void main(String[] args) {
    	Lowest_CommonAncestor_of_a_Binary_Tree solution = new Lowest_CommonAncestor_of_a_Binary_Tree();
    	Serialize_and_Deserialize_Binary_Tree treeBuild = new Serialize_and_Deserialize_Binary_Tree();
    	TreeNode root = treeBuild.deserializeBFS("1 2 7 4 5 6 # # # # # # #");
    	System.out.println(treeBuild.serializeBFS(root));
    	TreeNode p = root.left;
    	TreeNode q = root.right;
    	TreeNode node = solution.lowestCommonAncestor(root, p, q);
    	System.out.println(String.valueOf(node.val)); //2
    }
}
