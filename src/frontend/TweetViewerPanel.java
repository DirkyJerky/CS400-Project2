package frontend;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class TweetViewerPanel extends JPanel {
	JPanel fillerComp;
	GridBagConstraints gbc_fillerComp;
	
	static final GridBagConstraints relativeGBC = new GridBagConstraints();
	static {
		relativeGBC.gridx = 0;
		relativeGBC.gridy = GridBagConstraints.RELATIVE;
		relativeGBC.anchor = GridBagConstraints.NORTH;
		relativeGBC.fill = GridBagConstraints.HORIZONTAL;
		relativeGBC.weighty = 0.0;
	}
	
	public TweetViewerPanel() {
		super();
		
		this.setLayout(new GridBagLayout());
		
		fillerComp = new JPanel();
		gbc_fillerComp = new GridBagConstraints();
		gbc_fillerComp.gridx = 0;
		gbc_fillerComp.gridy = GridBagConstraints.RELATIVE;
		gbc_fillerComp.anchor = GridBagConstraints.NORTH;
		gbc_fillerComp.weighty = 1.0;
		gbc_fillerComp.fill = GridBagConstraints.BOTH;
		
//		this.add(new TweetPanel(), relativeGBC);
		// TODO:  Remove this
		List<FakeBackendTweet> tweets = new ArrayList<>(2);
		tweets.add(new FakeBackendTweet());
		tweets.add(new FakeBackendTweet());
		this.addTweets(tweets);
	}
	

	
	// TODO:  Use the real class to interact with rest of application
	public class FakeBackendTweet {
		private FakeBackendTweet() {}
	}
	
	public void addTweets(List<FakeBackendTweet> tweets) {
		this.remove(this.fillerComp);
		for (FakeBackendTweet tweet : tweets) {
			this.add(new TweetPanel(tweet), relativeGBC);
		}
		this.add(this.fillerComp, gbc_fillerComp);
		
		this.revalidate();
		this.repaint();
	}
	
	public void clearTweets() {
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
}
