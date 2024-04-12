package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Test{
	public static void main(String[] s) {
//		System.out.println("test");
		
		
		
		JFrame frame = new JFrame() ; 
		frame.setSize(new Dimension(600,600));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(); 
		JLabel imgLabel1 = new JLabel() ; 
		
		panel.add(imgLabel1) ; 
		 
		frame.add(panel) ; 
		frame.setVisible(true);
		
		
		try {
			BufferedImage img = ImageIO.read(new File("pic3.jpg")) ;
		
			imgLabel1.setIcon(new ImageIcon(img));
			
			int height =  img.getHeight() ;
			int width = img.getWidth() ;
			BufferedImage img2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB) ;
			for ( int i = 0 ; i < width ; i++ ) {
				for ( int j = 0 ; j < height ; j++ ) {
//					System.out.print(img.getRGB(i, j) + " : ") ; 
					if (Integer.valueOf( img.getRGB(i, j) ).equals(Integer.valueOf(Color.BLACK.getRGB()))){
						img2.setRGB(i, j,   img.getRGB(i, j) );
					}else {
						img2.setRGB(i, j, Color.white.getRGB());
					}
				}
			}
			
			JFrame frame2 = new JFrame() ; 
			frame2.setSize(new Dimension(600,600));
			frame2.setLocationRelativeTo(null);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel panel2 = new JPanel(); 
			 
			JLabel imgLabel2 = new JLabel() ; 
			 
			panel2.add(imgLabel2) ; 
			frame2.add(panel2) ; 
			frame2.setVisible(true);
			imgLabel2.setIcon(new ImageIcon(img2));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	
	
	
}
//
//
// import java.awt.AWTException;
//import java.awt.BasicStroke;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Rectangle;
// import java.awt.Robot;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.image.BufferedImage;
//
//import javax.swing.*;
//
//
// public class Test extends JFrame implements MouseListener{
//     // Method to get color at specified pixel
//	 public Test() {
//		 setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setSize(600,600);
//		setBackground(Color.RED);
//		setTitle("My GUI");
//		addMouseListener(this);
////		JLabel background = new JLabel(new ImageIcon("pic.jpg"));
////        // Set layout to null to position components freely
////        background.setLayout(null);
//		JPanel p1 = new JPanel(); 
//		p1.setLayout(new BorderLayout());
//		p1.add( new JLabel("LABEL !"));
//		p1.setBackground(Color.red);
//		JPanel p2 = new JPanel(); 
//		p2.setLayout(new BorderLayout());
//		p2.setBackground(Color.GREEN);
//
//        add(p1, BorderLayout.NORTH) ;
//        add(p2)  ; 
//			
//	}
//     private Color getColorWithPixel(int x, int y) {
////         try {
////             // Create a Robot instance
////             Robot robot = new Robot();
////
////             // Capture the screen at the specified pixel
////             BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, 1, 1));
////
////             // Get the color of the pixel
////             return new Color(image.getRGB(0, 0));
////         } catch (AWTException e) {
////             e.printStackTrace();
////             return null;
////         }
////    	BufferedImage image=new BufferedImage(600,600,BufferedImage.TYPE_INT_RGB) ; 
////    	for (int i = 0 ; i< 600 ; i++ ) {
////    		for (int j = 0 ; j< 600 ; j++ ) {
////    			System.out.println("c :"+image.getRGB(i,j));
////        	}
////    	}
////    	
//    	 try {
//             // Create a Robot object to capture screen contents
//             Robot robot = new Robot(); 
//              
//             BufferedImage singlePixelImage =robot.createScreenCapture(new Rectangle(x,y,1,1) ) ;
//             System.out.println( singlePixelImage.getRGB(0, 0) );
////             System.out.println("Captured pixel color: " + pixelColor);
//         } catch (AWTException e) {
//             e.printStackTrace();
//         }
////    	System.out.println(image.getGraphics().getColor());
// 		return null;
//     }
//
//     public static void main(String[] args) {
//         Test colorGetter = new Test();
//         colorGetter.setVisible(true);
//         // Example: Get color at position (100, 100)
//         
//         
//     }
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mousePressed(MouseEvent e) {
//		int x = e.getX(),y = e.getY();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
//		Color color = getColorWithPixel(x,y);
//
//        if (color != null) {
//            // Print the RGB values
//            System.out.println("Color at position (" + x + ", " + y + "):");
//            System.out.println("Red: " + color.getRed());
//            System.out.println("Green: " + color.getGreen());
//            System.out.println("Blue: " + color.getBlue());
//        } else {
//            System.out.println("Failed to get color at position (" + x + ", " + y + ")");
//        }
//		
//	}
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
// }
