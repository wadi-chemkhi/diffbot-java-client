package com.diffbot.clients;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by wadi chemkhi on 02/01/14.
 * Email : wadi.chemkhi@gmail.com
 */
public class DiffbotClientTest {

    private String testToken="353883355a5c7ff1793b14f81e19c359";

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
        DiffbotClient articlesClient = new DiffbotClient(testToken);
        Product a= (Product) articlesClient.getProducts(Product.class,"http://www.overstock.com/Home-Garden/iRobot-650-Roomba-Vacuuming-Robot/7886009/product.html").get(0);
        System.out.println(a.toString());
    }

}
