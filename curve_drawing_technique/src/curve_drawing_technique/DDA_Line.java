package curve_drawing_technique;

import java.awt.Graphics2D;

import javax.swing.JOptionPane;

/*
 * sujoy das
 * 
 * 
 * */




public class DDA_Line extends Curve {

	@Override
	public void draw(Graphics2D g, String input1, String input2, String input3, String input4) {
		double dx , dy , steps ;
		try {
			int x1, x2,y1 ,y2 ; 
			x1 = Integer.parseInt(input1);
			x2 = Integer.parseInt(input2);
			y1 = Integer.parseInt(input3);
			y2 = Integer.parseInt(input4);
			double xinc , yinc , x, y ; 
			dx = x2 - x1 ; 
			dy = y2 - y1 ; 
			if ( Math.abs(dx) > Math.abs(dy) ) {
				steps =Math.abs(dx);  
				
			}else {
				steps = Math.abs(dy);  
			}
			xinc = dx / steps ; 
			yinc = dy / steps ; 
			x = x1 ; 
			y = y1 ; 
			g.drawOval((int)x, (int)y, 1, 1);
			for ( int i = 1 ; i < steps ; i++ ) {
				Thread.sleep(1);
				x = x + xinc ; 
				y = y + yinc ; 
				g.drawOval((int)x, (int)y, 1, 1);
			}
			
		}catch (NumberFormatException | InterruptedException e) {
			JOptionPane.showMessageDialog(null, "error format");
		}
		onComplete();
		

	}

}
