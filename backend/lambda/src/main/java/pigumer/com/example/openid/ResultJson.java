package pigumer.com.example.openid;

import java.util.HashMap;
import java.util.Map;

public class ResultJson {

    private final int statusCode = 302;

    private final Map<String, String> headers;

    public ResultJson(String location) {
        this.headers = new HashMap<String, String>();
        headers.put("Location", location);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
