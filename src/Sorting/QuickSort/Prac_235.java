package Sorting.QuickSort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Prac_235 {
	private static int cnt = 0;
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) {
			return ;
		}
		int lt = lo, i = lo + 1, gt = hi;
		Comparable v = a[lo];
		while(i <= gt) {
			cnt++; //记录比较
			int cmp = a[i].compareTo(v);
			if(cmp < 0) {
				exch(a, lt++, i++);
			} else if(cmp > 0) {
				exch(a, gt--, i);
			} else {
				i++;
			}
		}
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	public static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	public static void show(Comparable[] a) {
		for(int i = 0; i < a.length; i++) {
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}
	public static boolean isSorted(Comparable[] a) {
		for(int i = 1; i < a.length; i++) { 
			if(less(a[i], a[i - 1])) {
				return false;
			}
		}
		return true;
	}
	public static Integer[] RandomInput(int a) {
		Integer[] b = new Integer[a];
		for(int t = 0; t < a; t++) { // 进行一次测试(生成一个数组并排序)
				b[t] = t;
			
		}
		return b;
	}
	public static void main(String[] args) {
		for(int N = 100; N <= 10000; N *= 10) {
			cnt = 0;
			sort(RandomInput(N));
			double t = cnt;
			double st = 2 * N * Math.log(N);
			StdOut.println("N=" + N + " " + t+ " " + st);
		}
	}
}
