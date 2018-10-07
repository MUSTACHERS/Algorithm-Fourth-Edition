package Fundamentals.BagsQueuesStacks;

/**
 * @Analise���Ѷ��������(String)ת��Ϊ�ַ��ͣ�Ȼ��ʹ��ѭ���Ե����ַ������ж�
 * 					������������ѹ��ջ��( push() )
 * 					������ҷ��ţ�������ջ�ж�һ��ǰһ�������Ƿ��� �ҷ��Ŷ�Ӧ������� �����( pop() )�����������������ѭ��
 *					 ���ջΪ�յĻ���˵��û�ж�ȡ����Ч�ķ���(ָ��������������ҷ����������)
 * @return ����ѭ��������i���봫���input�ַ����ĳ�����Ƚ�,ͬʱҪ����ջΪ��
 * (��Ϊÿ������������Ӧ���ҷ��ţ���ѭ��������ջ�������Ϊ����˵�����в�������Ӧ�ķ���)
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
//			���ַ���ת��Ϊ�ַ�
			char ch = input.charAt(i);
//			������������ѹ��ջ��
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
