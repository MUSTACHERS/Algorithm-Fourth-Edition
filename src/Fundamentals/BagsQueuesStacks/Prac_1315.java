package Fundamentals.BagsQueuesStacks;

/**
 * �ǵ�����ʱ����
 * Ҫ�Ӷ��������������k���ַ��������ǿ��Ե�֪���е�size()��Ȼ��ʹ�� size() - k �õ�������������
 * Ȼ��ʹ��ѭ����size() - k ǰ��������У����ѭ������������ӡ���еĳ���
 */
import java.util.Scanner;

import edu.princeton.cs.algs4.StdIn;

public class Prac_1315 {
	
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		Queue<String> queue = new Queue<String>();
		Scanner sca = new Scanner(System.in);
		while(sca.hasNext()) {
			queue.enqueue(sca.next());
		}
		sca.close();
		
		int n = queue.size();
		for(int i = 0; i < (n - k); i++) {
			queue.dequeue();
		}
		System.out.println(queue.dequeue());
	}
}
