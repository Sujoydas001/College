package gui;

import java.awt.Color;
import java.util.ArrayList;

public class BalloonDestructor  extends Thread{
	private Balloon balloon;
	private ArrayList<DisplayObject> displayBuffer;
	public BalloonDestructor(Balloon balloon, ArrayList<DisplayObject> displayBuffer) {
		super();
		this.balloon = balloon;
		this.displayBuffer = displayBuffer;
		
		
	}
	@Override
	public void run() {
		int r = 255;
		int g = 0 ;
		int b = 0 ; 
		while( g<=250 ) {
			g+=2;
			b+=2;
			this.balloon.setColor(new Color(r,g,b));
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		displayBuffer.remove(balloon);
	}
}
