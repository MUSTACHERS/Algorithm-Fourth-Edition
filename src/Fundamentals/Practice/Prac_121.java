package Fundamentals.Practice;

import edu.princeton.cs.algs4.Point2D;

public class Prac_121 {
	public static void main(String[] args) {
		int T = Integer.parseInt(args[0]);
		Point2D[] p = new Point2D[T]; // ��Ҫ��Ҫ�õ����飬��Ȼ���ѷ���ÿ��Point2D�е�x��y
		for(int t = 0; t < T; t++) {
			double x = Math.random();
			double y = Math.random();
			p[t] = new Point2D(x,y);
			p[t].draw();
					
			}
			double minDistance = Math.abs(p[0].x() - p[T-1].x())*Math.abs(p[0].x() - p[T-1].x())
									+ Math.abs(p[0].y() - p[T-1].y())*Math.abs(p[0].y() - p[T-1].y());
			for(int i = 1; i < T - 1; i++) { // T - 1 ����Ϊ����minDistance���Ѿ�����p[0]��p[T-1]ȡ��������
				double distance = Math.abs(p[i].x() - p[i-1].x())*Math.abs(p[i].x() - p[i-1].x())
						+ Math.abs(p[i].y() - p[i-1].y())*Math.abs(p[i].y() - p[i-1].y());
				if(minDistance > distance) {
					minDistance = distance;
				}
			}
			System.out.println("Nearest distance��" + Math.sqrt(minDistance));
		}
}

