import java.awt.Dimension;
import java.awt.Toolkit;

class Constant {
	public static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	public static  Integer SCREEN_HEIGHT = dimension.height-300;
	public static  Integer SCREEN_WIDTH = dimension.width-600 ;
	public static final Integer TILESCOUNT = 25; 
	public static final Integer TILE_HEIGHT = SCREEN_HEIGHT/TILESCOUNT ; 
	public static final Integer TILE_WIDTH = SCREEN_WIDTH/TILESCOUNT ;
	

}
