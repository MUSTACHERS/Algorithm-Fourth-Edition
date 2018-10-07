package Searching.SymbolTables;

import edu.princeton.cs.algs4.StdIn;

public class Prac_3_1_11 {
	public static void main(String[] args) {
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(15);
		while(!StdIn.isEmpty()) {
			String word = StdIn.readString();
			if(!st.contains(word)) {
				st.put(word, 1);
			} else {
				st.put(word, st.get(word) + 1);
			}
			for(String s:st.keys(st.min(),st.max())) {
				
				System.out.print(s + " " + st.get(s) + " --> ");
			}
			System.out.println();
		}
		System.out.println("比较次数：" + st.getCount());
	}
}
