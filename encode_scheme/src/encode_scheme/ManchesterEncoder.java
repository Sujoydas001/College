package encode_scheme;

import java.awt.Graphics2D;
import java.security.cert.PKIXRevocationChecker;

/**
 * 
 * @author Dr. Sunirmal Khatua
 *
 */
public class ManchesterEncoder extends Encoder{
	
	public ManchesterEncoder() {
		super("Manchester Encoding");
	}
	public ManchesterEncoder(int x ,int y ) {
		super("Manchester Encoding" , x , y );
	}

	public void encode(Graphics2D g, String data) {
		super.encode(g, data);
		int base = 0 ; 
		Character prev = null ; 
		for ( int i = 0 ; i < data.length() ; i++ ) {
			if (prev != null &&  prev == data.charAt(i)) {
				drawVerticalLine(g,base ); 
			}
			if ( data.charAt(i) == '1') {
				drawOne(g,base);
			}else if ( data.charAt(i) == '0') {
				drawZero(g,base);
			} 
			base += Helper.bitwidth  ; 
			prev = data.charAt(i);  
		}
	}
	
	
	private void drawVerticalLine(Graphics2D g, int base ) {
		g.drawLine(this.xbase+base ,this.ybase - Helper.bitheight/2, this.xbase+base, this.ybase + Helper.bitheight/2);
	}

	private void drawOne(Graphics2D g, int base ) {
		g.drawString("1", this.xbase+base+ Helper.bitwidth/2  ,this.ybase - Helper.bitheight );
		g.drawLine(this.xbase+base , this.ybase + Helper.bitheight/2, this.xbase+(base+ Helper.bitwidth/2 ),this.ybase + Helper.bitheight/2);
		g.drawLine(this.xbase+base+Helper.bitwidth/2 , this.ybase + Helper.bitheight/2, this.xbase+(base+ Helper.bitwidth/2), this.ybase - Helper.bitheight/2);
		g.drawLine(this.xbase+base+Helper.bitwidth/2   , this.ybase - Helper.bitheight/2, this.xbase+(base+Helper.bitwidth), this.ybase - Helper.bitheight/2);
		
	}
	private void drawZero(Graphics2D g, int base ) {
		g.drawString("0", this.xbase+base+ Helper.bitwidth/2  ,this.ybase - Helper.bitheight );
		g.drawLine(this.xbase+base ,this.ybase - Helper.bitheight/2, this.xbase+base+Helper.bitwidth/2, this.ybase - Helper.bitheight/2);
		g.drawLine(this.xbase+base+ Helper.bitwidth/2 , this.ybase + Helper.bitheight/2, this.xbase+base+ Helper.bitwidth/2, this.ybase - Helper.bitheight/2);
		g.drawLine(this.xbase+base+ Helper.bitwidth/2 ,  this.ybase + Helper.bitheight/2, this.xbase+base+ Helper.bitwidth,  this.ybase + Helper.bitheight/2);
	}

}
