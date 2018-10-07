package Searching.Application;

import Searching.BalanceSearchTree.RedBlackTree;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Practice 3_5_12
 * @author MUSTACHE
 *
 */
public class AllLookupCSV {
	public static void main(String[] args) {
		In in = new In(args[0]);
//		利用队列
		RedBlackTree<String, Queue<String>> st = new RedBlackTree<String, Queue<String>>();
		while(in.hasNextLine()) {
			String[] line = in.readLine().split("\\,");
			String key = line[0];
			for(int i = 1; i < line.length; i++) {
				String val = line[i];
				if(! st.contains(key)) {
					st.put(key, new Queue<String>());
				}
				st.get(key).enqueue(val);
			}
		}
		while(! StdIn.isEmpty()) {
			String query = StdIn.readString();
			if(st.contains(query)) {
				for(String s : st.get(query)) {
					System.out.println(" " + s);
				}
			}
		}
	}
}
