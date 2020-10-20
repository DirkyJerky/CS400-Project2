// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: Written by Jacob Lorenz
//                  A general HTTPClient class capable of making GET and POST calls

package data.api;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

/**
 * HTTPClient is a class dedicated to general implementations of HTTP GET and POST API calls.
 * The implementation wraps the okhttp library to be used for our specific Twitter API needs.
 */
public class HTTPClient {

    private OkHttpClient client;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public HTTPClient() {
        client = new OkHttpClient();
    }

    /**
     * A function that performs a general POST request based on the parameters in the requestObject=
     * @param requestObject a TwitterRequestObject containing url, body, and authorization header value
     * @return a Response object if the call was successful, null otherwise
     */
    public Response sendOneTimePostRequest(TwitterRequestObject requestObject) {
        RequestBody body = RequestBody.create(requestObject.getJsonBody(), JSON);
        Request request = new Request.Builder()
                                .header("Authorization", requestObject.getAuthorizationValue())
                                .header("Content-Type", "application/json")
                                .url(requestObject.getUrl())
                                .method("POST", body)
                                .build();
        try {
            Response response = client.newCall(request).execute();
            // System.out.println("Response Code: " + response.code());
            return response;
        } catch (IOException e) {
            System.out.println("An IOException has occurred.");
            e.printStackTrace();
            return null;
        }

    }

    /**
     * A function that performs a general GET request based on the parameters in the requestObject=
     * @param requestObject a TwitterRequestObject containing url, body, and authorization header value
     * @return a Response object if the call was successful, null otherwise
     */
    public Response sendOneTimeGetRequest(TwitterRequestObject requestObject) {
        Request request = new Request.Builder()
                .header("Authorization", requestObject.getAuthorizationValue())
                .header("Content-Type", "application/json")
                .url(requestObject.getUrl())
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            // System.out.println("Response Code: " + response.code());
            return response;
        } catch (IOException e) {
            System.out.println("An IOException has occurred.");
            e.printStackTrace();
            return null;
        }
    }
}
