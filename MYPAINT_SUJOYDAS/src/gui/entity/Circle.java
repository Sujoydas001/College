package gui.entity;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import gui.DisplayManager;

/*
 * 
 * @Author Sujoy Das
 * 
 * */
public class Circle implements DisplayObject , MouseListener , MouseMotionListener{
	
	public int x,y,r;
	int prevx, prevy ;
	double radius = 0 ; 
	private DisplayManager displayManager = null;
	private List<DisplayObject> displayBuffer = null;

	
	
	public Circle(int x, int y, int r , DisplayManager displayManager) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.displayManager = displayManager ; 
		this.displayBuffer = this.displayManager.getDisplayBuffer();
	}
	
	

	@Override
	public void draw(Graphics g) {
		g.drawOval(x-r, y-r, 2*r, 2*r);
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		prevx = e.getX() ; 
		prevy = e.getY(); 
		displayBuffer.add(this);
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		int currx = e.getX() ; 
		int curry = e.getY();
		radius = Math.sqrt((currx-prevx)*(currx-prevx) + (curry-prevy)*(curry-prevy)) ;
		((Circle)this).r = (int)radius;
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
