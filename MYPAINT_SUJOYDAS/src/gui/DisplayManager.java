package gui;

import java.util.ArrayList;
import java.util.List;

import gui.entity.DisplayObject;



/*
 * @Author Sujoy das
 */

public class DisplayManager {
	private List<DisplayObject> displayBuffer = null ;
	public SelectedTool toolSelected = SelectedTool.None; 
	
	
	public DisplayManager() {
		displayBuffer = new ArrayList<DisplayObject>(); 
	}
	public List<DisplayObject>  getDisplayBuffer() {
		return this.displayBuffer ; 
	}
	
}
