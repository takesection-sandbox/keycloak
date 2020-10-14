package pigumer.com.example.openid.adapter;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class TokenServiceImpl {

    private final String authorization;
    private final String redirectUri;
    private final String tokenEndpoint;

    public TokenServiceImpl() {
        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        System.out.println(clientId + ":" + clientSecret);
        authorization = "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));

        redirectUri = System.getenv("REDIRECT_URI");
        tokenEndpoint = System.getenv("TOKEN_ENDPOINT");
    }

    void parseResponse(InputStream is) throws IOException {
        try (ByteArrayOutputStream buf = new ByteArrayOutputStream()) {
            int ch;
            while ((ch = is.read()) != -1) {
                buf.write(ch);
            }
            // FIXME
            System.out.println(new String(buf.toByteArray(), StandardCharsets.UTF_8));
        }
    }

    void postTokenEndpoint(CloseableHttpClient client, HttpPost post) throws IOException {
        try (CloseableHttpResponse response = client.execute(post)) {
            int statusCode = response.getStatusLine().getStatusCode();
            // FIXME
            System.out.println("Response: status code: " + statusCode);
            // FIXME
            parseResponse(response.getEntity().getContent());
        }
    }

    void execute(UrlEncodedFormEntity entity) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(tokenEndpoint);
            post.addHeader("Content-Type", "application/x-www-form-urlencoded");
            post.addHeader("Authorization", authorization);
            post.setEntity(entity);
            postTokenEndpoint(client, post);
        }
    }

    public void getToken(String code) throws IOException {
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("grant_type", "authorization_code"));
        form.add(new BasicNameValuePair("code", code));
        form.add(new BasicNameValuePair("redirect_uri", redirectUri));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, StandardCharsets.UTF_8);
        execute(entity);
        System.out.println("end: getToken");
    }

}
