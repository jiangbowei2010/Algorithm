/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-37-boolean-
 * parenthesization-problem/ Given a boolean expression with following
 * symbols.
 * Symbols 'T' ---> true 'F' ---> false And following operators filled
 * between symbols
 * Operators & ---> boolean AND | ---> boolean OR ^ ---> boolean XOR Count
 * the number of ways we can parenthesize the expression so that the value
 * of expression evaluates to true.
 * Let the input be in form of two arrays one contains the symbols (T and F)
 * in order and other contains operators (&, | and ^}
 */

public class Boolean_Parenthesization_Problem {
	public int numParentheis(char[] symbol, char[] operator) {
		int n = symbol.length;
		int[][] numTrue = new int[n][n];
		int[][] numFalse = new int[n][n];
		for (int len = 1; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				if (len == 1) {
					numTrue[i][i] = symbol[i] == 'T' ? 1 : 0;
					numFalse[i][i] = symbol[i] == 'F' ? 1 : 0;
				} else {
					int hi = i + len - 1;
					for (int k = i; k < hi; k++) {
						switch (operator[k]) {
						case '&':
							numTrue[i][hi] += numTrue[i][k] * numTrue[k + 1][hi];
							numFalse[i][hi] += numFalse[i][k] * numTrue[k + 1][hi];
							numFalse[i][hi] += numTrue[i][k] * numFalse[k + 1][hi];
							numFalse[i][hi] += numFalse[i][k] * numFalse[k + 1][hi];
							break;
						case '|':
							numTrue[i][hi] += numTrue[i][k] * numTrue[k + 1][hi];
							numTrue[i][hi] += numFalse[i][k] * numTrue[k + 1][hi];
							numTrue[i][hi] += numTrue[i][k] * numFalse[k + 1][hi];
							numFalse[i][hi] += numFalse[i][k] * numFalse[k + 1][hi];
							break;
						case '^':
							numFalse[i][hi] += numTrue[i][k] * numTrue[k + 1][hi];
							numTrue[i][hi] += numFalse[i][k] * numTrue[k + 1][hi];
							numTrue[i][hi] += numTrue[i][k] * numFalse[k + 1][hi];
							numFalse[i][hi] += numFalse[i][k] * numFalse[k + 1][hi];
							break;
						default:
						}
					}
				}
			}
		}
		return numTrue[0][n - 1];
	}

	public static void main(String[] args) {
		char[] symbol = "TTFT".toCharArray();
		char[] operator = "|&^".toCharArray();
		// There are 4 ways
		// ((T|T)&(F^T)), (T|(T&(F^T))), (((T|T)&F)^T) and (T|((T&F)^T))
		Boolean_Parenthesization_Problem solution = new Boolean_Parenthesization_Problem();
		System.out.println(solution.numParentheis(symbol, operator));
	}
}
