package Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

class queueLink<Item> {
	private class Node {
		Item item;
		Node next;
	}
	private Node last;
	private int N;
	
	public boolean isEmpty() {
		return N == 0;
	}
	public int size() {
		return N;
	}
	public void enqueue(Item item) {
		Node node = new Node();
		node.item = item;
		if(isEmpty()) {
			last = node;
			node.next = node;
		} else {
			node.next = last.next;
			last.next = node;
			last = node;
		}
		this.N++;
	}
	public Item dequeue() {
		if(isEmpty()) {
			return null;
		}
		Item item = last.next.item;
		if(last.next == last) {
			last = null;
		} else {
			last.next = last.next.next;
		}
		this.N--;
		return item;
	}
}
public class Prac_1329 {
	public static void main(String[] args) {
		queueLink<String> queue = new queueLink<String>();
		queue.enqueue("a");
		queue.enqueue("b");
		queue.enqueue("c");
		String s;
		while((s = queue.dequeue()) != null) {
			System.out.println(s);
		}
	}	
}
