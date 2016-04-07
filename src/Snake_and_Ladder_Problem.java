
/*
 * http://www.geeksforgeeks.org/snake-ladder-problem-2/
 * 
 * Given a snake and ladder board, find the minimum number of dice throws
 * required to reach the destination or last cell from source or 1st cell.
 * Basically, the player has total control over outcome of dice throw and wants
 * to find out minimum number of throws required to reach last cell.
 * 
 * If the player reaches a cell which is base of a ladder, the player has to
 * climb up that ladder and if reaches a cell is mouth of the snake, has to go
 * down to the tail of snake without a dice throw.
 * 
 * snakesladders
 * 
 * For example consider the board shown on right side (taken from here), the
 * minimum number of dice throws required to reach cell 30 from cell 1 is 3.
 * Following are steps.
 * 
 * a) First throw two on dice to reach cell number 3 and then ladder to reach 22
 * b) Then throw 6 to reach 28. c) Finally through 2 to reach 30.
 * 
 * There can be other solutions as well like (2, 2, 6), (2, 4, 4), (2, 3, 5)..
 * etc.
 */
import java.util.*;

public class Snake_and_Ladder_Problem {
	// n is the total number of cell
	// move[i] = -1 mean no ladder or snake
	// move[i] > i is a ladder, move[i] < i is a snake
	public int minStep(int[] move, int n) {
		if (n <= 1)
			return 0;
		boolean[] visited = new boolean[n];
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		visited[0] = true;
		int step = 0;
		while (!q.isEmpty()) {
			Queue<Integer> nextQ = new LinkedList<>();
			while (!q.isEmpty()) {
				int x = q.poll();
				if (x == n - 1)
					return step;
				for (int i = x + 1; i < n && i <= x + 6; i++) {
					int y = move[i] >= 0 ? move[i] : i;
					if (!visited[y]) {
						nextQ.offer(y);
						visited[y] = true;
					}
				}
			}
			step++;
			q = nextQ;
		}
		return -1; // can not be reach, like snake at end
	}

	public static void main(String[] args) {
		int n = 30;
		int[] moves = new int[30];
		Arrays.fill(moves, -1);

		// ladder
		moves[2] = 21;
		moves[4] = 7;
		moves[10] = 25;
		moves[19] = 28;

		// Snakes
		moves[26] = 0;
		moves[20] = 8;
		moves[16] = 3;
		moves[18] = 6;

		Snake_and_Ladder_Problem solution = new Snake_and_Ladder_Problem();
		System.out.println(solution.minStep(moves, n));
	}
}
