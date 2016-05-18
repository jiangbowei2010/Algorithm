import java.util.*;

/*
 * index  person  votes  rank
 * 0      a       10     0
 * 1      b       8      1
 * 2      c       8      1
 * 3      d       8      1
 * 4      e       5      4
 * if vote(d), d-->9, swap row3 and row1
 */
public class TopKVote {
	Map<String, Integer> personToVotes = new HashMap<>();
	Map<String, Integer> personToIndex = new HashMap<>();
	Map<Integer, Integer> voteToRank = new HashMap<>();
	List<String> sortedArr = new ArrayList<>();

	public void vote(String person) {
		if (personToVotes.containsKey(person)) {
			int num = personToVotes.get(person);
			personToVotes.put(person, num + 1);
			int j = personToIndex.get(person);
			int i = voteToRank.get(num);
			swap(sortedArr, i, j);
			personToIndex.put(sortedArr.get(i), i);
			personToIndex.put(sortedArr.get(j), j);
			if (i + 1 < sortedArr.size() && personToVotes.get(sortedArr.get(i + 1)) == num) {
				voteToRank.put(num, i + 1);
			} else
				voteToRank.remove(num);
			if (!voteToRank.containsKey(num + 1))
				voteToRank.put(num + 1, i);
		} else {
			sortedArr.add(person);
			personToVotes.put(person, 1);
			personToIndex.put(person, sortedArr.size() - 1);
			if (!voteToRank.containsKey(1))
				voteToRank.put(1, sortedArr.size() - 1);
		}
	}

	private void swap(List<String> list, int i, int j) {
		String s1 = list.get(i);
		String s2 = list.get(j);
		list.set(i, s2);
		list.set(j, s1);
	}

	public List<String> getTopK(int k) {
		List<String> res = new ArrayList<>();
		for (int i = 0; i < k && i < sortedArr.size(); i++) {
			res.add(sortedArr.get(i));
		}
		return res;
	}
	
	public static void main(String[] args) {
		TopKVote solution = new TopKVote();
		for (int i = 0; i < 10; i++) solution.vote("a");
		for (int i = 0; i < 5; i++) solution.vote("e");
		for (int i = 0; i < 8; i++) solution.vote("b");
		for (int i = 0; i < 8; i++) solution.vote("c");
		for (int i = 0; i < 8; i++) solution.vote("d");
		System.out.println(solution.getTopK(5)); // a b c d e
		solution.vote("d");
		System.out.println(solution.getTopK(5)); // a d c b e
	}
}
