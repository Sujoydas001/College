
package encode_scheme;

import java.awt.Graphics2D;
import java.security.cert.PKIXRevocationChecker;

/**
 * 
 * @author Sujoy das
 *
 */
public class PseudometryEncoder extends Encoder{
	
	public PseudometryEncoder() {
		super("Pseudometry Encoding");
	}
	public PseudometryEncoder(int x , int y ) {
		super("Pseudometry Encoding",x, y );
	}
	public void encode(Graphics2D g, String data) {
		super.encode(g, data);
		int base = 0 ; 
		for ( int i = 0 ; i < data.length() ; i++ ) {
			
			if ( data.charAt(i) == '0') {
				draw(g,base) ; 
				g.drawString("0", this.xbase+base+Helper.bitwidth / 2, this.ybase - Helper.bitheight );
			}else if ( data.charAt(i) == '1') {
				g.drawString("1", this.xbase+base+Helper.bitwidth / 2, this.ybase - Helper.bitheight );
			}
			
			
			base += Helper.bitwidth ; 
			 
		}
	}
	
	private boolean prevPositive = true ; 
	private void draw(Graphics2D g,int base ) {
		if ( prevPositive ) {
			drawPositive(g,base);
			prevPositive = false ; 
		}else {
			drawNegative(g,base);
			prevPositive = true ; 
		}
	}


	private void drawPositive(Graphics2D g, int base ) {
		
		g.drawLine(this.xbase + base  , this.ybase, this.xbase + base, this.ybase- Helper.bitheight/2);
		g.drawLine(this.xbase + base  , this.ybase- Helper.bitheight/2, this.xbase + base+ Helper.bitwidth, this.ybase- Helper.bitheight/2);
		g.drawLine(this.xbase + base+ Helper.bitwidth  , this.ybase, this.xbase + base+ Helper.bitwidth, this.ybase- Helper.bitheight/2);
		
		
	}
	private void drawNegative(Graphics2D g, int base ) {
		g.drawLine(this.xbase + base  , this.ybase, this.xbase + base, this.ybase+ Helper.bitheight/2);
		g.drawLine(this.xbase + base  , this.ybase+ Helper.bitheight/2, this.xbase +base + Helper.bitwidth, this.ybase+ Helper.bitheight/2);
		g.drawLine(this.xbase + base+ Helper.bitwidth  , this.ybase, this.xbase + base+ Helper.bitwidth, this.ybase+ Helper.bitheight/2);
		
	}

}
