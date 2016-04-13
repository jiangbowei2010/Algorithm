
class TrieNode {
	// Initialize your data structure here.
	public boolean val = false;
	public TrieNode[] next = new TrieNode[26];
}

public class Implement_Trie {
	private TrieNode root = new TrieNode();

	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode x = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (x.next[index] == null)
				x.next[index] = new TrieNode();
			x = x.next[index];
		}
		x.val = true;
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode x = find(word);
		if (x == null)
			return false;
		return x.val;
	}

	private TrieNode find(String word) {
		TrieNode x = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (x.next[index] == null)
				return null;
			x = x.next[index];
		}
		return x;
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		TrieNode x = find(prefix);
		return x != null;
	}

	public static void main(String[] args) {
		Implement_Trie trie = new Implement_Trie();
		trie.insert("key");
		trie.insert("word");
		trie.insert("world");
		System.out.println(trie.search("word")); // true
		System.out.println(trie.search("wo")); // false
		System.out.println(trie.startsWith("wo")); // true
	}
}
