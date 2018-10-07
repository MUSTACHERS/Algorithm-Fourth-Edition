package Searching.Application;

import java.util.HashSet;
import java.util.Iterator;
/**
 * Practice 3_5_1
 * @author MUSTACHE
 *
 * HashSet allow null
 * HashSet<Key> ÎÞÐò 
 * method:
 * 1.add()
 * 2.remove()
 * 3.iterator()
 * 4.isEmpty()
 * 5.size()
 * 6.contains()
 * 
 */
public class HashSET <Key extends Comparable<Key>> implements Iterable<Key>{
	private HashSet<Key> set;
	
	public HashSET() {
		set = new HashSet<Key>();
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
		return set.contains(key);
	}
	public int size() {
		return set.size();
	}
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return set.iterator();
	}

}
