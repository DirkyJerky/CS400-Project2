package frontend;

import frontend.JFilteredTextField.FilterType;

enum RuleType {
	KEYWORD("Keyword", "keyword", FilterType.NORMAL),
	HASHTAG("Hashtag (#)", "#", FilterType.HASHTAG),
	MENTIONS("Mentions user", "@", FilterType.USERNAME),
	FROM_USER("By user", "from:", FilterType.USERNAME),
	TO_USER("To user", "to:", FilterType.USERNAME);
	// TODO:  Add the rest according to https://developer.twitter.com/en/docs/twitter-api/tweets/filtered-stream/integrate/build-a-rule
	
	final String name; // Name of the rule displayed to the user
	final String label; // Name of the rule constructed in the query to twitter
	final FilterType filterType; // The filter to be applied to the Text Field
	RuleType(String name, String label, FilterType filterType) {
		this.name = name;
		this.label = label;
		this.filterType = filterType;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}