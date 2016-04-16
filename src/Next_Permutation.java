/*
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column. 1,2,3 -> 1,3,2 3,2,1 ->
 * 1,2,3 1,1,5 -> 1,5,1
 * 
 */
public class Next_Permutation {
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length == 0)
			return;
		int n = nums.length;
		int i = n - 1;
		while (i > 0 && nums[i] <= nums[i - 1])
			i--;
		reverse(nums, i, n - 1);
		if (i == 0)
			return;
		int j = i - 1;
		while (nums[i] <= nums[j])
			i++;
		swap(nums, i, j);
	}

	private void reverse(int[] nums, int i, int j) {
		while (i < j)
			swap(nums, i++, j--);
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	public static void main(String[] args) {
		Next_Permutation solution = new Next_Permutation();
		int[] nums = {1, 2, 3};
		solution.nextPermutation(nums);
		for (int num : nums) {
			System.out.print(num);
			System.out.print(' ');
		}
	}
}
