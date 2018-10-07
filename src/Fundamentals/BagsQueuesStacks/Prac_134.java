package Fundamentals.BagsQueuesStacks;

/**
 * @Analise：把读入的数据(String)转换为字符型，然后使用循环对单个字符进行判断
 * 					如果是左符合则压入栈中( push() )
 * 					如果是右符号，则利用栈判断一下前一个符号是否与 右符号对应的左符号 相符合( pop() )，如果不符合则跳出循环
 *					 如果栈为空的话则说明没有读取到有效的符合(指的是如果不是左右符号外的内容)
 * @return 利用循环变量的i来与传入的input字符串的长度相比较,同时要瞒足栈为空
 * (因为每个左符号有其对应的右符号，当循环结束，栈如果还不为空则说明还有残留不对应的符号)
 * 
 * 
 * % more Prac_134.txt
 * [()]{}{[()()]()}
 * [(])
 * [2[]
 * 
 */

import edu.princeton.cs.algs4.StdIn;

public class Prac_134 {
	
	public static boolean isValid(String input) {
		Stack<Character> stack = new Stack<Character>();
		int i;
		for(i = 0; i < input.length(); i++) {
//			把字符串转换为字符
			char ch = input.charAt(i);
//			如果是左符号则压入栈中
			if(ch == '(' || ch == '[' || ch == '{') {
				stack.push(ch);
			} else if(ch == ')'){
				if('(' != stack.pop()) {
					break;
				}
			} else if(ch == ']') {
				if('[' != stack.pop()) {
					break;
				}
			} else if(ch == '}') {
				if('{' != stack.pop()) {
					break;
				}
			} else if(stack.isEmpty()) {
				break;
			} else {
//				other character
			}
		}
		return (i == input.length() && stack.isEmpty());
	}
	
	public static void main(String[] args) {
		String input = null;
		while(!StdIn.isEmpty()) {
			 input = StdIn.readString();
		}
		if(isValid(input)) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
}
