package Fundamentals.BagsQueuesStacks;

class TheLink {
	class Node {
		String item;
		Node next;
		public Node(String string) {
			this.item = string;
		}
		public void addNode(Node newNode) {
			if(this.next == null) {
				this.next = newNode;
			} else {
				this.next.addNode(newNode);
			}
		}
		public boolean containNode(String s) {
			if(s.equals(this.item)) {
				return true;
			} else {
				if(this.next != null) {
					return this.next.containNode(s);
				} else {
					return false;
				}
			}
		}
		public void printNode() {
			System.out.println(this.item);
			if(this.next != null) {
				this.next.printNode();
			}
		}
	}
	Node first;
	private int count = 0;;
	private int foot = 0;
	
	public void add(String string) {
		Node node = new Node(string);
		if(this.getFirst() == null) {
			this.setFirst(node);
		} else {
			this.getFirst().addNode(node);
		}
		count++;
	}
	public boolean contains(String k) {
		if(k == null || this.getFirst() == null) {
			return false;
		}
		return getFirst().next.containNode(k);
	}
	public void print() {
		if(this.getFirst() != null) {
			this.getFirst().printNode();
		}
	}
	public int size() {
		return this.count;
	}

	public static boolean find(TheLink l, String key) {
		TheLink link = l;
		while(link != null) {
			if(link.getFirst().item.equals(key)) {
				return true;
			}
			link.setFirst(link.getFirst().next);
		}
		return false;
	}
	public Node getFirst() {
		return first;
	}
	public void setFirst(Node first) {
		this.first = first;
	}	
	
}
public class Prac_1321 {
	public static void main(String[] args) {
		TheLink li = new TheLink();
		li.add("a");
		li.add("b");
		li.add("c");
		System.out.println(TheLink.find(li,"c"));
	}
}
