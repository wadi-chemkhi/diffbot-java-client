package com.diffbot.clients;

import org.codehaus.jackson.JsonNode;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by wadi chemkhi on 02/01/14.
 * Email : wadi.chemkhi@gmail.com
 */
public class DiffbotClientTest {

    private String testToken="353883355a5c7ff1793b14f81e19c359";

    @Test
    public void getArticleTestV2() throws IOException {
        DiffbotClient articlesClient = new DiffbotClient(testToken,"2");
        Article a= (Article) articlesClient.getArticle(Article.class,"http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/");
        BlogPost b= (BlogPost) articlesClient.getArticle(BlogPost.class,"http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/");
        System.out.println(a.toString());
        System.out.println(b.toString());
    }

    @Test
    public void getArticleTest() throws IOException {
        DiffbotClient articlesClient = new DiffbotClient(testToken);
        Article a= (Article) articlesClient.getArticle(Article.class,"http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/");
        BlogPost b= (BlogPost) articlesClient.getArticle(BlogPost.class,"http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/");
        System.out.println(a.toString());
        System.out.println(b.toString());
    }

    @Test
    public void getProductTest() throws IOException {
        DiffbotClient client = new DiffbotClient(testToken);
        List<Product> l= (List) client.getProducts(Product.class,"http://www.overstock.com/Home-Garden/iRobot-650-Roomba-Vacuuming-Robot/7886009/product.html");
        for(Product p :l)
        System.out.println(p.toString());
    }

    @Test
    public void callApiTest() throws IOException {
        DiffbotClient client = new DiffbotClient(testToken);
        Article a= (Article) client.callApi("article", Article.class, "http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/");
        System.out.println(a.toString());
    }

    @Test
    public void callApiJacksonTest() throws IOException {
        DiffbotClient client = new DiffbotClient(testToken);
        JsonNode a= (JsonNode) client.callApi("article",DiffbotClient.ResponseType.Jackson,"http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/");
        System.out.println(a.toString());
    }

    @Test
    public void callApiJSONObjectTest() throws IOException {
        DiffbotClient client = new DiffbotClient(testToken);
        JSONObject a= (JSONObject) client.callApi("article",DiffbotClient.ResponseType.JSONObject,"http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/");
        System.out.println(a.toString());
    }

    @Test
    public void callApiCustomJsonNodeTest() throws IOException {
        DiffbotClient client = new DiffbotClient(testToken);
        JsonNode a= (JsonNode) client.callApi("CustomAPI",DiffbotClient.ResponseType.Jackson,"http://wadi-chemkhi.blogspot.com/2013/09/la-conquete-de-lexcellence.html");
        System.out.println(a.toString());
    }
    @Test
    public void callApiCustomClassTest() throws IOException {
        DiffbotClient client = new DiffbotClient(testToken);
        BlogPost a= (BlogPost) client.callApi("CustomAPI",BlogPost.class,"http://wadi-chemkhi.blogspot.com/2013/09/la-conquete-de-lexcellence.html");
        System.out.println(a.toString());
    }
}
