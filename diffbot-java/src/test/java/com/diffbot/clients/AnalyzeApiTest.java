package com.diffbot.clients;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wadi chemkhi on 14/01/14.
 * Email : wadi.chemkhi@gmail.com
 */
public class AnalyzeApiTest {

    private String testToken="353883355a5c7ff1793b14f81e19c359";

    @Test
    public void testAnalyseJacksonResponse() throws IOException {
        DiffbotClient client = new DiffbotClient(testToken,"2");
        JsonNode a= (JsonNode) client.analyze(DiffbotClient.ResponseType.Jackson,"https://github.com/wadi-chemkhi/diffbot-java-client");
        System.out.println(a.toString());
    }
}
