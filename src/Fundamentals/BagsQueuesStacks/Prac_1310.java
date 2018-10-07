package Fundamentals.BagsQueuesStacks;


/**
 * 中序表达式转化为后序表达式
 * 分四种情况：
 * 		如果读取到的是数字，则直接打印输出
 * 		如果读取到的是开括号，则压入栈中
 * 		如果读取到的是闭括号，则连续弹出栈，直到弹出的是开括号
 * 		如果读取到的是运算符：
 * 				a. 如果 栈不为空 或 栈顶不是开括号 或 栈顶弹出的运算符的优先级不低于读取的运算符的优先级，则连续出栈打印，则到栈为空
 * 				b. 再把当前读取到的运算符压入栈中
 * 
 */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Prac_1310 {
	
	public static boolean isOperator(String s) {
		return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
	}
	
	public static void main(String[] args) {
//		输入的时候记得隔空
		Stack<String> operatorStack = new Stack<String>();
		while(!StdIn.isEmpty()) {
			String str = StdIn.readString();
			if(str.equals("(")) {
				operatorStack.push(str);
			} else if(isOperator(str)) {
				boolean isEmpty;
				boolean isLeftParen;
				boolean isLowerOperator; // 记录栈顶的操作符的优先级是否低于当前读取的操作符
				isEmpty = operatorStack.isEmpty();
				isLeftParen = false;
				isLowerOperator = false;
				if(! isEmpty) {
					String stackTop = operatorStack.peek();
					isLeftParen = stackTop.equals("(");
					if((stackTop.equals("-") || stackTop.equals("+")) && (str.equals("*") || str.equals("/"))) {
						isLowerOperator = true;
					}
				}
				if(! (isEmpty || isLeftParen || isLowerOperator)) {
					for(int i = 0; i < operatorStack.size(); i++) {
						StdOut.print(operatorStack.pop());
					}
				}
				operatorStack.push(str);
			} else if(str.equals(")")) {
				String pop = operatorStack.pop();
				while(! pop.equals("(")) {
					StdOut.print(pop);
					pop = operatorStack.pop();
				}
			} else {
				StdOut.print(str);
			}
		}
		for(String i : operatorStack) {
			StdOut.print(i);
		}
	}
}
