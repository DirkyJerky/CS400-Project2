package data;

import data.resources.TweetFilterRule;
import data.resources.TwitterUser;

import java.util.Set;

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
