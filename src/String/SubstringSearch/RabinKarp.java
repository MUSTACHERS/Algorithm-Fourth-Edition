package String.SubstringSearch;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
	private String pat; // ģʽ�ַ���������˹ά��˹�㷨��Ҫ��
	private long patHash; // ģʽ�ַ�����ɢ��ֵ
	private int M; // ģʽ�ַ����ĳ���
	private long Q; // һ���ܴ������
	private int R = 256; // ��ĸ��Ĵ�С
	private long RM; // R^(M-1)%Q
	public RabinKarp(String pat) {
		this.pat = pat; // ����ģʽ�ַ���������˹ά��˹�㷨��Ҫ��
		this.M = pat.length();
		Q = longRandomPrime();
		RM = 1;
		for(int i = 1; i <= M-1; i++) {
			RM = (R * RM) % Q;
		}
		patHash = hash(pat, M);
	}
//	public boolean check(int i) { // ���ؿ���汾���㷨��Monte Carlo Version��
//		return true; 
//	}
	
	public boolean check(String txt, int i) {
		for(int j = 0; j < M; j++) {
			if(pat.charAt(j) != txt.charAt(i + j)) {
				return false;
			}
		}
		return true;
	}
	private long hash(String key, int M) {
		// ����key[0..M-1]��ɢ��ֵ
		long h = 0;
		for(int j = 0; j < M; j++) {
			h = (R * h + key.charAt(j)) % Q;
		}
		return h;
	}
	private int search(String txt) {
		// ���ı��в�����ȵ�ɢ��ֵ
		int N = txt.length();
		long txtHash = hash(txt, M);
//		���ؿ����㷨
//		if(patHash == txtHash && check(0)) { // ����һ��ʼ��ƥ��ɹ�
//			return 0;
//		}
//		��˹ά��˹�㷨�����ģʽ��txt(i...i-M+1)��ƥ��
		if(patHash == txtHash && check(txt, 0)) {
			return 0;
		}
		for(int i = M; i < N; i++) {
			// ��ȥ��һ�����֣��������һ�����֣��ٴμ��ƥ��
			txtHash = (txtHash + Q - RM * txt.charAt(i-M) % Q) % Q;
			txtHash = (txtHash + txt.charAt(i)) % Q;
			// ��������ȼ���������䣬���ʻ���ͬ��ģ����ÿһ�������ȡһ���࣬�����м��������ȡ��Ľṹһ��
			// txtHash = (txtHash + Q - RM * txt.charAt(i-M) % Q) * R + txt.charAt(i)) % Q;
			// ����������䣺txtHash - RM*txt.charAt(i-M)�Ǵ����õ�ǰ��hashֵ��ȥ��һλ���֣�
			// +Q�����ֹǰ��Ľ��Ϊ������*R������Ի�����+txt.charAt(i)����������һλ���֣�Ȼ��ȡ�ࡣ
			if(patHash == txtHash) {
				if(check(txt, i-M+1)) {
					return i-M+1; // �ҵ�ƥ��
				}
			}
		}
		return N; // δ�ҵ�ƥ��
	}
	// һ�����31λ������
	private static long longRandomPrime() {
		// BigInteger �����ó���Integer��Χ�ڵ����ݽ������㣬����׼ȷ�ı�ʾ�κδ�С�����������ᶪʧ��Ϣ 
		// probablePrime(int bitlength, Random md) �����п����Ǿ��ж�����bitlengthλ�����������ؿ����������ĸ���Ϊ������2^-100
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();   // �������������long����
	}
}
