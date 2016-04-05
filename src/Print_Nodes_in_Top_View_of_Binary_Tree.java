
/*
 * http://www.geeksforgeeks.org/print-nodes-top-view-binary-tree/
 * Top view of a binary tree is the set of nodes visible when the tree is viewed
 * from the top. Given a binary tree, print the top view of it. The output nodes
 * can be printed in any order. Expected time complexity is O(n)
 * 
 * A node x is there in output if x is the topmost node at its horizontal
 * distance. Horizontal distance of left child of a node x is equal to
 * horizontal distance of x minus 1, and that of right child is horizontal
 * distance of x plus 1.
 * 
 * 1 / \ 2 3 / \ / \ 4 5 6 7 Top view of the above binary tree is 4 2 1 3 7
 * 
 * 1 / \ 2 3 \ 4 \ 5 \ 6 Top view of the above binary tree is 2 1 3 6
 */
import java.util.*;


public class Print_Nodes_in_Top_View_of_Binary_Tree {
	private class ListNode {
		private int val;
		private ListNode pre, next;

		public ListNode(int val) {
			this.val = val;
		}
	}

	public List<Integer> topView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null)
			return res;
		ListNode node = new ListNode(root.val);
		Queue<TreeNode> q1 = new LinkedList<>();
		Queue<ListNode> q2 = new LinkedList<>();
		q1.offer(root);
		q2.offer(node);
		while (!q1.isEmpty()) {
			TreeNode x = q1.poll();
			ListNode y = q2.poll();
			if (x.left != null) {
				if (y.pre == null) {
					y.pre = new ListNode(x.left.val);
					y.pre.next = y;
				}
				q1.offer(x.left);
				q2.offer(y.pre);
			}
			if (x.right != null) {
				if (y.next == null) {
					y.next = new ListNode(x.right.val);
					y.next.pre = y;
				}
				q1.offer(x.right);
				q2.offer(y.next);
			}
		}
		while (node.pre != null)
			node = node.pre;
		while (node != null) {
			res.add(node.val);
			node = node.next;
		}
		return res;
	}

	public static void main(String[] args) {
		Print_Nodes_in_Top_View_of_Binary_Tree solution = new Print_Nodes_in_Top_View_of_Binary_Tree();
		Serialize_and_Deserialize_Binary_Tree treeBuild = new Serialize_and_Deserialize_Binary_Tree();
		TreeNode root = treeBuild.deserializeBFS("1 2 3 4 5 6 7");
		System.out.println(solution.topView(root)); // 4 2 1 3 7
		root = treeBuild.deserializeBFS("1 2 3 # 4 # # # 5 # 6");
		System.out.println(solution.topView(root)); // 2 1 3 6
	}
}
