package Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Prac_131 {
	private String[] a; // stack entries
	private int N; // size
	
	public Prac_131(int cap) {
		a = new String[cap];
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public int size() {
		return N;
	}
	public void push(String item) {
		a[N++] = item;
	}
	public String pop() {
		return a[--N];
	}
	
	public boolean isFull() {
		return N == a.length;
	}
	
	public static void main(String[] args) {
		Prac_131 s;
		s = new Prac_131(4);
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if(!item.equals("-")) {
				if(s.isFull()) {
					System.out.println("The stack is full.");
				} else {
					s.push(item);
				}
			} else {
				if(!s.isEmpty()) {
					StdOut.print(s.pop() + " ");
				}
			}
		}
		StdOut.println("(" + s.size() + " left on stack)");
	}
}
