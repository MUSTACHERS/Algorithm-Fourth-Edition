package Fundamentals.Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prac_1128 {
	public static Integer[] distinctArray(int[] array) {
//		ʹ��list���ռ�
		List<Integer> list = new ArrayList<Integer>();
		Arrays.sort(array);
		list.add(array[0]);
		for(int i = 1; i < array.length; i++) {
			if(array[i] != array[i - 1]) {
				list.add(array[i]);
			}
		}
//		ע�⣺toArray�����֣�һ�����޲ε���Ҫǿ��ת�������п��ܻ����
//		��һ�֣���������һ����Ҫת��������
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
