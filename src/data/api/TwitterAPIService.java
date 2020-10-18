package data.api;

import RBTree.RedBlackTree;
import com.google.gson.*;
import data.TwitterDataAccessInterface;
import data.controllers.SessionController;
import data.resources.Tweet;
import data.resources.TweetFilterRule;
import data.resources.TwitterUser;
import okhttp3.Response;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.*;

public class TwitterAPIService implements TwitterDataAccessInterface {

    private final String BASE_URL = "https://api.twitter.com";
    private static final String consumerKey = "LtG6jpBInaEduKkkQmQCw5sso";
    private static final String consumerSecret = "ntqqLEGyBJsGbMi6kdybC7lUBlJKsZ1RuRa8lFbMXLCHEh7VWD";
    private static final String token = "1312243397688782850-MQmWy3OJN30ofo5FZx6w73rkNMoAkK";
    private static final String tokenSecret = "jnRSAHWwdLmQm0HVW9OOfxD2O8aVJL14r5Uv0l8d2OuB7";
    private static final String bearerToken = "AAAAAAAAAAAAAAAAAAAAAJgiIQEAAAAASKsySmauclKIif2r1nVEQzley5w%3DYCjJQMOgBwX3qoLummZYAuOAVx32ehjuK5DBZoM4ntGa84DtPV";

    private static HTTPClient httpClient
            = new HTTPClient();
    private static TwitterOauthHeaderGenerator twitterOauthHeaderGenerator
            = new TwitterOauthHeaderGenerator(consumerKey, consumerSecret, token, tokenSecret);
    private static SessionController sessionController
            = new SessionController();

    private TwitterStream ts;

    public TwitterAPIService() {
    }


    /**
     * Working
     * @param rule
     * @return
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
     * Working
     * @param rules
     * @return
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
     * Working
     * @param ruleValue
     * @return
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
     * Working
     * @param ruleValues
     * @return
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
     * Working
     * @param ruleId
     * @return
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
     * Working
     * @param ruleIds
     * @return
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
     * Working
     * @return
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
     * Working
     * @param username
     * @return
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
     * Working
     * @param usernames
     * @return
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


    // TODO: Implement These

    @Override
    public void getFilteredStream() {
        TwitterStream twitterStream = getTwitterStreamInstance();

        ArrayList<String> track = new ArrayList<String>();

        Set<TweetFilterRule> activeRuleSet = getTweetFilteringRules();
        activeRuleSet.forEach(i -> {
            System.out.println("Adding rule to filter set");
            System.out.println(i.getValue());
            track.add(i.getValue());
        });

        String[] trackArray = track.toArray(new String[track.size()]);
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(trackArray);

        twitterStream.addListener(getFilteredStatusListener());

        twitterStream.filter(filterQuery);
        // Need to create the filter query here
        // Does this automatically filter on the posted rules?

//        ArrayList<Long> follow = new ArrayList<Long>();
//        ArrayList<String> track = new ArrayList<String>();
//        for (String arg : args) {
//            if (isNumericalArgument(arg)) {
//                for (String id : arg.split(",")) {
//                    follow.add(Long.parseLong(id));
//                }
//            } else {
//                track.addAll(Arrays.asList(arg.split(",")));
//            }
//        }
//        long[] followArray = new long[follow.size()];
//        for (int i = 0; i < follow.size(); i++) {
//            followArray[i] = follow.get(i);
//        }
//        String[] trackArray = track.toArray(new String[track.size()]);
//
//        // filter() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
//        twitterStream.filter(new FilterQuery(0, followArray, trackArray));
    }

    /**
     * Working
     */
    @Override
    public void getSampleStream() {
        TwitterStream twitterStream = getTwitterStreamInstance();
        twitterStream.addListener(getSampleStatusListener());
        twitterStream.sample();
    }

    // Method below are used to build outgoing requests

    /**
     * Working
     * @param username
     * @return
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
     * Working
     * @return
     */
    private TwitterRequestObject buildGetTweetStreamRulesRequest() {
        TwitterRequestObject requestObject = new TwitterRequestObject();
        requestObject.setUrl(BASE_URL + "/2/tweets/search/stream/rules");
        requestObject.setAuthorizationValue("Bearer " + bearerToken);

        requestObject.setJsonBody("{}");

        return requestObject;
    }

    /**
     * Working
     * @param rule
     * @return
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
     * Working
     * @param filters
     * @return
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
     * Working
     * @param ruleValue
     * @return
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
     * Working
     * @param ruleValues
     * @return
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
     * Working
     * @param ruleId
     * @return
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
     * Working
     * @param ruleIds
     * @return
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

    // Methods below are used to parse incoming responses

    /**
     * Working
     * @param response
     * @return
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
     * Working
     * @param response
     * @return
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
     * Working
     * @param status
     * @return
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
     * Working
     * @return
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
     * Working
     * @return
     */
    private StatusListener getSampleStatusListener() {
        StatusListener newStatusListener = new StatusListener() {

            int counter = 0;

            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                Tweet newTweet = parseTweetFromResponse(status);
                sessionController.addTweetToSampleTree(newTweet);
                counter++;
                if (counter >= 1000) { // Gets N tweets before shutting down
                    System.out.println("Printing Final Tree:");
                    RedBlackTree<Tweet> copiedSampleStream = sessionController.getCopyOfOnePercentTweetTree();
                    System.out.println(copiedSampleStream.toString());
                    ts.shutdown();
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        return newStatusListener;
    }

    /**
     * Working
     * @return
     */
    private StatusListener getFilteredStatusListener() {
        StatusListener newStatusListener = new StatusListener() {

            int counter = 5;

            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                Tweet newTweet = parseTweetFromResponse(status);
                sessionController.addTweetToFilteredTree(newTweet);
                counter++;
                if (counter >= 5) { // Gets N tweets before shutting down
                    System.out.println("Printing Final Filtered Tree:");
                    RedBlackTree<Tweet> copiedSampleStream = sessionController.getCopyOfFilteredTweetTree();
                    System.out.println(copiedSampleStream.toString());
                    ts.shutdown();
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        return newStatusListener;
    }

}
