package com.diffbot.clients;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wadi chemkhi on 02/01/14.
 * Email : wadi.chemkhi@gmail.com
 */
public class DiffbotHttpClientTest {

    private String testToken="353883355a5c7ff1793b14f81e19c359";

    @Test
    public void getArticleTest() throws IOException {
        DiffbotHttpClient articlesClient = new DiffbotHttpClient(testToken);
        articlesClient.getArticle("http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/");
    }
    @Test
    public void getJsonTest() throws IOException {
        DiffbotHttpClient client = new DiffbotHttpClient(testToken);
        Map<String,String> params = new HashMap<>();
        params.put("fields","title");
        String q=client.getJson("article","http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/",params);
        System.out.println(q);
    }

}
