package gui.entity;

import java.awt.Graphics;


/*
 * 
 * @Author Sujoy Das
 * 
 * */
public class Coordinate implements DisplayObject {
	public int x,y;
	public Coordinate(int x,int y) {
		this.x = x; this.y = y; 
	}
	@Override
	public void draw(Graphics g) {
		g.drawOval(x, y, 1, 1);
		
	}
}
