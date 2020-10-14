package pigumer.com.example.openid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResultJsonTest {

    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ResultJson result = new ResultJson("http://localhost/test");
        String json = mapper.writeValueAsString(result);
        Assertions.assertEquals("{\"statusCode\":302,\"headers\":{\"Location\":\"http://localhost/test\"}}", json);
    }
}
