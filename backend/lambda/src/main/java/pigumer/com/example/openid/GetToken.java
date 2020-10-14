package pigumer.com.example.openid;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pigumer.com.example.openid.adapter.TokenServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetToken implements RequestStreamHandler {

    private final ObjectMapper objectMapper;
    private final String location;
    private final TokenServiceImpl tokenService;

    public GetToken() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        location = System.getenv("LOCATION");

        tokenService = new TokenServiceImpl();
    }

    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        LambdaLogger logger = context.getLogger();
        String requestJson;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int ch;
            while ((ch = inputStream.read()) != -1) {
                out.write(ch);
            }
            requestJson = new String(out.toByteArray(), StandardCharsets.UTF_8);
            logger.log(requestJson);
        }
        RequestJson request = objectMapper.readValue(requestJson, RequestJson.class);
        String code = request.getQueryStringParameters().get("code");
        logger.log("code = " + code);

        tokenService.getToken(code);

        ResultJson resultJson = new ResultJson(location);
        objectMapper.writeValue(outputStream, resultJson);

    }
}
