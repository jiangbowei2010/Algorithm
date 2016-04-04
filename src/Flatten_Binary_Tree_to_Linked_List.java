/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
*/
public class Flatten_Binary_Tree_to_Linked_List {
	private TreeNode pre = null;

	public void flatten(TreeNode root) {
		if (root == null)
			return;
		flatten(root.right);
		flatten(root.left);
		root.left = null;
		root.right = pre;
		pre = root;
	}
	
	public static void main(String[] args) {
		Serialize_and_Deserialize_Binary_Tree treeBuild = new Serialize_and_Deserialize_Binary_Tree();
		Flatten_Binary_Tree_to_Linked_List solution = new Flatten_Binary_Tree_to_Linked_List();
		TreeNode root = treeBuild.deserializeBFS("1 2 5 3 4 # 6 # # # # # #");
		solution.flatten(root);
		while (root != null) {
			System.out.print(String.valueOf(root.val) + " "); // 1 2 3 4 5 6
			root = root.right;
		}
	}
}
