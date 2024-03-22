import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.text.Position;

public class BCurve implements DisplayObject , MouseListener{
	
	ArrayList<DisplayObject> initialPoints ; 
	ArrayList<DisplayObject> pointstoRemove ; 
	ArrayList<DisplayObject> displayBuffer;
	
    public	boolean draw = false;
	public BCurve(ArrayList<DisplayObject> displayBuffer) {
		initialPoints = new ArrayList<DisplayObject>(); 
		pointstoRemove = new ArrayList<DisplayObject>(); 
		this.displayBuffer = displayBuffer;
	}


	public void addPoints(int x , int y ) {
		Coordinate point = new Coordinate(x, y, 10) ; 
		initialPoints.add( point) ;
		pointstoRemove.add( point) ;
		displayBuffer.add(point);
	}
	private double[][] b(double[][] position, Graphics g , float t) {
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
		return b(temp,g,t);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if ( this.draw ) {
			
			return ;
		}
		addPoints(e.getX(), e.getY());
		
		
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
	public void removePoints() {
		for (int i = 0 ; i < pointstoRemove.size() ; i++ ) {
			if (displayBuffer.contains(pointstoRemove.get(i)) ) {
				displayBuffer.remove(pointstoRemove.get(i)) ; 
			}
		}
		for (int i = 0 ; i < initialPoints.size() ; i++ ) {
			if (displayBuffer.contains(initialPoints.get(i)) ) {
				displayBuffer.remove(initialPoints.get(i)) ; 
			}
		}
	};
	@Override
	public void draw(Graphics g) {
		
		if ( !this.draw ) {
			System.out.println("not drawing");
			return ; 
		}
		System.out.println("drawing");
		double[][] temp = new double[initialPoints.size()][2] ; 
		for (int i =  0 ; i< initialPoints.size() ;i++) {
			Coordinate currentCoordinate = ( (Coordinate) initialPoints.get(i)) ;  
			temp[i][0] = currentCoordinate.x;temp[i][1] = currentCoordinate.y; 
		};  

		
		float step = 0.00001f ; 
		float t = 0 ; 
		while( t <= 1 ) {
			
			double[][] coordinate =  b(temp, g, t);
			Coordinate eachCoordinate = new Coordinate((int)coordinate[0][0], (int)coordinate[0][1], 2) ;
			displayBuffer.add(eachCoordinate) ; 
			pointstoRemove.add(eachCoordinate) ; 
			
			t+= step;
		}
		draw = false; 
		
		
	}

}
