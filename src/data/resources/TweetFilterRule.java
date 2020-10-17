package data.resources;

import java.util.Objects;

public class TweetFilterRule {

    private String id;
    private String value;
    private String tag;

    public TweetFilterRule() {
        this.id = null;
        this.value = null;
        this.tag = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

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
