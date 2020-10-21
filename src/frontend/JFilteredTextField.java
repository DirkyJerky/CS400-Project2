// --== CS400 File Header Information ==--
// Name: Geoff Yoerger
// Email: giyoerger@wisc.edu
// Team: BD
// Role: Frontend
// TA: Bri Cochran
// Lecturer: Florian Heimerl
package frontend;

import java.awt.Color;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 * An extended functionality JTextField that implements filtering on what can be inputted
 * 
 * Each instance has a corresponding FilterType, which will restrict what can be entered, and provide
 * visual feedback when the current contents of the field are not valid according to the filter.
 */
@SuppressWarnings("serial")
public class JFilteredTextField extends JTextField {
	// See comments for the particular filter details
	public enum FilterType {
		NUMERICAL, // Number, positive or negative
		NORMAL, // No restrictions
		HASHTAG, // Not all numbers;  (i18n) Letters, numbers, or underscore
		USERNAME; // 15 character limit;  A-Z, a-z, 0-9, _
		// TODO:  Add the rest of the filters

		static Pattern usernamePattern = Pattern.compile("[A-Za-z0-9_]{1,15}");
		static Pattern hashtagPattern_pos = Pattern.compile("[\\p{IsAlphabetic}0-9_]+");
		static Pattern hashtagPattern_neg = Pattern.compile("[0-9]+");
		
		public boolean test(String string) {
			switch(this) {
			
			case NUMERICAL: {
				try {
					Integer.parseInt(string);
					return true;
				} catch (NumberFormatException e) {
					return false;
				}
			}
			case NORMAL: {
				return true;
			}
			case HASHTAG: {
				return hashtagPattern_pos.matcher(string).matches() && ! hashtagPattern_neg.matcher(string).matches();
			}
			case USERNAME: {
				return usernamePattern.matcher(string).matches();
			}
			default: {
				System.err.println("unimplemented filter for " + this);
				return true;
			}
			
			}
		}

		static Pattern numericalEnterable = Pattern.compile("[\\-0-9]+");
		static Pattern usernameEnterable = Pattern.compile("[A-Za-z0-9_]+");
		static Pattern hashtagEnterable = Pattern.compile("[\\p{IsAlphabetic}0-9_]+");
		
		boolean testEnterable(String text) {
			switch(this) {
			
			case NUMERICAL: {
				return numericalEnterable.matcher(text).matches();
			}
			case NORMAL: {
				return true;
			}
			case HASHTAG: {
				return hashtagEnterable.matcher(text).matches();
			}
			case USERNAME: {
				return usernameEnterable.matcher(text).matches();
			}
			default: {
				System.err.println("unimplemented filter for " + this);
				return true;
			}
			
			}
		}
	}
	
	static Color errorColor = new Color(255,200,225);
	
	FilterType filterType;
	CustomFilter docFilter;

	/**
	 * @param filterType The type of filter to use for this text field
	 */
	public JFilteredTextField(FilterType filterType) {
		super();
		
		this.filterType = filterType;
		this.docFilter = new CustomFilter(this.getFilterType());
		
		PlainDocument doc = (PlainDocument) this.getDocument();
		doc.setDocumentFilter(this.docFilter);
	}

	/**
	 * @return The fields current filter type
	 */
	public FilterType getFilterType() {
		return this.filterType;
	}

	/**
	 * Update the filter type, and revalidate the field.
	 * @param filterType The new filter type
	 */
	public void updateFilterType(FilterType filterType) {
		this.filterType = filterType;
		
		this.docFilter.updateFilter(filterType);
		
		this.validateField();
	}
	
	/**
	 * @return If the field's contents are valid according to the filter.
	 */
	public boolean isFieldValid() {
		return this.getText() != null 
				&& this.getText().length() > 0 
				&& this.filterType.test(this.getText());
	}
	
	/**
	 * Hints the field to give visual feedback if the current contents are invalid
	 */
	public void validateField() {
		if (this.isFieldValid() || !this.isEnabled()) {
			this.setBackground(Color.WHITE);
		} else {
			this.setBackground(errorColor); // Very light red
		}
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		
		this.validateField();
	}
	
	
	// Based on https://stackoverflow.com/questions/11093326
	class CustomFilter extends DocumentFilter {
		FilterType filterType;
		public CustomFilter(FilterType filterType) {
			super();
			this.filterType = filterType;
		}
		
		public void updateFilter(FilterType filterType) {
			this.filterType = filterType;
		}

		@Override
		public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {

			Document doc = fb.getDocument();
			StringBuilder sb = new StringBuilder();
			sb.append(doc.getText(0, doc.getLength()));
			sb.insert(offset, text);

			if (this.filterType.testEnterable(text)) {
				super.insertString(fb, offset, text, attr);
			}
			
			validateField();
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

			Document doc = fb.getDocument();
			StringBuilder sb = new StringBuilder();
			sb.append(doc.getText(0, doc.getLength()));
			sb.replace(offset, offset + length, text);

			if (this.filterType.testEnterable(text)) {
				super.replace(fb, offset, length, text, attrs);
			}

			validateField();
		}

		@Override
		public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
			super.remove(fb, offset, length);
			
//			Document doc = fb.getDocument();
//			StringBuilder sb = new StringBuilder();
//			sb.append(doc.getText(0, doc.getLength()));
//			sb.delete(offset, offset + length);

			validateField();
		}
	}
}
