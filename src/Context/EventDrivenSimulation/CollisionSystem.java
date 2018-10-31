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
	
	private MinPQ<Event> pq; // 优先队列
	private double t = 0.0; // 模拟时钟
	private Particle[] particles; // 粒子数组
	
	public CollisionSystem(Particle[] particles) {
		this.particles = particles;
	}
	private void predictCollisions(Particle a, double limit) {
		if(a == null) return ;
		for(int i = 0; i < particles.length; i++) {
			// 将与particles[i]发生的碰撞的事件插入pq中
			double dt = a.timeToHit(particles[i]); // 距离该粒子和粒子particles[i]碰撞所需的时间
			if(t + dt <= limit) {
				pq.insert(new Event(t + dt, a, particles[i]));
			}
		}
		double dtX = a.timeToHitVerticalWall(); // 距离该粒子和垂直的墙体碰撞所需的时间
		if(t + dtX <= limit) {
			pq.insert(new Event(t + dtX, a, null));
		}
		double dtY = a.timeToHitHorizontalWall(); // 距离该粒子和水平的墙体碰撞所需的时间
		if(t + dtY <= limit) {
			pq.insert(new Event(t + dtY, null, a));
		}
	}
	
	public void redraw(double limit, double Hz) {
		// 重绘事件：重新画出所有粒子
		StdDraw.clear();
		for(int i = 0; i < particles.length; i++) {
			particles[i].draw();
		}
		StdDraw.show(20); // 显示所有图像并暂停dt毫秒
		if(t < limit) {
			pq.insert(new Event(t + 1.0 / Hz, null, null));
		}
	}
	
	public void simulate(double limit, double Hz) {
		pq = new MinPQ<Event>();
		for(int i = 0; i < particles.length; i++) {
			predictCollisions(particles[i], limit); // 预测其他粒子的碰撞事件
		}
		pq.insert(new Event(0, null, null)); // 添加重绘事件
		while(! pq.isEmpty()) {
			// 处理一个事件
			Event event = pq.delMin(); // 取出即将发生的事件
			if(! event.isValid()) { // 如果事件无效，将它忽略
				continue;
			}
			for(int i = 0; i < particles.length; i++) {
				particles[i].move(event.time - t); // 更新粒子的位置
			}
			t = event.time; // 和更新时间
			Particle a = event.a, b = event.b;
            // 更新所有参与碰撞的粒子速度
			if     (a != null && b != null) a.bounceOff(b); // 粒子与粒子的碰撞
			else if(a != null && b == null) a.bounceOffVerticalWall(); // 粒子a和垂直墙体的碰撞
			else if(a == null && b != null) b.bounceOffHorizontalWall(); // 粒子b和水平墙体的碰撞
			else if(a == null && b == null) redraw(limit, Hz); // 重绘事件（画出所有粒子）
	        // 预测参与碰撞的粒子在未来可能发生的碰撞，并向优先队列中插入相应的事件
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
