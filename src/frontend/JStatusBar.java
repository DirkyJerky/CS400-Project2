package frontend;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.Timer;

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
	
	public void info(String text) {
		this.setText(text);
		this.setBackground(infoColor);

		this.restartTimer(text.length());
	}
	
	public void warn(String text) {
		this.setText(text);
		this.setBackground(warningColor);

		this.restartTimer(text.length());
	}
	
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
