package Fundamentals.BagsQueuesStacks;

/**
 * 输入后序表达式，计算值并打印出结果
 * 
 * 如果读取到数字，则压入栈中
 * 如果读取到运算符，则从栈中弹出两个数，进行与对应的运算符的计算，再把计算的结果压入栈中
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
