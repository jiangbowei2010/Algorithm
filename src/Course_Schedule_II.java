
/*
 * Leetcode 210: https://leetcode.com/problems/course-schedule-ii/
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return
 * the ordering of courses you should take to finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If
 * it is impossible to finish all courses, return an empty array.
 * 
 * For example:
 * 
 * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0. So the correct course order is [0,1]
 * 
 * 4, [[1,0],[2,0],[3,1],[3,2]] There are a total of 4 courses to take. To take
 * course 3 you should have finished both courses 1 and 2. Both courses 1 and 2
 * should be taken after you finished course 0. So one correct course order is
 * [0,1,2,3]. Another correct ordering is[0,2,1,3].
 */
import java.util.*;

public class Course_Schedule_II {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0 || prerequisites == null || prerequisites.length == 0)
			return new int[0];
		List<Integer>[] adj = new List[numCourses];
		for (int i = 0; i < numCourses; i++) {
			adj[i] = new LinkedList<>();
		}
		for (int[] pre : prerequisites) {
			adj[pre[1]].add(pre[0]);
		}
		boolean[] visited = new boolean[numCourses];
		boolean[] onStack = new boolean[numCourses];
		boolean[] hasCycle = new boolean[1];
		int[] res = new int[numCourses];
		int[] index = new int[1];
		index[0] = numCourses - 1;
		for (int v = 0; v < numCourses; v++) {
			if (hasCycle[0])
				break;
			if (!visited[v]) {
				dfs(adj, v, visited, onStack, hasCycle, res, index);
			}
		}
		if (hasCycle[0])
			return new int[0];
		return res;
	}

	private void dfs(List<Integer>[] adj, int v, boolean[] visited, boolean[] onStack, boolean[] hasCycle, int[] res,
			int[] index) {
		visited[v] = true;
		onStack[v] = true;
		for (int w : adj[v]) {
			if (hasCycle[0])
				break;
			else if (!visited[w])
				dfs(adj, w, visited, onStack, hasCycle, res, index);
			else if (onStack[w])
				hasCycle[0] = true;
		}
		onStack[v] = false;
		res[index[0]--] = v;
	}

	public static void main(String[] args) {
		Course_Schedule_II solution = new Course_Schedule_II();
		int[][] prerequisites = { { 1, 0 } };
		int[] res = solution.findOrder(2, prerequisites); // [0, 1]
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i]);
			System.out.print(" ");
		}
		System.out.println();
		prerequisites = new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
		res = solution.findOrder(4, prerequisites); // [0 1 2 3]
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i]);
			System.out.print(" ");
		}
	}
}
