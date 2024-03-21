package encode_scheme;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * @Author Dr. Sunirmal Khatua
 */
public class LineEncodingGUI extends JFrame implements ActionListener {
	JTextField inpData = new JTextField(12);
	JTextField rcvData = new JTextField(12);
	JComboBox<String> techniques = null;
	JPanel drawPanel = new JPanel();
	public LineEncodingGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,1000);
		setTitle("Line Encoding Techniques....");
		initComponents();
	}

	private void initComponents() {
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Input Data:"));
		topPanel.add(inpData);
		String list[] = {"NRZ-I","NRZ-L","RZ","Manchester","Differential Manchester","AMI","Pseudoternary","ALL"};
		techniques = new JComboBox<String>(list);
		topPanel.add(new JLabel("Technique:"));
		topPanel.add(techniques);
		topPanel.add(new JLabel("Received Data:"));
		topPanel.add(rcvData);
		JButton encodeBtn = new JButton("Encode");
		JButton decodeBtn = new JButton("Decode");
		JButton clrBtn = new JButton("Clear");
		topPanel.add(encodeBtn);
		encodeBtn.addActionListener(this);
		topPanel.add(decodeBtn);
		decodeBtn.addActionListener(this);
		topPanel.add(clrBtn);
		clrBtn.addActionListener(this);
		drawPanel.setBackground(Color.WHITE);
		add(drawPanel);
		add(topPanel,BorderLayout.NORTH);

		
	}

	public static void main(String[] args) {
		LineEncodingGUI f = new LineEncodingGUI();
		f.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Encode")){
			encode();
		}else if(e.getActionCommand().equals("Decode")){
			decode();
		}else if(e.getActionCommand().equals("Clear")){
			repaint();
		}
		
	}


	private void encode() {		
		String scheme = techniques.getSelectedItem().toString();
		String data = inpData.getText();
		Graphics2D g = (Graphics2D)drawPanel.getGraphics();
		g.clearRect(0, 0, 1000, 1000);
		EncoderFactory.encode(g,scheme, data);
	}
	

	private void decode() {
		drawPanel.getGraphics().drawString("TO BE DONE LATTER......", 400, 100);
	}

}
