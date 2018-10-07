package Fundamentals.BagsQueuesStacks;


/**
 * % more tobe.txt
 * to be or not to - be - - that - - - is
 * % java FixedCapacityStack < tobe.txt
 * to be not that or be (2 left on stack)
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStack<Item> {
	
	private Item[] a;
	private int N;
	
	public FixedCapacityStack(int cap) {
//		java不允许泛型数组，所以只能用类型转换（向下转换）
		a = (Item[]) new Object[cap];
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public int size() {
		return N;
	}
	public void push(Item item) {
		a[N++] = item;
	}
	public Item pop() {
		return a[--N];
	}
	
	public static void main(String[] args) {
		FixedCapacityStack<String> s;
		s = new FixedCapacityStack<String>(100);
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if(!item.equals("-")) {
				s.push(item);
			} else {
				if(!s.isEmpty()) {
					StdOut.print(s.pop() + " ");
				}
			}
		}
		StdOut.println("(" + s.size() + " left on stack)");
	}
}
