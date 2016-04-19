/*
 * Leetcode 142: https://leetcode.com/problems/linked-list-cycle-ii/
 * Given a linked list, return the node where the cycle begins. If there is no
 * cycle, return null.
 * 
 * Note: Do not modify the linked list.
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class Linked_List_Cycle_II {
	public ListNode detectCycle(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast)
				break;
		}
		if (fast == null || fast.next == null)
			return null;
		ListNode x = head;
		while (slow != x) {
			x = x.next;
			slow = slow.next;
		}
		return x;
	}
	
	public static void main(String[] args) {
		Linked_List_Cycle_II solution = new Linked_List_Cycle_II();
		ListNode a = new ListNode(0);
		ListNode b = new ListNode(1);
		ListNode c = new ListNode(2);
		ListNode d = new ListNode(3);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = b;
		ListNode x = solution.detectCycle(a);
		if (x == null) {
			System.out.println("NULL");
		}
		else {
			System.out.println(x.val);
		}
	}
}

