package Fundamentals.BagsQueuesStacks;


public class MyLink {
	static class Node {
		String item;
		Node next;
		public Node(String item) {
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
	
	public void add(String k) {
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
	
	public void delete(int k) {
		this.count--;
		if(k <= 0 || this.first == null) {
			return ;
		}
		if(k == 1) {
			this.first = this.first.next;
			return ;
		}
		k--;
		Node current = this.first;
		while(--k > 0 && current.next != null) {
			current = current.next;
		}
		if(k != 0 || current == null || current.next == null) {
			return ;
		} else {
			current.next =current.next.next;
		}
		
	}
    
	public void removeAfter(Node li) {
		if(li == null || li.next == null) {
			return ;
		}
		Node current = li.next;
		Node next = current.next;
		li.next = null; // gc回收
		while(current.next != null) {
			current = null;
			current = next;
			next = next.next;
		}
	}
	public Node search(String item) {
		Node current = this.first;
		while(current != null) {
			if(item.equals(current.item)) {
				return current;
			}
			current = current.next;
		}
		return null;
	}
	
	public void insertAfter(Node x, Node t) {
		if(x == null || t == null) {
			return ;
		}
		t.next = x.next;
		x.next = t;
	}
	
	public void remove(String key) {
//		处理开头
		while (first != null && key.equals(first.item)){
            first = first.next;
        }
//		一个保存当前，一个保存当前的下一个
		Node current = first;
		Node node;
		while(current != null && current.next != null) {
			node = current.next;
			if(key.equals(node.item)) {
				current.next = node.next;
			} else {
				current = node;
			}
		}
	}
}
