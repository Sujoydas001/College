package gui;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class Gun extends Thread implements DisplayObject , KeyListener , MouseWheelListener{
	private int width,height;
	private boolean directionUp = false,directionDown = false ,isFiring=false , rotateLeft = false,rotateRight = false,isScalingUp=false,isScalingDown=false;
	int xcenter,ycenter;
	double angle = 0 , prevangle = 0 ; 
	ArrayList<DisplayObject> displayBuffer;

	double[][] rotatedMatrix; 
	private final float scaling = 1.1f;

	public Gun(int x, int y ,ArrayList<DisplayObject> displayBuffer) {
		/*
		 * 
		 * a(x,y)             b(x+30,y)
		 * 
		 * 			(x+15,y+5)
		 * 
		 * c(x,y+10)           d(x+30,y+10)
		 */
		
		/*
		 * matrix = {
		 * 			{ax,bx,cx,dx},
		 * 			{ay,by,cy,dy},
		 * 			{1,1,1,1},
		 *			 }
		 * 
		 * 
		 */
		
		super();
		this.height = 10 ; 
		this.width = 3 * height ;
		xcenter = x + (width/2 );
		this.displayBuffer = displayBuffer;
		displayBuffer.add(this);
		


		double[][]  matrix = {
				{x , x+width,x,x+width},
				{y , y,y+height,y+height},
				{1,1,1,1}
			};
		rotatedMatrix  = matrix;
		start();
	}
	
	public void fire() {
		Bullet bullet = new Bullet((int)rotatedMatrix[0][1],(int)rotatedMatrix[1][1],this.angle,this.height,displayBuffer);
		this.displayBuffer.add(bullet);
	}
	@Override
	public void run() {
		int k=0;
		while(!Helper.gameOver  ) {
			if ( !Helper.gamePaused) {
				if ( isDirectionUp() ) {
					rotatedMatrix[1][0]-=5;rotatedMatrix[1][1]-=5;rotatedMatrix[1][2]-=5;rotatedMatrix[1][3]-=5; 
				};
				if ( isDirectionDown()){
					rotatedMatrix[1][0]+=5;rotatedMatrix[1][1]+=5;rotatedMatrix[1][2]+=5;rotatedMatrix[1][3]+=5;
				};
			
				if ( isRotateLeft() && ( angle < 45 )) {
					angle += 5 ; 
				}
				if ( isRotateRight()&&( angle > -45 )) {
					angle-=5;
				}
			
				
				if ( ( isRotateLeft() || isRotateRight() ) && ( angle <45 && angle > -45 ) ) {
					rotatedMatrix  = rotate(angle-prevangle);
					prevangle = angle ; 
					
				}
				float toscale = 0;
				if ( isScalingUp() || isScalingDown() ) {
					if ( isScalingUp() ) {
						toscale = width < 100 ?this.scaling:1;
						rotatedMatrix= scale(rotatedMatrix,toscale );
					}
					if ( isScalingDown() ) {	
						toscale = width > 30 ?1/this.scaling:1;
						rotatedMatrix= scale( rotatedMatrix, toscale );
						
					}
					double ax= rotatedMatrix[0][0] , cx=rotatedMatrix[0][2] ; 
					double ay= rotatedMatrix[1][0] ,cy=rotatedMatrix[1][2] ;
					height =(int)( Math.sqrt((ax-cx)*(ax-cx)+(ay-cy)*(ay-cy) ));
					width = (3 * height) ;
				}
				int [] centers  = getCenter(rotatedMatrix) ;
				this.xcenter = centers[0];
			    this.ycenter = centers[1];	
				
				if ( isFiring() ) {
					if (k == 0) {
						this.fire();
					}
				}
				k= (k+1)%20;
				if(!isFiring()) {
					k=0;
				}
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	



	private int[] getCenter(double[][] rotatedMatrix2) {
		double ax =rotatedMatrix2[0][0] , bx=rotatedMatrix2[0][1], cx=rotatedMatrix2[0][2],dx=rotatedMatrix2[0][3];
		double ay =rotatedMatrix2[1][0] , by=rotatedMatrix2[1][1], cy=rotatedMatrix2[1][2],dy=rotatedMatrix2[1][3];

		int x = (int)(( ax + cx ) / 2 );
		int y = (int)(( ay + cy ) / 2 ); 
		
		return new int[]{x,y};
	}

	@Override
	public void draw(Graphics g) {
		g.drawOval(xcenter, ycenter, 2,2);
	
		g.drawLine( (int)rotatedMatrix[0][0],(int)rotatedMatrix[1][0],(int)rotatedMatrix[0][1],(int)rotatedMatrix[1][1]); /*a-b*/
		g.drawLine((int)rotatedMatrix[0][1],(int)rotatedMatrix[1][1],(int)rotatedMatrix[0][3], (int)rotatedMatrix[1][3]); /*b-d*/
		g.drawLine((int)rotatedMatrix[0][2],(int)rotatedMatrix[1][2],(int)rotatedMatrix[0][3], (int)rotatedMatrix[1][3]); /*c-d*/
		g.drawLine( (int)rotatedMatrix[0][0],(int)rotatedMatrix[1][0],(int)rotatedMatrix[0][2],(int)rotatedMatrix[1][2]); /*a-c*/
		
	}
	
	private double[][] scale(double[][] matrix, double sx) {

		double newmatrix[][]= new double[3][4];
		double matrix1[][] = {
				{sx,0,xcenter*(1-sx) },
				{ 0,sx,ycenter*(1-sx)},
				{0,0,1}
		}; 
		 double matrix2[][] = {
					{ matrix[0][0], matrix[0][1],matrix[0][2],matrix[0][3] },
					{ matrix[1][0], matrix[1][1],matrix[1][2],matrix[1][3] },
					{1,1,1,1}
					}; 


		 for (int i = 0; i < matrix1.length; i++)  
	        { 
	            for (int j = 0;  j < matrix2[i].length; j++)  
	            { 
	                for (int k = 0; k < matrix1[0].length ; k++)  
	                { 
	                	newmatrix[i][j] 
	                        += matrix1[i][k] * matrix2[k][j]; 
	                } 
	            } 
	        }
		 return newmatrix;
		
		
	}
	private double[][] rotate(double angle) {
		
		double newmatrix[][]= new double[3][4];
		angle = angle * ( Math.PI / 180 ) ;
		double matrix2[][] = rotatedMatrix ;  
		
		
		double matrix1[][] = {
						{ Math.cos(angle), -Math.sin(angle), (xcenter*(1-Math.cos(angle)) + ycenter*(Math.sin(angle)))  },
						{ Math.sin(angle), Math.cos(angle), (ycenter*(1-Math.cos(angle)) - xcenter*(Math.sin(angle)))  },
						{0,0,1}
				}; 
	
		
		
		 for (int i = 0; i < matrix1.length; i++)  
	        { 
	            for (int j = 0;  j < matrix2[i].length; j++)  
	            { 
	                for (int k = 0; k < matrix1[0].length ; k++)  
	                { 
	                	newmatrix[i][j] 
	                        += matrix1[i][k] * matrix2[k][j]; 
	                } 
	            } 
	        } 
		
		return newmatrix;
	}
	public boolean isRotateLeft() {
		return rotateLeft;
	}
	public void setRotateLeft(boolean rotateLeft) {
		this.rotateLeft = rotateLeft;
	}
	public boolean isRotateRight() {
		return rotateRight;
	}
	public void setRotateRight(boolean rotateRight) {
		this.rotateRight = rotateRight;
	}
	public boolean isFiring() {
		return isFiring;
	}
	public void setFiring(boolean isFiring) {
		this.isFiring = isFiring;
	}
	public boolean isDirectionUp() {
		return directionUp;
	}
	public void setDirectionUp(boolean directionUp) {
		this.directionUp = directionUp;
	}
	public boolean isDirectionDown() {
		return directionDown;
	}
	public void setDirectionDown(boolean direcitonDown) {
		this.directionDown = direcitonDown;
	}
	public boolean isScalingUp() {
		return isScalingUp;
	}

	public void setScalingUp(boolean isScalingUp) {
		this.isScalingUp = isScalingUp;
	}

	public boolean isScalingDown() {
		return isScalingDown;
	}

	public void setScalingDown(boolean isScalingDown) {
		this.isScalingDown = isScalingDown;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if ( rotatedMatrix[1][0] <= 50  ) {
			rotatedMatrix[1][0]+=5;rotatedMatrix[1][1]+=5;rotatedMatrix[1][2]+=5;rotatedMatrix[1][3]+=5;
			this.setDirectionUp(false);
			return;
		}
		
		if ( rotatedMatrix[1][0] >= Helper.screenHeight-100  ) {
			rotatedMatrix[1][0]-=5;rotatedMatrix[1][1]-=5;rotatedMatrix[1][2]-=5;rotatedMatrix[1][3]-=5;
			this.setDirectionDown(false);
			return;
		}
		if ( e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_8 ) {
			this.setDirectionUp(true);
			this.setDirectionDown(false);
		}
		if ( e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_2 ) {
				this.setDirectionUp(false);
				this.setDirectionDown(true);			
		}
		if ( e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_5 ) {
			this.setFiring(true);
		}
		if ( e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_6) {
			this.setRotateLeft(false);		
			this.setRotateRight(true);
		}
		if ( e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_4) {
			this.setRotateRight(false);
			this.setRotateLeft(true);
		}
		if ( e.getKeyCode() == KeyEvent.VK_W ) {
			this.setScalingDown(false);		
			this.setScalingUp(true);
		}
		if ( e.getKeyCode() == KeyEvent.VK_S ) {
			this.setScalingUp(false);
			this.setScalingDown(true);		
			
		}
	
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if ( e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_8 ) {
			this.setDirectionUp(false);
		}
		if ( e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_2 ) {
			
			this.setDirectionDown(false);
		}
		if ( e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_5 ) {
			this.setFiring(false);
		}
		if ( e.getKeyCode() == KeyEvent.VK_RIGHT  || e.getKeyCode() == KeyEvent.VK_6 ) {
			this.setRotateRight(false);
		}
		if ( e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_4) {
			this.setRotateLeft(false);
		}
		if ( e.getKeyCode() == KeyEvent.VK_W ) {
			this.setScalingUp(false);
		}
		if ( e.getKeyCode() == KeyEvent.VK_S ) {
			this.setScalingDown(false);		
			
		}
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rotation = e.getWheelRotation() ; 
		if ( rotation < 0 ) {
			
			this.setScalingUp(false);
			this.setScalingDown(true);		
			
		}
		if ( rotation > 0 ) {
			
			this.setScalingDown(false);		
			this.setScalingUp(true);
		}
	}
	

}
