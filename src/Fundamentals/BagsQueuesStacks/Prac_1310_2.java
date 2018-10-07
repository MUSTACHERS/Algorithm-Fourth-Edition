package Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 第二种做法，这种做法运算符优先级由右括号决定
 * @author MusTache
 * 
 *		如果读取到开括号，则不理，继续循环
 *		如果读取到数字，则直接打印输出
 *		如果读取到闭括号，则出一次栈
 *		如果读取到运算符，则压入栈
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
