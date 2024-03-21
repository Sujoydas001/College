package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import gui.entity.DisplayObject;
import gui.mousehandler.MyMouseListener;

/*
 * @Author Sujoy das
 */

public class DrawPanel extends JPanel {
	
	DisplayManager displayManager = null;
	private List<DisplayObject> displayBuffer = null;
//	DisplayObject circleObj= null; 
//	DisplayObject lineObj= null; 
//	DisplayObject freehandObj= null;
	DisplayObject currObject=null;
	

	public DrawPanel(DisplayManager displayManager) {
		this.displayManager = displayManager ; 
		this.displayBuffer= displayManager.getDisplayBuffer();
		setBackground(Color.WHITE);
//		MyMouseListener mouseListener = new MyMouseListener(circleObj, lineObj, lineObj, this.displayManager);
		MyMouseListener mouseListener = new MyMouseListener( this.displayManager);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(DisplayObject ob : displayBuffer) {
			ob.draw(g);
		}
	}
	
	

}
