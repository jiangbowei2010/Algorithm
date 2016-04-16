/*
 * Leetcode 55: https://leetcode.com/problems/jump-game/
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example: A = [2,3,1,1,4], return true.
 * 
 * A = [3,2,1,0,4], return false.
 */
public class Jump_Game {
	public boolean canJump(int[] nums) {
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > max)
				return false;
			max = Math.max(max, i + nums[i]);
		}
		return true;
	}

	public static void main(String[] args) {
		Jump_Game solution = new Jump_Game();
		int[] nums = { 2, 3, 1, 1, 4 };
		System.out.println(solution.canJump(nums));
		nums = new int[] { 3, 2, 1, 0, 4 };
		System.out.println(solution.canJump(nums));
	}
}
