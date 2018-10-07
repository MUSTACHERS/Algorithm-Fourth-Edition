package Fundamentals.Practice;


public class Prac_1115 {
	public static void main(String[] args) {
		int[] a = {1,1,2,4,3,4};
		int M = 4;
		int[] b = new int[4];
		b = histogram(a,M);
		System.out.println("返回数组中所有元素之和与a.length相等？"+((sum(b)==a.length) ? "相等" : "不相等"));
	}
	public static int[] histogram(int a[], int M) {
    	if (M > a.length) {
    		System.out.println("We don't have enough data in the input array");
    	}
		int[] b = new int[M];
		for(int i = 0; i < b.length; i++) { // 外层是b[]的循环
			int count = 0;
			for(int j = 0; j < a.length; j++) {
				if(i == a[j]) {
					count++; // 记录整数i在a[i](a[j])中出现的次数
				}
			}
			b[i] = count; // 把记录整数i出现的次数赋给b[i]
		}
		return b;
	}
	public static int sum(int b[]) {
		int sum = 0;
		for(int i = 0; i < b.length; i++) {
			sum += b[i];
		}
		return sum;
	}
}
