




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @Author Sujoy das
 */
public class MyGUI extends JFrame implements ActionListener , ComponentListener {
	JPanel topPanel = null;
	JButton drawBtn=  null;
	JButton clearBtn=  null;
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
		drawBtn= new JButton("Draw");
		clearBtn= new JButton("X");
		drawPanel = new DrawPanel();
		addComponentListener(this);
		this.drawBtn.addActionListener(this);
		this.clearBtn.addActionListener(this);
		topPanel.setLayout(new FlowLayout());
		topPanel.add(new JLabel("toppanel"));
		
		topPanel.add(drawBtn);
		topPanel.add(clearBtn);
		drawBtn.setFocusable(false);
		clearBtn.setFocusable(false);
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
		if ( e.getSource().equals(drawBtn)) {
			draw(); 
		}
		if ( e.getSource().equals(clearBtn)) {
			remove(); 
		}
		
		repaint();
		
		
//		}else if (e.getSource().equals(exitBtn) ){
////			drawBtn.setText("draw");
//			remove(); 
//		}
		
	}
	@Override
	public void componentResized(ComponentEvent e) {
		Helper.screenHeight= e.getComponent().getSize().height;
		Helper.screenWidth= e.getComponent().getSize().width;
		
		
	}
	@Override
	public void componentMoved(ComponentEvent e) {
		
		
	}
	@Override
	public void componentShown(ComponentEvent e) {
		
		
	}
	@Override
	public void componentHidden(ComponentEvent e) {
		
		
	}
	private void draw() {
		((DrawPanel)drawPanel).draw() ;
	}
	private void remove() {
		((DrawPanel)drawPanel).remove() ;
	}
	





}
