// --== CS400 File Header Information ==--
// Name: Geoff Yoerger
// Email: giyoerger@wisc.edu
// Team: BD
// Role: Frontend
// TA: Bri Cochran
// Lecturer: Florian Heimerl
package frontend;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

// Sourced from https://stackoverflow.com/questions/16213836
@SuppressWarnings("serial")
public class JPlaceholderTextField extends JTextField {
	private String placeholder;

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	
    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
            .getMaxAscent() + getInsets().top);
    }
}
