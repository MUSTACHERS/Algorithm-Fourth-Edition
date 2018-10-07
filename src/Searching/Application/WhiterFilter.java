package Searching.Application;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WhiterFilter {
	public static void main(String[] args) {
		HashSET<String> set;
		set = new HashSET<String>();
		In in = new In(args[0]); 
		while(! in.isEmpty()) {
			set.add(in.readString()); // 加入白名单
		}
		while(! StdIn.isEmpty()) {
			String word = StdIn.readString();
			if(set.contains(word)) { // 从输入中显示白名单存在的键
				StdOut.print(word + " ");
			}
		}
	}
}
