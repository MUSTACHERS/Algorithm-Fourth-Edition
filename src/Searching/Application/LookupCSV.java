package Searching.Application;



import Searching.BalanceSearchTree.RedBlackTree;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 
 * @author MUSTACHE
 * 
 * % java LookupCSV ip.csv 1 0
 * 128.112.136.35
 * www.cs.princeton.edu
 * 
 * % java LookupCSV amino.csv 0 3
 * TCC
 * Serine
 * 
 * % java LookupCSV DJIA.csv 0 3
 * 29-0ct-29
 * 230.07
 * 
 * % java LookupCSV UPC.csv 0 2
 * 0002100001086
 * Kraft Parmesan
 */
public class LookupCSV {
	public static void main(String[] args) {
		In in = new In(args[0]);
		int keyField = Integer.parseInt(args[1]); 
		int valField = Integer.parseInt(args[2]); 
		RedBlackTree<String, String> st = new RedBlackTree<String, String>();
		while(in.hasNextLine()) {
			String line = in.readLine();
			String[] tokens = line.split(",");
			String key = tokens[keyField];
			String val = tokens[valField];
			st.put(key, val);
		}
		while(! StdIn.isEmpty()) {
			String query = StdIn.readString();
			if(st.contains(query)) {
				StdOut.println(st.get(query));
			}
		}
	}
}
