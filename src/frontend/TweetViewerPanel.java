package frontend;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
	
	List<TweetPanel> tweetPanels;
	
	// The index of the tweet that is currently at the top of the viewer. -1 = viewer uninitialized
	int scrollIndex;
	
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
		
		this.tweetPanels = null;
		this.scrollIndex = -1;
		
		this.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				populateTweetPanels();
			}
			
			public void componentHidden(ComponentEvent arg0) {}
			public void componentMoved(ComponentEvent arg0) {}
			public void componentShown(ComponentEvent arg0) {}
		});
	}
	
	// Run after the layout manager has packed the component, or after resize
	public void populateTweetPanels() {
		int thisHeight = this.getParent().getSize().height;
		
		// Fuzz the number of panels down a bit so the vertical scrollbar never appears.
		int numPanels = Math.max((thisHeight - TweetPanel.PREFERRED_HEIGHT/2)/ TweetPanel.PREFERRED_HEIGHT, 0);
		
		this.tweetPanels = new ArrayList<>(numPanels);
		for (int i = 0; i < numPanels; i += 1) {
			this.tweetPanels.add(new TweetPanel());
		}
		
		this.resyncTweetPanels();
		
		// Repopulate the content of each panel if it was previously populated
		if (this.scrollIndex >= 0) {
			this.gotoN(this.scrollIndex);
		}
	}
	
	private void resyncTweetPanels() {
		this.removeAll();
		
		for (TweetPanel tweetPanel : this.tweetPanels) {
			this.add(tweetPanel, relativeGBC);
		}
		
		this.add(this.fillerComp, this.gbc_fillerComp);
		
		this.revalidate();
		this.repaint();
	}
	
	public void clearTweets() {
		this.tweetPanels.forEach((tweetPanel) -> tweetPanel.setTweetObj(null));
		this.scrollIndex = -1;
	}
	
	// Scroll to the Nth tweet
	// If n = -1, shortcut to get the newest tweets instead
	public void gotoN(int n) {
		
		int numTweetsRecieved = 0; // TODO:  Get from backend
		
		// If n is negative, make it index from the end of the list of tweets.
		if (n < 0) {
			n += numTweetsRecieved;
		}
		
		if (n < 0) {
			throw new IllegalStateException(
					"Attempted to goto tweet from the end #" + Math.abs(n - numTweetsRecieved) + ", but only " + numTweetsRecieved + " tweets have been recieved so far.");
		}
		
		if (n >= numTweetsRecieved) {
			throw new IllegalStateException(
					"Attempted to goto tweet #" + n + ", but only " + numTweetsRecieved + " tweets have been recieved so far.");
		}
		
		this.scrollIndex = n;
		
		System.err.println("Going to Nth tweet: n=" + n);
		
		// TODO Get tweets from index `n`, number_to_get = this.tweetPanels.size()
		//      If n == numTweetsRecieved - 1 (getting last tweet), get the newest tweets instead
		//      For i = 0..number_to_get
		//          this.tweetPanels.get(i).setTweetObj(tweets.get(i));
		//      this.repaint();
	}
}
