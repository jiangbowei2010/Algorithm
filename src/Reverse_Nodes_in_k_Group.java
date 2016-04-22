/*
 * Leetcode 25: https://leetcode.com/problems/reverse-nodes-in-k-group/
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 * 
 * If the number of nodes is not a multiple of k then left-out nodes in the end
 * should remain as it is.
 * 
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * 
 * Only constant memory is allowed.
 * 
 * For example, Given this linked list: 1->2->3->4->5
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * For k = 3, you should return: 3->2->1->4->5
 */
public class Reverse_Nodes_in_k_Group {
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || k <= 1)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode x = dummy;
		while (true) {
			ListNode y = x;
			for (int i = 0; i < k && y != null; i++)
				y = y.next;
			if (y == null)
				break;
			ListNode tail = y.next;
			y.next = null;
			ListNode nextX = x.next;
			y = x.next;
			while (y != null) {
				ListNode temp = y.next;
				y.next = tail;
				tail = y;
				y = temp;
			}
			x.next = tail;
			x = nextX;
		}
		return dummy.next;
	}

	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		ListNode e = new ListNode(5);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		Reverse_Nodes_in_k_Group solution = new Reverse_Nodes_in_k_Group();
		ListNode head = solution.reverseKGroup(a, 2);
		while (head != null) {
			System.out.print(head.val);
			System.out.print(" ");
			head = head.next;
		}
	}
}
