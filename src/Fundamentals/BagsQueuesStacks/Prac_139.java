package Fundamentals.BagsQueuesStacks;

/**
 * ���ʹ��char��Ƚ��ѣ����һ��ý���String������
 * �����ַ��������ӣ����ַ����� �� �� ��ֳ����飨split(�� ��)��
 * ����ջ��һ��ջ���洢�����(operStack)����һ��ջ���洢����(dataStack)
 * 		���Ϊ���������ѹ��operStack
 * 		���Ϊ ��)�������operStack�е���һ�����������dataStack�е����������֣��������ַ��������ӣ���ȫ ��(�������Ѳ�ȫ���ַ���ѹ��dataStack
 * 		����ѡ��ѹ��dataStack������Ϊ��ȫ���´ε����Ǵ����� () ��ʽ���ַ���
 * 		���Ϊ���֣���ѹ��dataStack
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
