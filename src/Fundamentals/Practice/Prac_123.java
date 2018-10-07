package Fundamentals.Practice;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.StdRandom;

public class Prac_123 {
	public static double max(double a, double b) {
		return a > b ? a : b;
	}
	public static double min(double  a, double b) {
		return a < b ? a : b;
	}
	public static void main(String[] args) {
		int N =  Integer.parseInt(args[0]);
		double min =  Double.parseDouble(args[1]);
		double max =  Double.parseDouble(args[2]);
		Interval2D[] a = new Interval2D[N];
		for(int i = 0; i < N; i++) {
			double xlo = StdRandom.uniform(min, max);
			double xhi = StdRandom.uniform(min, max);
			double ylo = StdRandom.uniform(min, max);
			double yhi = StdRandom.uniform(min, max);
//			因为Interval1D的构想函数参数需要第一个参数小，第一个参数大，所以得添加两个max和min方法
			a[i] = new Interval2D(new Interval1D(min(xlo, xhi),max(xlo, xhi)), new Interval1D(min(ylo, yhi), max(ylo, yhi)));
		}
		for(int i = 0; i < N; i++) {
			a[i].draw();
		}
		int containCount = 0;
		int intersectCount = 0;
		for(int i = 0; i < N - 1; i++) {
			for(int j = i + 1; j < N; j++) {
				if(a[i].intersects(a[j])) {
					intersectCount++;
				}

			}
		}
		System.out.println("Intersect Count: " + intersectCount);
	}
}
