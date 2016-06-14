// Use greedy, in final result, the char[i]: (1) if s[i - k] not selected, choose this one. Or 
//(2) choose min char in the window of s(i - k, i + k] -- left open ,right close
// use increasing deque to maintain the window

import java.util.*;

public class StrArrange {
	
	private class Node implements Comparable<Node> {
		private int index;
		private char c;
		public Node (int index, char c) {
			this.index = index;
			this.c = c;
		}
		@Override
		public int compareTo(Node that) {
			if (this.c != that.c) return this.c - that.c;
			return this.index - that.index;
		}
	}
	
	public String minString(String s, int k) {
		Map<Integer, Node> map = new HashMap<>();
		TreeSet<Node> set = new TreeSet<>();
		StringBuilder sb = new StringBuilder();
		boolean[] selected = new boolean[s.length()];
		for (int i = 0; i < s.length() && i < k; i++) {
			Node x = new Node(i, s.charAt(i));
			set.add(x);
			map.put(i, x);
		}
		for (int i = 0; i < s.length(); i++) {
			if (i + k < s.length()) {
				Node x = new Node(i + k, s.charAt(i + k));
				map.put(i + k, x);
				set.add(x);
			}
			if (i - k >= 0) set.remove(map.get(i - k));
			if (i - k >= 0 && !selected[i - k]) {
				sb.append(s.charAt(i - k));
				selected[i - k] = true;
			}
			else {
				Node x = set.pollFirst();
				selected[x.index] = true;
				sb.append(x.c);
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		StrArrange sol = new StrArrange();
		System.out.println(sol.minString("goog", 1));
		System.out.println(sol.minString("goog", 2));
		System.out.println(sol.minString("dcba", 1));
		System.out.println(sol.minString("dcba", 2));
		System.out.println(sol.minString("dcba", 3));
		
	}
}
