package String.DataCompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.StdOut;

/** 二进制转储(Dump)
 * @author MUSTACHE
 *
 * %java BinaryDump 16 < abra.txt
 * 0100000101000010
 * 0101001001000001
 * 0100001101000001
 * 0100010001000001
 * 0100001001010010
 * 0100000100100001
 * 96 bits
 * 
 */
public class BinaryDump {
	public static void main(String[] args) {
		int width = Integer.parseInt(args[0]);
		int cnt;
  		for(cnt = 0; !BinaryStdIn.isEmpty(); cnt++) {
			if(width == 0) {
				continue;
			}
			if(cnt != 0 && cnt % width == 0) {
				StdOut.println();
			}
			if(BinaryStdIn.readBoolean()) {
				StdOut.print("1");
			} else {
				StdOut.print("0");
			}
		}
		StdOut.println();
		StdOut.println(cnt + " bits");
	}
}
