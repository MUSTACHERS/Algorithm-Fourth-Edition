package Sorting.PriorityQueues;

/**
 *  堆数组从0开始
 */
import java.util.Arrays;

import edu.princeton.cs.algs4.In;

public class HeapSort{
	public static void sort(Comparable[] a) {
		int N = a.length;
//		建堆
		for(int k = N / 2 - 1; k >= 0; k--) { 
			sink(a, k, N);
		}
		System.out.println(Arrays.toString(a));
//		调整堆结构+交换堆顶元素与末尾元素
		while(N - 1 > 0) { 
			exch(a, 0, --N); // 把第一个元素(是最大的元素)跟最后一个交换
			sink(a, 0, N); // 重新对堆进行调整
		}
	}
	public static void sink(Comparable[] a, int k, int N) {
		while((2*k + 1) < N) {
			int j = 2 * k + 1;
			if(j+1 < N && less(a, j, j+1)) { // j + 1 < N 保证有j + 1
				j = j + 1;
			}
			if(! less(a, k, j)) { //如果父结点大于子节点的话，就退出
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
