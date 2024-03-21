package curve_drawing_technique;

import java.awt.Graphics2D;

import javax.swing.JOptionPane;




/*
 * sujoy das
 * 
 * 
 * */



public class Parametric_Spiral extends Curve {

	@Override
	public void draw(Graphics2D g, String input1, String input2, String input3, String input4) {
		System.out.println("drawing parametric spiral") ; 
		double t = 0 ; 
		double interval = 0.001 ; 
		int xc, yc ; 
		double angle ; 
		try {
			xc = Integer.parseInt(input1);
			yc = Integer.parseInt(input2);
			angle = Double.valueOf(input3);
			
			while ( t <=(Math.PI * angle )/180 ) {
				double x = xc +  t * Math.cos(t);  
				double y = yc - t * Math.sin(t);
				g.drawOval((int)x,(int)y,1,1);
				t = t + interval ; 
			}
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "error format");
		}
		onComplete();

	}

}
