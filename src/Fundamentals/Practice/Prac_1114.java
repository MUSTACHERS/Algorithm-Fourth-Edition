package Fundamentals.Practice;


public class Prac_1114 {
	public static void main(String[] args) {
		int g = lg(18);
		System.out.println(g);
	}
	public static int  lg(int N) {
		int k = 0;
		int M = 1; // ��2��0�η���ʼ����
		while(M <= N) {
			M *= 2;
			k++;
		}
//		��ѭ��������˵��M>N����k-1���������
		return k - 1;
	}
}
