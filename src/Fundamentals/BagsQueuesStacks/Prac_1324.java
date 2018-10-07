package Fundamentals.BagsQueuesStacks;

import Fundamentals.BagsQueuesStacks.MyLink.Node;

/**
 * 
 * @author MusTache
 *
 * public void insertAfter(Node x, Node t) {
 *		if(x == null || t == null) {
 *			return ;
 *		}
 *		t.next = x.next;
 *		x.next = t;
 *	}
 *
 */
public class Prac_1324 {
	
	public static void main(String[] args) {
		MyLink link = new MyLink();
		link.add("a");
		link.add("b");
		link.add("c");
		link.add("d");
		link.print();
		MyLink.Node node = link.search("b");
		link.removeAfter(node);
		System.out.println("");
		link.print();
	}
}
