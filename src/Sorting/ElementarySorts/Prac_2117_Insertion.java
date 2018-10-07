package Sorting.ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Prac_2117_Insertion {
	public static void sort(Comparable[] a){
		int N = a.length;
		for(int i=1;i<N;i++){
			for(int j=i;j>0 && less(a[j], a[j-1]); j--)
				exch(a, j, j-1);
		}
		drawSort(a);  // 因为上面的轨迹已经被清除，所以得再次绘画排序好的轨迹
	}
	public static void exch(Comparable[] a, int i, int j){
		drawSort(a);  // 画出数组的可视轨迹
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
		

		StdDraw.pause(100); 
		StdDraw.clear(); // 每次交换值后清除一下当前可视轨迹，以便下次再次绘画
	}
	private static void drawSort(Comparable[] a){
		int N = a.length;
		StdDraw.setXscale(-1, N+1); // 设置x轴范围：(-1,a.length+1)
//		先找出最大值，可以确定y的最大高度
		Comparable max=a[0];
		for(int i = 1; i < N; i++) {
			 if(less(max, a[i])) {
				 max = a[i];
			 }
		}
		StdDraw.setYscale(-1, (Integer)max+1); // 设置y轴范围：(-1,max+1)
		
		for(int i=0; i< N; i++){
			int y = (Integer)a[i];
			StdDraw.filledRectangle(i, y/2.0, 0.3, y/2.0);
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	public static boolean isSorted(Comparable[] a) {
		for(int i = 1; i < a.length; i++) { 
			if(less(a[i], a[i - 1])) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		int N = 20;
		Integer[] a = new Integer[N];
		for(int i=0;i<N;i++) {
			a[i] = i + 1;
		}
    	StdRandom.shuffle(a);
		sort(a);
		assert isSorted(a);
	}
}
