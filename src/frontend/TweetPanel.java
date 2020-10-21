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

import data.resources.Tweet;

/**
 * A JPanel used for displaying a single tweet.
 * Displays the username, and tweet contents in uneditable text fields.
 */
@SuppressWarnings("serial")
public class TweetPanel extends JPanel {
	
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
	
	Tweet tweetObj;
	
	JTextField usernameField;
	JTextField tweetField;

	public TweetPanel() {
		this(null);
	}
	
	public TweetPanel(Tweet fromTweet) {
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

	
	public Tweet getTweetObj() {
		return tweetObj;
	}

	/**
	 * @param tweetObj The tweet object to associate with this panel
	 * If tweetObj is null, the panel will make itself invisible.
	 */
	public void setTweetObj(Tweet tweetObj) {
		this.tweetObj = tweetObj;
		boolean objIsValid = this.tweetObj != null;
		
		for (Component comp : this.getComponents()) {
			comp.setVisible(objIsValid);
		}
		
		if (objIsValid) {
			this.usernameField.setText(this.tweetObj.getUser().getName());
			this.tweetField.setText(this.tweetObj.getText());
		}
		
		this.repaint();
	}
}
