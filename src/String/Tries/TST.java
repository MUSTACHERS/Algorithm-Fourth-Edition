package String.Tries;

import edu.princeton.cs.algs4.Queue;

public class TST<Value> {
	private Node root; // ���ĸ����
	private int N;
	
	private class Node {
		char c; // �ַ�
		Node left, mid, right; // �� �� �� ���������
		Value val; // ���ַ����������ֵ
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
		 // �ַ�c����ǰ�������ַ�x.c��Ƚ�
		if(c < x.c) { 
			return get(x.left, key, d); // �����������в���
		} else if(c > x.c) {
			return get(x.right, key, d); // �����������в���
		} else if(d < key.length() - 1) { // �ڴ����� �� c = x.c Ϊǰ�ᣬ�ж��Ƿ񵽴��ַ���ĩβ
			return get(x.mid, key, d+1); // �����û����ͼ���������������
		} else { // �ڴ����� c = x.c �� d = key.length()-1 Ϊǰ���
			return x; // ����
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
		// Ҫ���ҵ��ַ��ȵ�ǰ�ַ�С����������������
		if(c < x.c) {
			x.left = put(x.left, key, val, d);
		} else if(c > x.c) { // Ҫ���ҵ��ַ��ȵ�ǰ�ַ�����������������
			x.right = put(x.right, key, val, d);
		} else if(d < key.length() - 1) { // �� c=x.c��ǰ���£�����û����β�ַ������������м���������һ���ַ�
			x.mid = put(x.mid, key, val, d+1);
		} else { 
			// ���Ϊ����˵���²��������N++
			// �����Ϊ����˵������ֵ
			if(x.val == null) { 
				N++;
			}
			// ����ֵ�Ƿ�Ϊ�գ���������ֵ
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
	// ѡ�������ӱ�ʾ����������һ���ַ�
	// ѡ���������ӱ�ʾ������ַ�����
	public Iterable<String> keysWithPrefix(String pre) {
		Queue<String> q = new Queue<String>();
		Node x = get(root, pre, 0); // �õ�preǰ׺������
		if(x == null) {
			return q;
		}
		// ���get���ص��������ֵ��Ϊ�գ���ҲҪ��������
		if(x.val != null) {
			q.enqueue(pre); 
		}
		// �ҵ�ǰ׺�����������еݹ飬��ʾ������һ���ַ�
		// �����Ҳ�����˺þã�Ϊʲô���������ݹ�
		// ԭ�������Ѿ��ҵ���ǰ׺����ô����ǰ׺���������������� ǰ׺ Ϊ��ͷ��
		collect(x.mid, pre, q);
		return q;
	}
	private void collect(Node x, String pre, Queue<String> q) {
		if(x == null) {
			return ;
		}
		collect(x.left, pre, q);
		// ���ֵ��Ϊ�գ���˵������ĳ�ַ����Ľ�β��Ӧ�ñ����ַ���
		if(x.val != null) {
			// pre��������ǰ��x.c ����Ҫ����
			q.enqueue(pre + x.c);
		}
		// ���Ƕ�����������ģ���ʾ������һ���ַ������� pre Ӧ�ü��ϵ�ǰ�� x.c
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
		// �������ռ�
		if(next == '.' || next < x.c) {
			collect(x.left, pre, pat, d, q);
		}
		// �������ռ�
		if(next == '.' || next == x.c) {
			// ��ͨ����ַ����ĳ���һ�£���ֵ��Ϊ�ղŻᱻ�������
			if(d == pat.length() - 1 && x.val != null) {
				q.enqueue(pre + x.c);
			} else if(d < pat.length() - 1) { // �����û���� ͨ����ַ����ĳ���������������ռ�
				collect(x.mid, pre + x.c, pat, d+1, q);
			}
		}
		// �������ռ�
		if(next == '.' || next > x.c) {
			collect(x.right, pre, pat, d, q);
		}
	}
	// ���ظ����ַ����ڷ��ű��д�����ӵ���ǰ׺���ַ���
	public String longestPrefixOf(String s) {
		int length = search(root, s, 0, 0);
		return s.substring(0, length);
	}
	private int search(Node x, String s, int d, int length) {
		// ���������ӣ��򷵻�·���������һ����
		if(x == null) {
			return length;
		}
		// ��Ϊ����˵�����ű��д�������ַ������ǵ�ǰ�����ַ������ǰ׺������length
		if(x.val != null) {
			// ����� TrieST��ͬ��������root����ַ�������d�����ַ����������ַ����ܳ������1��������2���ַ����ܳ���Ϊ3
			length = d + 1;
		}
		// �����ַ���ĩβ�������ǰ׺�ĳ���
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
