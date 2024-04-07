package gui.entity;

import java.awt.Graphics;


/*
 * 
 * @Author Sujoy Das
 * 
 * */
public class Coordinate implements DisplayObject {
	public int x,y,size;
	public Coordinate(int x,int y) {
		this.x = x; this.y = y; size = 1 ; 
	}
	public Coordinate(int x,int y,int size) {
		this.x = x; this.y = y; this.size = size  ; 
	}
	@Override
	public void draw(Graphics g) {
		g.drawOval(x, y, size,size);
		
	}
}
