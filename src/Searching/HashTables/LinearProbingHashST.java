package Searching.HashTables;

import edu.princeton.cs.algs4.Queue;

public class LinearProbingHashST <Key, Value>{
	private int N;
	private int M = 16;
	private Key[] keys;
	private Value[] vals;
	@SuppressWarnings("unchecked")
	public LinearProbingHashST() {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	@SuppressWarnings("unchecked")
	public LinearProbingHashST(int cap) {
		keys = (Key[]) new Object[cap];
		vals = (Value[]) new Object[cap];
	}
	private int hash(Key key) {
		return (key.hashCode() & 0x7ffffff) % M;
	}
	private void resize(int cap) {
		LinearProbingHashST<Key, Value> t;
		t = new LinearProbingHashST<Key, Value>(cap);
		for(int i = 0; i < this.M; i++) {
			if(keys[i] != null) {
				t.put(keys[i], vals[i]);
			}
		}
		this.keys = t.keys;
		this.vals = t.vals;
		this.M = t.M;
	}
	public void put(Key key, Value val) {
		if(N >= M / 2) {
			resize(2 * M);
		}
		int i;
		for(i = hash(key) ; keys[i] != null; i = (i + 1) % M) {
			if(keys[i].equals(key)) {
				vals[i] = val;
				return ;
			}
			keys[i] = key;
			vals[i] = val;
			N++;
		}
	}
	public Value get(Key key) {
		for(int i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if(keys[i].equals(key)) {
				return vals[i];
			}
		}
		return null;
	}
	public boolean contains(Key key) {
		return get(key) != null;
	}
	public void delete(Key key) {
		if(!contains(key)) {
			return ;
		}
		int i = hash(key);
		while(! key.equals(keys[i])) {
			i = (i + 1) % M;
		}
		keys[i] = null; // 删除键
		vals[i] = null; // 删除值
		i = (i + 1) % M; // 指向当前删除的键的下一个键
		while(keys[i] != null) { // 要将删除的键的右侧的所有键重新插入散列表
//			因为删除键后，会导致删除键后面的键查找不到。所以重新插入
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			this.N--;
			put(keyToRedo, valToRedo);
			i = (i + 1) % M;
		}
		this.N--;
		if(this.N > 0 && N == N / 8) {
			resize(M / 2);
		}
	}
	
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for(int i = 0; i < M; i++) {
			if(keys[i] != null) {
				queue.enqueue(keys[i]);
			}
		}
		return queue;
	}
}
