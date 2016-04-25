/*
 * Leetcode 273: https://leetcode.com/problems/integer-to-english-words/
 * Convert a non-negative integer to its english words representation. Given
 * input is guaranteed to be less than 231 - 1.
 * 
 * For example, 123 -> "One Hundred Twenty Three" 12345 ->
 * "Twelve Thousand Three Hundred Forty Five" 1234567 ->
 * "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class Integer_to_English_Words {

	private String[] ones = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
			"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
	private String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

	public String numberToWords(int num) {
		if (num <= 0)
			return "Zero";
		String[] strs = { "", "Thousand", "Million", "Billion" };
		int[] nums = { 1, 1000, 1000000, 1000000000 };
		StringBuilder sb = new StringBuilder();
		for (int i = 3; i >= 0 && num > 0; i--) {
			if (num >= nums[i]) {
				if (sb.length() > 0)
					sb.append(' ');
				sb.append(convertThousand(num / nums[i]));
				if (i > 0) {
					sb.append(' ');
					sb.append(strs[i]);
				}
				num %= nums[i];
			}
		}
		return sb.toString();
	}

	private String convertThousand(int num) {
		if (num < 20)
			return ones[num];
		if (num < 100)
			return tens[num / 10] + ((num % 10 == 0) ? "" : " " + ones[num % 10]);
		return ones[num / 100] + " Hundred" + ((num % 100 == 0) ? "" : " " + convertThousand(num % 100));
	}
	
	public static void main(String[] args) {
		Integer_to_English_Words solution = new Integer_to_English_Words();
		System.out.println(solution.numberToWords(0));
		System.out.println(solution.numberToWords(123));
		System.out.println(solution.numberToWords(12345));
		System.out.println(solution.numberToWords(1234567));
		System.out.println(solution.numberToWords(1234567890));
	}
}
