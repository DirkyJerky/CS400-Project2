package frontend;

import java.awt.Component;

import javax.swing.JTextField;

import frontend.JFilteredTextField.FilterType;

enum RuleType {
	KEYWORD("Keyword", "keyword", new JTextField()),
	HASHTAG("Hashtag (#)", "#", new JFilteredTextField(FilterType.HASHTAG)),
	MENTIONS("Mentions user", "@", new JFilteredTextField(FilterType.USERNAME)),
	FROM_USER("By user", "from:", new JFilteredTextField(FilterType.USERNAME)),
	TO_USER("To user", "to:", new JFilteredTextField(FilterType.USERNAME));
	// TODO:  Add the rest according to https://developer.twitter.com/en/docs/twitter-api/tweets/filtered-stream/integrate/build-a-rule
	
	private final String name; // Name of the rule displayed to the user
	private final String label; // Name of the rule constructed in the query to twitter
	private final Component auxComponent; // The component to be displayed when this rule is selected in the dropdown
	RuleType(String name, String label, Component auxillaryComponent) {
		this.name = name;
		this.label = label;
		this.auxComponent = auxillaryComponent;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}