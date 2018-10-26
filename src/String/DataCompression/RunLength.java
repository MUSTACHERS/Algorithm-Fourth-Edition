package String.DataCompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class RunLength {
	
	/*
	 * 1.读取一个游程的长度。
	 * 2.将当前比特按照长度复制并打印。
	 * 3.转换当前比特然后继续，直到输入结束。
	 */
	public static void expand() {
		boolean b = false; // 比特
		while(! BinaryStdIn.isEmpty()) {
			char cnt = BinaryStdIn.readChar(); // 1
			for(int i = 0; i < cnt; i++) { // 2
				BinaryStdOut.write(b);
			}
			// 根据游程编码的长度打印0（或1）后 把当前比特取反变成1（或0）
			b = !b; // 3
		}
		BinaryStdOut.close();
	}
	
	/*
	 * 1.读取一个比特。
	 * 2.如果它和上一个比特不同，写入当前的计数值并将计数器归零。
	 * 3.如果它和上一个比特相同且计数器已经到达最大值，则写入计数值，再写入一个0计数值，然后将计数器归零
	 * 4.增加计数器的值。
	 */
	public static void compress() {
		char cnt = 0; // 计数器
		boolean b, old = false; // 比特 和 上一个比特
		while(! BinaryStdIn.isEmpty()) {
			b = BinaryStdIn.readBoolean(); // 1
			if(b != old) { // 2
				BinaryStdOut.write(cnt);
				cnt = 0;
				old = !old;
			} else { // 3
				if(cnt == 255) {
					BinaryStdOut.write(cnt);
					cnt = 0;
					BinaryStdOut.write(cnt);
				}
			}
			cnt++; // 4
		}
		BinaryStdOut.write(cnt);
		BinaryStdOut.close();
	}
	
	public static void main(String[] args) {
		if(args[0].equals("-")) {
			compress();
		}
		if(args[0].equals("+")) {
			expand();
		}
	}
}
