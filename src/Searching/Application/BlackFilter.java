package Searching.Application;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/**
 * 
 * @author MUSTACHE
 * @param args[0] list.txt
 * @param StdIn.raedString() tingTale.txt
 * 
 */
public class BlackFilter {
	public static void main(String[] args) {
		HashSET<String> set;
		set = new HashSET<String>();
		In in = new In(args[0]);
		while(! in.isEmpty()) {
			set.add(in.readString());
		}
		while(! StdIn.isEmpty()) {
			String word = StdIn.readString();
			if(! set.contains(word)) { // 从输入中显示不存在名单中的键
				StdOut.print(word + " ");
			}
		}
	}
}
