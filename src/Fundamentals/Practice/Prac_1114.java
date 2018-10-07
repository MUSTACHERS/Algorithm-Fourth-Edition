package Fundamentals.Practice;


public class Prac_1114 {
	public static void main(String[] args) {
		int g = lg(18);
		System.out.println(g);
	}
	public static int  lg(int N) {
		int k = 0;
		int M = 1; // 从2的0次方开始算起
		while(M <= N) {
			M *= 2;
			k++;
		}
//		当循环结束后说明M>N，则k-1是最大整数
		return k - 1;
	}
}
