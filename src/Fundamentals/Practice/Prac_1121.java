package Fundamentals.Practice;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Prac_1121 {
	public static void main(String[] args) {
		if(StdIn.hasNextChar()) {
			String name = StdIn.readString();
			int a = StdIn.readInt();
			int b = StdIn.readInt();
			StdOut.printf("%8s | %8d | %8d | %8.3f", name, a, b, (a*1.0)/b);
		}
	}
}
