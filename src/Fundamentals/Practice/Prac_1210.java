package Fundamentals.Practice;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

class VisualCounter {
	private int count;
	private int operationCount;
	private int max;
	private int N;
	public VisualCounter(int N, int max) {
		this.N = N;
		this.max = max;
		this.count = 0;
		this.operationCount = 0;
	}
	public void increment() {
		if(this.operationCount++ < N && this.count < max) {
			this.count++;
		}
	}
	public void decrement() {
		if(this.operationCount++ < N && this.count > -max) {
			this.count--;
		}
	}
	public void draw() {
		StdDraw.point(operationCount, count);
	}
}
public class Prac_1210 {
	public static void main(String[] args) {
		int N = 30;
		int max = 15;
		StdDraw.setXscale(0, N + 1);
		StdDraw.setYscale(-max, max);
		StdDraw.line(0, 0, N+1, 0);
        StdDraw.setPenColor(StdDraw.RED);
		VisualCounter visualCounter = new VisualCounter(N, max);
		visualCounter.draw();
		for(int i = 0; i < N; i++) {
			if(StdRandom.bernoulli()) {
				visualCounter.increment();
			} else {
				visualCounter.decrement();
			}
			visualCounter.draw();
		}
	}
}
