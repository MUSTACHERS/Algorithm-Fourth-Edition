package Fundamentals.BagsQueuesStacks;

import Fundamentals.BagsQueuesStacks.MyLink.Node;

public class Link {
	private class Node {
		private int item;
		private Node next;
		public Node(int item) {
			this.item = item;
		}
		private void addNode(Node newNode) {
			if(this.next == null) {
				this.next = newNode;
			} else {
				this.next.addNode(newNode);
			}
		}
		private boolean nodeContains(String s) {
			if(s.equals(this.item)) {
				return true;
			} else {
				if(this.next != null) {
					return this.next.nodeContains(s);
				} else {
					return false;
				}
			}
		}
		private void printNode() {
			System.out.println(this.item);
			if(this.next != null) {
				this.next.printNode();
			}
		}

	}
	private Node first;
	private int count = 0;;
	private int foot = 0;
	
	public void add(int k) {
		Node node = new Node(k);
		if(this.first == null) {
			this.first = node;
		} else {
			this.first.addNode(node);
		}
		count++;
	}
	public boolean contains(String k) {
		if(k == null || this.first == null) {
			return false;
		}
		return first.next.nodeContains(k);
	}
	public void print() {
		if(this.first != null) {
			this.first.printNode();
		}
	}
	public int size() {
		return this.count;
	}
	
	public int max() {
		if(this.first == null) {
			return 0;
		}
		int max = 0;
		Node current = this.first;
		while(current != null) {
			if(max < current.item) {
				max = current.item;
			}
			current = current.next;
		}
		return max;
	}
	
	public int maxResursive() {
		return maxR(this.first); 
	}
	private int maxR(Node a) {
		if(this.first == null) {
			return 0;
		}
		if(a.next != null) {
			return max1(a.item, maxR(a.next));
		}
		return 0;
	}
	private int max1(int a, int b) {
		if(a > b) {
			return a;
		} else {
			return b;
		}
	}
		
}
