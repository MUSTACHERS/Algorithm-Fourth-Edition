package Fundamentals.AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public final class DoublingText {
	public static double timeTrial(int N) {
//		为处理N个随机的六位整数的ThreeSum.count()计时
		int Max = 1000000;
		int[] a = new int[N];
		for(int i = 0; i < N; i++) {
			a[i] = StdRandom.uniform(-Max, Max);
		}
		Stopwatch timer = new Stopwatch();
		int cnt = ThreeSum.cout(a);
		return timer.elapsedTime();
	}
	
	public static void main(String[] args) {
		for(int N = 250; true; N += N) {
			double time = timeTrial(N);
			StdOut.printf("%7d %5.1f\n", N, time);
		}
	}
}
