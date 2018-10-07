package Searching.Application;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * Practice 3_5_1
 * @author MUSTACHE
 * 
 * RedBlackTree == ST == TreeMap ÓÐÐò ¼üºÍÖµ
 * method:
 * 1.put()
 * 2.remove()
 * 3.get()
 * 4.containsKey()
 * 5.firstKey()
 * 6.lastKey()
 * 7.size()
 * 8.isEmpty()
 * 9.floorKey()
 * 10.cailingKey()
 * 11.keySet()  // return Iterable<Key>
 * @Deprecated
 * 12.KeySet().iterator() // return Iterator<Key> 
 */
public class ST <Key extends Comparable<Key>, Value> implements Iterable<Key>{
	private TreeMap<Key, Value> st;
	
	public ST() {
		st = new TreeMap<Key, Value>();
	}
	
	public void put(Key key, Value val) {
		if(key == null) {
			throw new IllegalArgumentException("Key can't null");
		}
		if(val == null) {
			st.remove(key);
		} else {
			st.put(key, val);
		}
	}
	
	public Value get(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Key can't null");
		}
		return st.get(key);
	}
	
	public void delete(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Key can't null");
		}
		st.remove(key);
	}
	
	public boolean contains(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Key can't null");
		}
		return st.containsKey(key);
	}
	public int size() {
		return st.size();
	}
	public boolean isEmpty() {
		return st.isEmpty();
	}
	public Iterable<Key> keys() {
		return st.keySet();
	}
	public Key min() {
		return st.firstKey();
	}
	public Key max() {
		return st.lastKey();
	}
	public Key ceiling(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("key can't null");
		}
		Key k = st.ceilingKey(key);
		if(k == null) {
			throw new NoSuchElementException("All keys are less than " + key);
		}
		return k;
	}
	public Key floor(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("key can't null");
		}
		Key k = st.floorKey(key);
		if(k == null) {
			throw new NoSuchElementException("All keys are more than " + key);
		}
		return k;
	}

	@Override
	@Deprecated
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return st.keySet().iterator();
	}
}
