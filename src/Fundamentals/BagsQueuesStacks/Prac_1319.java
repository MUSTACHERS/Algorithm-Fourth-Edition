package Fundamentals.BagsQueuesStacks;

public class Prac_1319 {
	
	private class Node {
		int item;
		Node next;
	}
	private Node first;
	
	public void deleteLastList() {
		Node current = first;
		if(current == null) {
			return ;
		}
		Node next = current.next;
		if(next == null) {
			first = null;
		}
		while(next.next != null) {
			current = next;
			next = next.next;
		}
		current.next = null;
		
	}
}
