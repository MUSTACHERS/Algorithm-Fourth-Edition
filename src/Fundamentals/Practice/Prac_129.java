package Fundamentals.Practice;

import java.util.Arrays;

import Fundamentals.DataAbstraction.Counter;

public class Prac_129 {
	public static int rank(int key, int[] a, Counter b) {
		int lo = 0;
		int hi = a.length - 1;
		while(lo <= hi) {
			b.increment(); // 每次循环就统计一次
			int mid = lo + (hi - lo)/2;
			if(key < a[mid]) {
				hi = mid - 1;
			} else if(key > a[mid]) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] a = {1,3,5,8,9,5,4,2,3};
		Arrays.sort(a);
		Counter b = new Counter("Binary Search");
		rank(8,a,b);
		System.out.println(b);
	}
}
