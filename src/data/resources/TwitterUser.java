// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: Written By Jacob Lorenz
//                  POJO that represents a Twitter user within the app. This User representation is a simplified version of
//                  the User objects returned by Twitter in API responses.

package data.resources;

import java.util.Objects;

/**
 * POJO representing an User of Twitter.
 * These TwitterUser objects will be created and attached to every Tweet; would allow for analytics in a more advanced app.
 */
public class TwitterUser {

    /**
     * Core Object Fields
     * TwitterUsers are typically requested by their username
     */
    private String id;
    private String name;
    private String username;

    /**
     * Returns the id associated with this TwitterUser
     * @return the string id associated with this TwitterUser
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id associated with this TwitterUser
     * @param id the String id associated with this TwitterUser
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name associated with this TwitterUser
     * @return the string name associated with this TwitterUser
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name associated with this TwitterUser
     * @param name the String name associated with this TwitterUser
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the username associated with this TwitterUser
     * @return the string username associated with this TwitterUser
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username associated with this TwitterUser
     * @param username the String username associated with this TwitterUser
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TwitterUser {" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwitterUser that = (TwitterUser) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username);
    }
}
