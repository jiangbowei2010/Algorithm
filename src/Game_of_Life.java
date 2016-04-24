/*
 * Leetcode 289: https://leetcode.com/problems/game-of-life/
 * According to the Wikipedia's article:
 * "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * 
 * Given a board with m by n cells, each cell has an initial state live (1) or
 * dead (0). Each cell interacts with its eight neighbors (horizontal, vertical,
 * diagonal) using the following four rules (taken from the above Wikipedia
 * article):
 * 
 * Any live cell with fewer than two live neighbors dies, as if caused by
 * under-population. Any live cell with two or three live neighbors lives on to
 * the next generation. Any live cell with more than three live neighbors dies,
 * as if by over-population.. Any dead cell with exactly three live neighbors
 * becomes a live cell, as if by reproduction. Write a function to compute the
 * next state (after one update) of the board given its current state.
 * 
 * Follow up: Could you solve it in-place? Remember that the board needs to be
 * updated at the same time: You cannot update some cells first and then use
 * their updated values to update other cells. In this question, we represent
 * the board using a 2D array. In principle, the board is infinite, which would
 * cause problems when the active area encroaches the border of the array. How
 * would you address these problems?
 */
public class Game_of_Life {
	public void gameOfLife(int[][] board) {
		if (board == null || board.length == 0)
			return;
		int m = board.length, n = board[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 1) {
					int num = count(board, i, j);
					if (num < 2 || num > 3)
						board[i][j] = 2;
				} else if (count(board, i, j) == 3)
					board[i][j] = 3;
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] %= 2;
			}
		}
	}

	private int count(int[][] board, int i, int j) {
		int res = 0;
		for (int k = -1; k <= 1; k++) {
			for (int t = -1; t <= 1; t++) {
				int x = i + k, y = j + t;
				if (x < 0 || y < 0 || x >= board.length || y >= board[0].length)
					continue;
				if (k == 0 && t == 0)
					continue;
				if (board[x][y] == 1 || board[x][y] == 2)
					res++;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[][] board = {{1, 0, 0, 1}, {1, 1, 0, 1}, {0, 1, 1, 0}, {0, 0, 0, 1}};
		Game_of_Life solution = new Game_of_Life();
		solution.gameOfLife(board);
		solution.gameOfLife(board);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
}
