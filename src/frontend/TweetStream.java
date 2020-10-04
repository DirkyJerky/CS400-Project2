package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class TweetStream {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtFilterText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TweetStream window = new TweetStream();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TweetStream() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(10, 1, 0, 0));
		
		JLabel lblLabel = new JLabel("Tweet Stream");
		lblLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblLabel);
		
		JButton btnNewButton = new JButton("Start Streaming");
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = new JButton("Goto N");
		panel_1.add(btnNewButton_1, BorderLayout.WEST);
		
		textField = new JTextField();
		panel_1.add(textField, BorderLayout.CENTER);
		textField.setColumns(3);
		
		txtFilterText = new JTextField();
		txtFilterText.setToolTipText("Filter Text");
		panel.add(txtFilterText);
		txtFilterText.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1);
		
		JLabel lblNewLabel = new JLabel("TODO: Rules go here");
		scrollPane_1.setViewportView(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
