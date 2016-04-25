/*
 * Leetcode 165: https://leetcode.com/problems/compare-version-numbers/
 * Compare two version numbers version1 and version2. If version1 > version2
 * return 1, if version1 < version2 return -1, otherwise return 0.
 * 
 * You may assume that the version strings are non-empty and contain only digits
 * and the . character. The . character does not represent a decimal point and
 * is used to separate number sequences. For instance, 2.5 is not
 * "two and a half" or "half way to version three", it is the fifth second-level
 * revision of the second first-level revision.
 * 
 * Here is an example of version numbers ordering:
 * 
 * 0.1 < 1.1 < 1.2 < 13.37
 */
public class Compare_Version_Numbers {
	public int compareVersion(String version1, String version2) {
		int i = 0, j = 0;
		while (i < version1.length() || j < version2.length()) {
			int num1 = 0, num2 = 0;
			while (i < version1.length() && version1.charAt(i) != '.')
				num1 = num1 * 10 + version1.charAt(i++) - '0';
			while (j < version2.length() && version2.charAt(j) != '.')
				num2 = num2 * 10 + version2.charAt(j++) - '0';
			if (num1 > num2)
				return 1;
			if (num1 < num2)
				return -1;
			i++;
			j++;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Compare_Version_Numbers solution = new Compare_Version_Numbers();
		System.out.println(solution.compareVersion("0.1", "1.1"));
		System.out.println(solution.compareVersion("13.37.1", "13.37"));
	}
}
