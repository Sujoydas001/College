package gui.entity;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gui.DrawPanel;
import gui.SelectedTool;

public class MyRectangle implements DisplayObject , MouseListener,MouseMotionListener{
	int prevx,prevy,width,height;
	int [][] vertices = new int[2][2] ; 
	int previdx , curridx ; 
	private DrawPanel parentPanel;
	
	public MyRectangle(DrawPanel parentPanel) {
		this.parentPanel = parentPanel;
		previdx = 0 ; 
		curridx = 1 ; 
	}

	@Override
	public void draw(Graphics g) {
		int leftx = vertices[previdx][0] ; 
		int topy = vertices[previdx][1] ; 

		int rightx = vertices[curridx][0] ; 
		int bootomy = vertices[curridx][1] ; 
		int width = rightx - leftx ; 
		int height = bootomy - topy ; 
		g.drawString("("+leftx+","+topy+")", leftx, topy);
		g.drawString("("+(leftx+width)+","+topy+")", leftx+width, topy);
		g.drawString("("+leftx+","+(topy+height)+")", leftx, topy+height);
		g.drawString("("+(leftx+width)+","+(topy+height)+")", leftx+width, topy+height);
		
		g.drawRect(leftx, topy, width, height);
		

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
//		int currx= e.getX();
//		int curry= e.getY();
//		if (currx < prevx) {
//			
//			System.out.println("swap");
//		}else {
//			System.out.println("not swap");
//			width = currx - prevx ; height = curry - prevy ;
//			
//		}

		vertices[curridx][0] = e.getX();
		vertices[curridx][1] = e.getY();
		
		if ( vertices[curridx][0] < vertices[previdx][0] ) {
			curridx = ( curridx +1 )% 2 ; 
			previdx = ( previdx +1 )% 2 ; 
		}else {
			
		}
		 
		
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
//		prevx = e.getX() ; 
//		prevy = e.getY();
		vertices[previdx][0] = e.getX();
		vertices[previdx][1] = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
//		parentPanel.displayBuffer.remove(this) ; 
		parentPanel.addNewObj(SelectedTool.Rectangle);
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
