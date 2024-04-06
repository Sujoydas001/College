import java.awt.Graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;


public class BCurve implements DisplayObject ,MouseListener{
	
	ArrayList<DisplayObject> framingPoints ; 
	ArrayList<DisplayObject> points ; 
	ArrayList<DisplayObject> displayBuffer ; 
	String state ; 
	
//    public	boolean draw = false;
	public BCurve(ArrayList<DisplayObject> displayBuffer) {
		framingPoints = new ArrayList<DisplayObject>(); 
		points = new ArrayList<DisplayObject>(); 
		this.displayBuffer = displayBuffer ; 
		state = "null" ; 
	}


	public void addPoints(int x , int y ) {
		Coordinate point = new Coordinate(x, y, 10) ; 
		framingPoints.add( point) ;
	}
	@Override
	public void draw(Graphics g) {
		
		for (int i =  0 ; i< framingPoints.size() ;i++) {
			framingPoints.get(i).draw(g);
		};  
		for (int i =  0 ; i< framingPoints.size() -1  ;i++) {
			Coordinate c1 =  (Coordinate)(framingPoints.get(i)) ;
			Coordinate c2 =  (Coordinate)(framingPoints.get(i+1)) ;
			g.drawLine(c1.x, c1.y, c2.x, c2.y);
		};  
		
		
		for (int i =  0 ; i< points.size() ;i++) {
			points.get(i).draw(g);
		};  
		for (int i =  0 ; i< points.size() -1  ;i++) {
			Coordinate c1 =  (Coordinate)(points.get(i)) ;
			Coordinate c2 =  (Coordinate)(points.get(i+1)) ;
			g.drawLine(c1.x, c1.y, c2.x, c2.y);
		};  
		
		midB(framingPoints,1) ; 
	}
	private void midB(ArrayList<DisplayObject> initial,int count) {
		if ( count == 0 ) {
			return ;
		}
		for (int i =  0 ; i< initial.size() -1  ;i++) {
			Coordinate c1 =  (Coordinate)(initial.get(i)) ;
			Coordinate c2 =  (Coordinate)(initial.get(i+1)) ;
			DisplayObject midpoint  = new Coordinate((int)((c1.x+c2.x)/2),(int)((c1.y+c2.y)/2),5 );
			points.add( midpoint);
			
//			midB(points, count-1); 
		};  
		
		
	}
	
//	private double[][] b(double[][] position, float t) {
//		
//		if ( position.length == 1 ) {
//			return position;
//		}
//		float a = t  , b  = 1 - t ;
//		double[][] temp = new double[position.length-1][2] ; 
//		for ( int i = 0 ; i < temp.length ; i++ ) {
//			double[]coordinate1 = position[i]  ;
//			double[]coordinate2 = position[i+1]  ;
//			double x = (coordinate1[0] * b + coordinate2[0] * a) ;  
//			double y = (coordinate1[1] * b + coordinate2[1] * a) ;  
//			temp[i][0] = x ;
//			temp[i][1] = y ;
//		}
//		return b(temp,t);
//		
//	}
//	@Override
//	public void draw(Graphics g) {
//		for (int i =  0 ; i< framingPoints.size() ;i++) {
//			framingPoints.get(i).draw(g);
//		};  
//		for (int i =  0 ; i< points.size() ;i++) {
//			points.get(i).draw(g);
//		};  
//		if ( !state.equals("temp")) {
//			return ; 
//		}
//		double[][] temp = new double[framingPoints.size()][2] ; 
//		for (int i =  0 ; i< framingPoints.size() ;i++) {
//			Coordinate currentCoordinate = ( (Coordinate) framingPoints.get(i)) ;  
//			temp[i][0] = currentCoordinate.x;temp[i][1] = currentCoordinate.y; 
//		};  
//		
//		
//		float step = 0.0001f ; 
//		float t = 0 ; 
//		while( t <= 1 ) {
//			
//			double[][] coordinate =  b(temp, t);
//			points.add( new Coordinate((int)coordinate[0][0], (int)coordinate[0][1], 2) );
//			t+= step;
//		}
//		
//		
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		displayBuffer.add(this) ; 
		if (state.equals("null") || state.equals("temp")) {
			points.removeAll(points) ; 
			addPoints(e.getX(), e.getY()) ; 
			state = "temp"; 
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

	



}
