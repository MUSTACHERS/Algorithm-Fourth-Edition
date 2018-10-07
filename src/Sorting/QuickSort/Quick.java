package Sorting.QuickSort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) {
			return ;
		}
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi + 1; // ����ɨ��ָ��
		Comparable v = a[lo]; // �з�Ԫ��
		while(true) {
			while(less(a[++i], v)) {  // ��֤��ߵ�Ԫ�ز�����v�����������ֹͣѭ��
				if(i == hi) {
					break;
				}
			}
			while(less(v, a[--j])) { // ��֤�ұߵ�Ԫ�ز�С��v�����С����ֹͣѭ��
				if(j == lo) {
					break;
				}
			}
			if(i >= j) { // ָ��������ֹͣѭ��
				break;
			}
			exch(a, i, j); //��ʱ��a[i] > v, a[j] < v���������ǵ�λ�ã�a[i] < v, a[j] > v
		}
		exch(a, lo, j); // ��v=a[j]������ȷλ��
		return j;  // a[lo..j-1] <= a[j] <= a[j+1..hi]
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
