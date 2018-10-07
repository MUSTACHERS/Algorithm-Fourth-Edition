package Searching.SymbolTables;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Prac_3_1_19 {
	public static void main(String[] args) {
		int minlen = Integer.parseInt(args[0]);
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		while(!StdIn.isEmpty()) {
			String word = StdIn.readString();
			if(word.length() < minlen) {
				continue;
			}
			if(!st.contains(word)) {
				st.put(word, 1);
			} else {
				st.put(word, st.get(word) + 1);
			}

		}
//		找出频率最高的单词
		String max = " ";
		st.put(max,  0);
		for(String word:st.keys()) {
			if(st.get(word) > st.get(max)) {
				max = word;
			}
		}
		System.out.println("频率最高的单词有：");
		for(String word:st.keys()) {
			if(st.get(word) == st.get(max)) {
				System.out.print(word + " ");
			}
		}

	}
}
