// --== CS400 File Header Information ==--
// Name: Geoff Yoerger
// Email: giyoerger@wisc.edu
// Team: BD
// Role: Frontend
// TA: Bri Cochran
// Lecturer: Florian Heimerl
package frontend;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import RBTree.RedBlackTree;
import data.resources.Tweet;

import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of TweetPanels, with methods for retrieval and scrolling to specific indexed tweets.
 * The amount of tweets that can be contained within will dynamically change on resize.
 */
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
	
	/**
	 * Setup all the internal TweetPanels according to our current size.
	 * 
	 * Run after the layout manager has packed the component, or after resize
	 */
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
	
	/**
	 * Readd all the tweet panels in order.
	 */
	private void resyncTweetPanels() {
		this.removeAll();
		
		for (TweetPanel tweetPanel : this.tweetPanels) {
			this.add(tweetPanel, relativeGBC);
		}
		
		this.add(this.fillerComp, this.gbc_fillerComp);
		
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Clear all visible tweets.
	 */
	public void clearTweets() {
		this.tweetPanels.forEach((tweetPanel) -> tweetPanel.setTweetObj(null));
		this.scrollIndex = -1;
	}
	
	/**
	 * Set the set of tweets being viewed to the list that starts at the index `n`
	 * If `n` is negative, index from the end of the list instead (-1 is the newest set of tweets)
	 * @param n The new index to scroll this viewer to.
	 */
	public void gotoN(int n) {
		
		int numTweetsRecieved = TweetStream.sessionController.getSizeOfOnePercentTweetTree(); // TODO:  Get from backend
		
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
		
//		System.err.println("Going to Nth tweet: n=" + n);
		
		int numToGet = this.tweetPanels.size();
		
		if (n == numTweetsRecieved - 1) {
			n -= numToGet - 1;
		}
		
		RedBlackTree<Tweet> tweetTree = TweetStream.sessionController.getCopyOfOnePercentTweetTree();
		
		for (int i = 0; i < numToGet; i += 1) {
			Tweet tweet = getIndexedTweetFromTree(tweetTree.root, i + n);
			
			this.tweetPanels.get(i).setTweetObj(tweet);
		}
		
		this.repaint();
		
		// TODO Get tweets from index `n`, number_to_get = this.tweetPanels.size()
		//      If n == numTweetsRecieved - 1 (getting last tweet), get the newest tweets instead
		//      For i = 0..number_to_get
		//          this.tweetPanels.get(i).setTweetObj(tweets.get(i));
		//      this.repaint();
	}

	private static Tweet getIndexedTweetFromTree(RedBlackTree.Node<Tweet> tweetTree, int i) {
		if (tweetTree == null || tweetTree.data == null) {
			return null;
		}
		
		int cmp = tweetTree.data.getTreeIndex() - i;
		
		if (cmp == 0) {
			return tweetTree.data;
		} else if (cmp > 0 ) {
			return getIndexedTweetFromTree(tweetTree.leftChild, i);
		} else {
			return getIndexedTweetFromTree(tweetTree.rightChild, i);
		}
	}
}
