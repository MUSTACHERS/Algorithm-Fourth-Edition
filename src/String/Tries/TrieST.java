package String.Tries;

import edu.princeton.cs.algs4.Queue;

public class TrieST<Value> {
	private static int R = 256; // 基数
	private Node root; // 单词查找树的根结点
	private int N;
	
	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}
	public Value get(String key) {
		Node x = get(root, key, 0);
		if(x == null) {
			return null;
		}
		return (Value) x.val;
	}
	private Node get(Node x, String key, int d) {
		// 返回以x作为根结点的子单词查找树中与key相关联的值
		if(x == null) {
			return null;
		}
		// d记录单词查找树的层数，假设定义在根结点root时为0（树的层数一般定义根结点是第一层）
		// root没有保存字符，所以 d = 1 时表示字符串的第0个字符
		// 当d = key.length() 时，表示到达字符串的最后一个字符（length()-1）
		if(d == key.length()) {
			return x;
		}
		char c = key.charAt(d); // 找到第d个字符所对应的子单词查找树
		return get(x.next[c], key, d+1);
		// next[c] 指代的是扩展ASCII码为c的字符
		// 举个栗子：比如c为小写字母a，ACSII码为97，则 next[c] -> next[97] 表示的是 字符a。
	}
	
	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}
	private Node put(Node x, String key, Value val, int d) {
		// 不存在就创建结点
		if(x == null) {
			x = new Node();
		}
		// 如果key存在于以x为根结点的子单词查找树中则更新与它相关的值
		if(d == key.length()) {
			// 为空则说明插入新键，不为空则说明更新值
			if(x.val == null) {
				N++;
			}
			x.val = val;
			return x;
		}
		char c = key.charAt(d); // 找到第d个字符所对应的子单词查找树
		// 在这里要注意：首次是在根结点root的，在根结点root不会进行任何操作
		// 在下面 x.next[c] 表示的是 key的第一个字符，从d+1开始
		x.next[c] = put(x.next[c], key, val, d+1);
		return x;
	}
	// 查找所有键
	public Iterable<String> keys() {
		return keysWithPrefix(""); // 参数为空，表示任何字符都可以入列
	}
	/**
	 * @param pre 字符串前缀
	 * @return 迭代
	 */
	private Iterable<String> keysWithPrefix(String pre) {
		Queue<String> q = new Queue<String>();
		// 先找出给定前缀的子树，该子树包含了所有以给定前缀开头的字符串，从中收集并保存在队列中。
		collect(get(root, pre, 0), pre, q); 
		return q;
	}
	private void collect(Node x, String pre, Queue<String> q) {
		if(x == null) {
			return ;
		}
		// 如果值不为空，则说明已经到达某个字符串的结尾，就把该字符串入列保存
		if(x.val != null) {
			q.enqueue(pre);
		}
		for(char c = 0; c < R; c++) {
			// 注意：这里的 pre + c 是字符的拼接，不要把c当数字了
			// 因为c变量本来就是char型，而放在数组中当索引会自动转换（向上转换）成int型
			collect(x.next[c], pre + c, q);
		}
	}
	// 通配符匹配
	public Iterable<String> keysThatMatch(String pat) {
		Queue<String> q = new Queue<String>();
		collect(root, "", pat, q);
		return q;
	}
	private void collect(Node x, String pre, String pat, Queue<String> q) {
		int d = pre.length();
		if(x == null) {
			return ;
		}
		// 和通配符的长度要一致，且其值不能为空才会被加入队列
		if(d == pat.length() && x.val != null) {
			q.enqueue(pre);
		}
		// 检查到通配符的长度就行
		if(d == pat.length()) {
			return ;
		}
		char next = pat.charAt(d);
		for(char c = 0; c < R; c++) {
			// 是 * 就将结点数组next中所有的字符都递归收集，h或者指定了字符c，就按照指定字符来收集
			if(next == '.' || next == c) {
				collect(x.next[c], pre + c, pat, q);
			}
		}
	}
	// 返回给定字符串在符号表中存在且拥有最长前缀的字符串
	public String longestPrefixOf(String s) {
		int length = search(root, s, 0, 0);
		return s.substring(0, length);
	}
	private int search(Node x, String s, int d, int length) {
		// 遇到空链接，返回路径上最近的一个键
		if(x == null) {
			return length;
		}
		// 不为空，说明符号表中存在这个字符串，是当前给定字符串的最长前缀，更新length
		if(x.val != null) {
			length = d;
		}
		// 到达字符串结尾，返回最长前缀的长度
		if(d == s.length()) {
			return  length;
		}
		char c = s.charAt(d);
		return search(x.next[c], s, d+1, length);
	}
	
	public void delete(String key) {
		root = delete(root, key, 0);
	}
	private Node delete(Node x, String key, int d) {
		if(x == null) {
			return null;
		}
		// 到达给定字符串末尾，停止递归
		if(d == key.length()) {
			// 要删除的键要确定存在于符号表中才减小个数
			if(x.val != null) {
				x.val = null;
				N--;
			}
		} else {
			char c = key.charAt(d);
			// 没有到达字符串末尾就继续递归删除
			x.next[c] = delete(x.next[c], key, d + 1);
		}
		// 检查子树，如果结点的值不为空，则不能删除
		if(x.val != null) {
			return x;
		}
		// 如果结点的值为空，但是该结点有链接不为空，不能删除
		for(char c = 0; c < R; c++) {
			if(x.next[c] != null) {
				return x;
			}
		}
		// 不是以上的两种情况，即 结点链接为空，值也为空，则删除，返回null
		return null;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	public boolean contains(String key) {
		return get(key) != null;
	}
	public int size() {
		return N;
	}
	
	public static void main(String[] args) {
		TrieST<Integer> st = new TrieST<Integer>();
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
