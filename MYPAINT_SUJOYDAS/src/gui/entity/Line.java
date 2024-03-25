package gui.entity;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import gui.DisplayManager;


/*
 * @Author sujoy das
 */

public class Line implements DisplayObject , MouseListener,MouseMotionListener{
	public int x1,x2,y1,y2;
	DisplayManager displayManager = null;
	private List<DisplayObject> displayBuffer = null;
	public Line(int x1, int y1, DisplayManager displayManager) {
		super();
		this.x1 = x1;
		this.x2 = x1;
		this.y2 = y1;
		this.y1 = y1;
		this.displayManager = displayManager;
		this.displayBuffer = displayManager.getDisplayBuffer();
	}

	
	private void drawBresenham( Graphics g , int x1,int y1,int x2, int y2) {
		int x0 = x1,y0 = y1;
		g.drawOval(x0, y0, 1,1);
		int dx =Math.abs( x2-x0 );
		int dy =Math.abs( y2-y0 );
		
		if ( dy>dx) {
			if ( y2  < y1 ) {
				if ( x2 < x1 ) {
//					System.out.println(3);
					startDraw(g, dy, dx, x0, y0, 0,-1,-1,-1);
				}else {
//					System.out.println(2);
					startDraw(g, dy, dx, x0, y0, 0,-1,+1,-1);
				}
			}else {
				if ( x2 < x1 ) {
//					System.out.println(6);
					startDraw(g, dy, dx, x0, y0, 0,1,-1,1);
				}else {
//					System.out.println(7);
					startDraw(g, dy, dx, x0, y0, 0,1,1,1);
				}
			}
		}else {
			if ( y2  < y1 ) {
				if ( x2 < x1 ) {
//					System.out.println(4);
					startDraw(g, dx, dy, x0, y0, -1,0,-1,-1);
					
				}else {
//					System.out.println(1);
					startDraw(g, dx, dy, x0, y0, +1,0,+1,-1);	
				}
			}else {
				if ( x2 < x1 ) {
//					System.out.println(5);
					startDraw(g, dx, dy, x0, y0, -1,0,-1,1);
				}else {
//					System.out.println(8);
					startDraw(g, dx, dy, x0, y0, +1,0,+1,+1);
//					
				}
			}
		}
		
		
		
		
		
		
		
		return;
	}
	private void startDraw(Graphics g , int gDiff,int lDiff,int x0, int y0,int p,int q , int r , int s) {
		int pk = ( 2 * (lDiff) - (gDiff)) ;
		int k = 0 ;
		while(k < (gDiff) ) {
			if ( pk < 0  ) {
				g.drawOval(x0+p, y0+q, 1,1);
				x0 += p ; y0 += q ; 
				pk = pk + ( 2* lDiff);  
			}else {
				g.drawOval(x0+r, y0+s, 1,1);
				x0 += r ; y0 += s ; 
				pk = pk + ( 2* lDiff) - ( 2 * gDiff)  ;  
				
			}
			
			k++ ;
		}
	}
	
	
	@Override
	public void draw(Graphics g) {
		drawBresenham(g,x1,y1,x2,y2);
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		x2 = e.getX() ; 
		y2 = e.getY() ;
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
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

}
