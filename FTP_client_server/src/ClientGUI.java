import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.net.NoRouteToHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



/*
 * @author sujoy das - 19 
 * 
 * */


public class ClientGUI extends JFrame implements ActionListener{
	public TextArea output = new TextArea() ; 
	public JButton submitBtn = new JButton("GET") ; 
	public JTextField input_ip = new JTextField() ; 
	public JTextField input_port = new JTextField() ; 
	public JTextField input_path = new JTextField() ;
	
	public ClientGUI() {
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(600,600));
		this.setMinimumSize(new Dimension(600,600));
		this.setMaximumSize(new Dimension(600,600));

		this.setLayout(new GridLayout(2,1));
		
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		/*ip port path*/
		JPanel ippanel = new JPanel(); 
		ippanel.setLayout(new BorderLayout());
		JLabel ipLabel = new JLabel( "server ip : ") ;
		
		ippanel.add(ipLabel,BorderLayout.NORTH) ;
		ippanel.add(input_ip,BorderLayout.SOUTH) ;
		
		
		JPanel portpanel = new JPanel(); 
		portpanel.setLayout(new BorderLayout());
		JLabel portLabel = new JLabel( "server app. port : ") ;
		
		portpanel.add(portLabel,BorderLayout.NORTH) ;
		portpanel.add(input_port,BorderLayout.SOUTH) ;
		
		
		JPanel pathpanel = new JPanel(); 
		pathpanel.setLayout(new BorderLayout());
		JLabel pathLabel = new JLabel( "file path : ") ;
		
		pathpanel.add(pathLabel,BorderLayout.NORTH) ;
		pathpanel.add(input_path,BorderLayout.SOUTH) ;
		
		

		panel.add(ippanel) ; panel.add(portpanel) ;panel.add(pathpanel) ; panel.add(submitBtn) ; 		
		this.add(panel); 
		submitBtn.addActionListener(this) ; 
		this.add(output);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		
	}
	public static void main(String[] s) {
		new ClientGUI() ; 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ( e.getSource().equals(submitBtn)) {
//			System.out.println(input_ip.getText());
//			System.out.println(input_port.getText());
//			System.out.println(input_path.getText());

			/*validate ip */
			/*validate port*/
			Integer portInteger = Integer.parseInt(input_port.getText().toString());
			/*validate path*/
			try {
				FTPClient client = new FTPClient(input_ip.getText(), portInteger, input_path.getText());
				output.setText(client.outputToShow);

			} catch (ConnectException | NoRouteToHostException e2) {
				output.setText("error occured in connection");
			}
		}

		
	}
}
