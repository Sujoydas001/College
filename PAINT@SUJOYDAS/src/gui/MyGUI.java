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
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

import gui.entity.BackGround;

/**
 * @Author Sujoy das
 * 
 */

public class MyGUI extends JFrame implements ActionListener, ItemListener {
	
	JComboBox<String> techniques = null;
	JPanel drawPanel ;
	
	private JButton colorBtn,imgBtn;
	public MyGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,600);
		setTitle("My GUI");
		
		initComponents();
	}

	private void initComponents() {
		
		JPanel topPanel = new JPanel();
		String list[] = {"Select Tool","Circle","Line","Free Hand","Curve" , "Rectangle"};
		techniques = new JComboBox<String>(list);
		techniques.setSelectedIndex(0);
		techniques.addItemListener(this);
		imgBtn = new JButton("add img");
		imgBtn.addActionListener(this);
		topPanel.add(imgBtn) ; 
		colorBtn = new JButton("fill");
		topPanel.add(colorBtn) ; 
		colorBtn.addActionListener(this); 
		topPanel.add(new JLabel("Technique:"));
		topPanel.add(techniques);
		
		
		drawPanel = new DrawPanel();
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
		if ( e.getSource().equals(colorBtn)) {
			SelectedTool sTool = SelectedTool.ColorFill ; 
	         addNewObj(sTool) ; 
			 
			
		}
		if ( e.getSource().equals(imgBtn)) {
			if ( imgBtn.getText().equals("add img")) {
				   JFileChooser fileChooser = new JFileChooser();
	               int returnValue = fileChooser.showOpenDialog(null);
	               if (returnValue == JFileChooser.APPROVE_OPTION) {
	                  File selectedFile = fileChooser.getSelectedFile();
	                  if ( selectedFile == null ) {
	                	  return;
	                  }
	                  
	                  String ext=selectedFile.getAbsolutePath().substring(selectedFile.getAbsolutePath().lastIndexOf('.')+1) ;  
		               if ( ext.equals("jpg")||ext.equals("jpeg")|| ext.equals("png")) {
		            	   addBG(selectedFile.getAbsolutePath());
		 	              imgBtn.setText("rm img");
		            	 
		               }else {
		            	   JOptionPane.showMessageDialog(this,"file type ."+ext+" not supported now!");
		            	   return;
		               }
	                  
	              }
			}else {
				removeBG();
				imgBtn.setText("add img");
			}
			  
		}
		
		
		repaint();
	}


	private void removeBG() {
		((DrawPanel)drawPanel).removeBG();
	}

	private void addBG(String abspath) {
		((DrawPanel)drawPanel).loadBG(abspath);
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) { 
		 if (e.getStateChange() == ItemEvent.SELECTED) {
			 SelectedTool sTool = null ; 
			 if ( e.getItem().toString().equals("Select Tool") ) {
				 sTool = SelectedTool.None ;	     		
	         }else if ( e.getItem().toString().equals("Circle") ) {
	        	 sTool = SelectedTool.Circle;
	     		
	         }else if ( e.getItem().toString().equals("Line") ) {
	        	 sTool = SelectedTool.Line;
	     		
	         }else if ( e.getItem().toString().equals("Free Hand") ) {
	        	 sTool = SelectedTool.FreeHand;
	     		
	         }else if ( e.getItem().toString().equals("Curve") ) {
	        	 sTool = SelectedTool.BeizerCurve;
	     		
	         }else if ( e.getItem().toString().equals("Rectangle") ) {
	        	 sTool = SelectedTool.Rectangle;
	     		
	         }else {
	        	 
	         }
	         addNewObj(sTool) ; 
	         
		 }
	}

	private void addNewObj(SelectedTool sTool) {
		((DrawPanel)drawPanel).addNewObj(sTool) ; 
		
	}



}
