

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	
	ArrayList<DisplayObject> displayBuffer = null;
	DisplayObject cObj = null; 
	public DrawPanel() {
		setBackground(Color.WHITE);
		setRunnableComponents();
		
		
//		addMouseListener(this);
		
	}

	public void setRunnableComponents() {
		displayBuffer = new ArrayList<DisplayObject>();
		addObj();
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0 ; i< displayBuffer.size(); i++ ) {
			displayBuffer.get(i).draw(g);
		
		}
		
		
	}
	public void addObj() {
		removeMouseListener((MouseListener) this.cObj);
		cObj = new BCurve(displayBuffer); 
		addMouseListener((MouseListener) this.cObj);
		displayBuffer.add(cObj);
		
	}
	public void draw() {
		
		((BCurve)cObj).state = "done" ; 
		addObj();
	}
	public void remove() {
		displayBuffer.removeAll(displayBuffer);
		addObj() ;
	}


	

	
	

}


