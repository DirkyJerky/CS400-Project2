package test.data.resources;

import data.resources.TweetFilterRule;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestTweetFilterRule {
	@Test
	void testTwitterUserObject() {
		TweetFilterRule tfr1 = new TweetFilterRule();
		TweetFilterRule tfr2 = new TweetFilterRule();		
		tfr1.setId("1234");
		tfr1.setValue("24");
		tfr1.setTag("Bob");
		
		// toString() null and with information
		assertEquals("TweetFilterRule{id='1234', value='24', tag='Bob'}", tfr1.toString());
		assertEquals("TweetFilterRule{id='null', value='null', tag='null'}", tfr2.toString());
		
		// getters (null), then setters
		assertEquals("TweetFilterRule{" +
                "id='" + tfr1.getId() + '\'' +
                ", value='" + tfr1.getValue() + '\'' +
                ", tag='" + tfr1.getTag() + '\'' +
                "}", tfr1.toString());
		assertEquals("TweetFilterRule{" +
                "id='" + tfr2.getId() + '\'' +
                ", value='" + tfr2.getValue() + '\'' +
                ", tag='" + tfr2.getTag() + '\'' +
                "}", tfr2.toString());
	
	}	
	
	
	@Test
	void testTweetEquals() {
		TweetFilterRule tfr1 = new TweetFilterRule();
		TweetFilterRule tfr2 = new TweetFilterRule();
		TweetFilterRule tfr3 = new TweetFilterRule();
		TweetFilterRule tfr4 = new TweetFilterRule();
		TweetFilterRule tfr5 = new TweetFilterRule();

		tfr1.setId("1234");
		tfr1.setValue("24");
		tfr1.setTag("Bob");
		
		tfr2.setId("1234");
		tfr2.setValue("24");
		tfr2.setTag("Bob");
		
		tfr3.setId("4321");
		tfr3.setValue("42");
		tfr3.setTag("Bobby");
		
		assertTrue(tfr4.equals(tfr4));
		assertTrue(tfr1.equals(tfr2));
		assertFalse(tfr4.equals(new Object()));
		assertFalse(tfr1.equals(tfr3));
		assertTrue(tfr4.equals(tfr5));
		assertFalse(tfr1.equals(tfr4));
	}
}
