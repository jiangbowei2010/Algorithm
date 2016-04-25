/*
 * Leetcode 158: https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * 
 * The return value is the actual number of characters read. For example, it
 * returns 3 if there is only 3 characters left in the file.
 * 
 * By using the read4 API, implement the function int read(char *buf, int n)
 * that reads n characters from the file.
 * 
 * Note: The read function may be called multiple times.
 */
public class Read_N_Characters_Given_Read4_II {
	private String doc = "abdcfdfadfawefefe";
	private int i;
	private int read4(char[] buf) {
		int num = 0;
		while (i < doc.length() && num < 4) {
			buf[num++] = doc.charAt(i++);
		}
		return num;
	}
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    char[] buf4 = new char[4];
    int m = 0;
    
    public int read(char[] buf, int n) {
        int i = 0, j = 0;
        while (i < n && j < m) buf[i++] = buf4[j++];
        if (i == n) {
            for (int k = j; k < m; k++) buf4[k - j] = buf4[k];
            m -= j;
            return n;
        }
        while (true) {
            j = 0;
            m = read4(buf4);
            while (i < n && j < m) buf[i++] = buf4[j++];
            if (i == n) {
                for (int k = j; k < m; k++) buf4[k - j] = buf4[k];
                m -= j;
                return n;
            }
            if (m < 4) {
                m = 0;
                return i;
            }
        }
    }
    
    public static void main(String[] args) {
    	Read_N_Characters_Given_Read4_II solution = new Read_N_Characters_Given_Read4_II();
    	char[] buf = new char[100];
    	int n = 5;
    	System.out.println(solution.read(buf, n)); //5
    	System.out.println(solution.read(buf, n)); //5
    	System.out.println(solution.read(buf, n)); //5
    	System.out.println(solution.read(buf, n)); //2
    }
}
