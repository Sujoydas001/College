package curve_drawing_technique;

import java.awt.Graphics2D;

import javax.swing.JOptionPane;



/*
 * sujoy das
 * 
 * 
 * */




public class Parametric_Circle extends Curve{

	@Override
	public void draw(Graphics2D g, String input1, String input2, String input3, String input4) {
		System.out.println("drawing parametric circle") ; 
		double t = 0 ; 
		double interval = 0.001 ; 
		int xc, yc, r ; 
		try {
			xc = Integer.parseInt(input1);
			yc = Integer.parseInt(input2);
			r = Integer.parseInt(input3);
			
			while ( t <= 2 * Math.PI  ) {
				double x = xc +  r * Math.cos(t);  
				double y = yc -  r * Math.sin(t);
				Thread.sleep(1);
				g.drawOval((int)x,(int)y,1,1);
				t = t + interval ; 
			}
		}catch (NumberFormatException | InterruptedException e) {
			JOptionPane.showMessageDialog(null, "error format");
		}
		onComplete();
		
	}

}
