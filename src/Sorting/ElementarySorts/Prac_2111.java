package Sorting.ElementarySorts;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Prac_2111 {
	public static void sort(Comparable[] a) {
		int N = a.length;
		int[] array = new int[N / 3];
		int h = 1;
		int ch = 0;
		while(h < N / 3) {
			h = h * 3 + 1;
			array[ch++] = h;
		}
		while(h >= 0) {
			for(int i = h; i < N; i++) {
				for(int j = i; j >= h && less(a[j], a[j - h]); j-=h) {
					exch(a, j, j - h);
				}
			}
			h = h / 3;
		}
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
	
	public static void main(String[] args) {
		String[] a = In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);
	}
}

