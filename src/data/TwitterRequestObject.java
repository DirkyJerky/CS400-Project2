package data;

public class TwitterRequestObject {

    String url;
    String jsonBody;

    public TwitterRequestObject() {
        this.url = null;
        this.jsonBody = null;
    }

    public TwitterRequestObject(String url, String jsonBody) {
        this.url = url;
        this.jsonBody = jsonBody;
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
}
