package curve_drawing_technique;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;

/*
 * sujoy das
 * 
 * 
 * */



public class PaintFactory {
	
	public static  void draw(Graphics2D g, String type,String input1,String input2,String input3,String input4) {
		
		createCurve(type).draw(g,input1,input2,input3,input4) ; 
		
	}
	private static Curve createCurve( String type ) {
		if ( type.equals("parametric_line")) {
			return new Parametric_Line() ; 
		}else if ( type.equals("DDA_line")) {
			return new DDA_Line() ; 
		}else if ( type.equals("parametric_circle")) {
			return new Parametric_Circle() ; 
		}else if ( type.equals("parametric_arc")) {
			return new Parametric_Arc() ; 
		}else if ( type.equals("parametric_ellipse")) {
			return new Parametric_Ellipse() ; 
		}else if ( type.equals("parametric_spiral")) {
			return new Parametric_Spiral() ; 
		}; 
		return null ; 
	}

}
