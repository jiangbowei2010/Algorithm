/*
 * Leetcode 68: https://leetcode.com/problems/text-justification/
 * Given an array of words and a length L, format the text such that each line
 * has exactly L characters and is fully (left and right) justified.
 * 
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly L characters.
 * 
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line do not divide evenly between words, the empty
 * slots on the left will be assigned more spaces than the slots on the right.
 * 
 * For the last line of text, it should be left justified and no extra space is
 * inserted between words.
 * 
 * For example, words: ["This", "is", "an", "example", "of", "text",
 * "justification."] L: 16.
 * 
 * Return the formatted lines as: [ "This    is    an", "example  of text",
 * "justification.  " ] Note: Each word is guaranteed not to exceed L in length.
 */
import java.util.*;

public class Text_Justification {
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> res = new ArrayList<>();
		int i = 0, len = 0;
		for (int j = 0; j < words.length; j++) {
			len += words[j].length();
			if (len + j - i > maxWidth) {
				len -= words[j].length();
				int blank = maxWidth - len;
				StringBuilder sb = new StringBuilder();
				if (i == j - 1) {
					sb.append(words[i++]);
					for (int k = 0; k < blank; k++)
						sb.append(' ');
				} else {
					int gap = blank / (j - i - 1);
					int extra = blank % (j - i - 1);
					while (i < j) {
						sb.append(words[i++]);
						if (i != j) {
							for (int k = 0; k < gap; k++)
								sb.append(' ');
						}
						if (extra-- > 0)
							sb.append(' ');
					}
				}
				res.add(sb.toString());
				len = words[j].length();
			}
		}
		int last = maxWidth - (len + words.length - i - 1);
		StringBuilder sb = new StringBuilder();
		while (i < words.length) {
			sb.append(words[i++]);
			if (i == words.length) {
				for (int k = 0; k < last; k++)
					sb.append(' ');
			} else
				sb.append(' ');
		}
		res.add(sb.toString());
		return res;
	}
	
	public static void main(String[] args) {
		String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
		Text_Justification solution = new Text_Justification();
		System.out.println(solution.fullJustify(words, 16));
	}
}
