package tests.data.resources;

import data.resources.TwitterUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestTwitterUser {
	
	@Test
	void testTwitterUserObject() {
		TwitterUser tu1 = new TwitterUser();
		TwitterUser tu2 = new TwitterUser();
		tu1.setId("1234");
		tu1.setName("Bob");
		tu1.setUsername("Bobby");
		
		// toString() null and with information
		assertEquals("TwitterUser {id='1234', name='Bob', username='Bobby'}", tu1.toString());
		assertEquals("TwitterUser {id='null', name='null', username='null'}", tu2.toString());
		
		// getters (null), then setters
		assertEquals("TwitterUser {" +
                "id='" + tu1.getId() + '\'' +
                ", name='" + tu1.getName() + '\'' +
                ", username='" + tu1.getUsername() + '\'' +
                "}", tu1.toString());
		assertEquals("TwitterUser {" +
                "id='" + tu2.getId() + '\'' +
                ", name='" + tu2.getName() + '\'' +
                ", username='" + tu2.getUsername() + '\'' +
                "}", tu2.toString());
	
	}	
	
	
	@Test
	void testTweetEquals() {
		TwitterUser tu1 = new TwitterUser();
		TwitterUser tu2 = new TwitterUser();
		TwitterUser tu3 = new TwitterUser();
		TwitterUser tu4 = new TwitterUser();
		TwitterUser tu5 = new TwitterUser();

		tu1.setId("1234");
		tu1.setName("Bob");
		tu1.setUsername("Bobby");
		
		tu2.setId("1234");
		tu2.setName("Bob");
		tu2.setUsername("Bobby");
		
		tu3.setId("4321");
		tu3.setName("Bill");
		tu3.setUsername("NotBobby");
		
		assertTrue(tu4.equals(tu4));
		assertTrue(tu1.equals(tu2));
		assertFalse(tu4.equals(new Object()));
		assertFalse(tu1.equals(tu3));
		assertTrue(tu4.equals(tu5));
		assertFalse(tu1.equals(tu4));
	}
}
