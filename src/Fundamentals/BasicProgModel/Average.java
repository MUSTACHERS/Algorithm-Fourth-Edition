package Fundamentals.BasicProgModel;

/**
 * Execution: java Average < data.txt
 * Dependencies: StdIn.java StdOut.java
 * 
 * Read a number of digital computing averages.
 * 
 * % java Average
 * 1.23456
 * 2.34567
 * 3.45678
 * 4.56789
 * <ctrl + z>
 * Average is 2.90123
 * 
 * <Ctrl-d> signifies the end of file on Unix.
 * <Ctrl-z> signifies the end of file on Windows
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Average {
	public static void main(String[] args) {
		double sum = 0.0;
		int cnt = 0;
		while(!StdIn.isEmpty()) {
			sum += StdIn.readDouble();
			cnt++;
		}
		double avg = sum / cnt;
		StdOut.printf("Average is %.5f\n", avg);
	}
}
