




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

/**
 * @Author Sujoy das
 */
public class MyGUI extends JFrame implements ActionListener , ComponentListener {
	JPanel topPanel = null;
	JButton drawBtn=  null;
	JButton exitBtn=  null;
	Timer t =null;
	public JPanel drawPanel  = null ;
	public MyGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Helper.screenWidth,Helper.screenHeight);
		setMinimumSize(new Dimension(600,600));
		setTitle("My GUI");
		initComponents();
		
		
	}
	private void initComponents() {
		topPanel = new JPanel();
		drawBtn= new JButton("draw");
		exitBtn= new JButton("X");
		drawPanel = new DrawPanel();
		addComponentListener(this);
		this.drawBtn.addActionListener(this);
		this.exitBtn.addActionListener(this);
		topPanel.setLayout(new FlowLayout());
		topPanel.add(new JLabel("toppanel"));
		
		topPanel.add(drawBtn);
		topPanel.add(exitBtn);
		drawBtn.setFocusable(false);
		exitBtn.setFocusable(false);
		drawPanel.setBackground(Color.WHITE);
		add(drawPanel);
		add(topPanel,BorderLayout.NORTH);
		t = new Timer(1, this);
		t.start();
		
		

		
	}

	public static void main(String[] args) {
		MyGUI f = new MyGUI();
		f.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
		boolean tick= ((BCurve)(((DrawPanel)drawPanel).cObj)).draw ;
		if ( tick ) {
			drawBtn.setText("O");
		}else {
			drawBtn.setText("draw");
		}
		
		
		
		if ( e.getSource().equals(drawBtn)) {
			if ( drawBtn.getText().equals("O")) {
//				((BCurve)(((DrawPanel)drawPanel).cObj)).intake = true ; 
				
			}else {
				((BCurve)(((DrawPanel)drawPanel).cObj)).draw = true ;
				draw() ;
				System.out.println("1");
				
			}
			
		}else if (e.getSource().equals(exitBtn) ){
//			drawBtn.setText("draw");
			remove(); 
		}
		
	}
	@Override
	public void componentResized(ComponentEvent e) {
		Helper.screenHeight= e.getComponent().getSize().height;
		Helper.screenWidth= e.getComponent().getSize().width;
		
		
	}
	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void draw() {
		((DrawPanel)drawPanel).draw() ;
	}
	private void remove() {
		((DrawPanel)drawPanel).remove() ;
	}
	





}
