package gui;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JFrame;
//import javax.swing.Timer;
//
//public class MyGUI extends JFrame implements ActionListener{
//	
//	DrawPanel drawPanel = new DrawPanel();
//	
//	public MyGUI() {
//		setTitle("My GUI");
//		setExtendedState(MAXIMIZED_BOTH);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		initComponents();
//		Timer t = new Timer(60, this);
//		t.start();
//	}
//
//	private void initComponents() {
//		add(drawPanel);	
//	}
//
//	public static void main(String[] args) {
//		MyGUI gui = new MyGUI();
//		gui.setVisible(true);
//
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		drawPanel.repaint();
//		
//	}
//
//}



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

/**
 * @Author Sujoy das
 * 
 */

public class MyGUI extends JFrame implements ActionListener, ItemListener {
	
	JComboBox<String> techniques = null;
	
	DisplayManager displayManager ; 
	JPanel drawPanel ;
	public MyGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,600);
		setTitle("My GUI");
		
		initComponents();
	}

	private void initComponents() {
		JPanel topPanel = new JPanel();
		String list[] = {"Circle","Line","Free Hand"};
		techniques = new JComboBox<String>(list);
		techniques.setSelectedIndex(0);
		techniques.addItemListener(this);
		topPanel.add(new JLabel("Technique:"));
		topPanel.add(techniques);
		
		this.displayManager  = new DisplayManager() ; 
		drawPanel = new DrawPanel(this.displayManager);
		drawPanel.setBackground(Color.WHITE);
		add(drawPanel);
		add(topPanel,BorderLayout.NORTH);
		Timer t = new Timer(60, this);
		t.start();

		
	}

	public static void main(String[] args) {
		MyGUI f = new MyGUI();
		f.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}


	@Override
	public void itemStateChanged(ItemEvent e) { 
		 if (e.getStateChange() == ItemEvent.SELECTED) {
	          
	         if ( e.getItem().toString().equals("Circle") ) {
	        	 this.displayManager.toolSelected = SelectedTool.Circle;
	     		
	         }else if ( e.getItem().toString().equals("Line") ) {
	        	 this.displayManager.toolSelected = SelectedTool.Line;
	     		
	         }else if ( e.getItem().toString().equals("Free Hand") ) {
	        	 this.displayManager.toolSelected = SelectedTool.FreeHand;
	     		
	         }else {
	        	 
	         }
	         
		 }
	}



}
