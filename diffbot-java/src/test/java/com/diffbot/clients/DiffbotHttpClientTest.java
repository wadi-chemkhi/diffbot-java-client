package com.diffbot.clients;

import org.junit.Test;

import java.io.IOException;

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

}
