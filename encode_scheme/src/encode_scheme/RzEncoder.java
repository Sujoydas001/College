
package encode_scheme;

import java.awt.Graphics2D;

/**
 * 
 * @author Sujoy das
 *
 */
public class RzEncoder extends Encoder{
	
	public RzEncoder() {
		super("RZ Encoding");
	}

	public RzEncoder(int x , int y ) {
		super("RZ Encoding" ,x ,y);
	}
	public void encode(Graphics2D g, String data) {
		super.encode(g, data);
		
		int base = 0 ;  
		for ( int i = 0 ; i < data.length() ; i++ ) {
			
			if ( data.charAt(i) == '1') {
				drawOne(g,base) ;
			}else if ( data.charAt(i) == '0') {
				drawZero(g,base) ;
			}
			base += Helper.bitwidth ; 
			 
		}
	}
	
	
	private void drawZero(Graphics2D g, int base) {
		g.drawLine(this.xbase+base , this.ybase+Helper.bitheight/2, this.xbase+base+Helper.bitwidth/2, this.ybase+Helper.bitheight/2);
		g.drawLine(this.xbase+base , this.ybase, this.xbase+base, this.ybase+Helper.bitheight/2);
		g.drawLine(this.xbase+base , this.ybase, this.xbase+base+Helper.bitwidth/2 , this.ybase);
		g.drawLine(this.xbase+base+Helper.bitwidth/2 , this.ybase,this.xbase+base+Helper.bitwidth/2, this.ybase + Helper.bitheight/2);
		g.drawString("0",  this.xbase+base+Helper.bitwidth/2,this.ybase - Helper.bitheight );
		
	}

	private void drawOne(Graphics2D g, int base) {
		
		g.drawLine(this.xbase+base , this.ybase, this.xbase+base, this.ybase-Helper.bitheight/2);
		g.drawLine(this.xbase+base , this.ybase-Helper.bitheight/2, this.xbase+base+Helper.bitwidth/2, this.ybase-Helper.bitheight/2);
		g.drawLine(this.xbase+base+Helper.bitwidth/2 , this.ybase- Helper.bitheight/2, this.xbase+base+Helper.bitwidth/2 , this.ybase);
		g.drawLine(this.xbase+base+Helper.bitwidth/2 , this.ybase,this.xbase+base+Helper.bitwidth, this.ybase);
		g.drawString("1",  this.xbase+base+Helper.bitwidth/2,this.ybase - Helper.bitheight );
		
	}


}
