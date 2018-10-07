package Sorting.ElementarySorts;

import java.util.Arrays;

public class Prac_2116 {
	public static boolean check(Comparable[] a) {
		int N = a.length;
		Comparable[] b = new Comparable[N];
		for(int i = 0; i < N; i++) {
			b[i] = a[i];
		}
		Arrays.sort(b);
		for(int i = 0; i < N; i++) {
			if(b[i] != a[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String[] a = {"C", "B", "A", "D", "E"};
		if(check(a)) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
}
