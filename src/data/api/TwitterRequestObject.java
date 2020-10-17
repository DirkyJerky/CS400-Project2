package data.api;

import java.util.Objects;

public class TwitterRequestObject {

    private String url;
    private String jsonBody;
    private String authorizationValue;

    public TwitterRequestObject() {
        this.url = null;
        this.jsonBody = null;
        this.authorizationValue = null;
    }

    public TwitterRequestObject(String url, String jsonBody) {
        this.url = url;
        this.jsonBody = jsonBody;
        this.authorizationValue = null;
    }

    public TwitterRequestObject(String url, String jsonBody, String authorizationValue) {
        this.url = url;
        this.jsonBody = jsonBody;
        this.authorizationValue = authorizationValue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJsonBody() {
        return jsonBody;
    }

    public void setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
    }

    public String getAuthorizationValue() {
        return authorizationValue;
    }

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
