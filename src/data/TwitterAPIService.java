package data;

import com.google.gson.*;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    public Set<TweetFilterRule> getTweetFiltersRules() {
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

    }

    @Override
    public void getSampleFilteredStream() {

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

    // TODO: Implement these

    private TwitterRequestObject buildGetTweetStreamRequest() {
        // TODO: Implement
        return null;
    }

    private TwitterRequestObject buildGetTweetSampleStreamRequest() {
        // TODO: Implement
        return null;
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

    private Set<Tweet> parseTweetFromResponse(Response response){
        // TODO: Implement
        return null;
    }



    /**
     * Filtered Stream:
     * GET /2/tweets/search/stream
     * GET /2/tweets/search/stream/rules
     * POST /2/tweets/search/stream/rules
     */

    /**
     * Sample Stream:
     * GET /2/tweets/search/sample/stream
     */


    /**
     * Users:
     * GET /2/users/by/username/:username (lookup by single username)
     */
}
