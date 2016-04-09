/*
 * Leetcode 78: https://leetcode.com/problems/subsets/
 * Given a set of distinct integers, nums, return all possible subsets.
 * 
 * Note: Elements in a subset must be in non-descending order. The solution set
 * must not contain duplicate subsets. For example, If nums = [1,2,3], a
 * solution is:
 * 
 * [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 */
import java.util.*;

public class Subsets {
	
	//Method 1: use backtracking
	public List<List<Integer>> subsetsDFS(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		Arrays.sort(nums);
		dfs(nums, 0, path, res);
		return res;
	}

	private void dfs(int[] nums, int d, List<Integer> path, List<List<Integer>> res) {
		if (d == nums.length)
			res.add(new ArrayList<>(path));
		else {
			path.add(nums[d]);
			dfs(nums, d + 1, path, res);
			path.remove(path.size() - 1);
			dfs(nums, d + 1, path, res);
		}
	}
	
	//Method 2, iteration
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        res.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int size = res.size();
            for (int k = 0; k < size; k++) {
                List<Integer> list = new ArrayList<>(res.get(k));
                list.add(nums[i]);
                res.add(list);
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
    	Subsets solution = new Subsets();
    	int[] nums = {1, 2, 3};
    	System.out.println(solution.subsets(nums));
    	System.out.println(solution.subsetsDFS(nums));
    }
}
