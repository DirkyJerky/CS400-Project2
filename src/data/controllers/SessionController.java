// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: Written By Jacob Lorenz
//                  Regulates access to the core Red Black Trees of the application.
//                  Builds, resets, and provides copies of the Red Black Trees created by Sample Stream and Filtered Stream Tweets.

package data.controllers;

import RBTree.RedBlackTree;
import data.resources.Tweet;

/**
 * Used to manage the core Red Black Trees of the application. Used by the initial setup (data wrangler code), and can
 * then be used to retrieve copies of each tree, reset a tree to be empty, or add in Tweets too either tree.
 */
public class SessionController {

    /**
     * Populated when the app makes a call against the sample stream endpoint, which delivers a random 1% sample
     * of all streams; not filterable
     */
    private static RedBlackTree<Tweet> OnePercentSampleTweetTree
            = new RedBlackTree<>();
    /**
     * Populated when the app makes a call against the filtered stream endpoint, which delivers all tweets matching the
     * filter criteria
     */
    private static RedBlackTree<Tweet> FilteredTweetTree
            = new RedBlackTree<>();

    private static int OnePercentSize = 0;
    private static int FilteredSize = 0;

    /**
     * Default Constructor
     */
    public SessionController() {

    }

    /**
     * Adds a Tweet object to the Red Black Tree dedicated to organizing Tweets from the sample stream endpoint.
     * @param tweet the Tweet object to add into the Sample Red Black Tree
     * @return true if the Tweet was successfully added to the tree, false otherwise
     */
    public boolean addTweetToSampleTree(Tweet tweet) {
        if (tweet == null) {
            return false;
        }
        try {
            OnePercentSampleTweetTree.insert(tweet);
            OnePercentSize++;
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    /**
     * Adds a Tweet object to the Red Black Tree dedicated to organizing Tweets from the filtered stream endpoint.
     * @param tweet the Tweet object to add into the Filter Red Black Tree
     * @return true if the Tweet was successfully added to the tree, false otherwise
     */
    public boolean addTweetToFilteredTree(Tweet tweet) {
        if (tweet == null) {
            return false;
        }
        try {
            FilteredTweetTree.insert(tweet);
            FilteredSize++;
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    /**
     * Clears the existing sample stream Red Black Tree free of Tweets
     */
    public void clearSampleTree() {
        OnePercentSampleTweetTree = new RedBlackTree<>();
        OnePercentSize = 0;
    }

    /**
     * Clears the existing filtered stream Red Black Tree free of Tweets.
     */
    public void clearFilteredTree() {
        FilteredTweetTree = new RedBlackTree<>();
        FilteredSize = 0;
    }

    /**
     * Creates a copy of the sample stream Red Black Tree and returns the copy
     * @return a copy of the sample RedBlackTree<Tweet>
     */
    public RedBlackTree<Tweet> getCopyOfOnePercentTweetTree() {
        RedBlackTree<Tweet> copy = OnePercentSampleTweetTree;
        return copy;
    }

    /**
     * Creates a copy of the filtered stream Red Black Tree and returns the copy
     * @return a copy of the filtered RedBlackTree<Tweet>
     */
    public RedBlackTree<Tweet> getCopyOfFilteredTweetTree() {
        RedBlackTree<Tweet> copy = FilteredTweetTree;
        return copy;
    }

    /**
     * Returns the current size of the OnePercentSampleTweetTree
     * @return current size (int) of the OnePercentSampleTweetTree
     */
    public int getSizeOfOnePercentTweetTree() {
        return OnePercentSize;
    }

    /**
     * Returns the current size of the FilteredTweetTree
     * @return current size (int) of the FilteredTweetTree
     */
    public int getSizeOfFilteredTweetTree() {
        return FilteredSize;
    }
}
