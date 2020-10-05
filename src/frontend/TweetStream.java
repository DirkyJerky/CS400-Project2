package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TweetStream {

	private JFrame frame;
	private JTextField textGotoN;
	private JTextField textFilterText;

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
		
		JPanel panelMenu = new JPanel();
		frame.getContentPane().add(panelMenu, BorderLayout.WEST);
		GridBagLayout gbl_panelMenu = new GridBagLayout();
		gbl_panelMenu.columnWidths = new int[]{145, 0};
		gbl_panelMenu.rowHeights = new int[]{26, 26, 26, 26, 26, 0, 0};
		gbl_panelMenu.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelMenu.setLayout(gbl_panelMenu);
		
		JLabel labelTitle = new JLabel("Tweet Stream");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelTitle = new GridBagConstraints();
		gbc_labelTitle.fill = GridBagConstraints.BOTH;
		gbc_labelTitle.insets = new Insets(0, 0, 5, 0);
		gbc_labelTitle.gridx = 0;
		gbc_labelTitle.gridy = 0;
		panelMenu.add(labelTitle, gbc_labelTitle);
		
		JButton buttonStreamToggle = new JButton("Start Streaming");
		GridBagConstraints gbc_buttonStreamToggle = new GridBagConstraints();
		gbc_buttonStreamToggle.fill = GridBagConstraints.BOTH;
		gbc_buttonStreamToggle.insets = new Insets(0, 0, 5, 0);
		gbc_buttonStreamToggle.gridx = 0;
		gbc_buttonStreamToggle.gridy = 1;
		panelMenu.add(buttonStreamToggle, gbc_buttonStreamToggle);
		
		JPanel panelGotoN = new JPanel();
		GridBagConstraints gbc_panelGotoN = new GridBagConstraints();
		gbc_panelGotoN.fill = GridBagConstraints.BOTH;
		gbc_panelGotoN.insets = new Insets(0, 0, 5, 0);
		gbc_panelGotoN.gridx = 0;
		gbc_panelGotoN.gridy = 2;
		panelMenu.add(panelGotoN, gbc_panelGotoN);
		panelGotoN.setLayout(new BorderLayout(0, 0));
		
		JButton buttonGotoN = new JButton("Goto N");
		panelGotoN.add(buttonGotoN, BorderLayout.WEST);
		
		textGotoN = new JTextField();
		panelGotoN.add(textGotoN, BorderLayout.CENTER);
		textGotoN.setColumns(3);
		
		textFilterText = new JTextField();
		textFilterText.setToolTipText("Filter Text");
		GridBagConstraints gbc_textFilterText = new GridBagConstraints();
		gbc_textFilterText.fill = GridBagConstraints.BOTH;
		gbc_textFilterText.insets = new Insets(0, 0, 5, 0);
		gbc_textFilterText.gridx = 0;
		gbc_textFilterText.gridy = 3;
		panelMenu.add(textFilterText, gbc_textFilterText);
		textFilterText.setColumns(10);
		
		JScrollPane scrollpaneRules = new JScrollPane();
		GridBagConstraints gbc_scrollpaneRules = new GridBagConstraints();
		gbc_scrollpaneRules.insets = new Insets(0, 0, 5, 0);
		gbc_scrollpaneRules.fill = GridBagConstraints.VERTICAL;
		gbc_scrollpaneRules.gridx = 0;
		gbc_scrollpaneRules.gridy = 4;
		gbc_scrollpaneRules.gridheight = GridBagConstraints.REMAINDER;
		panelMenu.add(scrollpaneRules, gbc_scrollpaneRules);
		
		JPanel panelRules = new JPanel();
		scrollpaneRules.setViewportView(panelRules);
		GridBagLayout gbl_panelRules = new GridBagLayout();
		gbl_panelRules.columnWidths = new int[]{0, 0};
		gbl_panelRules.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelRules.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelRules.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelRules.setLayout(gbl_panelRules);
		
		JLabel lblNewLabel = new JLabel("TODO: Rules go here");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelRules.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("TODO: Rules go here");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panelRules.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton buttonAddNewRule = new JButton("Add new Rule");
		GridBagConstraints gbc_buttonAddNewRule = new GridBagConstraints();
		gbc_buttonAddNewRule.gridx = 0;
		gbc_buttonAddNewRule.gridy = 2;
		panelRules.add(buttonAddNewRule, gbc_buttonAddNewRule);
		
		JScrollPane scrollpaneTweetViewer = new JScrollPane();
		frame.getContentPane().add(scrollpaneTweetViewer, BorderLayout.CENTER);
		
		JPanel panelTweetViewer = new JPanel();
		scrollpaneTweetViewer.setViewportView(panelTweetViewer);
	}

}
