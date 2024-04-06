package gui;

import java.util.ArrayList;
import java.util.Random;

public class BalloonMaker extends Thread {
	
	ArrayList<DisplayObject> displayBuffer ; 
	Random random = new Random();
	public BalloonMaker(ArrayList<DisplayObject> displayBuffer) {
		this.displayBuffer = displayBuffer;
	}
	
	
	
	@Override
	public void run() {
		while(!Helper.gameOver ) {
			if ( !Helper.gamePaused ) {
			Balloon ballon = new Balloon(random.nextInt(Helper.screenWidth-300,Helper.screenWidth-100),0,this.displayBuffer);
			this.displayBuffer.add(ballon );
			try {
				Thread.sleep(random.nextInt(900,1200));
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
}
