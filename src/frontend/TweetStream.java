package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TweetStream {

	private JFrame frame;
	private JLabel labelTitle;
	@SuppressWarnings("rawtypes")
	private JComboBox comboboxStreamSelector;
	private JToggleButton buttonStreamToggle;
	private JButton btnClearTweets;
	private JButton buttonGotoN;
	private JFilteredTextField textGotoN;
	private JPlaceholderTextField textFilterText;
	private RulePanel panelRules;
	private JStatusBar labelStatus;
	private TweetViewerPanel panelTweetViewer;
	

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

	public TweetStream() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) // My GUI builder doesn't like parameterized combo boxes
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		
		JPanel panelMenu = new JPanel();
		frame.getContentPane().add(panelMenu, BorderLayout.WEST);
		GridBagLayout gbl_panelMenu = new GridBagLayout();
		gbl_panelMenu.columnWidths = new int[]{145};
		gbl_panelMenu.rowHeights = new int[]{26, 26, 26, 26, 26, 26, 0, 0, 0};
		gbl_panelMenu.columnWeights = new double[]{1.0};
		gbl_panelMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelMenu.setLayout(gbl_panelMenu);
		
		labelTitle = new JLabel("Tweet Stream");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelTitle = new GridBagConstraints();
		gbc_labelTitle.fill = GridBagConstraints.BOTH;
		gbc_labelTitle.insets = new Insets(0, 0, 5, 0);
		gbc_labelTitle.gridx = 0;
		gbc_labelTitle.gridy = 0;
		panelMenu.add(labelTitle, gbc_labelTitle);
		
		comboboxStreamSelector = new JComboBox(StreamSource.values());
		comboboxStreamSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand() == "comboBoxChanged") {
					StreamSource source = (StreamSource) comboboxStreamSelector.getSelectedItem();
					
					switch (source) {
					case FILTERED: {
						panelRules.requestSetEnabled(0b0001, true);
						break;
					}
					case SAMPLE: {
						panelRules.requestSetEnabled(0b0001, false);
						break;
					}
					default: {
						System.err.println("No case statement for StreamSource = " + source + " in combobox logic");
					}
					}
				}
			}
		});
		GridBagConstraints gbc_comboboxStreamSelector = new GridBagConstraints();
		gbc_comboboxStreamSelector.insets = new Insets(0, 0, 5, 0);
		gbc_comboboxStreamSelector.gridx = 0;
		gbc_comboboxStreamSelector.gridy = 1;
		panelMenu.add(comboboxStreamSelector, gbc_comboboxStreamSelector);
		
		String startText = "Start Streaming";
		String stopText = "Stop Streaming";
		buttonStreamToggle = new JToggleButton(startText);
		buttonStreamToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand() == startText) {
					try {
						// TODO Test if the app is ready to go
						
						String rule = (streamNeedsRule()) ? panelRules.buildRule() : ""; // Will throw exception if rule not valid/complete
						
						buttonStreamToggle.setEnabled(false);
						buttonStreamToggle.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						
						comboboxStreamSelector.setEnabled(false);
						panelRules.requestSetEnabled(0b0010, false);
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									// TODO:  Start streaming, then wait till we are running
									// TODO:  Do stuff with this rule
									labelStatus.info("Streaming with rule: " + rule);
									
									buttonStreamToggle.setText(stopText);
									buttonStreamToggle.setEnabled(true);
									buttonStreamToggle.setCursor(Cursor.getDefaultCursor());
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					} catch (IllegalStateException e) {
						buttonStreamToggle.setSelected(false);
						buttonStreamToggle.setEnabled(false);
						
						labelStatus.error(e.getMessage());
						
						panelRules.focusInvalidOperator();
						
						Timer timerToReset = new Timer(3000, (ev) -> buttonStreamToggle.setEnabled(true));
						timerToReset.setRepeats(false);
						timerToReset.start();
					}
				} else if (event.getActionCommand() == stopText) {
					buttonStreamToggle.setEnabled(false);
					buttonStreamToggle.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								// TODO:  Wait till we have stopped streaming
								
								buttonStreamToggle.setText(startText);
								buttonStreamToggle.setEnabled(true);
								buttonStreamToggle.setCursor(Cursor.getDefaultCursor());

								comboboxStreamSelector.setEnabled(true);
								panelRules.requestSetEnabled(0b0010, true);
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
		gbc_buttonStreamToggle.gridy = 2;
		panelMenu.add(buttonStreamToggle, gbc_buttonStreamToggle);
		
		int millisToEnable = 1000;
		int millisToReset = 3000;
		String startingText = "Clear All Tweets";
		String confirmText = "Really clear tweets?";
		btnClearTweets = new JButton(startingText);
		btnClearTweets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand() == startingText) {
					btnClearTweets.setEnabled(false);
					btnClearTweets.setText(confirmText);
					
					Timer timerToEnable = new Timer(millisToEnable, (ev) -> btnClearTweets.setEnabled(true));
					
					timerToEnable.setRepeats(false);
					timerToEnable.start();

					Timer timerToReset = new Timer(millisToReset, (ev) -> btnClearTweets.setText(startingText));

					timerToReset.setRepeats(false);
					timerToReset.start();
				} else if (event.getActionCommand() == confirmText) {
					btnClearTweets.setEnabled(false);
					btnClearTweets.setText(startingText);
					
					// Delay to allow the `timerToReset` above to catch up and not overlap any future timers
					Timer timerToEnable = new Timer(millisToReset - millisToEnable, 
							(ev) -> btnClearTweets.setEnabled(true));
					timerToEnable.setRepeats(false);
					timerToEnable.start();
					
					panelTweetViewer.clearTweets();
				}
			}
		});
		GridBagConstraints gbc_btnClearTweets = new GridBagConstraints();
		gbc_btnClearTweets.fill = GridBagConstraints.BOTH;
		gbc_btnClearTweets.insets = new Insets(0, 0, 5, 0);
		gbc_btnClearTweets.gridx = 0;
		gbc_btnClearTweets.gridy = 3;
		panelMenu.add(btnClearTweets, gbc_btnClearTweets);
		
		JPanel panelGotoN = new JPanel();
		GridBagConstraints gbc_panelGotoN = new GridBagConstraints();
		gbc_panelGotoN.fill = GridBagConstraints.BOTH;
		gbc_panelGotoN.insets = new Insets(0, 0, 5, 0);
		gbc_panelGotoN.gridx = 0;
		gbc_panelGotoN.gridy = 4;
		panelMenu.add(panelGotoN, gbc_panelGotoN);
		panelGotoN.setLayout(new BorderLayout(0, 0));
		
		buttonGotoN = new JButton("Goto N");
		buttonGotoN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand() != "Goto N") {
					return;
				}
				
				if (textGotoN.isFieldValid()) {
					try {
						panelTweetViewer.gotoN(Integer.parseInt(textGotoN.getText()));
					} catch (NumberFormatException e) {
						System.err.println("textGotoN invalid int after isFieldValid() true (this shouldnt ever happen)");
					}
				} else {
					textGotoN.validateField();
					textGotoN.requestFocus();
				}
			}
		});
		panelGotoN.add(buttonGotoN, BorderLayout.WEST);
		
		textGotoN = new JFilteredTextField(JFilteredTextField.FilterType.NUMERICAL);
		panelGotoN.add(textGotoN, BorderLayout.CENTER);
		
		textFilterText = new JPlaceholderTextField();
		textFilterText.setPlaceholder("Search");
		GridBagConstraints gbc_textFilterText = new GridBagConstraints();
		gbc_textFilterText.fill = GridBagConstraints.BOTH;
		gbc_textFilterText.insets = new Insets(0, 0, 5, 0);
		gbc_textFilterText.gridx = 0;
		gbc_textFilterText.gridy = 5;
		panelMenu.add(textFilterText, gbc_textFilterText);
		
		JScrollPane scrollpaneRules = new JScrollPane();
		scrollpaneRules.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
		GridBagConstraints gbc_scrollpaneRules = new GridBagConstraints();
		gbc_scrollpaneRules.insets = new Insets(0, 0, 5, 0);
		gbc_scrollpaneRules.fill = GridBagConstraints.BOTH;
		gbc_scrollpaneRules.gridx = 0;
		gbc_scrollpaneRules.gridy = 6;
		gbc_scrollpaneRules.gridheight = GridBagConstraints.REMAINDER;
		panelMenu.add(scrollpaneRules, gbc_scrollpaneRules);
		
		panelRules = new RulePanel();
		scrollpaneRules.setViewportView(panelRules);
		
		JPanel rightsidePanel = new JPanel();
		rightsidePanel.setLayout(new BorderLayout());
		frame.getContentPane().add(rightsidePanel, BorderLayout.CENTER);
		
		labelStatus = new JStatusBar();
		rightsidePanel.add(labelStatus, BorderLayout.NORTH);
		
		JScrollPane scrollpaneTweetViewer = new JScrollPane();
		rightsidePanel.add(scrollpaneTweetViewer, BorderLayout.CENTER);
		
		panelTweetViewer = new TweetViewerPanel();
		scrollpaneTweetViewer.setViewportView(panelTweetViewer);

		panelMenu.setPreferredSize(new Dimension(250, 500));
		rightsidePanel.setPreferredSize(new Dimension(1000, 500));
		labelStatus.setPreferredSize(new Dimension(0, 25));
		frame.pack();
	}
	
	private boolean streamNeedsRule() {
		return (StreamSource) this.comboboxStreamSelector.getSelectedItem() == StreamSource.FILTERED;
	}

}
