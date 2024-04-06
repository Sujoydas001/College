package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	
	ArrayList<DisplayObject> displayBuffer = null;
	DisplayObject cObj= null; 
	public Gun gun = null; 
	
	public DrawPanel() {
		setBackground(Color.WHITE);
		setRunnableComponents();
		
	}

	public void setRunnableComponents() {
		displayBuffer = new ArrayList<DisplayObject>();
		gun = new Gun( 100 , Helper.screenHeight/2, displayBuffer);
		BalloonMaker ballonMakerThread  = new BalloonMaker(displayBuffer);
		ballonMakerThread.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0 ; i< displayBuffer.size(); i++ ) {
			displayBuffer.get(i).draw(g);
		
		}
		
		
	}
	

	
	

}


