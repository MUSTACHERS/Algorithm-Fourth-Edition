package Fundamentals.Practice;


public class Prac_1115 {
	public static void main(String[] args) {
		int[] a = {1,1,2,4,3,4};
		int M = 4;
		int[] b = new int[4];
		b = histogram(a,M);
		System.out.println("��������������Ԫ��֮����a.length��ȣ�"+((sum(b)==a.length) ? "���" : "�����"));
	}
	public static int[] histogram(int a[], int M) {
    	if (M > a.length) {
    		System.out.println("We don't have enough data in the input array");
    	}
		int[] b = new int[M];
		for(int i = 0; i < b.length; i++) { // �����b[]��ѭ��
			int count = 0;
			for(int j = 0; j < a.length; j++) {
				if(i == a[j]) {
					count++; // ��¼����i��a[i](a[j])�г��ֵĴ���
				}
			}
			b[i] = count; // �Ѽ�¼����i���ֵĴ�������b[i]
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
