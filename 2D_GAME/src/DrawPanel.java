

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

//import gui.mousehandler.MyMouseListener;

/*
 * @Author Sujoy das
 */

public class DrawPanel extends JPanel {
	
	
	private List<DisplayObject> displayBuffer = null;
	DisplayObject cObj=null;
	BufferedImage image ;
	BackGround backGround=null;
	Platform platform = null;

	public DrawPanel() {
		
		this.displayBuffer= new ArrayList<DisplayObject>(); 
		setBackground(Color.WHITE);
		backGround = new BackGround();
		displayBuffer.add(backGround) ; 
		platform = new Platform();
		displayBuffer.add(platform);

	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(DisplayObject ob : displayBuffer) {
			ob.draw(g);
		}
//		int currx = 0 ; 
//		g.setColor(Color.BLACK);
//		for (int i = 0 ; i <= Constant.TILESCOUNT;i++) {
//			
//			g.drawLine(currx, 0, currx, Constant.SCREEN_HEIGHT);currx += Constant.TILE_WIDTH;
//		}
//		int curry = 0 ; 
//		for (int i = 0 ; i <= Constant.TILESCOUNT;i++) {
//			
//			g.drawLine( 0, curry, Constant.SCREEN_WIDTH,curry);curry += Constant.TILE_HEIGHT;
//		}
		
		
		
	}
	
	
	public void updateBackGround() {
		backGround.updateBackGround();
	}
	
	
	

}
