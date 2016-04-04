/*
 * Leetcode 222 : https://leetcode.com/problems/count-complete-tree-nodes/
 * Given a complete binary tree, count the number of nodes.
 * 
 * Definition of a complete binary tree from Wikipedia: In a complete binary
 * tree every level, except possibly the last, is completely filled, and all
 * nodes in the last level are as far left as possible. It can have between 1
 * and 2h nodes inclusive at the last level h
 */
public class Count_Complete_Tree_Nodes {
	public int countNodes(TreeNode root) {
		int left = countLeft(root);
		int right = countRight(root);
		int res = 0;
		TreeNode x = root;
		while (true) {
			if (left == right) {
				res += (1 << left) - 1;
				break;
			} else {
				int mid = countRight(x.left);
				if (left == mid + 1) {
					res += 1 << left - 1;
					x = x.right;
					left = countLeft(x);
					right--;
				} else {
					res += 1 << right - 1;
					x = x.left;
					left--;
					right = mid;
				}
			}
		}
		return res;
	}

	private int countLeft(TreeNode x) {
		int res = 0;
		while (x != null) {
			x = x.left;
			res++;
		}
		return res;
	}

	private int countRight(TreeNode x) {
		int res = 0;
		while (x != null) {
			x = x.right;
			res++;
		}
		return res;
	}

	public static void main(String[] args) {
		Count_Complete_Tree_Nodes solution = new Count_Complete_Tree_Nodes();
		Serialize_and_Deserialize_Binary_Tree treeBuild = new Serialize_and_Deserialize_Binary_Tree();
		TreeNode root = treeBuild.deserializeBFS("1 2 3 4 5 # # # # # #");
		System.out.println(solution.countNodes(root)); // 5
		root = null;
		System.out.println(solution.countNodes(root)); // 0
	}
}
