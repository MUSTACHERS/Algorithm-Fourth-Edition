package Fundamentals.BagsQueuesStacks;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stack<Item> implements Iterable<Item>{
	
	private Node first;
	private int N;
	private class Node {
		Item item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	public int size() {
		return N;
	}
	public void push(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	public Item pop() {
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() {
			return current != null;
		}
		public Item next() {
			Item item = current .item;
			current = current.next;
			return item;
		}
		public void remove() {
			
		}
	}
//	Practice_1.3.12
//	ע�ⷽ��ջ�ĵ������������
//	���巽�����������ͣ���Ҫ��static��ķ��ͣ���Ϊ���Ǿ�̬�������ڻ�û�������ʵ�����ʹ��ڣ����Բ���Ǿ�̬�����Ѿ�ָ������
	public static <Item> Stack<Item> copy(Stack<Item> s){
		Iterator<Item> iterator = s.iterator();
		Stack<Item> stack = new Stack<Item>();
		Stack<Item> result = new Stack<Item>();
		while(iterator.hasNext()) {
			stack.push(iterator.next());
		}
		iterator = stack.iterator();
		while(iterator.hasNext()) {
			result.push(iterator.next());
		}
		return result;
	}
//	Practice_1.3.7
	public Item peek() {
		return first.item;
	}
	
	public static void main(String[] args) {
		Stack<String> q = new Stack<String>();
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if(!item.equals("-")) {
				q.push(item);
			} else if(!q.isEmpty()) {
				StdOut.print(q.pop() + " ");
			}
		}
		StdOut.println("(" + q.size() + " left on queue)");
	}
}
