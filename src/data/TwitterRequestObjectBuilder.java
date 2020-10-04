package data;

public class TwitterRequestObjectBuilder {

    private static String BASE_URL = "https://api.twitter.com";

    /**
     * Filtered Stream:
     * GET /2/tweets/search/stream
     * GET /2/tweets/search/stream/rules
     * POST /2/tweets/search/stream/rules
     */

    /**
     * Sample Stream:
     * GET /2/tweets/search/sample/stream
     * GET /2/tweets/search/stream/rules
     * POST /2/tweets/search/stream/rules
     */

    /**
     * Users:
     * GET /2/users (lookup by list of IDs)
     * GET /2/users/:id (lookup by single id
     * GET /2/users/by (lookup by list of usernames
     * GET /2/users/by/username/:username (lookup by single username)
     */

    /**
     * Authentication:
     * GET /oauth/authenticate
     * GET /oauth/authorize
     * POST /oauth/access_token
     * POST /oauth/invalidate_token
     * POST /oauth/request_token
     * POST /oauth2/invalidate_token
     * POST /oauth2/token
     */

    public TwitterRequestObjectBuilder() {

    }

}
