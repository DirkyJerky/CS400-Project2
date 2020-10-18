package data.resources;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Tweet implements Comparable<Tweet> {

    private String id;
    private String text;
    private String author_id;
    private TwitterUser user;
    private String created_at;
    private String conversation_id;


    public Tweet() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public TwitterUser getUser() {
        return user;
    }

    public void setUser(TwitterUser user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(String conversation_id) {
        this.conversation_id = conversation_id;
    }

    @Override
    public int compareTo(@NotNull Tweet o) {
        System.out.println("In Comparison");
        System.out.println("ID1: " + this.id);
        System.out.println("ID2: " + o.id);
        int returnValue = (int) (Long.parseLong(this.id) - Long.parseLong(o.id));
        System.out.println("Difference: " + returnValue);
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
