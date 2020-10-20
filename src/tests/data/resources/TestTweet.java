package test.data.resources;

import data.resources.Tweet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;


public class TestTweet {
	
	@Test
	void testTweetObject() {
		Tweet t = new Tweet();
		
		Tweet t2 = new Tweet();
		t2.setAuthor_id("Bob");
		t2.setConversation_id("Hello");
		t2.setCreated_at("23");
		t2.setId("1234");
		t2.setText("Eating Bagels");
		// test setUser(User) separately
		
		// note: toString() is missing '' around the user object
		// toString() null and with information
		assertEquals("Tweet{id='null', text='null', author_id='null', " + 
				"user=null, created_at='null', conversation_id='null'}", t.toString());
		assertEquals("Tweet{id='1234', text='Eating Bagels', author_id='Bob', " + 
				"user=null, created_at='23', conversation_id='Hello'}", t2.toString());
		
		// getters (null), then setters
		assertEquals("Tweet{" +
                "id='" + t.getId() + '\'' +
                ", text='" + t.getText() + '\'' +
                ", author_id='" + t.getAuthor_id() + '\'' +
                ", user=" + t.getUser() +
                ", created_at='" + t.getCreated_at() + '\'' +
                ", conversation_id='" + t.getConversation_id() + '\'' +
                '}', t.toString());
		assertEquals("Tweet{" +
                "id='" + t2.getId() + '\'' +
                ", text='" + t2.getText() + '\'' +
                ", author_id='" + t2.getAuthor_id() + '\'' +
                ", user=" + t2.getUser() +
                ", created_at='" + t2.getCreated_at() + '\'' +
                ", conversation_id='" + t2.getConversation_id() + '\'' +
                '}', t2.toString());
	
	}
	
	@Test
	void testTweetCompareTo() {
		Tweet t = new Tweet();
		Tweet t2 = new Tweet();
		Tweet t3 = new Tweet();
		Tweet t4 = new Tweet();
		
		t.setId("1234");
		t2.setId("4321");
		t4.setId("Hello");
		
		int difference = 3087;
		assertEquals(difference, t2.compareTo(t));
		difference = -3087;
		assertEquals(difference, t.compareTo(t2));
		difference = 0;
		assertEquals(difference, t.compareTo(t));
		try {
			t4.compareTo(t);
			fail("Incorrect ID format was allowed.");
		}
		catch(NumberFormatException e) {
			// success
		}
		try {
			t.compareTo(t4);
			fail("Incorrect ID format was allowed.");
		}
		catch(NumberFormatException e) {
			// success
		}
		
	}
		
		
	@Test
	void testTweetEquals() {
		Tweet t = new Tweet();
		Tweet t2 = new Tweet();
		Tweet t3 = new Tweet();
		Tweet t4 = new Tweet();
		Tweet t5 = new Tweet();

		t3.setAuthor_id("Bob");
		t3.setConversation_id("Hello");
		t3.setCreated_at("23");
		t3.setId("1234");
		t3.setText("Eating Bagels");
		
		t4.setAuthor_id("Bob");
		t4.setConversation_id("Hello");
		t4.setCreated_at("23");
		t4.setId("1234");
		t4.setText("Eating Bagels");
		
		t5.setAuthor_id("Bob");
		t5.setConversation_id("Hello");
		t5.setCreated_at("23");
		t5.setId("1234");
		t5.setText("Not Eating Bagels");
		
		assertTrue(t.equals(t));
		assertTrue(t.equals(t2));
		assertFalse(t.equals(new Object()));
		assertFalse(t.equals(t3));
		assertTrue(t3.equals(t4));
		assertFalse(t4.equals(t5));
	}
}
