import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackGround extends Thread implements DisplayObject  , KeyListener{
	private  Integer BG_HEIGHT =Constant.SCREEN_HEIGHT,BG_WIDTH = Constant.SCREEN_WIDTH ; 
	private boolean left,right;
	


	private int[] xposBg = new int[2] ;
	private int curridx = 1 , initidx = 0; 
	BufferedImage image;
	public BackGround() {
		try {
			image = ImageIO.read(new File("cloud1.jpg")) ;
			xposBg[0] = 0 ; xposBg[1] = BG_WIDTH ; 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		start();
	}
	
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	 @Override
	    public void run() {
	    	while( true ) {
	    		update();
	    		try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    }
	    private void update() {
	    	if ( isLeft() ) {
	    		goTo(1);
	    	}
	    	if ( isRight() ) {
	    		goTo(-1);
	    	}
	    }

	@Override
	public void draw(Graphics g) {
		for ( int i = 0 ; i < xposBg.length ; i++ ) {
			g.drawImage(image, xposBg[i], 0, BG_WIDTH,BG_HEIGHT, null) ;
		}
		
	}

	public void updateBackGround() {
//		System.out.println("x1(init) :"+xposBg[initidx]+" x2(curr) :"+xposBg[curridx]+" initidx "+initidx+" curridx "+curridx);
		if ( xposBg[curridx] < 0 ) {
			xposBg[initidx] = xposBg[curridx] + Constant.SCREEN_WIDTH ; 
			curridx= ( curridx +1 ) % xposBg.length;
			initidx= ( initidx +1 ) % xposBg.length;
		}
		if ( xposBg[initidx] > 0 ) {
			xposBg[curridx] = xposBg[initidx] - Constant.SCREEN_WIDTH ; 
			curridx= ( curridx +1 ) % xposBg.length;
			initidx= ( initidx +1 ) % xposBg.length;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
  @Override
    public void keyPressed(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_A){
        	gotoLeft();
           
        }
        if ( e.getKeyCode() == KeyEvent.VK_D){
        	gotoRight();
          
        }

    }

    private void gotoRight() {
	// TODO Auto-generated method stub
    	 setRight(true);
         setLeft(false);
	
}

	private void gotoLeft() {
	// TODO Auto-generated method stub
		 setLeft(true);
         setRight(false);
	
}

	@Override
    public void keyReleased(KeyEvent e) {
    	if ( e.getKeyCode() == KeyEvent.VK_A){
            setLeft(false);
            
        }
        if ( e.getKeyCode() == KeyEvent.VK_D){
           setRight(false);
           
        }
    }
	private void goTo(int offset) {
		xposBg[initidx] += offset ;
		xposBg[curridx] += offset;
		updateBackGround();
	}

}
