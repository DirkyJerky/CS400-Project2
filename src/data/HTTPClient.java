package data;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HTTPClient {

    OkHttpClient client;

    public HTTPClient() {
        client = new OkHttpClient();
    }

    public void sendPostRequest(String urlString, String requestBody) {
        Request request = new Request.Builder()
                                .header("Authorization", "TOKEN")
                                .url(urlString)
                                .build();
    }

    public void sendGetRequest(String urlString, String requestBody) {

    }


}
