
package encode_scheme;

import java.awt.Graphics2D;


/**
 * 
 * @author Sujoy Das
 *
 */
public class NrzLEncoder extends Encoder{
	
	public NrzLEncoder() {
		super("NrzL Encoding");
	}
	public NrzLEncoder(int x , int y ) {
		super("NrzL Encoding" , x, y);
	}

	public void encode(Graphics2D g, String data) {
		super.encode(g, data);
		
		int base = 0 ;
		Character prev = null ; 
		for( int i = 0 ; i < data.length() ; i++ ) {
			if (prev != null  &&  prev != data.charAt(i)) {
				g.drawLine(this.xbase+base , this.ybase - Helper.bitheight/2, this.xbase+(base), this.ybase +Helper.bitheight/2);
			}
			if ( data.charAt(i) == '1') {
				g.drawString("1",this.xbase + base + Helper.bitwidth / 2 , this.ybase - Helper.bitheight);
				g.drawLine(this.xbase+base , this.ybase - Helper.bitheight/2, this.xbase+(base+Helper.bitwidth), this.ybase - Helper.bitheight/2);
			}else if ( data.charAt(i) == '0') {
				g.drawString("0",this.xbase + base + Helper.bitwidth / 2 , this.ybase - Helper.bitheight);
				g.drawLine(this.xbase+base , this.ybase + Helper.bitheight/2, this.xbase+(base+Helper.bitwidth), this.ybase + Helper.bitheight/2);
			}else {
				
			}
			prev =Character.valueOf( data.charAt(i) ); 
			base = base + Helper.bitwidth  ; 
		}
		
	}

}
