package Searching.SymbolTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;

public class SequentialSearchST <Key extends Comparable<Key>, Value>{
	private Node first;
	private int N;
//	3_1_10
	private int count = 0; // 纪律比较次数
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
		 if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 
	     if (val == null) {
	            delete(key);
	            return;
	       }
		for(Node x = first; x != null; x = x.next) {
			this.count++;
			if(key.equals(x.key)) {
				x.val = val;
				return ;
			}
		}
		first = new Node(key, val, first);
		this.N++;
	}
	public int getCount() {
		return this.count;
	}
	public Value get(Key key) {
		for(Node x = first; x != null; x = x.next) {
			if(key.equals(x.key)) {
				return x.val; // 命中
			}
		}
		return null; // 未命中
	}
	
	public boolean isEmpty() {
		return this.first == null;
	}
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
	}
//	Prac_3_1_5
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
		SequentialSearchST<String, Integer> q = new SequentialSearchST<String, Integer>();
		while(!StdIn.isEmpty()) {
			String str = StdIn.readString();
			String[] Array = str.split("\\,");
			String key = Array[0];
			int val = Integer.parseInt(Array[1]);
			q.put(key, val);
		}
		
		System.out.println(q.size() + "个");
		for(String s : q.keys()) {
			System.out.println(s + " ");
		}
	}

}
