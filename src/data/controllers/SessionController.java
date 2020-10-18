package data.controllers;

import RBTree.RedBlackTree;
import data.resources.Tweet;

/**
 * A basic class at the moment that allows the data to be streamed, collected, and delivered to the front end
 *
 */
public class SessionController {

    private static RedBlackTree<Tweet> OnePercentSampleTweetTree
            = new RedBlackTree<>();
    private static RedBlackTree<Tweet> FilteredTweetTree
            = new RedBlackTree<>();

    public SessionController() {

    }

    public boolean addTweetToSampleTree(Tweet tweet) {
        if (tweet == null) {
            return false;
        }
        try {
            OnePercentSampleTweetTree.insert(tweet);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public boolean addTweetToFilteredTree(Tweet tweet) {
        if (tweet == null) {
            return false;
        }
        try {
            FilteredTweetTree.insert(tweet);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public void clearSampleTree() {
        OnePercentSampleTweetTree = new RedBlackTree<>();
    }

    public void clearFilteredTree() {
        FilteredTweetTree = new RedBlackTree<>();
    }

    public RedBlackTree<Tweet> getCopyOfOnePercentTweetTree() {
        RedBlackTree<Tweet> copy = OnePercentSampleTweetTree;
        return copy;
    }

    public RedBlackTree<Tweet> getCopyOfFilteredTweetTree() {
        RedBlackTree<Tweet> copy = FilteredTweetTree;
        return copy;
    }
}
