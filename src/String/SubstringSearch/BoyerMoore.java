package String.SubstringSearch;

import edu.princeton.cs.algs4.StdOut;

public class BoyerMoore {
	private int[] right;
	private String pat;
	public BoyerMoore(String pat) {
		// ������Ծ��
		this.pat = pat;
		int M = pat.length();
		int R = 256;
		right = new int[R];
		for(int c = 0; c < R; c++) {
			right[c] = -1; // ��������ģʽ�ַ����ֵ��ַ���ֵΪ-1
		}
		for(int j = 0; j < M; j++) {
			right[pat.charAt(j)] = j;
		}
	}
	
	public int search(String txt) {
		// ��txt�ֲ���ģʽ�ַ���
		int N = txt.length();
		int M = pat.length();
		int skip;
		for(int i = 0; i < N-M; i += skip) {
			// ģʽ�ַ������ı���λ��iƥ����
			skip = 0;
			for(int j = M-1; j >= 0; j--) {
				if(pat.charAt(j) != txt.charAt(j + i)) { // ƥ��ʧ�ܵĴ���
					// �����������ִ���һ����txt�ַ�������pat�У�һ����txt�ַ���������pat�С�
					// ������ڣ������right[]���� ��ģʽ�ַ������ı����룬ʹ�ø��ַ�������ģʽ�ַ����г��������ҵ���ƥ��
					// ��������ڣ����ǻ������� ���룬ֻ������pat���Ҳ����᷵�� -1������ skip=j+1,��ô�´�i+j+1��λ��
					skip = j - right[txt.charAt(j + i)];
					// �������������䲻������i����ֱ�ӽ�i+1����֤ģʽ�ַ������������ƶ���һ��λ�� 
					// ΪʲôҪ��������Ϊ��pat���ֶ����ͬ�ַ���rightֻ��Ǹ��ַ������ֵ�λ�ã�
					// ���Լ��豾�����Ѿ����ַ���ģʽ���һ���ַ�ƥ�䣬����;������һ����ģʽ���һ���ַ�ƥ�䵫����pat��ǰλ��ƥ����ַ������������ԭ��
					// �����ֻ�����һ���ַ��ƶ�����ǰtxt��λ�ã����� skip=j-right[]=0������ƥ��ʧ�ܺ�������ѭ�������޲�ƥ�䣩��ΪiҲ����仯
					// ���ԣ���������Ҫ��֤ i+1
					if(skip < 1) {
						skip = 1;
					}
					break;
				}
			}
			if(skip == 0) { // ƥ��ɹ�
				return i;
			}
		}
		return N; // ƥ��ʧ��
	}
	
	public static void main(String[] args) {
		String pat = args[0];
		String txt = args[1];
		BoyerMoore bm = new BoyerMoore(pat);
		StdOut.println("text:    " + txt);
		int offset = bm.search(txt);
		StdOut.print("pattern: ");
		for(int i = 0; i < offset; i++) {
			StdOut.print(" ");
		}
		StdOut.println(pat);
	}
}
