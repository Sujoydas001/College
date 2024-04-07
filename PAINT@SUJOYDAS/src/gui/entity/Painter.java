package gui.entity;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Taskbar.State;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.Box.Filler;

import gui.DrawPanel;

public class Painter implements DisplayObject,MouseListener,MouseMotionListener{
	private DrawPanel parentPanel ; 
	private Color fillColor ;//, oldColor ; 
	private int x , y ; 
	private String state ; 
	private ArrayList<DisplayObject> fill ; 
	public Painter(DrawPanel drawPanel ) {
		this.parentPanel = drawPanel;
		state = "notdraw" ; 
		fill = new ArrayList<DisplayObject>() ; 
//		oldColor = getColorWithPixel(x, y);
		chooseColor() ;
	}

	private void chooseColor() {
		this.fillColor =  JColorChooser.showDialog(parentPanel, "fill",Color.BLACK) ; 
		
	}
	
	@Override
	public void draw(Graphics g) {
		
	}
	private void floodFillAlgo(Graphics g, int x, int y, Color fillColor, Color oldColor) {
		/*boundary with diff color ok */
		Color currColor = getColorWithPixel(x,y);
		System.out.println(x + "  current : "+currColor);
		System.out.println("fill : "+fillColor);
		System.out.println("old : "+oldColor);
		if ( !currColor.equals(oldColor)) {
			return ; 
		}
		if ( currColor.equals(fillColor)) {
			return ; 
		}
		fill.add(new Coordinate(x, y) );

		floodFillAlgo(g,x+1,y,fillColor,oldColor) ;
		floodFillAlgo(g,x,y+1,fillColor,oldColor) ;
		floodFillAlgo(g,x-1,y,fillColor,oldColor) ;
		floodFillAlgo(g,x,y-1,fillColor,oldColor) ;
		
		
		
	}

	private Color getColorWithPixel(int x, int y) {
       
//       BufferedImage img = new BufferedImage(parentPanel.getWidth(),parentPanel.getHeight(), BufferedImage.TYPE_INT_RGB) ; 
//       int color =  img.getRGB(x, y) ; 
//       System.out.println(color);
//		try {
//			Robot robot = new Robot();
//			Color color = robot.getPixelColor(x, y) ; 
//			System.out.println(color);
//			return color ; 
//		} catch (AWTException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		Robot robot;
		try {
			robot = new Robot();
			BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(x, y, 1, 1)); // Capture a single pixel
	        Graphics g=  screenCapture.getGraphics(); 
	        ((Graphics2D)g).setStroke(new BasicStroke(20));
	        g.drawLine(x, y, x+100, y+100);
	        System.out.println(g);
	        try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        // Now you can use BufferedImage to get the pixel color
//	        int pixelColor = screenCapture.getRGB(0, 0); // Get the color of the captured pixel
//	        System.out.println("Pixel Color at (x, y): " + pixelColor);
//	        // If you need a Color object:
//	        Color color = new Color(pixelColor);
//	        System.out.println("Pixel Color: " + color);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
       return null;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX() ; y = e.getY();
		state = "draw" ; 
//		floodFillAlgo(parentPanel.getGraphics(), x, y, fillColor, getColorWithPixel(x, y));
		getColorWithPixel(x, y);
		
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
