/*
 * Leetcode 307 https://leetcode.com/problems/range-sum-query-mutable/
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i <= j), inclusive.
 * 
 * The update(i, val) function modifies nums by updating the element at index i
 * to val. Example: Given nums = [1, 3, 5]
 * 
 * sumRange(0, 2) -> 9 update(1, 2) sumRange(0, 2) -> 8 Note: The array is only
 * modifiable by the update function. You may assume the number of calls to
 * update and sumRange function is distributed evenly.
 */
import java.util.*;

public class Range_Sum_Query_Mutable {
    
    long[] BIT;
    int[] nums;

    public Range_Sum_Query_Mutable(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        BIT = new long[n + 1];
        for (int i = 0; i < n; i++) {
            int index = i + 1;
            while (index <= n) {
                BIT[index] += nums[i];
                index += index & -index;
            }
        }
    }

    void update(int i, int val) {
        int delta = val - nums[i];
        nums[i] = val;
        i++;
        while (i <= nums.length) {
            BIT[i] += delta;
            i += i & -i;
        }
    }

    public int sumRange(int i, int j) {
        return sum(j + 1) - sum(i);
    }
    
    private int sum(int i) {
        int res = 0;
        while (i != 0) {
            res += BIT[i];
            i -= i & -i;
        }
        return res;
    }
    
    public static void main(String[] args) {
    	int[] nums = {1, 3, 5};
    	Range_Sum_Query_Mutable solution = new Range_Sum_Query_Mutable(nums);
    	System.out.println(solution.sumRange(0, 2)); //9
    	solution.update(1, 2);
    	System.out.println(solution.sumRange(0, 2)); //8
    }
}
