package Fundamentals.Practice;

import edu.princeton.cs.algs4.StdOut;

public class Prac_1119 {
	public static long F(int N) {
		if(N == 0) {
			return 0;
		} else if(N == 1) {
			return 1;
		} else {
			long[] F = new long[N + 1];
			int i;
			F[0] = 0;
			F[1] = 1;
			for(i = 2; i < N + 1; ++i) {
				F[i] = F[i - 1] + F[i - 2];
			}
			return F[i - 1];
		}
	}
	public static void main(String[] args) {
		for(int N = 0; N < 100; N++) {
			StdOut.println(N + " " + F(N));
		}
	}
}
