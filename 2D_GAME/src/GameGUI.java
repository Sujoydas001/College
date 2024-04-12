
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @Author Sujoy das
 * 
 */

public class GameGUI extends JFrame implements ActionListener,ComponentListener {
	
	JPanel drawPanel ;
	
	public GameGUI() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Constant.SCREEN_WIDTH , Constant.SCREEN_HEIGHT);
		setTitle("My GUI");
		addComponentListener(this);
		setResizable(false);
		
		initComponents();
	}

	private void initComponents() {
		
		
		drawPanel = new DrawPanel();
		drawPanel.setBackground(Color.WHITE);
		add(drawPanel);
		
		Timer t = new Timer(60, this);
		t.start();

		
	}

	public static void main(String[] args) {
		GameGUI f = new GameGUI();
		f.addKeyListener(((DrawPanel)(f.drawPanel)).platform );
		f.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void componentResized(ComponentEvent e) {
		 Constant.SCREEN_WIDTH= e.getComponent().getWidth();
		 
	     Constant.SCREEN_HEIGHT = e.getComponent().getHeight();
	     
	     ((DrawPanel)drawPanel).updateBackGround();
		
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
