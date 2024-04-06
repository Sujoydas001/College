package gui.entity;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import gui.DrawPanel;
import gui.SelectedTool;



/*
 * 
 * @Author Sujoy Das
 * 
 * */
public class FreeHand implements DisplayObject , MouseListener , MouseMotionListener{
	

	private ArrayList<Coordinate> coordinates = null;
	private DrawPanel parentPanel ; 
	public FreeHand(DrawPanel drawPanel) {
		this.parentPanel = drawPanel ; 
		coordinates = new ArrayList<>();
		
	}
	
	

	@Override
	public void draw(Graphics g) {
		
		for (Coordinate each: coordinates ) {
//			g.drawOval(each.x,each.y,1,1);
			each.draw(g);
		}
		for ( int i = 0 ; i < coordinates.size()- 1; i++ ) {
			drawBresenham(g, coordinates.get(i).x, coordinates.get(i).y, coordinates.get(i+1).x,coordinates.get(i+1).y);
		}
		
		
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		coordinates.add(new Coordinate(e.getX(),e.getY()));
//		displayManager.getDisplayBuffer().add(this);
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		parentPanel.addNewObj(SelectedTool.FreeHand);
		
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
		coordinates.add(new Coordinate(e.getX(),e.getY()));
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
