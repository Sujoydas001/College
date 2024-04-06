package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import gui.entity.BCurve;
import gui.entity.Circle;
import gui.entity.DisplayObject;
import gui.entity.FreeHand;
import gui.entity.Line;
import gui.entity.Painter;
//import gui.mousehandler.MyMouseListener;

/*
 * @Author Sujoy das
 */

public class DrawPanel extends JPanel {
	
	
	private List<DisplayObject> displayBuffer = null;
	DisplayObject cObj=null;
	BufferedImage image ;

	public DrawPanel() {
		
		this.displayBuffer= new ArrayList<DisplayObject>(); 
		setBackground(Color.WHITE);

	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(DisplayObject ob : displayBuffer) {
			ob.draw(g);
		}
	}
	public void addNewObj(SelectedTool sTool) {

		removeMouseListener((MouseListener) cObj); 
		removeMouseMotionListener((MouseMotionListener) cObj);
		
		switch (sTool) {
		case  None : {
			return ;  
		}
		case  Circle : {
			cObj = new Circle(this) ;
			break;
			
		}
		case  Line : {
			cObj = new Line(this) ;
			break ; 
			
		}
		case  FreeHand : {
			cObj = new FreeHand(this) ;
			
			break ;
			
		}
		case  BeizerCurve : {
			cObj = new BCurve(this) ;
			
			break ;
			
		}
		case  ColorFill : {
			cObj = new Painter(this) ;
			
			break ;
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " );
		}
		addMouseListener((MouseListener) cObj);
		addMouseMotionListener((MouseMotionListener) cObj);
		
		displayBuffer.add( cObj) ; 
		
	}
	
	

}
