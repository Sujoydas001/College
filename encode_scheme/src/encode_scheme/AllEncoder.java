package encode_scheme;

import java.awt.Graphics2D;

public class AllEncoder extends Encoder{
	public AllEncoder(String name) {
		super(name); 
	}
	public AllEncoder(String name, int x, int y) {
		super(name, x, y);
	}
	@Override
	public void encode(Graphics2D g, String data) {
		int i = 0 ; 
		new NrzLEncoder(this.xbase , this.ybase +90*i).encode(g, data);i++ ;
		new NrzIEncoder(this.xbase , this.ybase +90*i).encode(g, data);i++ ;
		new ManchesterEncoder(this.xbase , this.ybase +90*i).encode(g, data);i++ ;
		new DifferentialManchesterEncoder(this.xbase , this.ybase +90*i).encode(g, data);i++ ;
		new AmiEncoder(this.xbase , this.ybase +90*i).encode(g, data);i++ ;
		new PseudometryEncoder(this.xbase , this.ybase +90*i).encode(g, data);i++ ;
		new RzEncoder(this.xbase , this.ybase +90*i).encode(g, data);i++ ;
		
	}
	
	

}
