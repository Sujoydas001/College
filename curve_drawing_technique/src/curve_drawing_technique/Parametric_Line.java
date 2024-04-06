package curve_drawing_technique;


import java.awt.Graphics2D;

import javax.swing.JOptionPane;




/*
 * sujoy das
 * 
 * 
 * */




public class Parametric_Line  extends Curve{

	@Override
	public void draw(Graphics2D g,String input1,String input2,String input3,String input4) {
		System.out.println("drawing parametric line") ; 
		double t = 0 ; 
		double interval = 0.001 ; 
		int x1, x2,y1 ,y2 ; 
		try {
			x1 = Integer.parseInt(input1);
			x2 = Integer.parseInt(input2);
			y1 = Integer.parseInt(input3);
			y2 = Integer.parseInt(input4);
			while ( t < 1 ) {
				double x = (1 - t )*x1 + t* x2  ;
				double y = (1 - t )*y1 + t* y2  ;
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

