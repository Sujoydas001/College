package gui.entity;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackGround implements DisplayObject{
	BufferedImage image;
	boolean setBG=false;
	public BackGround() {
		
		
	}
	
	
	@Override
	public void draw(Graphics g) {
		if ( !setBG ) {
			return;
		}
		g.drawImage(image,0,0,image.getWidth(),image.getHeight(), null) ;
		
		
	}


	public void loadSource(String abspath) {
		try {
			image = ImageIO.read(new File(abspath)) ;
			setBG = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	public void removeSource() {
		setBG= false ; 	
	}
	
}