package Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {
	
	public static void main(String[] args) {
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
		
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
//			读取字符，如果是运算符则压入栈
			if      	(s.equals("("))           			  ;
            else if 	(s.equals("+"))    	ops.push(s);
            else if 	(s.equals("-"))    	ops.push(s);
            else if 	(s.equals("*"))    	ops.push(s);
            else if 	(s.equals("/"))    	ops.push(s);
            else if 	(s.equals("sqrt")) 	ops.push(s);
            else if 	(s.equals(")")) {
//				如果字符为 ）, 则弹出操作数和运算符，计算结果并压入栈
				String op = ops.pop();
				double v = vals.pop();
				if      	(op.equals("+"))    		v = vals.pop() + v;
                else if 	(op.equals("-"))    		v = vals.pop() - v;
                else if 	(op.equals("*"))    		v = vals.pop() * v;
                else if 	(op.equals("/"))    		v = vals.pop() / v;
                else if 	(op.equals("sqrt")) 		v = Math.sqrt(v);
				
				vals.push(v);
			} else {
//				如果字符既不是运算符也不是括号，则将它作为double值压入栈
				vals.push(Double.parseDouble(s));
			}
		}
		StdOut.println(vals.pop());
	}
}
