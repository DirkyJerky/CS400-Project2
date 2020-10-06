package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class TweetViewerPanel extends JScrollPane {
	JPanel panelTweetViewer;
	
	public TweetViewerPanel() {
		super();
		
		this.setPreferredSize(new Dimension(1000, 0));
		
		panelTweetViewer = new JPanel();
		this.setViewportView(panelTweetViewer);
	}
}
