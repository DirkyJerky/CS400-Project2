// --== CS400 File Header Information ==--
// Name: Mason Dorgan
// Email: msdorgan@wisc.edu
// Team: BD
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.*;
import data.resources.Tweet;
import data.resources.TweetFilterRule;
import data.resources.TwitterUser;


/**
 * Tests the data resources
 * 
 * @author Mason
 *
 */
public class ResourcesTest {

  /**
   * test Tweet.java
   */
  @Test
  public void testTweet() {
    // test the getters and setters
    Tweet test = new Tweet();
    TwitterUser mason = new TwitterUser();

    test.setUser(mason);
    if (!test.getUser().equals(mason))
      fail("wrong user");

    test.setId("1");
    if (!test.getId().contains("1"))
      fail("wrong id");

    test.setText("test text");
    if (!test.getText().contains("test text"))
      fail("wrong text");

    test.setAuthor_id("mason");
    if (!test.getAuthor_id().contains("mason"))
      fail("wrong author");

    test.setConversation_id("test convo");
    if (!test.getConversation_id().contains("test convo"))
      fail("wrong conversation");

    test.setCreated_at("time");
    if (!test.getCreated_at().contains("time"))
      fail("wrong created at string");

    // test toString() method
    assertEquals("Tweet{" + "id='" + test.getId() + '\'' + ", text='" + test.getText() + '\''
        + ", author_id='" + test.getAuthor_id() + '\'' + ", user=" + test.getUser()
        + ", created_at='" + test.getCreated_at() + '\'' + ", conversation_id='"
        + test.getConversation_id() + '\'' + '}', test.toString());

  }

  /**
   * test TweetFilterRule.java
   */
  @Test
  public void testTweetFilterRule() {
    TweetFilterRule test = new TweetFilterRule();
    // test setters and getters
    test.setId("1");
    if (!test.getId().contains("1"))
      fail("FilterRule: wrong id");

    test.setValue("2");
    if (!test.getValue().contains("2"))
      fail("FilterRule: wrong value");

    test.setTag("3");
    if (!test.getTag().contains("3"))
      fail("FilterRule: wrong tag");

    // test toString() method
    assertEquals("TweetFilterRule{" + "id='" + test.getId() + '\'' + ", value='" + test.getValue()
        + '\'' + ", tag='" + test.getTag() + '\'' + '}', test.toString());

  }

  /**
   * test TestTwitterUser.java
   */
  @Test
  public void testTwitterUser() {
    // test the setters and getters
    TwitterUser test = new TwitterUser();

    test.setId("1");
    if (!test.getId().contains("1"))
      fail("TwitterUser: wrong id");

    test.setName("mason");
    if (!test.getName().contains("mason"))
      fail("TwitterUser: wrong name");

    test.setUsername("test");
    if (!test.getUsername().contains("test"))
      fail("TwitterUser: wrong username");

    // test toString() method
    assertEquals("TwitterUser {" + "id='" + test.getId() + '\'' + ", name='" + test.getName() + '\''
        + ", username='" + test.getUsername() + '\'' + '}', test.toString());
  }

}
