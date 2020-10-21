// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: Written by Jacob Lorenz
//                  The TwitterRequestObject captures all necessary information to perform a HTTP request to Twitter.
//                  Information such as URL, Authorization header, and request body are populated in the Business Logic classes.

package data.api;

import java.util.Objects;

/**
 * POJO used to organize information for a HTTP GET or POST call to Twitter APIs.
 */
public class TwitterRequestObject {

    /**
     * Core Object Fields
     */
    private String url;
    private String jsonBody;
    private String authorizationValue;

    /**
     * Default Constructor
     */
    public TwitterRequestObject() {
        this.url = null;
        this.jsonBody = null;
        this.authorizationValue = null;
    }

    /**
     * Constructor for a TwitterRequestObject with a String url as the only parameter
     * @param url the String url extension for this TwitterRequestObject
     */
    public TwitterRequestObject(String url) {
        this.url = url;
        this.jsonBody = null;
        this.authorizationValue = null;
    }

    /**
     * Constructor for a TwitterRequestObject with both String url and String jsonBody parameters passed
     * @param url the String url extension for this TwitterRequestObject
     * @param jsonBody the String jsonBody for this TwitterRequestObject
     */
    public TwitterRequestObject(String url, String jsonBody) {
        this.url = url;
        this.jsonBody = jsonBody;
        this.authorizationValue = null;
    }

    /**
     * Constructor for a TwitterRequestObject with all three parameters passed; String url, String jsonBody, and
     * String authorizationValue
     * @param url the String url for this TwitterRequestObject
     * @param jsonBody the String jsonBody for this TwitterRequestObject
     * @param authorizationValue the String authorizationValue for this TwitterRequestObject
     */
    public TwitterRequestObject(String url, String jsonBody, String authorizationValue) {
        this.url = url;
        this.jsonBody = jsonBody;
        this.authorizationValue = authorizationValue;
    }

    /**
     * Returns the url associated with this TwitterRequestObject
     * @return the string url associated with this TwitterRequestObject
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the url associated with this TwitterRequestObject
     * @param url the String url associated with this TwitterRequestObject
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Returns the String JSON body associated with this TwitterRequestObject
     * @return the string JSON body associated with this TwitterRequestObject
     */
    public String getJsonBody() {
        return jsonBody;
    }

    /**
     * Set the String JSON body associated with this TwitterRequestObject
     * @param jsonBody the String JSON body associated with this TwitterRequestObject
     */
    public void setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
    }

    /**
     * Returns the authorization header value associated with this TwitterRequestObject
     * This can either be OAuth 1.0, or Bearer token based depending on Twitter APIs
     * @return the string url associated with this TwitterRequestObject
     */
    public String getAuthorizationValue() {
        return authorizationValue;
    }

    /**
     * Set the Authorization header's value for this TwitterRequestObject
     * @param authorizationValue the String authorizationValue associated with this TwitterRequestObject
     */
    public void setAuthorizationValue(String authorizationValue) {
        this.authorizationValue = authorizationValue;
    }

    @Override
    public String toString() {
        return "TwitterRequestObject{" +
                "url='" + url + '\'' +
                ", jsonBody='" + jsonBody + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, jsonBody, authorizationValue);
    }
}
