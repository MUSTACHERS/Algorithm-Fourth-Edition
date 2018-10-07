package Fundamentals.DataAbstraction;

/**
 * Execution: java Flips args[0]
 * Dependencies: Counter.java StdOut.java StdRandom.java
 * 
 * % java Flips 10
 * (random) heads
 * (random) tails
 * delta: heads.tally() - tails.tally()
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Filps {
	
	public static void main(String[] args) {
		int T = Integer.parseInt(args[0]);
		Counter heads = new Counter("heads");
		Counter tails = new Counter("tails");
		for(int t = 0; t < T; t++) {
			if(StdRandom.bernoulli(0.5)) {
				heads.increment();
			} else {
				tails.increment();
			}
		}
		StdOut.println(heads);
		StdOut.println(tails);
		int d = heads.tally() - tails.tally();
		StdOut.println("delta: " + Math.abs(d));
 	}
}
