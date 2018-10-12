package String.Tries;

import edu.princeton.cs.algs4.Queue;

public class TrieST<Value> {
	private static int R = 256; // ����
	private Node root; // ���ʲ������ĸ����
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
		// ������x��Ϊ�������ӵ��ʲ���������key�������ֵ
		if(x == null) {
			return null;
		}
		// d��¼���ʲ������Ĳ��������趨���ڸ����rootʱΪ0�����Ĳ���һ�㶨�������ǵ�һ�㣩
		// rootû�б����ַ������� d = 1 ʱ��ʾ�ַ����ĵ�0���ַ�
		// ��d = key.length() ʱ����ʾ�����ַ��������һ���ַ���length()-1��
		if(d == key.length()) {
			return x;
		}
		char c = key.charAt(d); // �ҵ���d���ַ�����Ӧ���ӵ��ʲ�����
		return get(x.next[c], key, d+1);
		// next[c] ָ��������չASCII��Ϊc���ַ�
		// �ٸ����ӣ�����cΪСд��ĸa��ACSII��Ϊ97���� next[c] -> next[97] ��ʾ���� �ַ�a��
	}
	
	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}
	private Node put(Node x, String key, Value val, int d) {
		// �����ھʹ������
		if(x == null) {
			x = new Node();
		}
		// ���key��������xΪ�������ӵ��ʲ������������������ص�ֵ
		if(d == key.length()) {
			// Ϊ����˵�������¼�����Ϊ����˵������ֵ
			if(x.val == null) {
				N++;
			}
			x.val = val;
			return x;
		}
		char c = key.charAt(d); // �ҵ���d���ַ�����Ӧ���ӵ��ʲ�����
		// ������Ҫע�⣺�״����ڸ����root�ģ��ڸ����root��������κβ���
		// ������ x.next[c] ��ʾ���� key�ĵ�һ���ַ�����d+1��ʼ
		x.next[c] = put(x.next[c], key, val, d+1);
		return x;
	}
	// �������м�
	public Iterable<String> keys() {
		return keysWithPrefix(""); // ����Ϊ�գ���ʾ�κ��ַ�����������
	}
	/**
	 * @param pre �ַ���ǰ׺
	 * @return ����
	 */
	private Iterable<String> keysWithPrefix(String pre) {
		Queue<String> q = new Queue<String>();
		// ���ҳ�����ǰ׺�������������������������Ը���ǰ׺��ͷ���ַ����������ռ��������ڶ����С�
		collect(get(root, pre, 0), pre, q); 
		return q;
	}
	private void collect(Node x, String pre, Queue<String> q) {
		if(x == null) {
			return ;
		}
		// ���ֵ��Ϊ�գ���˵���Ѿ�����ĳ���ַ����Ľ�β���ͰѸ��ַ������б���
		if(x.val != null) {
			q.enqueue(pre);
		}
		for(char c = 0; c < R; c++) {
			// ע�⣺����� pre + c ���ַ���ƴ�ӣ���Ҫ��c��������
			// ��Ϊc������������char�ͣ������������е��������Զ�ת��������ת������int��
			collect(x.next[c], pre + c, q);
		}
	}
	// ͨ���ƥ��
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
		// ��ͨ����ĳ���Ҫһ�£�����ֵ����Ϊ�ղŻᱻ�������
		if(d == pat.length() && x.val != null) {
			q.enqueue(pre);
		}
		// ��鵽ͨ����ĳ��Ⱦ���
		if(d == pat.length()) {
			return ;
		}
		char next = pat.charAt(d);
		for(char c = 0; c < R; c++) {
			// �� * �ͽ��������next�����е��ַ����ݹ��ռ���h����ָ�����ַ�c���Ͱ���ָ���ַ����ռ�
			if(next == '.' || next == c) {
				collect(x.next[c], pre + c, pat, q);
			}
		}
	}
	// ���ظ����ַ����ڷ��ű��д�����ӵ���ǰ׺���ַ���
	public String longestPrefixOf(String s) {
		int length = search(root, s, 0, 0);
		return s.substring(0, length);
	}
	private int search(Node x, String s, int d, int length) {
		// ���������ӣ�����·���������һ����
		if(x == null) {
			return length;
		}
		// ��Ϊ�գ�˵�����ű��д�������ַ������ǵ�ǰ�����ַ������ǰ׺������length
		if(x.val != null) {
			length = d;
		}
		// �����ַ�����β�������ǰ׺�ĳ���
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
		// ��������ַ���ĩβ��ֹͣ�ݹ�
		if(d == key.length()) {
			// Ҫɾ���ļ�Ҫȷ�������ڷ��ű��вż�С����
			if(x.val != null) {
				x.val = null;
				N--;
			}
		} else {
			char c = key.charAt(d);
			// û�е����ַ���ĩβ�ͼ����ݹ�ɾ��
			x.next[c] = delete(x.next[c], key, d + 1);
		}
		// ����������������ֵ��Ϊ�գ�����ɾ��
		if(x.val != null) {
			return x;
		}
		// �������ֵΪ�գ����Ǹý�������Ӳ�Ϊ�գ�����ɾ��
		for(char c = 0; c < R; c++) {
			if(x.next[c] != null) {
				return x;
			}
		}
		// �������ϵ������������ �������Ϊ�գ�ֵҲΪ�գ���ɾ��������null
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
