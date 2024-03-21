package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;




public class Balloon extends Thread implements DisplayObject{
	public int x, y , width ;
	private int diff;
	int random ;
	private Color color;
	public boolean detected = false ;
	ArrayList<DisplayObject> displayBuffer = null;
	public Balloon(int x, int y,ArrayList<DisplayObject> displayBuffer ) {
		this.x = x ;
		this.y = y ;
		this.width = 40 ; 
		this.displayBuffer = displayBuffer  ;
		diff = Helper.screenWidth - x ;
		random = new Random().nextInt(1,10) ;
		this.color = Color.red;
		start();
	}
	@Override
	public void run() {
		boolean ballonOutOfScreen = false;
		while( !Helper.gameOver && !ballonOutOfScreen) {
			if ( !Helper.gamePaused ) {
			x = Helper.screenWidth - diff;
			y++;
			if ( y >=  Helper.screenHeight ){
				ballonOutOfScreen = true ;
				this.displayBuffer.remove(this) ;
			}
			try {
				Thread.sleep(random);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else {
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void setColor(Color color) {
		this.color = color;
	}
	@Override
	public void draw(Graphics g) {
		
		g.drawLine(x, y+30, x+25, y);
		g.drawLine(x+40, y+30, x+15,y);
		g.drawLine(x+15, y, x+25, y);
		g.setColor(this.color);
		g.fillOval(x,y+10, width,width);
		g.setColor(Color.black);
		
	}

}
