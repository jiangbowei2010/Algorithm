
/*
 * Leetcode 315: https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * You are given an integer array nums and you have to return a new counts
 * array. The counts array has the property where counts[i] is the number of
 * smaller elements to the right of nums[i].
 * 
 * Example:
 * 
 * Given nums = [5, 2, 6, 1]
 * 
 * To the right of 5 there are 2 smaller elements (2 and 1). To the right of 2
 * there is only 1 smaller element (1). To the right of 6 there is 1 smaller
 * element (1). To the right of 1 there is 0 smaller element. Return the array
 * [2, 1, 1, 0].
 */
import java.util.*;

public class Count_of_Smaller_Numbers_After_Self {

	private class TreeNode {
		private int size, val;
		private TreeNode left, right;

		public TreeNode(int val) {
			this.size = 1;
			this.val = val;
		}
	}

	public List<Integer> countSmaller(int[] nums) {
		LinkedList<Integer> res = new LinkedList<>();
		if (nums == null || nums.length == 0)
			return res;
		TreeNode root = null;
		int[] count = new int[1];
		for (int i = nums.length - 1; i >= 0; i--) {
			count[0] = 0;
			root = add(root, nums[i], count);
			res.addFirst(count[0]);
		}
		return res;
	}

	private TreeNode add(TreeNode x, int val, int[] count) {
		if (x == null)
			return new TreeNode(val);
		if (val < x.val)
			x.left = add(x.left, val, count);
		else if (val > x.val) {
			count[0] += x.size - size(x.right);
			x.right = add(x.right, val, count);
		} else
			count[0] += size(x.left);
		x.size++;
		return x;
	}

	private int size(TreeNode x) {
		if (x == null)
			return 0;
		return x.size;
	}
	
	public static void main(String[] args) {
		Count_of_Smaller_Numbers_After_Self solution = new Count_of_Smaller_Numbers_After_Self();
		int[] nums = {5, 2, 6, 1};
		System.out.println(solution.countSmaller(nums)); //2 1 1 0
		nums = new int[]{};
		System.out.println(solution.countSmaller(nums)); //[]
	}
}
