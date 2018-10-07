package Searching.BinarySearchTree;

public class NoRecursiveBST <Key extends Comparable<Key>, Value>{
	private Node root;
	private class Node {
		private Key key;
		private Value val;
		private Node left,right;
		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}
	public Value get(Key key) {
		Node x = root;
		while(x != null) {
			int cmp = key.compareTo(x.key);
			if(cmp == 0) {
				return x.val;
			} else if(cmp > 0) {
				x = x.right;
			} else if(cmp < 0) {
				x = x.left;
			}
		}
		return null;
	}
	public void put(Key key, Value val) {
		Node n = new Node(key, val);
		if(root == null) {
			root = n;
			return ;
		}
		Node present = null, x = root;
		while(x != null) {
			present = x;
			int cmp = key.compareTo(x.key);
			if(cmp < 0) {
				x = x.left;
			} else if(cmp > 0) {
				x = x.right;
			} else {
				x.val = val;
				return ;
			}
		}
		int cmp = key.compareTo(present.key);
		if(cmp < 0) {
			present.left = n;
		} else {
			present.right = n;
		}
	}
	
	public Key min() {
		if(root == null) {
			return null;
		}
		Node present = null,n = root;
		while(n != null) {
			present = n;
			n = n.left;
		}
		return present.key;
	}
	public Key max() {
		if(root == null) {
			return null;
		}
		Node present = null,n = root;
		while(n != null) {
			present = n;
			n = n.right;
		}
		return present.key;
	}
// wait
}
