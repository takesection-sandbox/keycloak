package pigumer.com.example.openid;

import java.util.Map;

public class RequestJson {

    private Map<String, String> queryStringParameters;

    public Map<String, String> getQueryStringParameters() {
        return queryStringParameters;
    }

    public void setQueryStringParameters(Map<String, String> queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }
}
