package String.DataCompression;

import java.awt.Color;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author MUSTACHE
 * % java PictureDump 16 6 < abra.txt
 * a picture
 * 96 bits
 */
public class PictureDump {
	
	public static void main(String[] args) {
		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		Picture pic = new Picture(width, height);
		int count = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				pic.set(j, i, Color.BLACK);
				if(! BinaryStdIn.isEmpty()) {
					count++;
					boolean bit = BinaryStdIn.readBoolean();
					if(bit) {
						pic.set(j, i, Color.RED);
					} else {
						pic.set(j, i, Color.WHITE);
					}
				}
			}
		}
		pic.show();
		StdOut.println(count + " bits");
	}
}
