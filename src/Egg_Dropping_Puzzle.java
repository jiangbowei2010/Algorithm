/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
 * The following is a description of the instance of this famous puzzle
 * involving n=2 eggs and a building with k=36 floors.
 * 
 * Suppose that we wish to know which stories in a 36-story building are safe to
 * drop eggs from, and which will cause the eggs to break on landing. We make a
 * few assumptions:
 * 
 * …..An egg that survives a fall can be used again. …..A broken egg must be
 * discarded. …..The effect of a fall is the same for all eggs. …..If an egg
 * breaks when dropped, then it would break if dropped from a higher floor.
 * …..If an egg survives a fall then it would survive a shorter fall. …..It is
 * not ruled out that the first-floor windows break eggs, nor is it ruled out
 * that the 36th-floor do not cause an egg to break.
 * 
 * If only one egg is available and we wish to be sure of obtaining the right
 * result, the experiment can be carried out in only one way. Drop the egg from
 * the first-floor window; if it survives, drop it from the second floor window.
 * Continue upward until it breaks. In the worst case, this method may require
 * 36 droppings. Suppose 2 eggs are available. What is the least number of
 * egg-droppings that is guaranteed to work in all cases? The problem is not
 * actually to find the critical floor, but merely to decide floors from which
 * eggs should be dropped so that total number of trials are minimized.
 */
public class Egg_Dropping_Puzzle {
	public int eggDrop(int n, int k) {
		int[][][] dp = new int[n + 1][k][k];
		for (int egg = 1; egg <= n; egg++) {
			for (int j = 0; j < k; j++) {
				for (int i = j; i >= 0; i--) {
					if (egg == 1) dp[egg][i][j] = j - i + 1;
					else if (i == j) dp[egg][i][j] = 1;
					else {
						dp[egg][i][j] = Integer.MAX_VALUE;
						for (int t = i; t <= j; t++) {
							int left = t == i? 0 : dp[egg - 1][i][t - 1];
							int right = t == j? 0 : dp[egg][t + 1][j];
							dp[egg][i][j] = Math.min(dp[egg][i][j], 1 + Math.max(left, right));
						}
					}
				}
			}
		}
		return dp[n][0][k - 1];
	}
	public static void main(String[] args) {
		Egg_Dropping_Puzzle solution = new Egg_Dropping_Puzzle();
		System.out.println(solution.eggDrop(2, 36)); //8
		System.out.println(solution.eggDrop(2, 10)); //4
	}
}
