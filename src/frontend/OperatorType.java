package frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import frontend.JFilteredTextField.FilterType;

// TODO:  Extend from/replace with what the other application creators provide.
enum OperatorType {
	KEYWORD("Keyword", "", FilterType.NORMAL),
	HASHTAG("Hashtag (#)", "#", FilterType.HASHTAG),
	MENTIONS("Mentions user", "@", FilterType.USERNAME),
	FROM_USER("By user", "from:", FilterType.USERNAME),
	TO_USER("To user", "to:", FilterType.USERNAME);
	// TODO:  Add the rest according to https://developer.twitter.com/en/docs/twitter-api/tweets/filtered-stream/integrate/build-a-rule
	
	final String name; // Name of the rule displayed to the user
	final String label; // Name of the rule constructed in the query to twitter
	final FilterType filterType; // The filter to be applied to the Text Field
	OperatorType(String name, String label, FilterType filterType) {
		this.name = name;
		this.label = label;
		this.filterType = filterType;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
	// State info, 1 line
	// this.name
	
	public void writeState(PrintWriter writer) {
		writer.println(this.name);
	}
	
	public static OperatorType readState(BufferedReader reader) throws IOException {
		String state = reader.readLine();
		
		for (OperatorType opType : OperatorType.values()) {
			if (opType.name.contentEquals(state)) {
				return opType;
			}
		}
		
		throw new IOException("Invalid OperatorType in readState(): " + state);
	}
}