// --== CS400 File Header Information ==--
// Name: Max Roth
// Email: mroth8@wisc.edu
// Team: BD
// Role: Testing
// TA: Bri Cochran
// Lecturer: Florian Heimerl

package test.data.controllers;

import data.resources.Tweet;
import data.controllers.SessionController;
import RBTree.RedBlackTree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSessionController {
	
	/*
	 * This class contains only one test, 
	 * which tests all parts of controller.
	 * 
	 */
	@Test
	void testAdditionToTree() {
		Tweet t = new Tweet();
		Tweet t2 = new Tweet();
		Tweet t3 = new Tweet();
		Tweet t4 = new Tweet();
		Tweet t5 = new Tweet();

		t.setId("12357");
		t2.setId("3000");
		t3.setId("1234");
		t5.setId("557");
		
		// Tests the add method of the controller
		// Which returns true if Id data fits format
		SessionController sc = new SessionController();
		assertTrue(sc.addTweetToSampleTree(t));
		assertFalse(sc.addTweetToSampleTree(t4));
		sc.addTweetToSampleTree(t2);
		sc.addTweetToSampleTree(t3);
		sc.addTweetToSampleTree(t5);
		
		assertTrue(sc.addTweetToFilteredTree(t));
		assertFalse(sc.addTweetToFilteredTree(t4));
		RedBlackTree<Tweet> rbttest = new RedBlackTree<>();
		rbttest = sc.getCopyOfOnePercentTweetTree();
		// System.out.println(rbttest.toString());
				
		// THIS TEST MAY DEPEND ON INDIVIDUAL RED BLACK TREES
		// but it shouldn't
		// it compares four added tweet objects to the RBTree's toString()
		// added through the controller
		// (level order)
		assertEquals("[Tweet{id='3000', text='null', author_id='null', user=null, " +
				 "created_at='null', conversation_id='null'}, " +
				 "Tweet{id='1234', text='null', author_id='null', user=null, " +
				 "created_at='null', conversation_id='null'}, " +
				 "Tweet{id='12357', text='null', author_id='null', user=null, " +
				 "created_at='null', conversation_id='null'}, " +
				 "Tweet{id='557', text='null', author_id='null', user=null, " +
				 "created_at='null', conversation_id='null'}]",
				 rbttest.toString());
		
		
		
		// test clearing the tree
		sc.clearFilteredTree();
		sc.clearSampleTree();
		try {
			sc.getCopyOfOnePercentTweetTree().toString();
			fail("Tree did not clear.");
		}
		catch(NullPointerException e) {
			// success
		}
		
		try {
			sc.getCopyOfFilteredTweetTree().toString();
			fail("Tree did not clear.");
		}
		catch(NullPointerException e) {
			// success
		}
		
	}

}
