package gui.mousehandler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import gui.DisplayManager;
import gui.entity.Circle;
import gui.entity.DisplayObject;
import gui.entity.FreeHand;
import gui.entity.Line;




/*
 * @Author Sujoy das
 */

public class MyMouseListener implements MouseListener,MouseMotionListener {
	DisplayObject circleObj= null; 
	DisplayObject lineObj= null; 
	DisplayObject freehandObj= null;
	DisplayManager displayManager = null;
	
//	public MyMouseListener(DisplayObject circleObj, DisplayObject lineObj, DisplayObject freehandObj,
//			DisplayManager displayManager) {
//		super();
//		this.circleObj = circleObj;
//		this.lineObj = lineObj;
//		this.freehandObj = freehandObj;
//		this.displayManager = displayManager;
//		
//	}
	public MyMouseListener(DisplayManager displayManager) {
		super();
		
		this.displayManager = displayManager;
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		switch (this.displayManager.toolSelected) {
		case  None : {
			break ; 
			
		}
		case  Circle : {
			circleObj = new Circle(e.getX(),e.getY(),0 ,displayManager) ; 
			((Circle)circleObj).mousePressed(e) ;
			break;
			
		}
		case  Line : {
			lineObj = new Line(e.getX(),e.getY(),displayManager) ; 
			((Line)lineObj).mousePressed(e) ;
			
			break ; 
			
		}
		case  FreeHand : {
			freehandObj = new FreeHand(e.getX(),e.getY(),displayManager) ; 
			((FreeHand)freehandObj).mousePressed(e) ;
			
			break ;
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " );
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		
		
		switch (this.displayManager.toolSelected) {
			case  None : {
				break; 
				
			}
			case  Circle : {
				((Circle)circleObj).mouseDragged(e);
				break;
				
			}
			case  Line : {
				((Line)lineObj).mouseDragged(e);
				break;
			}
			case  FreeHand : {
				((FreeHand)freehandObj).mouseDragged(e);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " );
		}
 
		
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
