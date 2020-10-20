// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: Written By Jacob Lorenz
//                  POJO represents a filtering rule based on information received from / needed by
//                  Twitter APIs in order to stream filtered Tweet results.

package data.resources;

import java.util.Objects;

/**
 * POJO representing a filter rule that can be posted to Twitter.
 * These rules are the filters that get set when the front end calls the filter stream endpoint.
 */
public class TweetFilterRule {

    /**
     * Core Object Fields
     * Rules are primarily driven by the 'value' field
     * See the following resource for information on building rules:
     * https://developer.twitter.com/en/docs/twitter-api/tweets/filtered-stream/integrate/build-a-rule
     */
    private String id;
    private String value;
    private String tag;

    /**
     * Default Constructor
     */
    public TweetFilterRule() {
        this.id = null;
        this.value = null;
        this.tag = null;
    }

    /**
     * Returns the id associated with this TweetFilterRule
     * @return the string id associated with this TweetFilterRule
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id associated with this TweetFilterRule
     * @param id the String id associated with this TweetFilterRule
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the value associated with this TweetFilterRule
     * @return the string value associated with this TweetFilterRule
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value associated with this TweetFilterRule
     * @param value the String value associated with this TweetFilterRule
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns the tag associated with this TweetFilterRule
     * @return the string tag associated with this TweetFilterRule
     */
    public String getTag() {
        return tag;
    }

    /**
     * Set the tag associated with this TweetFilterRule
     * @param tag the String tag associated with this TweetFilterRule
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TweetFilterRule{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TweetFilterRule that = (TweetFilterRule) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(value, that.value) &&
                Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, tag);
    }
}
