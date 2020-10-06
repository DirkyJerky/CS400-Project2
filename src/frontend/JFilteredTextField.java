package frontend;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class JFilteredTextField extends JTextField {
	public enum FilterType {
		EMOJI, // Allows up to one emoji
		HASHTAG, // No all numbers;  (i18n) Letters, numbers, or underscore
		USERNAME, // 15 character limit;  A-Z, a-z, 0-9, _
		// TODO:  Add the rest of the filters
	}
	
	FilterType filterType;
	
	public JFilteredTextField(FilterType filterType) {
		super();
		
		this.filterType = filterType;
	}
	
	// TODO:  Documents listeners to only allow specified data.
}
