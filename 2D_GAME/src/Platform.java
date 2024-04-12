import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Platform extends Thread implements DisplayObject , KeyListener {
    private int [][] tiles = null;
    private String [][] tile_defination ;
    private boolean left=false,right=false;
    private int minxidx,maxxidx;
    
    public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	Platform(){
        
        BufferedReader bReader = null; 
        try  {
            bReader= new BufferedReader(new FileReader("tile_defination.txt")) ; 
            int count = Integer.parseInt( bReader.readLine() ) ;
            if ( count != 0 ){
                tile_defination = new String[count][2];
                bReader.readLine();
                int i=0;
                while( bReader.ready()){
                 String [] type = bReader.readLine().split(",") ;
                 tile_defination[i][0] = type[1]  ; /*color */
                 tile_defination[i][1] = type[2] ; /*border color */
                 i++ ;
                }
            }
           
           bReader.close();
       } catch (IOException e) {
           e.printStackTrace();
       } 
        try  {
             bReader= new BufferedReader(new FileReader("level1.txt")) ; 
             int count = Integer.parseInt( bReader.readLine() ) ;
            if ( count != 0 ){
                tiles = new int[count][3];
                bReader.readLine();
                minxidx = Integer.parseInt(bReader.readLine());
                bReader.readLine();
                maxxidx = Integer.parseInt(bReader.readLine());
                bReader.readLine();
                int i=0;
                while( bReader.ready()){
                 String [] info = bReader.readLine().split(",") ; 
                 tiles[i][0] =Integer.parseInt( info[0] ) ; /* id */
                //  System.out.println(Integer.parseInt( info[1] ) );
                 tiles[i][1] = Integer.parseInt( info[1] ) * Constant.TILE_WIDTH  ; /* xindex */
                 tiles[i][2] =Integer.parseInt( info[2] ) * Constant.TILE_HEIGHT  ; /* yindex */
                 
//                 System.out.println(info[1] + ":" + tiles[i][1]  + ":"+info[2] + ":" + tiles[i][2]  );
                i++;
                }
            }
           
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
        
    }
    @Override
    public void run() {
    	while( true ) {
    		update();
    		try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    private void update() {
    	if ( tiles[0][1] >  minxidx* Constant.TILE_WIDTH ) {
    		
    		goTo(-2);
    		return;
    	}
    	if ( tiles[tiles.length-1][1] <  Constant.SCREEN_WIDTH - 200 ) {
    		
    		goTo(2);
    		return;
    	}
    	if ( isLeft() ) {
    		goTo(-1);
    	}
    	if ( isRight() ) {
    		goTo(1);
    	}
    }

    @Override
    public void draw(Graphics g) {
        for ( int i = 0  ; i < tiles.length ; i++  ){
        	String color = tile_defination[tiles[i][0]][0] ;
            String bgcolor = tile_defination[tiles[i][0]][1] ;
            Color clr = ( color.equals("red") )?Color.red:Color.black;
            Color bgclr = ( bgcolor.equals("red") )?Color.red:Color.black;

            
        	
            Graphics2D g2d = ((Graphics2D)g);
            Stroke oldStroke = g2d.getStroke(); // Save the current stroke
            Stroke borderStroke = new BasicStroke(3); // Create a new stroke with thickness 3
            g2d.setStroke(borderStroke); // Set the new stroke
            g2d.setColor(bgclr); // Border color
            g2d.drawRect(tiles[i][1],tiles[i][2],Constant.TILE_WIDTH,Constant.TILE_HEIGHT);
            g2d.setStroke(oldStroke);
            g2d.setColor(clr);
            g2d.fillRect(tiles[i][1],tiles[i][2],Constant.TILE_WIDTH,Constant.TILE_HEIGHT);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_A){
            setLeft(true);
            setRight(false);
        }
        if ( e.getKeyCode() == KeyEvent.VK_D){
           setRight(true);
           setLeft(false);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    	if ( e.getKeyCode() == KeyEvent.VK_A){
            setLeft(false);
            
        }
        if ( e.getKeyCode() == KeyEvent.VK_D){
           setRight(false);
           
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
    
    
    
    private void goTo(int offset) {
        for ( int i = 0  ; i < tiles.length ; i++  ){
            tiles[i][1] += offset ; 
        }
    }
    
    
    
}
