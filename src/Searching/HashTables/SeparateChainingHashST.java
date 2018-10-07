package Searching.HashTables;



import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

public class SeparateChainingHashST <Key, Value>{
	private int N;  // ��ֵ�Ե�����
	private int M;  // ɢ�б�Ĵ�С
	private SequentialSearchST<Key, Value>[] st;  // ���������������
	
	public SeparateChainingHashST() {
		this(997);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SeparateChainingHashST(int M) {
		this.M = M;
		st = (SequentialSearchST<Key,Value>[]) new SequentialSearchST[M]; // ����������Ҫǿ��ת��
		for(int i = 0; i < M; i++) {
			st[i] = new SequentialSearchST();
		}
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	public Value get(Key key) {
		return (Value) st[hash(key)].get(key);
	}
	public void put(Key key, Value val) {
		st[hash(key)].put(key, val);
	}
	public void delete(Key key) {
		int i = hash(key);
		if(st[i].contains(key)) {
			N--;
		}
		st[i].delete(key);
	}
	
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for(int i = 0; i < M; i++) {
			for(Key key : st[i].keys()) {
				queue.enqueue(key);
			}
		}
		return queue;
	}
}
