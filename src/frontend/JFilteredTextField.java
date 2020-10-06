package frontend;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class JFilteredTextField extends JTextField {
	public enum FilterType {
		NUMERICAL, // Number, positive or negative
		NORMAL, // No restrictions
		EMOJI, // One emoji
		HASHTAG, // No all numbers;  (i18n) Letters, numbers, or underscore
		USERNAME, // 15 character limit;  A-Z, a-z, 0-9, _
		// TODO:  Add the rest of the filters
	}
	
	FilterType filterType;

	public JFilteredTextField(FilterType filterType) {
		super();
		
		this.filterType = filterType;
	}

	public FilterType getFilterType() {
		return this.filterType;
	}

	public void updateFilterType(FilterType filterType) {
		this.filterType = filterType;
		
		// TODO:  Revalidate according to new filter type
	}
	
	// TODO:  Document listeners to only allow specified data.
}
