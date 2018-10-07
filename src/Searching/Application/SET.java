package Searching.Application;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 * Practice 3_5_1
 * @author MUSTACHE
 * 
 * TreeSet<Key> ÎÞÐò
 * method:
 * 1.add()
 * 2.remove()
 * 3.first()
 * 4.last()
 * 5.iterator()  // return Iterator<Key> and implements Iterable<Key>
 * 6.floor()
 * 7.ceiling()
 * 8.contains()
 * 9.size()
 * 10.isEmpty()
 */
public class SET <Key extends Comparable<Key>> implements Iterable<Key> {
	private TreeSet<Key> set;
	
	public SET() {
		set = new TreeSet<Key>();
	}
	
	public void add(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Key can't null");
		}
		set.add(key);
	}
	

	public void delete(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Key can't null");
		}
		set.remove(key);
	}
	
	public boolean contains(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Key can't null");
		}
		return set.contains(key);
	}
	public int size() {
		return set.size();
	}
	public boolean isEmpty() {
		return set.size() == 0;
	}
	public Iterator<Key> iterator() {
		return set.iterator();
	}
	public Key min() {
		return set.first();
	}
	public Key max() {
		return set.last();
	}
	public Key ceiling(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("key can't null");
		}
		Key k = set.ceiling(key);
		if(k == null) {
			throw new NoSuchElementException("All keys are less than " + key);
		}
		return k;
	}
	public Key floor(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("key can't null");
		}
		Key k = set.floor(key);
		if(k == null) {
			throw new NoSuchElementException("All keys are more than " + key);
		}
		return k;
	}


}

