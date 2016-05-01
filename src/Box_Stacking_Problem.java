import java.util.List;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem/
 * You are given a set of n types of rectangular 3-D boxes, where the i^th box
 * has height h(i), width w(i) and depth d(i) (all real numbers). You want to
 * create a stack of boxes which is as tall as possible, but you can only stack
 * a box on top of another box if the dimensions of the 2-D base of the lower
 * box are each strictly larger than those of the 2-D base of the higher box. Of
 * course, you can rotate a box so that any side functions as its base. It is
 * also allowable to use multiple instances of the same type of box. Following
 * are the key points to note in the problem statement: 1) A box can be placed
 * on top of another box only if both width and depth of the upper placed box
 * are smaller than width and depth of the lower box respectively. 2) We can
 * rotate boxes. For example, if there is a box with dimensions {1x2x3} where 1
 * is height, 2×3 is base, then there can be three possibilities, {1x2x3},
 * {2x1x3} and {3x1x2}. 3) We can use multiple instances of boxes. What it means
 * is, we can have two different rotations of a box as part of our maximum
 * height stack.
 */
import java.util.*;

public class Box_Stacking_Problem {
	
	private class BoxComparator implements Comparator<int[]> {
		@Override
		public int compare(int[] box1, int[] box2) {
			return box1[0] * box1[1] - box2[0] * box2[1];
		}
	}
	
	public int maxHeight(int[][] boxArr) {
		int n = boxArr.length;
		List<int[]> list = new ArrayList<>();
		for (int[] box : boxArr) {
			list.add(new int[]{box[0], box[1], box[2]});
			list.add(new int[]{box[0], box[2], box[1]});
			list.add(new int[]{box[1], box[2], box[0]});
		}
		Collections.sort(list, new BoxComparator());
		int[] height = new int[3 * n];
		int res = 0;
		for (int i = 0; i < 3 * n; i++) {
			height[i] = list.get(i)[2];
			for (int k = 0; k < i; k++) {
				if (canOntop(list.get(k), list.get(i))) {
					height[i] = Math.max(height[i], list.get(i)[2] + height[k]);
				}
			}
			res = Math.max(res, height[i]);
		}
		return res;
	}
	
	private boolean canOntop(int[] box1, int[] box2) {
		if (box1[0] < box2[0] && box1[1] < box2[1]) return true;
		if (box1[0] < box2[1] && box1[1] < box2[0]) return true;
		return false;
	}
	
	public static void main(String[] args) {
		Box_Stacking_Problem solution = new Box_Stacking_Problem();
		int[][] boxArr = { {4, 6, 7}, {1, 2, 3}, {4, 5, 6}, {10, 12, 32} };
		System.out.println(solution.maxHeight(boxArr)); //60
	}
}
