package Fundamentals.Practice;

import edu.princeton.cs.algs4.Point2D;

public class Prac_121 {
	public static void main(String[] args) {
		int T = Integer.parseInt(args[0]);
		Point2D[] p = new Point2D[T]; // 主要是要用到数组，不然很难访问每个Point2D中的x，y
		for(int t = 0; t < T; t++) {
			double x = Math.random();
			double y = Math.random();
			p[t] = new Point2D(x,y);
			p[t].draw();
					
			}
			double minDistance = Math.abs(p[0].x() - p[T-1].x())*Math.abs(p[0].x() - p[T-1].x())
									+ Math.abs(p[0].y() - p[T-1].y())*Math.abs(p[0].y() - p[T-1].y());
			for(int i = 1; i < T - 1; i++) { // T - 1 是因为上面minDistance中已经算了p[0]和p[T-1]取到距离了
				double distance = Math.abs(p[i].x() - p[i-1].x())*Math.abs(p[i].x() - p[i-1].x())
						+ Math.abs(p[i].y() - p[i-1].y())*Math.abs(p[i].y() - p[i-1].y());
				if(minDistance > distance) {
					minDistance = distance;
				}
			}
			System.out.println("Nearest distance：" + Math.sqrt(minDistance));
		}
}

