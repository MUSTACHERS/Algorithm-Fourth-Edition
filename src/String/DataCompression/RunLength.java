package String.DataCompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class RunLength {
	
	/*
	 * 1.��ȡһ���γ̵ĳ��ȡ�
	 * 2.����ǰ���ذ��ճ��ȸ��Ʋ���ӡ��
	 * 3.ת����ǰ����Ȼ�������ֱ�����������
	 */
	public static void expand() {
		boolean b = false; // ����
		while(! BinaryStdIn.isEmpty()) {
			char cnt = BinaryStdIn.readChar(); // 1
			for(int i = 0; i < cnt; i++) { // 2
				BinaryStdOut.write(b);
			}
			// �����γ̱���ĳ��ȴ�ӡ0����1���� �ѵ�ǰ����ȡ�����1����0��
			b = !b; // 3
		}
		BinaryStdOut.close();
	}
	
	/*
	 * 1.��ȡһ�����ء�
	 * 2.���������һ�����ز�ͬ��д�뵱ǰ�ļ���ֵ�������������㡣
	 * 3.���������һ��������ͬ�Ҽ������Ѿ��������ֵ����д�����ֵ����д��һ��0����ֵ��Ȼ�󽫼���������
	 * 4.���Ӽ�������ֵ��
	 */
	public static void compress() {
		char cnt = 0; // ������
		boolean b, old = false; // ���� �� ��һ������
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
