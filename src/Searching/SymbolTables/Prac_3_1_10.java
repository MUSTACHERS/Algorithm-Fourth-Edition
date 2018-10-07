package Searching.SymbolTables;

import edu.princeton.cs.algs4.StdIn;

public class Prac_3_1_10 {
	public static void main(String[] args) {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		while(!StdIn.isEmpty()) {
			String word = StdIn.readString();
			if(!st.contains(word)) {
				st.put(word, 1);
			} else {
				st.put(word, st.get(word) + 1);
			}
			for(String s:st.keys()) {
				
				System.out.print(s + " " + st.get(s) + " --> ");
			}
			System.out.println();
		}
		System.out.println("比较次数：" + st.getCount());
	}
}
