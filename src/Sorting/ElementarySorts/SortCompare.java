package Sorting.ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {
	
	public static double time(String alg, Comparable[] a) {
		Stopwatch timer = new Stopwatch();
		if(alg.equals("Insertion")) {
			Insertion.sort(a);
		}
		if(alg.equals("Selection")) {
			Selection.sort(a);
		}
		if(alg.equals("Shell")) {
			Shell.sort(a);
		}
		return timer.elapsedTime();
	}
	
	public static double timeRandomInput(String alg, int N, int T) {
//		使用算法alg将T个长度为N的数组排序
		double total = 0.0;
		Integer[] a = new Integer[N];
		for(int t = 0; t < T; t++) { // 进行一次测试(生成一个数组并排序)
			for(int i = N - 1; i >= 0; i--) {
				a[i] = i;
			}
			total += time(alg, a);
		}
		return total;
	}
	
	public static void main(String[] args) {
		String alg1 = args[0];
		String alg2 = args[1];
		int N = Integer.parseInt(args[2]);
		int T =  Integer.parseInt(args[3]);
		double t1 = timeRandomInput(alg1, N, T);
		double t2 = timeRandomInput(alg2, N, T);
		StdOut.printf("For %d random Double\n	％ｓ　is", N, alg1);
		StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
	}
}
