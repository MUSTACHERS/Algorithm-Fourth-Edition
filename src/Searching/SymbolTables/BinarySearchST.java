package Searching.SymbolTables;

import edu.princeton.cs.algs4.Queue;

public class BinarySearchST <Key extends Comparable<Key>, Value>{
	private Key[] keys;
	private Value[] vals;
	private int N;
//	3_1_11
	private int count = 0; // 纪律比较
	@SuppressWarnings("unchecked")
	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity]; // 数组不允许泛型，所以得向下转型
		vals = (Value[]) new Object[capacity];
	}
	public int size() {
		return N;
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public Value get(Key key) {
		if(isEmpty()) {
			return null;
		}
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0) {
			return vals[i];
		} else {
			return null;
		}
	}
	public int rank(Key key) {
		int lo = 0, hi = N - 1;
		while(lo <= hi) {
			count++;
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp > 0) {
				lo = mid + 1;
			} else if(cmp < 0) {
				hi = mid - 1;
			} else {
				return mid;
			}
		}

		return lo; 
	}
	public int getCount() {
		return count;
	}
	public void put(Key key, Value value) {
		int i = rank(key);
		if(i < N && key.compareTo(keys[i]) == 0) {
			vals[i] = value; // 找到的话则更新值
			return ;
		}
		for(int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		keys[i] = key;
		vals[i] = value;
		N++;
	}
	public Key min() {
		return keys[0]; 
	}
	public Key max() {
		return keys[N - 1];
	}
	public Key select(int k) {
		return keys[k];
	}
	public Key ceiling(Key key) {
		int i = rank(key);
		return keys[i];
	}
	public boolean contains(Key k) {
		return get(k) != null;
	}
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> q = new Queue<Key>();
		for(int i = rank(lo); i < rank(hi); i++) {
			q.enqueue(keys[i]);
		}
		if(contains(hi)) {
			q.enqueue(keys[rank(hi)]);
		}
		return q;
	}
	public void delete(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}
		if(isEmpty()) {
			return ;
		}
		int i = rank(key);
		if(i >= this.N || key.compareTo(keys[i]) != 0) {
			return ;
		}
		for(int j = i; j < N - 1; j++) {
			keys[j] = keys[j + 1];
			vals[j] = vals[j + 1];
		}
		keys[N - 1] = null;
		vals[N - 1] = null;
		this.N--;
		
	}
	public Key floor(Key key) { // 小于等于该键的最大键
		if(key == null) {
			throw new IllegalArgumentException("argument to floor() is null");
		}
		int i = rank(key);
		if(i < N && key.compareTo(keys[i]) == 0) {
			return keys[i];
		}
		if(i == 0) {
			return null;
		} else {
			return keys[i - 1];
		}
	}
	public static void main(String[] args) {
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(11);
		st.put("A", 2);
		System.out.println(st.get("B"));
	}
}
