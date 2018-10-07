package Fundamentals.BagsQueuesStacks;

/**
 * 如果使用char会比较难，而且还得借助String来连接
 * 利用字符串的连接，把字符串按 “ ” 拆分成数组（split(“ ”)）
 * 两个栈，一个栈来存储运算符(operStack)，另一个栈来存储数字(dataStack)
 * 		如果为运算符，则压入operStack
 * 		如果为 “)”，则从operStack中弹出一个运算符，从dataStack中弹出两个数字，并利用字符串的连接，补全 “(”，最后把补全的字符串压入dataStack
 * 		这里选择压入dataStack中是因为补全后，下次弹出是带这种 () 格式的字符串
 * 		如果为数字，则压入dataStack
 */
import edu.princeton.cs.algs4.StdIn;

public class Prac_139 {
	
	public static boolean isOperator(String s) {
		return (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*"));
	}
	
	public static String getCompleteExpression(String a) {
		Stack<String> operStack = new Stack<String>();
		Stack<String> dataStack = new Stack<String>();
		String[] ch = a.split(" ");
		for(int i =0; i < ch.length; i++) {
			if(isOperator(ch[i])) {
				operStack.push(ch[i]);
			} else if(ch[i].equals(")")) {
				String d1 = dataStack.pop();
				String d2 = dataStack.pop();
				String o = operStack.pop();
				dataStack.push("( " + d2 + " " + o + " " + d1 + " )");
			} else {
				dataStack.push(ch[i]);
			}
		}
		return dataStack.pop();
	}
	public static void main(String[] args) {
		String expression = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
		System.out.println(getCompleteExpression(expression));
	}
}
