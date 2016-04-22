/*
 * Leetcode 148: https://leetcode.com/problems/sort-list/
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class Sort_List {
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode x = head;
		int n = 0;
		while (x != null) {
			x = x.next;
			n++;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		for (int size = 1; size < n; size *= 2) {
			x = dummy;
			while (true) {
				ListNode p1 = x;
				for (int i = 0; i < size; i++) {
					if (p1 == null)
						break;
					p1 = p1.next;
				}
				if (p1 == null || p1.next == null)
					break;
				ListNode p2 = p1;
				for (int i = 0; i < size; i++) {
					if (p2.next == null)
						break;
					p2 = p2.next;
				}
				ListNode tail = p2.next;
				p2.next = null;
				p2 = p1.next;
				p1.next = null;
				p1 = x.next;
				while (p1 != null || p2 != null) {
					if (p1 == null) {
						x.next = p2;
						p2 = p2.next;
					} else if (p2 == null) {
						x.next = p1;
						p1 = p1.next;
					} else if (p1.val < p2.val) {
						x.next = p1;
						p1 = p1.next;
					} else {
						x.next = p2;
						p2 = p2.next;
					}
					x = x.next;
				}
				x.next = tail;
			}
		}
		return dummy.next;
	}

	public static void main(String[] args) {
		ListNode a = new ListNode(4);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(7);
		ListNode d = new ListNode(-2);
		a.next = b;
		b.next = c;
		c.next = d;
		Sort_List solution = new Sort_List();
		ListNode head = solution.sortList(a);
		while (head != null) {
			System.out.print(head.val);
			System.out.print(" ");
			head = head.next;
		}
	}
}
