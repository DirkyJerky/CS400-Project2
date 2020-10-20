// --== CS400 File Header Information ==--
// Name: Geoff Yoerger
// Email: giyoerger@wisc.edu
// Team: BD
// Role: Frontend
// TA: Bri Cochran
// Lecturer: Florian Heimerl
package frontend;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TweetPanel extends JPanel {
	// TODO:  Use the real class to interact with rest of application
	public static class BackendTweet {
		BackendTweet() {}
	}
	
	static int PREFERRED_HEIGHT = 15;
	static int PREFERRED_WIDTH = 990;
	
	
	static final GridBagConstraints relativeGBC = new GridBagConstraints();
	static final GridBagConstraints fillGBC = new GridBagConstraints();
	static {
		relativeGBC.gridx = GridBagConstraints.RELATIVE;
		relativeGBC.gridy = 0;
		relativeGBC.anchor = GridBagConstraints.NORTHWEST;
		relativeGBC.fill = GridBagConstraints.HORIZONTAL;

		fillGBC.gridx = GridBagConstraints.RELATIVE;
		fillGBC.gridy = 0;
		fillGBC.anchor = GridBagConstraints.NORTHWEST;
		fillGBC.fill = GridBagConstraints.BOTH;
	}
	
	BackendTweet tweetObj;
	
	JTextField usernameField;
	JTextField tweetField;

	public TweetPanel() {
		this(null);
	}
	
	public TweetPanel(BackendTweet fromTweet) {
		super();

		this.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 150, 15, 0};
//		gridBagLayout.rowHeights = new int[]{1, 19, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0};
//		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gridBagLayout);
		
		this.add(new JLabel("@"), relativeGBC);
		
		this.usernameField = new JTextField();
		this.usernameField.setEditable(false);
		this.add(usernameField, relativeGBC);
		
		this.add(new JLabel(":"), relativeGBC);
		
		this.tweetField = new JTextField();
		this.tweetField.setEditable(false);
		this.add(tweetField, fillGBC);
		
		this.setTweetObj(fromTweet);
	}

	
	public BackendTweet getTweetObj() {
		return tweetObj;
	}

	public void setTweetObj(BackendTweet tweetObj) {
		this.tweetObj = tweetObj;
//		boolean objIsValid = this.tweetObj != null;
		boolean objIsValid = true; // TODO:  Replace with above line
		
		for (Component comp : this.getComponents()) {
			comp.setVisible(objIsValid);
		}
		
		if (objIsValid) {
			// TODO: Set usernameField, tweetField according to this.tweetObj
		}
		
		this.repaint();
	}
}
