package String.DataCompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author MUSTACHE
 * 
 * % java HexDump 4 < abra.txt
 * 41 42 52 41
 * 43 41 44 41
 * 42 52 41 21
 * 96 bits
 */
public class HexDump {
	public static void main(String[] args) {
		int BYTES_PRE_LINE = 16;
		if(args.length == 1) {
			BYTES_PRE_LINE = Integer.parseInt(args[0]);
		}
		
		int i;
		for(i = 0; !BinaryStdIn.isEmpty(); i++) {
			if(BYTES_PRE_LINE == 0) {
				BinaryStdIn.readChar();
				continue;
			}
			if(i == 0) {
				StdOut.printf("");
			} else if(i % BYTES_PRE_LINE == 0) {
				StdOut.printf("\n", i);
			} else {
				StdOut.print(" ");
			}
			char c = BinaryStdIn.readChar();
			StdOut.printf("%02x", c & 0xff);
		}
		if(BYTES_PRE_LINE != 0) {
			StdOut.println();
		}
		StdOut.println((i*8) + " bits");
	}
}
