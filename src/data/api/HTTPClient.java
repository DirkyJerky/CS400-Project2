package data.api;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class HTTPClient {

    private OkHttpClient client;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public HTTPClient() {
        client = new OkHttpClient();
    }

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
            System.out.println("Response Code: " + response.code());
            return response;
        } catch (IOException e) {
            System.out.println("An IOException has occurred.");
            e.printStackTrace();
            return null;
        }

    }

    public Response sendOneTimeGetRequest(TwitterRequestObject requestObject) {
        // RequestBody body = RequestBody.create(requestObject.getJsonBody(), JSON);
        Request request = new Request.Builder()
                .header("Authorization", requestObject.getAuthorizationValue())
                .header("Content-Type", "application/json")
                .url(requestObject.getUrl())
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println("Response Code: " + response.code());
            return response;
        } catch (IOException e) {
            System.out.println("An IOException has occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public Response sendStreamedGetRequest(TwitterRequestObject requestObject) {
        return null;
    }


}
