package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		panelMenu.setPreferredSize(new Dimension(300, 500));
		frame.getContentPane().add(panelMenu, BorderLayout.WEST);
		GridBagLayout gbl_panelMenu = new GridBagLayout();
		gbl_panelMenu.columnWidths = new int[]{145, 0};
		gbl_panelMenu.rowHeights = new int[]{26, 26, 26, 26, 26, 0, 0, 0};
		gbl_panelMenu.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE, 0.0};
		panelMenu.setLayout(gbl_panelMenu);
		
		JLabel labelTitle = new JLabel("Tweet Stream");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelTitle = new GridBagConstraints();
		gbc_labelTitle.fill = GridBagConstraints.BOTH;
		gbc_labelTitle.insets = new Insets(0, 0, 5, 0);
		gbc_labelTitle.gridx = 0;
		gbc_labelTitle.gridy = 0;
		panelMenu.add(labelTitle, gbc_labelTitle);
		
		JToggleButton buttonStreamToggle = new JToggleButton("Start Streaming");
		buttonStreamToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand() == "Start Streaming") {
					if (true) { // TODO Test if rules are set, and the app is ready to go
						buttonStreamToggle.setEnabled(false);
						buttonStreamToggle.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									// TODO:  Start streaming, then wait till we are running
									
									buttonStreamToggle.setText("Stop Streaming");
									buttonStreamToggle.setEnabled(true);
									buttonStreamToggle.setCursor(Cursor.getDefaultCursor());
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					} else {
						buttonStreamToggle.setSelected(false);
					}
				} else if (event.getActionCommand() == "Stop Streaming") {
					buttonStreamToggle.setEnabled(false);
					buttonStreamToggle.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								// TODO:  Wait till we have stopped streaming
								
								buttonStreamToggle.setText("Start Streaming");
								buttonStreamToggle.setEnabled(true);
								buttonStreamToggle.setCursor(Cursor.getDefaultCursor());
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		buttonStreamToggle.setMargin(new Insets(3,0,3,0));
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
		gbc_scrollpaneRules.fill = GridBagConstraints.BOTH;
		gbc_scrollpaneRules.gridx = 0;
		gbc_scrollpaneRules.gridy = 4;
		gbc_scrollpaneRules.gridheight = GridBagConstraints.REMAINDER;
		panelMenu.add(scrollpaneRules, gbc_scrollpaneRules);
		
		RulesPanel panelRules = new RulesPanel();
		scrollpaneRules.setViewportView(panelRules);
		
		JScrollPane scrollpaneTweetViewer = new JScrollPane();
		scrollpaneTweetViewer.setPreferredSize(new Dimension(1000, 0));
		frame.getContentPane().add(scrollpaneTweetViewer, BorderLayout.CENTER);
		
		JPanel panelTweetViewer = new JPanel();
		scrollpaneTweetViewer.setViewportView(panelTweetViewer);
		
		frame.pack();
		frame.setVisible(true);
	}

}
