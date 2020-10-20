// --== CS400 File Header Information ==--
// Name: Mason Dorgan
// Email: msdorgan@wisc.edu
// Team: BD
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

package tests;

import static org.junit.Assert.*;
import org.junit.*;


import data.controllers.SessionController;
import data.resources.Tweet;

import RBTree.RedBlackTree;


/**
 * Tests the Session Controller 
 * 
 * @author Mason
 *
 */
public class ControllerTest {

  /**
   * Tests the controller methods
   */
  @Test
  public void Test() {
    SessionController test = new SessionController();
    RedBlackTree<Tweet> rbTest = new RedBlackTree<>();
    Tweet test1 = new Tweet();
    Tweet test2 = new Tweet();
    Tweet test3 = new Tweet();
    
    //tests adding a null tweet that returns false
    assertFalse(test.addTweetToSampleTree(test1));
    assertFalse(test.addTweetToFilteredTree(test1));
    
    //sets text to the tweets
    test1.setText("1");
    test2.setText("2");
    test3.setText("3");
    
    //tests adding a tweet with text that returns true
    assertTrue(test.addTweetToSampleTree(test2));
    assertTrue(test.addTweetToFilteredTree(test2));
    
    test.addTweetToSampleTree(test1);
    test.addTweetToSampleTree(test2);
    test.addTweetToFilteredTree(test3);
    
    //tests clearing the trees
    test.clearSampleTree();
    test.clearFilteredTree();
    
    try {
      test.getCopyOfOnePercentTweetTree().toString();
      fail("Sample tree was not cleared");
    }
    catch (NullPointerException e) {
      //Sample tree was cleared
    }
    try {
      test.getCopyOfFilteredTweetTree().toString();
      fail("Filtered tree was not cleared");
    } catch (NullPointerException e) {
      //Filtered tree was cleared
    }
  }
}
