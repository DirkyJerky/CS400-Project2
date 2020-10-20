// --== CS400 File Header Information ==--
// Name: Geoff Yoerger
// Email: giyoerger@wisc.edu
// Team: BD
// Role: Frontend
// TA: Bri Cochran
// Lecturer: Florian Heimerl
package frontend;

import java.awt.EventQueue;

public class Main {
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
}
