// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: Written by Jacob Lorenz
//                  This Main.java class is used to showcase the usage of all of the various API wrapper methods

package data;

import data.api.TwitterAPIService;
import data.resources.TweetFilterRule;
import data.resources.TwitterUser;

import java.util.Set;

public class Main {

    public static void main(String[] args) {

        // Create the interface
        TwitterDataAccessInterface apiAccessInterface = new TwitterAPIService();

        // Test a call to the getUserByUsername endpoint:
        // GET /2/users/by/username/:username (lookup by single username)
        System.out.println("Testing call to GET /2/users/by/username/:username with username = JacobLorenz12");
        TwitterUser myUser = apiAccessInterface.getUserByUsername("JacobLorenz12");
        System.out.println("Name: " + myUser.getName());
        System.out.println("ID: " + myUser.getId());
        System.out.println("Username: " + myUser.getUsername());
        System.out.println();

        // Test a call to getUsersByUsernames endpoint:
        // GET /2/users/by/username/:username (lookup by single username for multiple usernames)
        System.out.println("Testing call to GET /2/users/by/username/:username with multiple usernames:" +
                "username1 = JacobLorenz12" +
                "username2 = realDonaldTrump");
        String[] requestUserArray = {"JacobLorenz12", "realDonaldTrump"};
        System.out.println();
        Set<TwitterUser> responseUserSet = apiAccessInterface.getUsersByUsernames(requestUserArray);
        responseUserSet.forEach(i -> {
            System.out.println("User:");
            System.out.println("Name: " + i.getName());
            System.out.println("ID: " + i.getId());
            System.out.println("Username: " + i.getUsername());
            System.out.println();
        });

        // Test a call to the postTweetFilteringRule endpoint:
        // POST /2/tweets/search/stream/rules
        System.out.println("Testing a call to POST /2/tweets/search/stream/rules with rule = 'cat has:images'");
        boolean responseStatus1 = apiAccessInterface.postTweetFilteringRule("cat has:images");
        System.out.println("Response Received: " + responseStatus1);
        System.out.println();

        System.out.println("Testing a call to POST /2/tweets/search/stream/rules with multiple rules" +
                " rule1 = 'dog has:images'" +
                " rule2 = 'rabbit has:images'" +
                " rule3 = 'elephant has:images'" +
                " rule4 = 'goat has:images'");
        String[] requestArray = {"dog has:images", "rabbit has:images", "elephant has:images", "goat has:images"};
        boolean responseStatus2 = apiAccessInterface.postMultipleTweetFilteringRules(requestArray);
        System.out.println("Response Received: " + responseStatus2);
        System.out.println();

        // Test a call to the getTweetFilteringRules endpoint:
        // GET /2/tweets/search/stream/rules
        System.out.println("Testing a call to GET /2/tweets/search/stream/rules");
        Set<TweetFilterRule> ruleSet = apiAccessInterface.getTweetFilteringRules();
        ruleSet.forEach(i -> {
            System.out.println();
            System.out.println("Existing Rule:");
            System.out.println("ID: " + i.getId());
            System.out.println("Value: " + i.getValue());
            System.out.println("Tag: " + i.getTag());
            System.out.println();
        });

        // Test a call to the deleteTweetFilteringRuleByValue endpoint
        // POST /2/tweets/search/stream/rules - delete functionality
        // Using value of 'cat has:images' here
        System.out.println("Testing a call to POST /2/tweets/search/strem/rules to delete a rule by value");
        boolean responseStatus3 = apiAccessInterface.deleteTweetFilteringRuleByValue("goat has:images");
        System.out.println();


        // Test a call to the deleteMultipleTweetFilteringRulesByValue endpoint
        // POST /2/tweets/search/stream/rules - delete functionality
        // Using id of '1317285188687323138' here (rabbit has:images)
        System.out.println("Testing a call to POST /2/tweets/search/strem/rules to delete multiple rules by value");
        String[] ruleValuesToDelete = {"rabbit has:images", "elephant has:images", "dog has:images", "goat has images"};
        boolean responseStatus4 = apiAccessInterface.deleteMultipleTweetFilteringRulesByValue(ruleValuesToDelete);
        System.out.println();

//        Test a call to the deleteTweetFilteringRuleByValue endpoint
//        POST /2/tweets/search/stream/rules - delete functionality
//        Using value of 'cat has:images' here
//        System.out.println("Testing a call to POST /2/tweets/search/strem/rules to delete a rule by id");
//        boolean responseStatus3 = apiAccessInterface.deleteTweetFilteringRuleByRuleId("cat has:images");
//        System.out.println();
//
//
//        // Test a call to the deleteMultipleTweetFilteringRulesByValue endpoint
//        // POST /2/tweets/search/stream/rules - delete multiple functionality
//        // Using ids of 'id____1' through ... 'id_____n' here
//        System.out.println("Testing a call to POST /2/tweets/search/strem/rules to delete multiple rules by value");
//        String[] ruleValuesToDelete = {"id____1", "id_____n"};
//        boolean responseStatus4 = apiAccessInterface.deleteMultipleTweetFilteringRulesByRuleIds(ruleValuesToDelete);
//        System.out.println();

        // Test a call to the getTweetFilteringRules endpoint after deleting 3 rules in previous calls, should be 2 left:
        // GET /2/tweets/search/stream/rules
        System.out.println("Testing a call to GET /2/tweets/search/stream/rules");
        Set<TweetFilterRule> ruleSetAfterDelete = apiAccessInterface.getTweetFilteringRules();
        ruleSetAfterDelete.forEach(i -> {
            System.out.println();
            System.out.println("Existing Rule:");
            System.out.println("ID: " + i.getId());
            System.out.println("Value: " + i.getValue());
            System.out.println("Tag: " + i.getTag());
            System.out.println();
        });

        System.out.println("Testing a call to the sample stream.");
        apiAccessInterface.getSampleStream();

        //System.out.println("Testing a call to the filtered stream.");
        // apiAccessInterface.getFilteredStream();

    }
}