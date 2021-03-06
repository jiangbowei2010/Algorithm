
/*
 * http://www.geeksforgeeks.org/bottom-view-binary-tree/
 * Given a Binary Tree, we need to print the bottom view from left to right. A
 * node x is there in output if x is the bottommost node at its horizontal
 * distance. Horizontal distance of left child of a node x is equal to
 * horizontal distance of x minus 1, and that of right child is horizontal
 * distance of x plus 1.
 * 
 * Examples:
 * 
 * 20 / \ 8 22 / \ \ 5 3 25 / \ 10 14
 * 
 * For the above tree the output should be 5, 10, 3, 14, 25.
 */

import java.util.*;

public class Bottom_View_of_a_Binary_Tree {
	private class ListNode {
		private int val;
		private ListNode pre, next;

		public ListNode(int val) {
			this.val = val;
		}
	}

	public List<Integer> bottomView(TreeNode root) {
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
				} else
					y.pre.val = x.left.val;
				q1.offer(x.left);
				q2.offer(y.pre);
			}
			if (x.right != null) {
				if (y.next == null) {
					y.next = new ListNode(x.right.val);
					y.next.pre = y;
				} else
					y.next.val = x.right.val;
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
		Serialize_and_Deserialize_Binary_Tree treeBuild = new Serialize_and_Deserialize_Binary_Tree();
		Bottom_View_of_a_Binary_Tree solution = new Bottom_View_of_a_Binary_Tree();
		TreeNode root = treeBuild.deserializeBFS("20 8 22 5 3 # 25 # # 10 14");
		System.out.println(solution.bottomView(root)); // 5 10 3 14 25
	}
}
