package encode_scheme;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * 
 * @author Dr. Sunirmal Khatua
 *
 */
public abstract class Encoder {
	
	String name;
	protected int xbase , ybase ; 
	
	public Encoder(String name) {
		this.name = name;
		this.xbase = Helper.xbase ;
		this.ybase = Helper.ybase ;
	}
	public Encoder(String name , int x , int y ) {
		this.name = name;
		this.xbase = x ; 
		this.ybase = y ; 
	}
	
	public void encode(Graphics2D g, String data) {
		g.setBackground(Color.WHITE);
//		g.clearRect(0, 0, 800, 800);
//		g.drawString(data, 120,90);
		int base = 0 ; 
		for ( int i = 0 ; i <= data.length() ; i++ ) {
			g.drawLine(this.xbase+ base , this.ybase-Helper.bitheight-10 , this.xbase+ base , this.ybase+Helper.bitheight+10 );
			base += Helper.bitwidth  ; 
		}
		
		Image i = new ImageIcon("computer.jpg").getImage();
		g.drawImage(i,this.xbase-100,this.ybase-50,100,100,null);
		g.drawImage(i,this.xbase+600,this.ybase-50,100,100,null);
		g.drawString(name,this.xbase + 700, this.ybase );
		g.drawLine(this.xbase, this.ybase, this.xbase + 600, this.ybase );
	}

}
