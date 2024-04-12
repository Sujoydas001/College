package gui.entity;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Taskbar.State;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Box.Filler;
import javax.swing.ImageIcon;

import gui.DrawPanel;
import gui.MyGUI;

public class Painter implements DisplayObject,MouseListener,MouseMotionListener{
	private DrawPanel parentPanel ; 
	private Color fillColor ;//, oldColor ; 
	private int x , y ; 
	private String state ; 
	private ArrayList<DisplayObject> fill ; 
	private Robot robot = null  ; 
	private JPanel showPanel;
	private JLabel imgPanel;
	public Painter(DrawPanel drawPanel ) {
		
		this.parentPanel = drawPanel;
		state = "notdraw" ; 
		fill = new ArrayList<DisplayObject>() ; 
//		oldColor = getColorWithPixel(x, y);
//		chooseColor() ;
		JFrame frame = new JFrame(); 
		showPanel = new JPanel() ; imgPanel = new JLabel("LLL") ; 
//		showPanel.setBackground(Color.red);
		showPanel.add(imgPanel) ; 
		
		frame.add(showPanel) ; 
		frame.setSize(new Dimension(300,300));
		frame.setLocation(700,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize() ; 
		
		BufferedImage imgBufferedImage = new BufferedImage(MyGUI.WIDTH, MyGUI.HEIGHT, BufferedImage.TYPE_INT_RGB) ; 
		imgPanel.setIcon(new ImageIcon(imgBufferedImage));
		
		try {

//			robot = new Robot(); 
			robot = new Robot( GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
//			 robot.disableServiceMode();
		}catch (AWTException e) {
			e.printStackTrace();
		}
//		update(0,0) ;
		
	}
	
	
	private void update(int xk,  int yk ) {
		imgPanel.setIcon(new ImageIcon(capture(xk,yk)));
		
	}
	private BufferedImage capture(int xk,int yk) {
//		Rectangle rectangle = new Rectangle(xk,yk,800,800) ; 

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		return  robot.createScreenCapture(rectangle) ; 
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
//		x = e.getX() ; y = e.getY();
//		state = "draw" ; 
////		floodFillAlgo(parentPanel.getGraphics(), x, y, fillColor, getColorWithPixel(x, y));
//		getColorWithPixel(x, y);
//		update(e.getX(),e.getY()) ;

//		try {
//			Robot robot = new Robot();
//			Thread.sleep(10000);
//			Color color = robot.getPixelColor(e.getX(),e.getY()) ; 
//			System.out.println(color.getRGB());
////			System.out.println();
//		} catch ( InterruptedException | AWTException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
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
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
