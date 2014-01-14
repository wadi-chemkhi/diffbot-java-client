package com.diffbot.clients;

import com.diffbot.clients.models.Article;
import com.diffbot.clients.models.BlogPost;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wadi chemkhi on 14/01/14.
 * Email : wadi.chemkhi@gmail.com
 */
public class ArticleApiTest {
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
}
