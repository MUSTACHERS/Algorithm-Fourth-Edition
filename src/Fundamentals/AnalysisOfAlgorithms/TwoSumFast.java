package Fundamentals.AnalysisOfAlgorithms;

/**
 * 
 * @author MusTache
 * 
 * % java TwoSumFast 2Kints.txt
 * % java TwoSumFast 4Kints.txt
 * % java TwoSumFast 16Kints.txt
 */

import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TwoSumFast {
	public static int count(int[] a) {
		Arrays.sort(a);
		int N = a.length;
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(BinarySearch.rank(-a[i], a) > i) {
				cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) {
		int[] a = In.readInts(args[0]);
		StdOut.println(count(a));
	}
}
