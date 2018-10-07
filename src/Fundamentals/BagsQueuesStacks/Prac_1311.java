package Fundamentals.BagsQueuesStacks;

/**
 * ���������ʽ������ֵ����ӡ�����
 * 
 * �����ȡ�����֣���ѹ��ջ��
 * �����ȡ������������ջ�е������������������Ӧ��������ļ��㣬�ٰѼ���Ľ��ѹ��ջ��
 * 
 */
import java.util.Scanner;
import edu.princeton.cs.algs4.StdOut;

public class Prac_1311 {
	
	public static int evaluatePostfix(String s) {
		String[] str = s.split(" ");
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < str.length; i++) {
			if(str[i].equals("+")) {
				int d1 = stack.pop();
				int d2 = stack.pop();
				stack.push(d1 + d2);
			} else if(str[i].equals("-")) {
				int d1 = stack.pop();
				int d2 = stack.pop();
				stack.push(d1 - d2);
			} else if(str[i].equals("*")) {
				int d1 = stack.pop();
				int d2 = stack.pop();
				stack.push(d1 * d2);
			} else if(str[i].equals("/")) {
				int d1 = stack.pop();
				int d2 = stack.pop();
				stack.push(d1 / d2);
			} else {
				stack.push(Integer.parseInt(str[i]));
			}
		}
		return stack.pop();
	}
	
	public static void main(String[] args) {
		Scanner sca = new Scanner(System.in);
		while(sca.hasNext()) {
			StdOut.println(evaluatePostfix(sca.nextLine()));
		}
		sca.close();
	}
}
