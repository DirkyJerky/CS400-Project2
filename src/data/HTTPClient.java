package data;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HTTPClient {

    private OkHttpClient client;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public HTTPClient() {
        client = new OkHttpClient();
    }

    public void sendPostRequest(TwitterRequestObject requestObject) {
        RequestBody body = RequestBody.create(requestObject.getJsonBody(), JSON);
        Request request = new Request.Builder()
                                .header("Authorization", "Bearer " + "AAAAAAAAAAAAAAAAAAAAAJgiIQEAAAAASKsySmauclKIif2r1nVEQzley5w%3DYCjJQMOgBwX3qoLummZYAuOAVx32ehjuK5DBZoM4ntGa84DtPV")
                                .url(requestObject.getUrl())
                                .method("POST", body)
                                .build();
    }

    public void sendOneTimeGetRequest(TwitterRequestObject requestObject) {

    }

    public void sendStreamedGetRequest(String urlString, String requestBody) {

    }


}
