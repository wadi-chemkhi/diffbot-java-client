package com.diffbot.clients.demo;

import com.diffbot.clients.DiffbotClient;

/**
 * Created by wadi chemkhi on 02/01/14.
 * Email : wadi.chemkhi@gmail.com
 */
public class Demo {

    public static void main(String[] args ){

        String testToken="353883355a5c7ff1793b14f81e19c359";

        // Create DiffbotClient instance with the appropriate token
        DiffbotClient articlesClient = new DiffbotClient(testToken);

        /*
         article data is loaded into the Article class fields depending on the fields' names
         if the class has a certain field available in the RESTful article resource then the field is filled with the
         appropriate data
         */
        Article a= (Article) articlesClient.getArticle(Article.class,"http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/");
        System.out.println(a.toString());

        /*
         Same thing with the BlogPost class. Fields are filled with the appropriate data depending on their names
         */
        BlogPost b= (BlogPost) articlesClient.getArticle(BlogPost.class,"http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/");
        System.out.println(b.toString());

    }
}
