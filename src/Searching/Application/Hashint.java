package Searching.Application;

import edu.princeton.cs.algs4.LinearProbingHashST;
/**
 * Practice 3_5_4
 * @author MUSTACHE
 *
 * @param <Value>
 */
public class Hashint <Value>{
	private LinearProbingHashST<Integer, Value> st;
	public Hashint() {
		st = new LinearProbingHashST<Integer, Value>();
	}
	public void put(int key,Value val) {
		st.put(key, val);
	}
	public Value get(int key) {
		return st.get(key);
	}
	public boolean isEmpty() {
		return st.isEmpty();
	}
	public boolean contains(int key) {
		return st.contains(key);
	}
	public int size() {
		return st.size();
	}
	public void delete(int key) {
		st.delete(key);
	}
	public Iterable<Integer> keys() {
		return st.keys();
	}
}
