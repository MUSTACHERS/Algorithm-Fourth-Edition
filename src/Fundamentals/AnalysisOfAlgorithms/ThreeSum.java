package Fundamentals.AnalysisOfAlgorithms;

/**
 * @author MusTache
 * 
 * % java ThreeSum 1Kints.txt
 * % java ThreeSum 2Kints.txt
 * % java ThreeSum 4Kints.txt
 *
 */
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ThreeSum {
	public static int cout(int[] a) {
//		ͳ�ƺ�Ϊ0��Ԫ�������
		int N = a.length;
		int cnt = 0;
		for(int i =0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				for(int k = j + 1; k < N; k++) {
					if(a[i] + a[j] + a[k] ==  0) {
						cnt++;
					}
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) {
		int[] a = In.readInts(args[0]);
		StdOut.println(cout(a));
		System.out.println();
	}
}
