package Fundamentals.BagsQueuesStacks;

/**
 * 记得输入时隔空
 * 要从队列中输出倒数第k个字符，则我们可以得知队列的size()，然后使用 size() - k 得到从左边算起的数
 * 然后使用循环将size() - k 前面的数出列，最后循环结束再来打印队列的出列
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
