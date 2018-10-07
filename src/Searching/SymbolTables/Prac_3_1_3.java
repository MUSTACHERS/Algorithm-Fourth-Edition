package Searching.SymbolTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;

public class Prac_3_1_3 <Key extends Comparable<Key>, Value>{
	private Node first;
	private int N;
	private class Node {
		private Key key;
		private Value val;
		private Node next;
		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	public void put(Key key, Value val) {
		if(first == null) {
			first = new Node(key, val, first);
			this.N++;
			return ;
		}
		Node previous = first;
		for(Node x = first; x != null; x = x.next) {
			int cmp = key.compareTo(x.key);
			if(cmp == 0) {
				x.val = val;
				return ;
			} else if(cmp < 0) {
				x = new Node(key, val, x);
				previous.next = x;
				this.N++;
				return ;
			}
			previous = x;
		}

	}
	public Value get(Key key) {
		for(Node x = first; x != null; x = x.next) {
			if(key.compareTo(x.key) == 0) {
				return x.val;
			}
		}
		return null;
	}
	public void delete(Key key) {
		Node previous = first;
		for(Node x = first; x != null; x = x.next) {
			if(key.compareTo(x.key) == 0) {
				this.N--;
				if(x == first) {
					first = first.next;
					return ;
				}
				previous.next = x.next;

			}
			previous = x;
		}
	}
	public int size() {
		return this.N;
	}
	public boolean isEmpty() {
		return size() == 0;
	}
	public boolean contains(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		return get(key) != null;
	}
	public Iterable<Key> keys(){
		Queue<Key> q = new Queue<Key>();
		for(Node x = first; x != null; x = x.next) {
			q.enqueue(x.key);
		}
		return q;
	}
	
	public static void main(String[] args) {
		Prac_3_1_3<String, Integer> q = new Prac_3_1_3<String, Integer>();
		while(!StdIn.isEmpty()) {
			String str = StdIn.readString();
			String[] Array = str.split("\\,");
			String key = Array[0];
			int val = Integer.parseInt(Array[1]);
			q.put(key, val);
		}
		
		System.out.println(q.size() + "¸ö");
		for(String s : q.keys()) {
			System.out.println(s + " " + q.get(s));
		}
	}
}
