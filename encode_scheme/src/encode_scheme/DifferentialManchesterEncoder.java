package encode_scheme;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author Dr. Sunirmal Khatua
 *
 */
public class DifferentialManchesterEncoder extends Encoder{
	
	public DifferentialManchesterEncoder() {
		super("Differential Manchester Encoding");
	}
	public DifferentialManchesterEncoder(int x, int y ) {
		super("Differential Manchester Encoding" , x, y);
	}

	public void encode(Graphics2D g, String data) {
		super.encode(g, data); 
		int base = 0 ; 
		for ( int i = 0 ; i < data.length() ; i++ ) {
			if ( data.charAt(i) == '1') {
				g.drawString("1",this.xbase+ base + Helper.bitwidth/2 ,this.ybase - Helper.bitheight );
				keepSame(g,base); 
			}else if ( data.charAt(i) == '0') {
				g.drawString("0",this.xbase+ base + Helper.bitwidth/2 ,this.ybase - Helper.bitheight );
				invert(g,base); 
			}
			
			base += Helper.bitwidth ; 
		}
		
	}
	private static int prevVar = 0 ; 
	private void invert(Graphics2D g, int base ) {
		drawVertical(g, base);
		if ( prevVar == 1 ) {
			startAtDown(g, base);
			
		}else if ( prevVar == 0 ) {
			startAtUp(g, base );
			
		}
		
	}

	private void keepSame(Graphics2D g, int base ) {
		if ( prevVar == 1 ) {
			startAtUp(g, base );
			prevVar = 0 ; 
		}else if ( prevVar == 0 ) {
			startAtDown(g, base );
			prevVar = 1 ; 
			
		}
	}
	private void  drawVertical(Graphics2D g , int base   ) {
		g.setColor(Color.RED);
		g.drawLine(this.xbase+base ,this.ybase - Helper.bitheight/2, this.xbase+base, this.ybase + Helper.bitheight/2);
	}
	private void startAtDown(Graphics2D g, int base ) {
		g.setColor(Color.BLACK);
		g.drawLine(this.xbase+base , this.ybase + Helper.bitheight/2, this.xbase+(base+ Helper.bitwidth/2 ),this.ybase + Helper.bitheight/2);
		g.drawLine(this.xbase+base+Helper.bitwidth/2 , this.ybase + Helper.bitheight/2, this.xbase+(base+ Helper.bitwidth/2), this.ybase - Helper.bitheight/2);
		g.drawLine(this.xbase+base+Helper.bitwidth/2   , this.ybase - Helper.bitheight/2, this.xbase+(base+Helper.bitwidth), this.ybase - Helper.bitheight/2);
		
	}
	private void startAtUp(Graphics2D g, int base ) {
		g.setColor(Color.BLACK);
		g.drawLine(this.xbase+base ,this.ybase - Helper.bitheight/2, this.xbase+base+Helper.bitwidth/2, this.ybase - Helper.bitheight/2);
		g.drawLine(this.xbase+base+ Helper.bitwidth/2 , this.ybase + Helper.bitheight/2, this.xbase+base+ Helper.bitwidth/2, this.ybase - Helper.bitheight/2);
		g.drawLine(this.xbase+base+ Helper.bitwidth/2 ,  this.ybase + Helper.bitheight/2, this.xbase+base+ Helper.bitwidth,  this.ybase + Helper.bitheight/2);
	}

}
