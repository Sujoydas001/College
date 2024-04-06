package gui.entity;
import java.awt.Graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import gui.DrawPanel;
import gui.SelectedTool;


public class BCurve implements DisplayObject ,MouseListener,MouseMotionListener{
	
	ArrayList<DisplayObject> framingPoints ; 
	ArrayList<DisplayObject> points ; 
//	ArrayList<DisplayObject> displayBuffer ; 
	private DrawPanel parentPanel;
	String state ; 
	public BCurve(ArrayList<DisplayObject> displayBuffer) {
		framingPoints = new ArrayList<DisplayObject>(); 
		points = new ArrayList<DisplayObject>(); 
//		this.displayBuffer = displayBuffer ; 
		state = "idle" ;  /*idle / draw / done */
	}
	public BCurve(DrawPanel drawPanel) {
		framingPoints = new ArrayList<DisplayObject>(); 
		points = new ArrayList<DisplayObject>(); 
//		this.displayBuffer = displayBuffer ; 
		state = "idle" ;  /*idle / draw / done */
		this.parentPanel = drawPanel ; 
	}


	private void addPoints(int x , int y ) {
		
		Coordinate point = new Coordinate(x, y,5) ; 
		framingPoints.add( point) ;
		state = "draw" ; 
	}
	private double[][] b(double[][] position, float t) {
		
		if ( position.length == 1 ) {
			return position;
		}
		float a = t  , b  = 1 - t ;
		double[][] temp = new double[position.length-1][2] ; 
		for ( int i = 0 ; i < temp.length ; i++ ) {
			double[]coordinate1 = position[i]  ;
			double[]coordinate2 = position[i+1]  ;
			double x = (coordinate1[0] * b + coordinate2[0] * a) ;  
			double y = (coordinate1[1] * b + coordinate2[1] * a) ;  
			temp[i][0] = x ;
			temp[i][1] = y ;
		}
		return b(temp,t);
		
	}
	@Override
	public void draw(Graphics g) {
		for (int i =  0 ; i< framingPoints.size() ;i++) {
			framingPoints.get(i).draw(g);
		};  
		for (int i =  0 ; i< points.size() ;i++) {
			points.get(i).draw(g);
		};  
		if ( !state.equals("draw")) {
			return ; 
		}
		double[][] temp = new double[framingPoints.size()][2] ; 
		for (int i =  0 ; i< framingPoints.size() ;i++) {
			Coordinate currentCoordinate = ( (Coordinate) framingPoints.get(i)) ;  
			temp[i][0] = currentCoordinate.x;temp[i][1] = currentCoordinate.y; 
		};  
		float step = 0.0001f ; 
		float t = 0 ; 
		while( t <= 1 ) {
			 
			double[][] coordinate =  b(temp, t);
			points.add( new Coordinate((int)coordinate[0][0], (int)coordinate[0][1], 1) );
			t+= step;
		}
		state = "idle" ;
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if ( e.getClickCount() == 2 ) {
			framingPoints.removeAll(framingPoints)  ;
			parentPanel.addNewObj(SelectedTool.BeizerCurve);
		}
		if ( e.getClickCount() == 1 ) {
			if (state.equals("idle") ) {
				points.removeAll(points) ; 
				addPoints(e.getX(), e.getY()) ; 
				 
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	



}
