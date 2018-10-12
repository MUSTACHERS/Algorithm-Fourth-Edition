package String.Tries;

import edu.princeton.cs.algs4.Queue;

public class TST<Value> {
	private Node root; // 树的根结点
	private int N;
	
	private class Node {
		char c; // 字符
		Node left, mid, right; // 左 中 右 三向的链接
		Value val; // 和字符串相关联的值
	}
	public Value get(String key) {
		Node x = get(root, key, 0);
		if(x == null) {
			return null;
		}
		return (Value)x.val;
	}
	private Node get(Node x, String key, int d) {
		if(x == null) {
			return null;
		}
		char c = key.charAt(d);
		 // 字符c跟当前根结点的字符x.c相比较
		if(c < x.c) { 
			return get(x.left, key, d); // 则在左子树中查找
		} else if(c > x.c) {
			return get(x.right, key, d); // 则在右子树中查找
		} else if(d < key.length() - 1) { // 在此是以 当 c = x.c 为前提，判断是否到达字符串末尾
			return get(x.mid, key, d+1); // 如果还没到达，就继续在中子树查找
		} else { // 在此是以 c = x.c 和 d = key.length()-1 为前提的
			return x; // 命中
		}
	}
	
	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}
	private Node put(Node x, String key, Value val, int d) {
		char c = key.charAt(d);
		if(x == null) {
			x = new Node();
			x.c = c;
		}
		// 要查找的字符比当前字符小，则在左子树查找
		if(c < x.c) {
			x.left = put(x.left, key, val, d);
		} else if(c > x.c) { // 要查找的字符比当前字符大，则在右子树查找
			x.right = put(x.right, key, val, d);
		} else if(d < key.length() - 1) { // 在 c=x.c的前提下，但还没到达尾字符，在中子树中继续查找下一个字符
			x.mid = put(x.mid, key, val, d+1);
		} else { 
			// 如果为空则说明新插入键，则N++
			// 如果不为空则说明更新值
			if(x.val == null) { 
				N++;
			}
			// 不管值是否为空，都会设置值
			x.val = val;
		}
		return x;
	}
	
	public boolean contains(String key) {
		return get(key) != null;
	}
	public int size() {
		return N;
	}
	public boolean isEmpty() {
		return N == 0;
	}
	
	public Iterable<String> keys() {
		return keysWithPrefix("");
	}
	// 选择中链接表示继续处理下一个字符
	// 选择左右链接表示处理的字符不变
	public Iterable<String> keysWithPrefix(String pre) {
		Queue<String> q = new Queue<String>();
		Node x = get(root, pre, 0); // 得到pre前缀的子树
		if(x == null) {
			return q;
		}
		// 如果get返回的这个结点的值不为空，则也要把它入列
		if(x.val != null) {
			q.enqueue(pre); 
		}
		// 找到前缀，在中子树中递归，表示处理下一个字符
		// 这个我也纳闷了好久，为什么是中子树递归
		// 原因：我们已经找到了前缀，那么沿着前缀的中子树，都是以 前缀 为开头的
		collect(x.mid, pre, q);
		return q;
	}
	private void collect(Node x, String pre, Queue<String> q) {
		if(x == null) {
			return ;
		}
		collect(x.left, pre, q);
		// 如果值不为空，则说明到达某字符串的结尾，应该保存字符串
		if(x.val != null) {
			// pre不包含当前的x.c 所以要加上
			q.enqueue(pre + x.c);
		}
		// 凡是对中子树处理的，表示处理下一个字符，所以 pre 应该加上当前的 x.c
		collect(x.mid, pre + x.c, q);
		collect(x.right, pre, q);
	}
	
	public Iterable<String> keysThatMatch(String pat) {
		Queue<String> q = new Queue<String>();
		collect(root, "", pat, 0, q);
		return q;
	}
	private void collect(Node x, String pre, String pat, int d,Queue<String> q) {
		if(x == null) {
			return ;
		}
		char next = pat.charAt(d);
		// 左子树收集
		if(next == '.' || next < x.c) {
			collect(x.left, pre, pat, d, q);
		}
		// 中子树收集
		if(next == '.' || next == x.c) {
			// 与通配符字符串的长度一致，且值不为空才会被加入队列
			if(d == pat.length() - 1 && x.val != null) {
				q.enqueue(pre + x.c);
			} else if(d < pat.length() - 1) { // 如果还没到达 通配符字符串的长度则继续中子树收集
				collect(x.mid, pre + x.c, pat, d+1, q);
			}
		}
		// 右子树收集
		if(next == '.' || next > x.c) {
			collect(x.right, pre, pat, d, q);
		}
	}
	// 返回给定字符串在符号表中存在且拥有最长前缀的字符串
	public String longestPrefixOf(String s) {
		int length = search(root, s, 0, 0);
		return s.substring(0, length);
	}
	private int search(Node x, String s, int d, int length) {
		// 遇到空链接，则返回路径上最近的一个键
		if(x == null) {
			return length;
		}
		// 不为空则说明符号表中存在这个字符串，是当前给定字符串的最长前缀，更新length
		if(x.val != null) {
			// 这里跟 TrieST不同，在这里root存放字符，所以d就是字符索引，和字符串总长度相差1。如索引2，字符串总长度为3
			length = d + 1;
		}
		// 到达字符串末尾，返回最长前缀的长度
		if(d == s.length() - 1) {
			return length;
		}
		
		char c = s.charAt(d);
		if(c < x.c) {
			return search(x.left, s, d, length);
		} else if(c > x.c) {
			return search(x.right, s, d, length);
		} else {
			return search(x.mid, s, d + 1, length);
		}
	}
	
	public static void main(String[] args) {
		TST<Integer> st = new TST<Integer>();
		st.put("she", 0);
		st.put("sells", 1);
		st.put("sea", 2);
		st.put("shells", 3);
		st.put("by", 4);
		st.put("the", 5);
		st.put("sea", 6);
		st.put("shore", 7);
		System.out.println(st.size());
		System.out.println(st.get("sea"));
		System.out.println(st.get("sno"));
		
		System.out.println(st.keysWithPrefix("she"));
		System.out.println(st.keysWithPrefix("s.."));
		
		System.out.println(st.longestPrefixOf("shell"));
	}
}
