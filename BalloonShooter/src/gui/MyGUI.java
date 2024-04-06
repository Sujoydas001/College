package gui;




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
	JLabel scoreLabel= null;
	JLabel bulletCountLabel= null;
	JButton pauseBtn=  null;
	JButton exitBtn=  null;
	Timer t =null;
	public JPanel drawPanel  = null ;
	public MyGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Helper.screenWidth,Helper.screenHeight);
		setMinimumSize(new Dimension(600,600));
		setTitle("My GUI- BALLOON SHOOTER");
		initComponents();
		
		
	}
	private void initComponents() {
		topPanel = new JPanel();
		scoreLabel=new JLabel("score :");
		bulletCountLabel =new JLabel("remaining bullets :");
		pauseBtn= new JButton("||");
		exitBtn= new JButton("X");
		drawPanel = new DrawPanel();
		addComponentListener(this);
		this.pauseBtn.addActionListener(this);
		this.exitBtn.addActionListener(this);
		topPanel.setLayout(new FlowLayout());
		topPanel.add(new JLabel("toppanel"));
		
		topPanel.add(pauseBtn);
		topPanel.add(exitBtn);
		topPanel.add(scoreLabel);
		topPanel.add(bulletCountLabel);
		pauseBtn.setFocusable(false);
		exitBtn.setFocusable(false);
		drawPanel.setBackground(Color.WHITE);
		add(drawPanel);
		add(topPanel,BorderLayout.NORTH);
		t = new Timer(1, this);
		t.start();
		
		

		
	}

	public static void main(String[] args) {
		MyGUI f = new MyGUI();
		f.addKeyListener(  ((DrawPanel)f.drawPanel).gun  );
		f.addMouseWheelListener(  ((DrawPanel)f.drawPanel).gun  );
		f.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		scoreLabel.setText("score : "+  String.valueOf((Helper.score) ));
		bulletCountLabel.setText("bullets remaining : "+String.valueOf(Helper.bulletCount));
		if ( Helper.gameOver ) {
			int reply= JOptionPane.showConfirmDialog(drawPanel, "score : " + Helper.score  + " play again?");
			if ( reply == JOptionPane.YES_OPTION) {

				Helper.gameOver = false;
				Helper.gamePaused  =false;
				Helper.bulletCount = 100;
				Helper.score = 0 ;
				this.dispose();
				
				main(null);
			}else {
				t.stop();
				System.exit(0);
			}
		}
		
		if ( !Helper.gamePaused ) {
			repaint();
		}
		
		if ( e.getSource().equals(pauseBtn)) {
			if ( pauseBtn.getText().equals("||")) {
				Helper.gamePaused = true;
				t.stop();
				pauseBtn.setText(">");
			}else {
				Helper.gamePaused = false;
				t.start();
				pauseBtn.setText("||");
			}
			
		}else if (e.getSource().equals(exitBtn) ){
			t.stop();
			System.exit(0);
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
	





}
