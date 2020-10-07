package frontend;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TweetPanel extends JPanel {
	public TweetPanel() {
		super();
		
		// TODO: Implement this
		
		JLabel sampleLabel = new JLabel("TODO:  Tweets go here");
		this.add(sampleLabel);
	}
	
	public TweetPanel(TweetViewerPanel.FakeBackendTweet fromTweet) {
		super();
		// TODO:  Implement this, use real class
		
		JLabel sampleLabel = new JLabel("TODO:  Tweets go here");
		this.add(sampleLabel);
	}
}
