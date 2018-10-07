package Fundamentals.Practice;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdIn;

public class Prac_122 {
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		Interval1D[] a = new Interval1D[N];
		for(int i = 0; i < N; i++) {
			double x = StdIn.readDouble();
			double y = StdIn.readDouble();
			a[i] = new Interval1D(x,y);
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i != j && a[i].intersects(a[j])) {
					System.out.println(a[j]);
					break; // 只要有一条相交就结束内循环
				}
			}
		}
	}
}
