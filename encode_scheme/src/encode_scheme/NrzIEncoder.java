package encode_scheme;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Base64;

/**
 * 
 * @author Sujoy Das
 *
 */
public class NrzIEncoder extends Encoder{
	
	public NrzIEncoder() {
		super("NrzI Encoding");
	}
	public NrzIEncoder(int x , int y ) {
		super("NrzI Encoding" , x, y );
	}

	public void encode(Graphics2D g, String data) {
		super.encode(g, data);
		
		int base = 0 ; 
		for ( int i = 0 ; i < data.length() ; i++ ) {

			choose(g, base, (data.charAt(i)) );

			base += Helper.bitwidth ; 
		}
		
		
	}
	private void choose(Graphics2D g, int base, char i ) {
		if( i == '0'){
			invert(g,base ) ; 
			g.drawString("0", this.xbase+base+Helper.bitwidth /2 , this.ybase - Helper.bitheight );
		}else if ( i == '1' ) {
			g.drawString("1", this.xbase+base+Helper.bitwidth /2 , this.ybase - Helper.bitheight );
			same(g,base) ;
		}else {
		}
		
	}
	private void same(Graphics2D g, int base  ) {
		if ( prevVar == 1 ) {
			
			drawHorizontalPositive(g, base );

		}else if ( prevVar == 0 ) {
			drawHorizontalNegative(g, base);

		}
	}
	private static int prevVar = 0 ; 
	private void invert(Graphics2D g, int base) {
		drawVertical(g, base);
		if ( prevVar == 1 ) {
			drawHorizontalNegative(g, base );
			prevVar = 0 ; 
		}else if ( prevVar == 0 ) {
			drawHorizontalPositive(g, base );
			prevVar = 1 ; 
		}
		
	}
	private void  drawVertical(Graphics2D g , int base ) {
		g.setColor(Color.RED);
		g.drawLine(this.xbase+base , this.ybase - Helper.bitheight/2, this.xbase + base ,this.ybase + Helper.bitheight/2);
	}
	private void  drawHorizontalPositive(Graphics2D g , int base ) {
		g.setColor(Color.BLACK);
		g.drawLine(this.xbase+base , this.ybase - Helper.bitheight/2, this.xbase + base + Helper.bitwidth, this.ybase - Helper.bitheight/2);
	}
	private void  drawHorizontalNegative(Graphics2D g , int base ) {
		g.setColor(Color.BLACK);
		g.drawLine(this.xbase+base , this.ybase + Helper.bitheight/2, this.xbase + base + Helper.bitwidth, this.ybase + Helper.bitheight/2);
	}
	

}
