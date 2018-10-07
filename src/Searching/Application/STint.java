package Searching.Application;

import Searching.BalanceSearchTree.RedBlackTree;

/**
 * Prac_3_5_5
 * @author MUSTACHE
 *
 */
public class STint <Value> {
	private RedBlackTree<Integer, Value> st;
	public STint() {
		st = new RedBlackTree<Integer, Value>();
	}
	public void put(int key, Value val) {
		st.put(key, val);
	}
	public Value get(int key) {
		return st.get(key);
	}
	public void delete(int key) {
		st.delete(key);
	}
	public boolean contains(int key) {
		return st.contains(key);
	}
	public boolean isEmpty() {
		return st.isEmpty();
	}
	public int size() {
		return st.size();
	}
	public int max() {
		return st.max();
	}
	public int min() {
		return st.min();
	}
	public Iterable<Integer> keys() {
		return st.keys();
	}
}
