package Sorting.PriorityQueues;

/**
 *  �������0��ʼ
 */
import java.util.Arrays;

import edu.princeton.cs.algs4.In;

public class HeapSort{
	public static void sort(Comparable[] a) {
		int N = a.length;
//		����
		for(int k = N / 2 - 1; k >= 0; k--) { 
			sink(a, k, N);
		}
		System.out.println(Arrays.toString(a));
//		�����ѽṹ+�����Ѷ�Ԫ����ĩβԪ��
		while(N - 1 > 0) { 
			exch(a, 0, --N); // �ѵ�һ��Ԫ��(������Ԫ��)�����һ������
			sink(a, 0, N); // ���¶Զѽ��е���
		}
	}
	public static void sink(Comparable[] a, int k, int N) {
		while((2*k + 1) < N) {
			int j = 2 * k + 1;
			if(j+1 < N && less(a, j, j+1)) { // j + 1 < N ��֤��j + 1
				j = j + 1;
			}
			if(! less(a, k, j)) { //������������ӽڵ�Ļ������˳�
				break;
			}
			exch(a, k, j);
			k = j;
		}
	}
	public static void exch(Comparable[] a, int v, int w) {
		Comparable temp = a[v];
		a[v] = a[w];
		a[w] = temp;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean less(Comparable[] a, int v, int w) {
		return a[v].compareTo(a[w]) < 0;
	}
	public static void main(String[] args) {
		String[] a = {"A","B","C","D","E"};
		sort(a);
		System.out.println(Arrays.toString(a));
	}
}
