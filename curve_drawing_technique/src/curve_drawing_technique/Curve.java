package curve_drawing_technique;


import java.awt.Graphics2D;

import javax.swing.JOptionPane;


/*
 * sujoy das
 * 
 * 
 * */


public abstract class Curve {
	public abstract void draw(Graphics2D g,String input1,String input2,String input3,String input4); 
	public void onComplete() {
		JOptionPane.showMessageDialog(null,"completed drawing") ; 
	}
}

