package Searching.SymbolTables;

import edu.princeton.cs.algs4.StdIn;

public class Prac_3_1_1 {
	public static void main(String[] args) {
		BinarySearchST<String, Double> st = new BinarySearchST<String, Double>(11);
		while(!StdIn.isEmpty()) {
			String str = StdIn.readString();
			String[] Array = str.split("\\,");
			String key = Array[0];
			double value = Double.parseDouble(Array[1]);
			st.put(key, value);
		}
		for(String s : st.keys(st.min(),st.max())) {
			System.out.println(s + ": " + st.get(s)); 
		}
	}
	
}
