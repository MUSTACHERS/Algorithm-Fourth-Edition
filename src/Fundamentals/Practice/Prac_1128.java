package Fundamentals.Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prac_1128 {
	public static Integer[] distinctArray(int[] array) {
//		使用list来收集
		List<Integer> list = new ArrayList<Integer>();
		Arrays.sort(array);
		list.add(array[0]);
		for(int i = 1; i < array.length; i++) {
			if(array[i] != array[i - 1]) {
				list.add(array[i]);
			}
		}
//		注意：toArray有两种，一种是无参但需要强制转换，但有可能会出错
//		第一种，参数接受一个需要转换的类型
		Integer[] newList = list.toArray(new Integer[list.size()]); 
		return newList;
	}
	
	public static void main(String[] args) {
		int[] a = {1,2,5,9,5,4,2,1,1,2,4};
		Integer[] list = distinctArray(a);
		for(int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
	}
}
