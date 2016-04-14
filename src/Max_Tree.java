
/*
 * Given an integer array with no duplicates. A max tree building on this array
 * is defined as follow:
 * 
 * The root is the maximum number in the array The left subtree and right
 * subtree are the max trees of the subarray divided by the root number.
 * Construct the max tree by the given array.
 */
import java.util.*;

public class Max_Tree {

	public TreeNode generateTree(int[] nums) {
		if (nums == null || nums.length == 0)
			return null;
		Deque<TreeNode> stack = new LinkedList<>();
		for (int num : nums) {
			TreeNode right = null;
			while (!stack.isEmpty() && stack.peek().val < num) {
				TreeNode x = stack.pop();
				x.right = right;
				right = x;
			}
			TreeNode x = new TreeNode(num);
			x.left = right;
			stack.push(x);
		}
		TreeNode right = null;
		while (!stack.isEmpty()) {
			TreeNode x = stack.pop();
			x.right = right;
			right = x;
		}
		return right;
	}

	public static void main(String[] args) {
		Max_Tree solution = new Max_Tree();
		int[] nums = { 3, 1, 5, 2 };
		TreeNode x = solution.generateTree(nums);
		Serialize_and_Deserialize_Binary_Tree buildtree = new Serialize_and_Deserialize_Binary_Tree();
		System.out.println(buildtree.serializeBFS(x)); // 5 3 2 # 1 # #
	}
}
