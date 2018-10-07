package Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * �ڶ�������������������������ȼ��������ž���
 * @author MusTache
 * 
 *		�����ȡ�������ţ���������ѭ��
 *		�����ȡ�����֣���ֱ�Ӵ�ӡ���
 *		�����ȡ�������ţ����һ��ջ
 *		�����ȡ�����������ѹ��ջ
 *
 */
public class Prac_1310_2 {
	
	public static boolean isOperator(String ch) {
		return (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/"));
	}
	public static void infixToPostfix(String s) {
		String[] str = s.split(" ");
		Stack<String> operatorStack = new Stack<String>();
		for(int i = 0; i < str.length; i++) {
			if(str[i].equals("(")) {
				continue;
			} else if(str[i].equals(")")) {
				StdOut.print(operatorStack.pop() + " ");
			} else if(isOperator(str[i])) {
				operatorStack.push(str[i]);
			} else {
				StdOut.print(str[i] + " ");
			}
		}
		StdOut.println("");
	}
	
	public static void main(String[] args) {
		String da = "( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )";
		infixToPostfix(da);
	}
}
