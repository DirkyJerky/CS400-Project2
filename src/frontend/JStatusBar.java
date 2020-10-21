// --== CS400 File Header Information ==--
// Name: Geoff Yoerger
// Email: giyoerger@wisc.edu
// Team: BD
// Role: Frontend
// TA: Bri Cochran
// Lecturer: Florian Heimerl
package frontend;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * A JLabel with methods for setting the text at varying levels of error (error, warn, info)
 * The text is displayed for a time period proportional to its length, then the label is cleared.
 *
 */
@SuppressWarnings("serial")
public class JStatusBar extends JLabel {
	static Color infoColor = new Color(225, 225, 225);
	static Color warningColor = new Color(225, 225, 150);
	static Color errorColor = new Color(255,200,225);
	
	static int RESET_DELAY_PER_CHAR = 50;
	
	Timer timerToReset;
	Color defaultColor; // 
	public JStatusBar() {
		this.setOpaque(true); // So background colors work
		this.defaultColor = this.getBackground();
		
		timerToReset = new Timer(0, (e) -> { setText(""); setBackground(defaultColor); });
		timerToReset.setRepeats(false);
	}
	
	/**
	 * @param text Text to be displayed at info level (minimal background)
	 */
	public void info(String text) {
		this.setText(text);
		this.setBackground(infoColor);

		this.restartTimer(text.length());
	}
	
	/**
	 * @param text Text to be displayed at warning level (light yellow background)
	 */
	public void warn(String text) {
		this.setText(text);
		this.setBackground(warningColor);

		this.restartTimer(text.length());
	}
	
	/**
	 * @param text Text to be displayed at error level (light red background)
	 */
	public void error(String text) {
		this.setText(text);
		this.setBackground(errorColor);
		
		this.restartTimer(text.length());
	}
	
	private void restartTimer(int numChars) {
		this.timerToReset.setInitialDelay(numChars * RESET_DELAY_PER_CHAR);
		this.timerToReset.restart();
	}
}
