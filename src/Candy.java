/*
 * Leetcode 135: https://leetcode.com/problems/candy/
 * There are N children standing in a line. Each child is assigned a rating
 * value.
 * 
 * You are giving candies to these children subjected to the following
 * requirements:
 * 
 * Each child must have at least one candy. Children with a higher rating get
 * more candies than their neighbors. What is the minimum candies you must give?
 */
public class Candy {
	
	//Method 1: 3 iteration, O(n) time and O(n) complexity
	public int candy(int[] ratings) {
		if (ratings == null || ratings.length == 0)
			return 0;
		int n = ratings.length;
		int[] candy = new int[n];
		for (int i = 0; i < n; i++) {
			if (i > 0 && ratings[i] > ratings[i - 1])
				candy[i] = 1 + candy[i - 1];
			else
				candy[i] = 1;
		}
		for (int i = n - 1; i >= 0; i--) {
			if (i < n - 1 && ratings[i] > ratings[i + 1])
				candy[i] = Math.max(candy[i], 1 + candy[i + 1]);
		}
		int res = 0;
		for (int i = 0; i < n; i++)
			res += candy[i];
		return res;
	}
	
	public static void main(String[] args) {
		int[] ratings = {1, 2, 3, 3, 3};
		Candy solution = new Candy();
		System.out.println(solution.candy(ratings));
	}
}
