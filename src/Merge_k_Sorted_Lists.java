/*
 * Leetcode 23: https://leetcode.com/problems/merge-k-sorted-lists/
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 */
public class Merge_k_Sorted_Lists {
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		for (int size = 1; size < lists.length; size *= 2) {
			for (int i = 0; i < lists.length - size; i += 2 * size) {
				lists[i] = merge(lists[i], lists[i + size]);
			}
		}
		return lists[0];
	}

	private ListNode merge(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode x = dummy;
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				x.next = l2;
				l2 = l2.next;
			} else if (l2 == null) {
				x.next = l1;
				l1 = l1.next;
			} else if (l1.val < l2.val) {
				x.next = l1;
				l1 = l1.next;
			} else {
				x.next = l2;
				l2 = l2.next;
			}
			x = x.next;
		}
		return dummy.next;
	}
	
	public static void main(String[] args) {
		Merge_k_Sorted_Lists solution = new Merge_k_Sorted_Lists();
		ListNode a = new ListNode(3);
		ListNode b = new ListNode(7);
		ListNode c = new ListNode(2);
		ListNode d = new ListNode(5);
		a.next = b;
		c.next = d;
		ListNode[] lists = {a, c};
		ListNode head = solution.mergeKLists(lists);
		while (head != null) {
			System.out.print(head.val);
			System.out.print(" ");
			head = head.next;
		}
	}
}
