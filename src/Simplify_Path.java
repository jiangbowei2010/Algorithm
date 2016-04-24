/*
 * Leetcode 71: https://leetcode.com/problems/simplify-path/
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example, path = "/home/", => "/home" path = "/a/./b/../../c/", => "/c"
 */
import java.util.*;

public class Simplify_Path {
    public String simplifyPath(String path) {
        String[] dirs = path.split("/");
        Deque<String> dq = new LinkedList<>();
        for (String dir : dirs) {
            switch(dir) {
                case "":
                case ".": break;
                case "..":
                    if (!dq.isEmpty()) dq.pollLast();
                    break;
                default: dq.offerLast(dir);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            sb.append('/');
            sb.append(dq.pollFirst());
        }
        if (sb.length() == 0) return "/";
        return sb.toString();
    }
    
    public static void main(String[] args) {
    	Simplify_Path solution = new Simplify_Path();
    	System.out.println(solution.simplifyPath("/home//foo/"));
    	System.out.println(solution.simplifyPath("/../"));
    }
}
