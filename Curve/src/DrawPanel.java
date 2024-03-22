

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	
	ArrayList<DisplayObject> displayBuffer = null;
	DisplayObject cObj = null; 
	public DrawPanel() {
		setBackground(Color.WHITE);
		setRunnableComponents();
		addMouseListener((MouseListener) this.cObj);
//		addMouseListener(this);
		
	}

	public void setRunnableComponents() {
		displayBuffer = new ArrayList<DisplayObject>();
		cObj= new BCurve(displayBuffer);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0 ; i< displayBuffer.size(); i++ ) {
			displayBuffer.get(i).draw(g);
		
		}
		
		
	}
	public void draw() {
		
		displayBuffer.add(cObj);
//		System.out.println("daraw3");
	}
	public void remove() {
//		displayBuffer.remove(cObj);
		((BCurve)cObj).removePoints() ; 

	}


	

	
	

}


