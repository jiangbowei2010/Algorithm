/*
 * Leetcode 275: https://leetcode.com/problems/h-index-ii/
 * Follow up for H-Index: What if the citations array is sorted in ascending
 * order? Could you optimize your algorithm?
 */
public class H_Index_II {
    public int hIndex(int[] citations) {
        int lo = 0, hi = citations.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (citations[mid] >= citations.length - mid) hi = mid - 1;
            else lo = mid + 1;
        }
        return citations.length - lo;
    }
    
    public static void main(String[] args) {
    	int[] nums = {0, 0, 0, 1, 2, 2};
    	H_Index_II solution = new H_Index_II();
    	System.out.println(solution.hIndex(nums));
    }
}
