package gui.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Taskbar.State;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;


import gui.DrawPanel;
import gui.SelectedTool;

/*
 * 
 * @Author Sujoy Das
 * 
 * */
public class Circle implements DisplayObject , MouseListener , MouseMotionListener{
	
	public int x,y,r;
	int currx,curry ; 
	int prevx, prevy ;
	double radius = 0 ; 
	private List<DisplayObject> displayBuffer = null;
	private String state ; 
	DrawPanel parentPanel;
	
	
	
	public Circle(DrawPanel drawPanel) {
		this.parentPanel = drawPanel ;  
		this.r = 0 ;
		state = "draw" ; 
		
	}
	
	

	@Override
	public void draw(Graphics g) {
		g.drawOval(x-r, y-r, 2*r, 2*r);
		
//		midPointCircleDraw(g);
	}
	
//	private void midPointCircleDraw(Graphics g ) {
////		g.setColor(Color.blue);
////		g.drawOval(currx,curry, 2, 2);
//		int a = x+ r; 
//		int b = -( y + r  ) ; 
//		int pk = 1- r ; 
//		int xk =  a ; 
//		int yk =  -(b-r) ; 
//		System.out.println(xk +  " :init:  "  + yk );
//		g.setColor(Color.black);
//		while( xk  < yk    ) {
//			if ( pk < 0 ) {
//				System.out.println(1+xk + " : " + yk );
//				g.drawOval(++xk, yk, 10, 10);
//				pk = pk + (2 * xk ) + 1 - ( 2 * a ); 
//			}else {
//				System.out.println(1+xk + " : " +1+ yk );
//				g.drawOval(++xk, 1+yk, 10, 10);
//				pk = pk + (2 * xk ) + 1 - ( 2 * a)  + ( 2 + 2*( yk- b) );
//				yk++;
//			}
////			try {
////				Thread.sleep(1000);
////			} catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//			
//		}
//		
//		
//		
//		
//	}
//
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
//		if ( this.state =="done") {
//			return ; 
//		}
		prevx = e.getX() ; 
		prevy = e.getY(); 
//		displayBuffer.add(this);
	
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
//		this.state  = "done" ; 
		parentPanel.addNewObj(SelectedTool.Circle);
		
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
//		if ( this.state =="done") {
//			return ; 
//		}
		 int currx = e.getX() ; 
		 int  curry = e.getY();
		 this.x = currx ; 
		 this.y = curry ; 
		radius = Math.sqrt((currx-prevx)*(currx-prevx) + (curry-prevy)*(curry-prevy)) ;
		((Circle)this).r = (int)radius;
		
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
