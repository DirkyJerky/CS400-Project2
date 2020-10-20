// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: Written by Jacob Lorenz
//                  A custom implementation of the TwitterDataAccessInterface to implement the API Accessor methods
//                  available within the app. This class abstracts the HTTP functionality involved when dealing with the
//                  data to allow the frontend / backend make simple function calls.

package data.api;

import RBTree.RedBlackTree;
import data.TwitterDataAccessInterface;
import data.controllers.SessionController;
import data.resources.Tweet;
import data.resources.TweetFilterRule;
import data.resources.TwitterUser;

import okhttp3.Response;

import com.google.gson.*;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.*;

/**
 * Implements TwitterDataAccessInterface
 * Uses okhttp and twitter4j to abstract the Twitter API calls into descriptive functions for simple
 * and intuitive access for the frontend and backend in whatever data needs they have.
 */
public class TwitterAPIService implements TwitterDataAccessInterface {

    /**
     * Private Variables used for HTTP routing and authorization
     */
    private final String BASE_URL = "https://api.twitter.com";
    private static final String consumerKey = "LtG6jpBInaEduKkkQmQCw5sso";
    private static final String consumerSecret = "ntqqLEGyBJsGbMi6kdybC7lUBlJKsZ1RuRa8lFbMXLCHEh7VWD";
    private static final String token = "1312243397688782850-MQmWy3OJN30ofo5FZx6w73rkNMoAkK";
    private static final String tokenSecret = "jnRSAHWwdLmQm0HVW9OOfxD2O8aVJL14r5Uv0l8d2OuB7";
    private static final String bearerToken = "AAAAAAAAAAAAAAAAAAAAAJgiIQEAAAAASKsySmauclKIif2r1nVEQzley5w%3DYCjJQMOgBwX3qoLummZYAuOAVx32ehjuK5DBZoM4ntGa84DtPV";

    /**
     * Private class instance references used to manage the core of the app's data
     */
    private static HTTPClient httpClient
            = new HTTPClient();
    private static TwitterOauthHeaderGenerator twitterOauthHeaderGenerator
            = new TwitterOauthHeaderGenerator(consumerKey, consumerSecret, token, tokenSecret);
    private static SessionController sessionController
            = new SessionController();

    /**
     * Used when calling the sample stream or filtered stream endpoint
     */
    private TwitterStream ts;

    /**
     * Default Constructor
     */
    public TwitterAPIService() {
    }


    /**
     * Wraps the POST /2/tweets/search/stream/rules for a single rule
     * Working
     *
     * @param rule the rule you would like to add to the filtering set
     * @return true if the rule was successfully posted to the rules set, false otherwise
     */
    @Override
    public boolean postTweetFilteringRule(String rule) {
        TwitterRequestObject request = buildPostTweetStreamRuleRequest(rule);
        Response response = httpClient.sendOneTimePostRequest(request);
        if (response == null) {
            return false;
        }
        return true;
    }

    /**
     * Wraps the POST /2/tweets/search/stream/rules for multiple rules
     * Working
     *
     * @param rules the rules you would like to add to the filtering set
     * @return true if the rules were successfully posted to the rules set, false otherwise
     */
    @Override
    public boolean postMultipleTweetFilteringRules(String[] rules) {
        TwitterRequestObject request = buildPostMultipleTweetStreamRulesRequest(rules);
        Response response = httpClient.sendOneTimePostRequest(request);
        if (response == null) {
            return false;
        }
        return true;
    }

    /**
     * Wraps the POST /2/tweets/search/stream/rules to delete a single rule from the filtering set by its value
     * Working
     *
     * @param ruleValue the value of the rule to be deleted
     * @return true if the rule was successfully deleted from the rules set, false otherwise
     */
    @Override
    public boolean deleteTweetFilteringRuleByValue(String ruleValue) {
        TwitterRequestObject request = buildDeleteTweetStreamRuleRequest(ruleValue);
        Response response = httpClient.sendOneTimePostRequest(request);
        if (response == null) {
            return false;
        }
        return true;
    }

    /**
     * Wraps the POST /2/tweets/search/stream/rules to delete multiple rules from the filtering set by their values
     * Working
     *
     * @param ruleValues the values of the rules to be deleted
     * @return true if the rules were successfully deleted from the rules set, false otherwise
     */
    @Override
    public boolean deleteMultipleTweetFilteringRulesByValue(String[] ruleValues) {
        TwitterRequestObject request = buildDeleteMultipleTweetStreamRulesRequest(ruleValues);
        Response response = httpClient.sendOneTimePostRequest(request);
        if (response == null) {
            return false;
        }
        return true;
    }

    /**
     * Wraps the POST /2/tweets/search/stream/rules to delete a single rule from the filtering set by its id
     * Working
     *
     * @param ruleId the id of the rule to be deleted
     * @return true if the rule was successfully deleted from the rules set, false otherwise
     */
    @Override
    public boolean deleteTweetFilteringRuleByRuleId(String ruleId) {
        TwitterRequestObject request = buildDeleteTweetStreamRuleByIdRequest(ruleId);
        Response response = httpClient.sendOneTimePostRequest(request);
        if (response == null) {
            return false;
        }
        return true;
    }

    /**
     * Wraps the POST /2/tweets/search/stream/rules to delete multiple rules from the filtering set by their ids
     * Working
     *
     * @param ruleIds the ids of the rules to be deleted
     * @return true if the rules were successfully deleted from the rules set, false otherwise
     */
    @Override
    public boolean deleteMultipleTweetFilteringRulesByRuleIds(String[] ruleIds) {
        TwitterRequestObject request = buildDeleteMultipleTweetStreamRulesByIdRequest(ruleIds);
        Response response = httpClient.sendOneTimePostRequest(request);
        if (response == null) {
            return false;
        }
        return true;
    }

    /**
     * Wraps the GET /2/tweets/search/stream/rules to lookup all rules that are previous active and filtering in the filtering stream
     * Working
     *
     * @return a Set<TweetFilteringRule> containing all active rules
     */
    @Override
    public Set<TweetFilterRule> getTweetFilteringRules() {
        TwitterRequestObject request = buildGetTweetStreamRulesRequest();
        Response response = httpClient.sendOneTimeGetRequest(request);
        if (response == null) {
            return null;
        }
        Set<TweetFilterRule> retrievedRulesSet = parseRulesFromResponse(response);
        return retrievedRulesSet;
    }

    /**
     * Wraps the GET /2/users/by/username/:username to lookup a Twitter User by their username
     * Working
     *
     * @param username the username of the user to lookup
     * @return a TwitterUser representation of the user found matching the username
     */
    @Override
    public TwitterUser getUserByUsername(String username) {
        TwitterRequestObject request = buildGetUserDetailsFromUsernameRequest(username);
        Response response = httpClient.sendOneTimeGetRequest(request);
        if (response == null) {
            return null;
        }
        TwitterUser retrievedUser = parseUserFromResponse(response);
        return retrievedUser;
    }

    /**
     * Wraps the GET /2/users/by/username/:username to lookup multiple Twitter Users by their usernames
     * Working
     *
     * @param usernames the usernames of the users to lookup
     * @return a Set<TwitterUser> containing all of the users found matching the usernames
     */
    @Override
    public Set<TwitterUser> getUsersByUsernames(String[] usernames) {

        Set<TwitterUser> retrievedUsersSet = new HashSet<>();

        for (int i = 0; i < usernames.length; i++) {
            TwitterRequestObject request = buildGetUserDetailsFromUsernameRequest(usernames[i]);
            Response response = httpClient.sendOneTimeGetRequest(request);
            if (response == null) {
                return null;
            }
            TwitterUser retrievedUser = parseUserFromResponse(response);

            if (retrievedUser != null) {
                retrievedUsersSet.add(retrievedUser);
            }
        }

        return retrievedUsersSet;
    }

    /**
     * Wraps Twitter APIs and uses twitter4j to stream Tweets based on previously set filters
     * Working
     */
    @Override
    public void getFilteredStream() {
        TwitterStream twitterStream = getTwitterStreamInstance();

        ArrayList<String> track = new ArrayList<String>();

        Set<TweetFilterRule> activeRuleSet = getTweetFilteringRules();
        activeRuleSet.forEach(i -> {
            track.add(i.getValue());
        });

        String[] trackArray = track.toArray(new String[track.size()]);
        FilterQuery filterQuery = new FilterQuery();
        String ruleQuery = trackArray[0];
        for (int i = 1; i < trackArray.length; i++) {
            ruleQuery += " OR " + trackArray[i];
        }

        filterQuery.track(ruleQuery);
        twitterStream.addListener(getFilteredStatusListener());
        twitterStream.filter(filterQuery);
    }

    /**
     * Wraps Twitter APIs and uses twitter4j to sample a one percent random sample of all Tweets, unfiltered
     * Working
     */
    @Override
    public void getSampleStream() {
        TwitterStream twitterStream = getTwitterStreamInstance();
        twitterStream.addListener(getSampleStatusListener());
        twitterStream.sample();
    }



    /**
     * Creates a new TwitterRequestObject and populates it with the correct information for getUserByUsername and
     * getUsersByUsernames methods
     * Working
     *
     * @param username the username of the TwitterUser to create a request for
     * @return TwitterRequestObject
     */
    private TwitterRequestObject buildGetUserDetailsFromUsernameRequest(String username) {
        TwitterRequestObject requestObject = new TwitterRequestObject();
        requestObject.setUrl(BASE_URL + "/2/users/by/username/" + username);
        Map<String, String> requestParamMap = new HashMap<>();
        String authHeader = twitterOauthHeaderGenerator.generateHeader("GET", requestObject.getUrl(), requestParamMap);
        requestObject.setAuthorizationValue(authHeader);
        requestObject.setJsonBody("{}");
        return requestObject;
    }

    /**
     * Builds the TwitterRequestObject for use in the getTweetFilteringRules wrapper method
     * Working
     *
     * @return a TwitterRequestObject for use in the future API call
     */
    private TwitterRequestObject buildGetTweetStreamRulesRequest() {
        TwitterRequestObject requestObject = new TwitterRequestObject();
        requestObject.setUrl(BASE_URL + "/2/tweets/search/stream/rules");
        requestObject.setAuthorizationValue("Bearer " + bearerToken);

        requestObject.setJsonBody("{}");

        return requestObject;
    }

    /**
     * Builds the TwitterRequestObject for use in the postTweetFilteringRule wrapper method
     * Working
     *
     * @param rule the rule of the current request to format in the body
     * @return a TwitterRequestObject for use in the future API call
     */
    private TwitterRequestObject buildPostTweetStreamRuleRequest(String rule) {
        TwitterRequestObject requestObject = new TwitterRequestObject();
        requestObject.setUrl(BASE_URL + "/2/tweets/search/stream/rules");
        requestObject.setAuthorizationValue("Bearer " + bearerToken);

        if (rule == null) {
            return null;
        } else {
            String bodyString = "{\n";
            bodyString += "\t\"add\": [\n";
            bodyString += "\t\t";
            bodyString += "{\"value\": \"";
            bodyString += rule;
            bodyString += "\",\"tag\": \"";
            bodyString += rule;
            bodyString += "\"}";
            bodyString += "\n\t]\n}";

            requestObject.setJsonBody(bodyString);

            System.out.println(bodyString);

            return requestObject;
        }
    }

    /**
     * Builds the TwitterRequestObject for use in the postMultipleTweetFilteringRules wrapper method
     * Working
     *
     * @param filters the rules of the current request to format in the body
     * @return a TwitterRequestObject for use in the future API call
     */
    private TwitterRequestObject buildPostMultipleTweetStreamRulesRequest(String[] filters) {
        TwitterRequestObject requestObject = new TwitterRequestObject();
        requestObject.setUrl(BASE_URL + "/2/tweets/search/stream/rules");
        requestObject.setAuthorizationValue("Bearer " + bearerToken);

        String bodyString = "";

        if (filters.length < 1) {
            return null;
        } else {
            bodyString = "{\n";
            bodyString += "\t\"add\": [\n";
            bodyString += "\t\t";
            bodyString += "{\"value\": \"";
            bodyString += filters[0];
            bodyString += "\", \"tag\": \"";
            bodyString += filters[0];
            bodyString += "\"}";
        }

        for (int i = 1; i < filters.length; i++) {
            bodyString += ",\n";
            bodyString += "\t\t";
            bodyString += "{\"value\": \"";
            bodyString += filters[i];
            bodyString += "\", \"tag\": \"";
            bodyString += filters[i];
            bodyString += "\"}";
        }
        bodyString += "\n\t]\n}";

        requestObject.setJsonBody(bodyString);

        System.out.println(bodyString);

        return requestObject;
    }

    /**
     * Builds the TwitterRequestObject for use in the deleteTweetFilteringRuleByValue wrapper method
     * Working
     *
     * @param ruleValue the value of the rule of the current request to be deleted
     * @return a TwitterRequestObject for use in the future API call
     */
    private TwitterRequestObject buildDeleteTweetStreamRuleRequest(String ruleValue) {
        TwitterRequestObject requestObject = new TwitterRequestObject();
        requestObject.setUrl(BASE_URL + "/2/tweets/search/stream/rules");
        requestObject.setAuthorizationValue("Bearer " + bearerToken);

        if (ruleValue == null) {
            return null;
        } else {
            String bodyString = "{\n";
            bodyString += "\t\"delete\": {\n";
            bodyString += "\t\t\"values\": [\n";
            bodyString += "\t\t\t\"";
            bodyString += ruleValue;
            bodyString += "\"";
            bodyString += "\n\t\t]\n\t}\n}";

            requestObject.setJsonBody(bodyString);

            System.out.println(bodyString);

            return requestObject;
        }
    }

    /**
     * Builds the TwitterRequestObject for use in the deleteMultipleTweetFilteringRulesByValue wrapper method
     * Working
     *
     * @param ruleValues the values of the rules of the current request to be deleted
     * @return a TwitterRequestObject for use in the future API call
     */
    private TwitterRequestObject buildDeleteMultipleTweetStreamRulesRequest(String[] ruleValues) {
        TwitterRequestObject requestObject = new TwitterRequestObject();
        requestObject.setUrl(BASE_URL + "/2/tweets/search/stream/rules");
        requestObject.setAuthorizationValue("Bearer " + bearerToken);

        String bodyString = "";

        if (ruleValues.length < 1) {
            return null;
        } else {
            bodyString = "{\n";
            bodyString += "\t\"delete\": {\n";
            bodyString += "\t\t\"values\": [\n";
            bodyString += "\t\t\t\"";
            bodyString += ruleValues[0];
            bodyString += "\"";
        }

        for (int i = 1; i < ruleValues.length; i++) {
            bodyString += ",\n";
            bodyString += "\t\t\t\"";
            bodyString += ruleValues[i];
            bodyString += "\"";
        }
        bodyString += "\n\t\t]\n\t}\n}";

        requestObject.setJsonBody(bodyString);

        System.out.println(bodyString);

        return requestObject;
    }

    /**
     * Builds the TwitterRequestObject for use in the deleteTweetFilteringRuleById wrapper method
     * Working
     *
     * @param ruleId the id of the rule of the current request to be deleted
     * @return a TwitterRequestObject for use in the future API call
     */
    private TwitterRequestObject buildDeleteTweetStreamRuleByIdRequest(String ruleId) {
        TwitterRequestObject requestObject = new TwitterRequestObject();
        requestObject.setUrl(BASE_URL + "/2/tweets/search/stream/rules");
        requestObject.setAuthorizationValue("Bearer " + bearerToken);

        if (ruleId == null) {
            return null;
        } else {
            String bodyString = "{\n";
            bodyString += "\t\"delete\": {\n";
            bodyString += "\t\t\"ids\": [\n";
            bodyString += "\t\t\t\"";
            bodyString += ruleId;
            bodyString += "\"";
            bodyString += "\n\t\t]\n\t}\n}";

            requestObject.setJsonBody(bodyString);

            System.out.println(bodyString);

            return requestObject;
        }
    }

    /**
     * Builds the TwitterRequestObject for use in the deleteMultipleTweetFilteringRulesByRuleIds wrapper method
     * Working
     *
     * @param ruleIds the ids of the rules of the current request to be deleted
     * @return a TwitterRequestObject for use in the future API call
     */
    private TwitterRequestObject buildDeleteMultipleTweetStreamRulesByIdRequest(String[] ruleIds) {
        TwitterRequestObject requestObject = new TwitterRequestObject();
        requestObject.setUrl(BASE_URL + "/2/tweets/search/stream/rules");
        requestObject.setAuthorizationValue("Bearer " + bearerToken);

        String bodyString = "";

        if (ruleIds.length < 1) {
            return null;
        } else {
            bodyString = "{\n";
            bodyString += "\t\"delete\": {\n";
            bodyString += "\t\t\"ids\": [\n";
            bodyString += "\t\t\t\"";
            bodyString += ruleIds[0];
            bodyString += "\"";
        }

        for (int i = 1; i < ruleIds.length; i++) {
            bodyString += ",\n";
            bodyString += "\t\t\t\"";
            bodyString += ruleIds[i];
            bodyString += "\"";
        }
        bodyString += "\n\t\t]\n\t}\n}";

        requestObject.setJsonBody(bodyString);

        System.out.println(bodyString);

        return requestObject;
    }

    /**
     * Uses gson to deserialize a response from Twitter into a TwitterUser object containing id, name, and username
     * Working
     *
     * @param response the HTTP response to parse
     * @return a TwitterUser with information from the response
     */
    private TwitterUser parseUserFromResponse(Response response) {
        Gson gson = new GsonBuilder().create();
        try {
            JsonParser parser = new JsonParser();
            String responseBody = response.body().string();
            Object obj = parser.parse(responseBody);
            JsonObject jsonObj = (JsonObject)  obj;
            TwitterUser parsedUser = gson.fromJson(jsonObj.getAsJsonObject("data").toString(), TwitterUser.class);
            return parsedUser;
        } catch (IOException e) {
            System.out.println("An IOException has occurred.");
            return null;
        }
    }

    /**
     * Uses gson to deserialize a response from Twitter into a Set of TweetFilterRule objects
     * Working
     *
     * @param response the HTTP response to parse
     * @return a Set<TweetFilterRule> with information from the response
     */
    private Set<TweetFilterRule> parseRulesFromResponse(Response response) {
        Gson gson = new GsonBuilder().create();
        try {
            Set<TweetFilterRule> ruleSet = new HashSet<>();

            // Converts the response into a string, and then into a JsonObject to easily extract data elements
            JsonParser parser = new JsonParser();
            String responseBody = response.body().string();
            Object obj = parser.parse(responseBody);
            JsonObject jsonObj = (JsonObject)  obj;

            JsonArray rulesArray = jsonObj.getAsJsonArray("data");

            for (int i = 0; i < rulesArray.size(); i++) {
                TweetFilterRule parsedRule = gson.fromJson(rulesArray.get(i).getAsJsonObject().toString(), TweetFilterRule.class);
                ruleSet.add(parsedRule);
            }
            return ruleSet;
        } catch (IOException e) {
            System.out.println("An IOException has occurred.");
            return null;
        }
    }

    /**
     * Uses gson to deserialize a response from Twitter into a Tweet object
     * Working
     *
     * @param status the HTTP response from the Streaming endpoints to parst
     * @return a Tweet with information from the response / Status
     */
    private Tweet parseTweetFromResponse(Status status) {
        User tweetUser = status.getUser();

        TwitterUser newUser = new TwitterUser();
        newUser.setId(String.valueOf(tweetUser.getId()));
        newUser.setName(tweetUser.getName());
        newUser.setUsername(tweetUser.getScreenName());

        Tweet newTweet = new Tweet();
        newTweet.setAuthor_id(newUser.getId());
        newTweet.setId(String.valueOf(status.getId()));
        newTweet.setText(status.getText());
        newTweet.setCreated_at(status.getCreatedAt().toString());
        newTweet.setUser(newUser);

        return newTweet;
    }

    /**
     * Returns an instance of a TwitterStream to be used for one of the Streaming endpoints
     * Working
     *
     * @return a TwitterStream to use in filtering endpoints
     */
    private TwitterStream getTwitterStreamInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(token)
                .setOAuthAccessTokenSecret(tokenSecret);
        ts = new TwitterStreamFactory(cb.build()).getInstance();
        return ts;
    }

    /**
     * Creates a StatusListener to handle incoming Status updates in the persistent Sample Streaming Endpoint
     * Working
     *
     * @return a StatusListener that will handle the cases of incoming Streamed Tweets
     */
    private StatusListener getSampleStatusListener() {
        StatusListener newStatusListener = new StatusListener() {

            int counter = 0;

            @Override
            public void onStatus(Status status) {
                // System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                Tweet newTweet = parseTweetFromResponse(status);
                sessionController.addTweetToSampleTree(newTweet);
                counter++;
                if (counter >= 50000) { // Gets N tweets before shutting down
                    // System.out.println("Printing Final Tree:");
                    RedBlackTree<Tweet> copiedSampleStream = sessionController.getCopyOfOnePercentTweetTree();
                    // System.out.println(copiedSampleStream.toString());
                    ts.shutdown();
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                // System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                // System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                // System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                // System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        return newStatusListener;
    }

    /**
     * Creates a StatusListener to handle incoming Status updates in the persistent Filtered Streaming Endpoint
     * Working
     *
     * @return a StatusListener that will handle the cases of incoming Streamed Tweets
     */
    private StatusListener getFilteredStatusListener() {
        StatusListener newStatusListener = new StatusListener() {

            int counter = 0;

            @Override
            public void onStatus(Status status) {
                // System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                Tweet newTweet = parseTweetFromResponse(status);
                sessionController.addTweetToFilteredTree(newTweet);
                counter++;
                if (counter >= 1000) { // Gets N tweets before shutting down
                    // System.out.println("Printing Final Filtered Tree:");
                    RedBlackTree<Tweet> copiedSampleStream = sessionController.getCopyOfFilteredTweetTree();
                    // System.out.println(copiedSampleStream.toString());
                    ts.shutdown();
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                // System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                // System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                // System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                // System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        return newStatusListener;
    }

    /**
     * Untested
     */
    private void shutdownStream() {
        ts.shutdown();
    }

}
