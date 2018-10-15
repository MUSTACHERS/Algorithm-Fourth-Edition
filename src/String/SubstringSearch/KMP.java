package String.SubstringSearch;



import edu.princeton.cs.algs4.StdOut;

/**
 * @author MUSTACHE
 * 
 * %java KMP AACAA AABRAACADABRAACAADABRA
 * text:   AABRAACADABRAACAADABRA
 * pattern:             AACAA
 */
public class KMP {
	private String pat;
	private int[][] dfa;
	public KMP(String pat) {
		this.pat = pat;
		int M = pat.length();
		int R = 256;
		dfa = new int[R][M];
		dfa[pat.charAt(0)][0] = 1;
		for(int X = 0, j = 1; j < M; j++) {
			// ����dfa[][]
			for(int c = 0; c < R; c++) {
				dfa[c][j] = dfa[c][X]; // ����ƥ��ʧ������µ�ֵ
			}
			dfa[pat.charAt(j)][j] = j+1; // ����ƥ��ɹ�����µ�ֵ
			X = dfa[pat.charAt(j)][X]; // ��������״̬
		}
	}
	
	public int search(String txt) {
		int i, j, N = txt.length(), M = pat.length();
		for(i = 0, j = 0; i < N && j < M; i++) {
			j = dfa[txt.charAt(i)][j];
		}
		if(j == M) {
			return i - M; // �ҵ�ƥ�䣨����ģʽ�ַ����Ľ�β��
		} else {
			return N; // δƥ�䣨�����ı��ַ����Ľ�β��
		}
	}
	
	public static void main(String[] args) {
		String pat = args[0];
		String txt = args[1];
		KMP kmp = new KMP(pat);
		StdOut.println("text:   " + txt);
		int offset = kmp.search(txt);
		StdOut.print("pattern: ");
		for(int i = 0; i < offset; i++) {
			StdOut.print(" ");
		}
		StdOut.println(pat);
	}
}
