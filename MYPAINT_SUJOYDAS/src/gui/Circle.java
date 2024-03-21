package gui;

import java.awt.Graphics;

public class Circle extends Thread implements DisplayObject{
	
	public int x,y,r;
	
	public Circle(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
//		start();
	}
	
	@Override
	public void run() {
		while(true) {
			x++;
			y++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawOval(x, y, r, r);
		
	}

}
