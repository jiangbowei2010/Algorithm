/*
 * Leetcode 287: https://leetcode.com/problems/find-the-duplicate-number/
 * Given an array nums containing n + 1 integers where each integer is between 1
 * and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * 
 * Note: You must not modify the array (assume the array is read only). You must
 * use only constant, O(1) extra space. Your runtime complexity should be less
 * than O(n2). There is only one duplicate number in the array, but it could be
 * repeated more than once.
 */

public class Find_the_Duplicate_Number {
	
	//Method 1: find cycle in linkedlist way
	public int findDuplicate(int[] nums) {
		int slow = 0, fast = 0;
		while (true) {
			slow = nums[slow];
			fast = nums[nums[fast]];
			if (slow == fast)
				break;
		}
		int x = 0;
		while (slow != x) {
			x = nums[x];
			slow = nums[slow];
		}
		return x;
	}
	
	//Method 2: use binary search
    public int findDuplicateII(int[] nums) {
        int n = nums.length - 1;
        int lo = 1, hi = n;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int num = count(nums, lo, mid);
            if (num > mid - lo + 1) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
    
    private int count(int[] nums, int lo, int hi) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= hi && nums[i] >= lo) res++;
        }
        return res;
    }
    
    public static void main(String[] args) {
    	Find_the_Duplicate_Number solution = new Find_the_Duplicate_Number();
    	int[] nums = {1, 2, 2, 3};
    	System.out.println(solution.findDuplicate(nums));
    	System.out.println(solution.findDuplicateII(nums));
    }
}
