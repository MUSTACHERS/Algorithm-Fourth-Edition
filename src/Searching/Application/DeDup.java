package Searching.Application;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
// 过滤器 --- 过滤掉重复的键
public class DeDup {
	public static void main(String[] args) {
		HashSET<String> set;
		set = new HashSET<String>();
		while(! StdIn.isEmpty()) {
			String key = StdIn.readString();
			if(! set.contains(key)) {
				set.add(key);
				StdOut.print(key + " ");
			}
		}
	}
}
