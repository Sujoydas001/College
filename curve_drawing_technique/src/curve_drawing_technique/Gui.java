package curve_drawing_technique;


import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * sujoy das
 * 
 * 
 * */


public class Gui extends JFrame implements ActionListener , ItemListener{

	JTextField lvl1Data = new JTextField(5);
	JTextField lvl2Data = new JTextField(5);
	JTextField lvl3Data = new JTextField(5);
	JTextField lvl4Data = new JTextField(5);
	JLabel lvl1 =  new JLabel("")  ; 
	JLabel lvl2 =  new JLabel("")  ;
	JLabel lvl3 =  new JLabel("")  ; 
	JLabel lvl4 =  new JLabel("")  ;
	JButton drawBtn = new JButton("Draw");
	JButton clrBtn = new JButton("Clear");
	JButton defaultCenterBtn = new JButton("set default center");
	
	
	JPanel contextPanel = new JPanel() ; 
	JPanel midPanel = new JPanel();
	JComboBox<String> techniques = null;
	JPanel drawPanel = new JPanel();
	Dimension screenSize ; 
	
	public Gui() {
		Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
		screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
		setSize(screenSize.width,screenSize.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Curve drawing Techniques....");
		initComponents();
	}
	public void drawInputBarforParametricLine() {
		lvl1.setText("x1");lvl2.setText("x2");lvl3.setText("y1");lvl4.setText("y2");
		lvl1Data.setVisible(true);lvl2Data.setVisible(true);lvl3Data.setVisible(true);lvl4Data.setVisible(true);
	}
	public void drawInputBarforParametricCircle() {
		lvl1.setText("center_X");lvl2.setText("center_Y");lvl3.setText("radius");lvl4.setText("");
		lvl1Data.setVisible(true);lvl2Data.setVisible(true);lvl3Data.setVisible(true);lvl4Data.setVisible(false);
	}
	public void drawInputBarforParametricArc() {
		lvl1.setText("center_X");lvl2.setText("center_Y");lvl3.setText("radius");lvl4.setText("maxAngle(reference from 0)");
		lvl1Data.setVisible(true);lvl2Data.setVisible(true);lvl3Data.setVisible(true);lvl4Data.setVisible(true);
	}
	public void drawInputBarforParametricEllipse() {
		lvl1.setText("center_X");lvl2.setText("center_Y");lvl3.setText("a");lvl4.setText("b");
		lvl1Data.setVisible(true);lvl2Data.setVisible(true);lvl3Data.setVisible(true);lvl4Data.setVisible(true);
	}
	public void drawInputBarforParametricSpiral() {
		lvl1.setText("center_X");lvl2.setText("center_Y");lvl3.setText("t");lvl4.setText("");
		lvl1Data.setVisible(true);lvl2Data.setVisible(true);lvl3Data.setVisible(true);lvl4Data.setVisible(false);
	}
	
	private void initComponents() {
		
		JPanel topPanel = new JPanel();

		String list[] = {"DDA_line","parametric_line","parametric_circle","parametric_arc","parametric_ellipse","parametric_spiral",};
		techniques = new JComboBox<String>(list);
		techniques.addItemListener(this) ; 
		
		contextPanel.setLayout(new GridLayout(2,1));
		
		
		topPanel.add(new JLabel("Technique:"));
		topPanel.add(techniques);
		topPanel.add(defaultCenterBtn); 
		defaultCenterBtn.setVisible(false);
		defaultCenterBtn.addActionListener(this);
		midPanel.add(lvl1);
 		midPanel.add(lvl1Data);
 		midPanel.add(lvl2);
 		midPanel.add(lvl2Data);
 		midPanel.add(lvl3);
 		midPanel.add(lvl3Data);
 		midPanel.add(lvl4);
 		midPanel.add(lvl4Data);
  		add(midPanel) ; 
		midPanel.setVisible(false) ; 
		
		
		topPanel.add(drawBtn);
		topPanel.add(clrBtn) ; 
		topPanel.add(new JLabel("implemented by Sujoy Das")) ; 
		drawBtn.setEnabled(false);
		
		drawBtn.addActionListener(this);
		clrBtn.addActionListener(this); 
		contextPanel.add(topPanel); 
		contextPanel.add(midPanel); 
 		add(drawPanel);
 		add(contextPanel , BorderLayout.NORTH) ;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		 if(e.getSource().equals(drawBtn)){
			draw();
		 }; 
		 if ( e.getSource().equals(clrBtn)) {
			 System.out.println("clear");
			 repaint(); 
		 }; 
		 if ( e.getSource().equals(defaultCenterBtn)) {
			 String selectedString = techniques.getSelectedItem().toString(); 
			 System.out.println("set default center 2");

			 if (selectedString.equals("parametric_circle") ||selectedString.equals("parametric_arc") ||selectedString.equals("parametric_ellipse") ||selectedString.equals("parametric_spiral") ) {
				 System.out.println("set default center ");
				 System.out.println(screenSize.width);
				 lvl1Data.setText(String.valueOf(screenSize.width / 2 )) ; 
				 lvl2Data.setText(String.valueOf(screenSize.height / 2 )) ; 

			 }
		 }
		
	}


	private void draw() {		
		System.out.println("draw button click") ; 
		String scheme = techniques.getSelectedItem().toString();
		Graphics2D g = (Graphics2D)drawPanel.getGraphics();
		
		g.clearRect(0, 0, screenSize.width , screenSize.height );
		PaintFactory.draw(g,scheme, lvl1Data.getText(),lvl2Data.getText(),lvl3Data.getText(),lvl4Data.getText());
	}
	
	public static void main(String[] s) {
		Gui f = new Gui();
		f.setVisible(true);
	}

	
	@Override
	public void itemStateChanged(ItemEvent e) { 
		 if (e.getStateChange() == ItemEvent.SELECTED) {
	          Object item = e.getItem();
	          System.out.println(item.toString()) ; 
	          midPanel.setVisible(true) ;
	         if ( e.getItem().toString().equals("parametric_line") || e.getItem().toString().equals("DDA_line")) {
	        	 drawInputBarforParametricLine();
	         	drawBtn.setEnabled(true);
	         	defaultCenterBtn.setVisible(false); 
	         }else  if ( e.getItem().toString().equals("parametric_circle")) {
	        	 drawInputBarforParametricCircle();
	         	drawBtn.setEnabled(true);
	         	defaultCenterBtn.setVisible(true); 
	     		
	         }else  if ( e.getItem().toString().equals("parametric_arc")) {
	        	 drawInputBarforParametricArc();
	         	drawBtn.setEnabled(true);
	         	defaultCenterBtn.setVisible(true); 
	     		
	         }else  if ( e.getItem().toString().equals("parametric_ellipse")) {
	        	 drawInputBarforParametricEllipse();
	         	drawBtn.setEnabled(true);
	         	defaultCenterBtn.setVisible(true); 
	     		
	         }else  if ( e.getItem().toString().equals("parametric_spiral")) {
	        	 drawInputBarforParametricSpiral();
	         	 drawBtn.setEnabled(true);
	         	defaultCenterBtn.setVisible(true); 
	     		
	         }
	          
	          
	          
	       }
	}

}