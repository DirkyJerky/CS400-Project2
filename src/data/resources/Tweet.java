// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: Written by Jacob Lorenz
//                  The app's representation of a Tweet, with reduced fields compared with the natural Tweet objects from Twitter.

package data.resources;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * POJO used as the 'data' of our Red Black Trees. A Tweet contains a subset of the data offered as a part of the Twitter
 * API. For more informatioon on fields associated with a Tweet, or if you would like to expand the field selection, you
 * can reference the following:
 * https://developer.twitter.com/en/docs/twitter-api/data-dictionary/object-model/tweet
 */
public class Tweet implements Comparable<Tweet> {

    /**
     * Core Object Fields
     * Red Black Trees are ordered according to the Tweet ID (id)
     */
    private String id;
    private String text;
    private String author_id;
    private TwitterUser user;
    private String created_at;
    private String conversation_id;
    private int treeIndex;

    /**
     * Default Constructor
     */
    public Tweet() {
        this.id = null;
        this.text = null;
        this.author_id = null;
        this.user = null;
        this.created_at = null;
        this.conversation_id = null;
        this.treeIndex = -1;
    }

    /**
     * Returns the id associated with this Tweet
     * @return the string id associated with this Tweet
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id associated with this Tweet
     * @param id the String id associated with this Tweet
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the text associated with this Tweet
     * @return the string text associated with this Tweet
     */
    public String getText() {
        return text;
    }

    /**
     * Set the text associated with this Tweet
     * @param text the String text associated with this Tweet
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns the author_id associated with this Tweet
     * @return the string author_id associated with this Tweet
     */
    public String getAuthor_id() {
        return author_id;
    }

    /**
     * Set the author_id associated with this Tweet
     * @param author_id the String author_id associated with this Tweet
     */
    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    /**
     * Returns the TwitterUser associated with this Tweet
     * @return the TwitterUser user associated with this Tweet
     */
    public TwitterUser getUser() {
        return user;
    }

    /**
     * Set the TwitterUser associated with this Tweet
     * @param user the TwitterUser user associated with this Tweet
     */
    public void setUser(TwitterUser user) {
        this.user = user;
    }

    /**
     * Returns the created_at date-string associated with this Tweet
     * @return the created_at date-string associated with this Tweet
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * Set the created_at date-string associated with this Tweet
     * @param created_at the String created_at date-string associated with this Tweet
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     * Returns the conversation_id associated with this Tweet
     * @return the string conversation_id associated with this Tweet
     */
    public String getConversation_id() {
        return conversation_id;
    }

    /**
     * Set the conversation_id associated with this Tweet
     * @param conversation_id the String conversation_id associated with this Tweet
     */
    public void setConversation_id(String conversation_id) {
        this.conversation_id = conversation_id;
    }

    /**
     * Returns the index of this Tweet within its respective tree
     * @return the int index of the Tweet in the Red Black Tree
     */
    public int getTreeIndex() {
        return treeIndex;
    }

    /**
     * Set the index of this Tweet within its respective tree
     * @param treeIndex the int index of the Tweet in the Red Black Tree
     */
    public void setTreeIndex(int treeIndex) {
        this.treeIndex = treeIndex;
    }

    /**
     * Returns an integer corresponding to the relationship between the Tweet's Tree Indices:
     *
     * -N if the comparing integer is less than the Tweet being compared
     * +N if the comparing integer is greater than the Tweet being compared
     * 0 if the Tweets are equal in key
     * @param o the Tweet being compared
     * @return an integer corresponding to the relationship of the Tweets as described above
     */
    @Override
    public int compareTo(@NotNull Tweet o) {
        int returnValue = this.treeIndex - o.treeIndex;
        return returnValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return Objects.equals(id, tweet.id) &&
                Objects.equals(text, tweet.text) &&
                Objects.equals(author_id, tweet.author_id) &&
                Objects.equals(user, tweet.user) &&
                Objects.equals(created_at, tweet.created_at) &&
                Objects.equals(conversation_id, tweet.conversation_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, author_id, user, created_at, conversation_id);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", author_id='" + author_id + '\'' +
                ", user=" + user +
                ", created_at='" + created_at + '\'' +
                ", conversation_id='" + conversation_id + '\'' +
                '}';
    }
}
