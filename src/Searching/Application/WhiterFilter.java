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
			set.add(in.readString()); // ���������
		}
		while(! StdIn.isEmpty()) {
			String word = StdIn.readString();
			if(set.contains(word)) { // ����������ʾ���������ڵļ�
				StdOut.print(word + " ");
			}
		}
	}
}
