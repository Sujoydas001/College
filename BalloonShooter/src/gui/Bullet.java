package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Bullet extends Thread implements DisplayObject {
	int x ,  y,width ;
	int hitPointX = 0 , hitPointY = 0 , startX=0,startY=0 ;
	double angle = 0 ;
	ArrayList<DisplayObject> displayBuffer;
//	ArrayList<DisplayObject> temp;
	public Bullet(int x, int y,double angle, int size ,ArrayList<DisplayObject> displayBuffer) {
		
		super();
		Helper.bulletCount--;
		this.x = x;
		this.y = y;
		this.startX = x;
		this.startY = y;
		this.width  = size ;
		this.hitPointX = this.x + ( width / 2 ) ;
		this.hitPointY = this.y + ( width / 2 ) ;
		this.angle  = angle ;
		this.displayBuffer = displayBuffer;
//		temp = new ArrayList<>() ; 
		start();
	}
	@Override
	public void run() {
		boolean bulletOutOfScreen = false;
			while(!Helper.gameOver && !bulletOutOfScreen) {
				if ( !Helper.gamePaused ) {
					this.hitPointX = this.x + ( width / 2 ) ;
					this.hitPointY = this.y + ( width / 2 ) ;
					
					if ( Helper.bulletCount <= 0 ) {
						Helper.gameOver = true;
					}
				
					
					
					if ( x > Helper.screenWidth-250 ) {
						checkforHit();
					}
					
					this.x++;
					if ( x >=Helper.screenWidth ) {
						displayBuffer.remove(this);
						bulletOutOfScreen = true;
						
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
			
	}
	private  void checkforHit() {
	      for ( int i = 0 ; i <  this.displayBuffer.size();i++ ) {
	    	  if ( displayBuffer.get(i) instanceof Balloon) {
	    		  	Bullet bullet = this;
					Balloon balloon = (Balloon)(displayBuffer.get(i));
					if ( !balloon.detected ) {

//						if ( !temp.contains(balloon)) {
//							temp.add(balloon) ; 
//						}
						
						int balloonX = balloon.x+(balloon.width/2) , balloonY = balloon.y+10+(balloon.width/2); 
						double distance = Math.sqrt((hitPointX-balloonX)*(hitPointX-balloonX)+(hitPointY-balloonY)*(hitPointY-balloonY)) ;
						int sumofRadius = (balloon.width/2)+(bullet.width/2); 
//						System.out.println(distance + " :  " + sumofRadius) ; 
						if (
								(int)distance < sumofRadius
						) {
							balloon.detected = true;
							++Helper.score ;
//							Helper.gamePaused  = true ;
							BalloonDestructor balloonDestructorThread = new BalloonDestructor(balloon, displayBuffer);
							balloonDestructorThread.start();
							
							
						}
					}
					
	    	  }
				
				
			
		}
	}
	@Override
	public void draw(Graphics g) {
		this.y = (int) (this.startY + (this.x - this.startX ) * Math.tan(angle*(Math.PI/180))); 
		g.setColor(Color.BLACK);
		g.fillOval(this.x, this.y, width,width);
//		g.setColor(Color.GREEN);
//		for ( int i = 0 ; i < temp.size(); i++ ) {
//			Balloon balloon =(Balloon)( temp.get(i) ) ;
//			if ( balloon.y > Helper.screenHeight ) { temp.remove(balloon) ; }
//			int balloonX = balloon.x+(balloon.width/2) , balloonY = balloon.y+10+(balloon.width/2);
//			g.drawLine(this.hitPointX,this.hitPointY,balloonX,balloonY) ;
//			
//		}
		
	}

}
