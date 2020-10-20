// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: Written By Jacob Lorenz
//                  The main interface that the frontend and backend can use to retrieve, customize, reset the data in the app.
package data;

import data.resources.TweetFilterRule;
import data.resources.TwitterUser;

import java.util.Set;

/**
 * Wrapper functions for a number of the API endpoints hosted on Twitter for developers.
 * For more information on the entire collection, Twitter has extensive documentation found here:
 * https://developer.twitter.com/en/docs
 *
 * This Interface is implemented by TwitterAPIService.java for the purposes of this project
 */
public interface TwitterDataAccessInterface {

    boolean postTweetFilteringRule(String rule);

    boolean postMultipleTweetFilteringRules(String[] rules);

    boolean deleteTweetFilteringRuleByValue(String ruleValue);

    boolean deleteMultipleTweetFilteringRulesByValue(String[] ruleValues);

    boolean deleteTweetFilteringRuleByRuleId(String ruleId);

    boolean deleteMultipleTweetFilteringRulesByRuleIds(String[] ruleIds);

    Set<TweetFilterRule> getTweetFilteringRules();

    TwitterUser getUserByUsername(String username);

    Set<TwitterUser> getUsersByUsernames(String[]  usernames);

    void getFilteredStream();

    void getSampleStream();
}
