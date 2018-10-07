package Searching.Application;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;

/**
 * Practice 3_5_2\\
 * @author MUSTACHE
 *
 * @param <Key>
 */
public class SequentialSearchSET <Key extends Comparable<Key>>{
	private Node first;
	private int N;
	private class Node {
		private Key key;
		private Node next;
		public Node(Key key, Node next) {
			this.key = key;
			this.next = next;
		}
	}
	public void put(Key key) {
		 if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 
		for(Node x = first; x != null; x = x.next) {
			if(key.equals(x.key)) {
				return ;
			}
		}
		first = new Node(key, first);
		this.N++;
	}

	
	public boolean isEmpty() {
		return this.first == null;
	}
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		for(Node x = first; x != null; x = x.next) {
			if(key.equals(x.key)) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return this.N;
	}
	public void delete(Key key) {
//		存储前一个节点
		Node pre = first;
		for(Node x = first; x != null; x = x.next) {
			if(key.equals(x.key)) {
				this.N--;
//				如果是首链表
				if(x == first) {
					first = first.next;
					return;
				}
//				把当前节点的下一节点赋给前一节点的下一个节点
				pre.next = x.next;
			}
//			更改pre
			pre = x;
		}
	}

	public Iterable<Key> keys() {
		Queue<Key> q = new Queue<Key>();
		for(Node x = first; x != null; x = x.next) {
			q.enqueue(x.key);
		}
		return q;
	}
	
	public static void main(String[] args) {
		SequentialSearchSET<String> q = new SequentialSearchSET<String>();
		while(!StdIn.isEmpty()) {
			String str = StdIn.readString();

			q.put(str);
		}
		
		System.out.println(q.size() + "个");
		for(String s : q.keys()) {
			System.out.println(s + " ");
		}
	}

}

