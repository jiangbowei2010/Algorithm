/*
 * Follow up for "Remove Duplicates": What if duplicates are allowed at most
 * twice?
 * 
 * For example, Given sorted array nums = [1,1,1,2,2,3],
 * 
 * Your function should return length = 5, with the first five elements of nums
 * being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new
 * length.
 */
public class Remove_Duplicates_from_Sorted_Array_II {
	public int removeDuplicates(int[] nums) {
		int j = 0;
		int dup = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				dup++;
			else
				dup = 1;
			if (dup <= 2)
				nums[j++] = nums[i];
		}
		return j;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 1, 1, 2, 2, 3 };
		Remove_Duplicates_from_Sorted_Array_II solution = new Remove_Duplicates_from_Sorted_Array_II();
		int len = solution.removeDuplicates(nums);
		for (int i = 0; i < len; i++) {
			System.out.print(nums[i]);
			System.out.print(" ");
		}
	}
}
