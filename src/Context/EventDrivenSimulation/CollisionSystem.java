package Context.EventDrivenSimulation;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Particle;
import edu.princeton.cs.algs4.StdDraw;

public class CollisionSystem {
	private class Event implements Comparable<Event> {
		private final double time;
		private final Particle a, b;
		private final int countA, countB;
		
		public Event(double t, Particle a, Particle b) { 
			this.time = t;
			this.a    = a;
			this.b    = b;
			if(a != null) countA = a.count(); else countA = -1;
			if(b != null) countB = b.count(); else countB = -1;
		}
		
		public boolean isValid() {
			if(a != null && a.count() != countA) return false;
			if(b != null && b.count() != countB) return false;
			return true;
		}

		public int compareTo(Event that) {
			if     (this.time < that.time) return -1;
			else if(this.time > that.time) return +1;
			else return 0;
		}
	}
	
	private MinPQ<Event> pq; // ���ȶ���
	private double t = 0.0; // ģ��ʱ��
	private Particle[] particles; // ��������
	
	public CollisionSystem(Particle[] particles) {
		this.particles = particles;
	}
	private void predictCollisions(Particle a, double limit) {
		if(a == null) return ;
		for(int i = 0; i < particles.length; i++) {
			// ����particles[i]��������ײ���¼�����pq��
			double dt = a.timeToHit(particles[i]); // ��������Ӻ�����particles[i]��ײ�����ʱ��
			if(t + dt <= limit) {
				pq.insert(new Event(t + dt, a, particles[i]));
			}
		}
		double dtX = a.timeToHitVerticalWall(); // ��������Ӻʹ�ֱ��ǽ����ײ�����ʱ��
		if(t + dtX <= limit) {
			pq.insert(new Event(t + dtX, a, null));
		}
		double dtY = a.timeToHitHorizontalWall(); // ��������Ӻ�ˮƽ��ǽ����ײ�����ʱ��
		if(t + dtY <= limit) {
			pq.insert(new Event(t + dtY, null, a));
		}
	}
	
	public void redraw(double limit, double Hz) {
		// �ػ��¼������»�����������
		StdDraw.clear();
		for(int i = 0; i < particles.length; i++) {
			particles[i].draw();
		}
		StdDraw.show(20); // ��ʾ����ͼ����ͣdt����
		if(t < limit) {
			pq.insert(new Event(t + 1.0 / Hz, null, null));
		}
	}
	
	public void simulate(double limit, double Hz) {
		pq = new MinPQ<Event>();
		for(int i = 0; i < particles.length; i++) {
			predictCollisions(particles[i], limit); // Ԥ���������ӵ���ײ�¼�
		}
		pq.insert(new Event(0, null, null)); // ����ػ��¼�
		while(! pq.isEmpty()) {
			// ����һ���¼�
			Event event = pq.delMin(); // ȡ�������������¼�
			if(! event.isValid()) { // ����¼���Ч����������
				continue;
			}
			for(int i = 0; i < particles.length; i++) {
				particles[i].move(event.time - t); // �������ӵ�λ��
			}
			t = event.time; // �͸���ʱ��
			Particle a = event.a, b = event.b;
            // �������в�����ײ�������ٶ�
			if     (a != null && b != null) a.bounceOff(b); // ���������ӵ���ײ
			else if(a != null && b == null) a.bounceOffVerticalWall(); // ����a�ʹ�ֱǽ�����ײ
			else if(a == null && b != null) b.bounceOffHorizontalWall(); // ����b��ˮƽǽ�����ײ
			else if(a == null && b == null) redraw(limit, Hz); // �ػ��¼��������������ӣ�
	        // Ԥ�������ײ��������δ�����ܷ�������ײ���������ȶ����в�����Ӧ���¼�
			predictCollisions(a, limit);
			predictCollisions(b, limit);
		}
	}
	
	public static void main(String[] args) {
		StdDraw.show(0);
		int N = Integer.parseInt(args[0]);
		Particle[] particles = new Particle[N];
		
		for(int i = 0; i < N; i++) {
			particles[i] = new Particle();
		}
		CollisionSystem system = new CollisionSystem(particles);
		system.simulate(10000, 0.5);
	}
}
