import java.awt.Color;
import java.awt.Graphics;

public class Coordinate implements DisplayObject{
	public int x ,y , size  ; 
	
	public Coordinate(int x, int y , int size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size ; 
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(x, y, size,size);
	}

}
