[diffbot-java](http://diffbot.com/)
=================

Diffbot's Product API turns any commerce site into an on-demand product database.

To learn more about it, check out [diffbot.com](http://diffbot.com/)!

How it works 
------------

Diffbot offers a RESTful API for turning unstructured web pages into structured json data.

This is example JSON data made by the articles API at http://api.diffbot.com/v2/article :

```JSON
{
  "type": "article",
  "icon": "http://www.diffbot.com/favicon.ico",
  "title": "Diffbot's New Product API Teaches Robots to Shop Online",
  "author": "John Davi",
  "date": "Wed, 31 Jul 2013 08:00:00 GMT",
  "media": [
    {
      "primary": "true",
      "link": "http://www.youtube.com/embed/lfcri5ungRo?feature=oembed",
      "type": "video"
    }
  ],
  "tags": [
    "e-commerce",
    "SaaS"
  ]
  "url": "http://blog.diffbot.com/diffbots-new-product-api-teaches-robots-to-shop-online/",
  "humanLanguage": "en",
  "text": "Diffbot's human wranglers are proud today to announce the release of our newest product: an API for... products!\nThe Product API can be used for extracting clean, structured data from any e-commerce product page. It automatically makes available all the product data you'd expect: price, discount/savings amount, shipping cost, product description, any relevant product images, SKU and/or other product IDs.\nEven cooler: pair the Product API with Crawlbot, our intelligent site-spidering tool, and let Diffbot determine which pages are products, then automatically structure the entire catalog. Here's a quick demonstration of Crawlbot at work:\nWe've developed the Product API over the course of two years, building upon our core vision technology that's extracted structured data from billions of web pages, and training our machine learning systems using data from tens of thousands of unique shopping sites. We can't wait for you to try it out.\nWhat are you waiting for? Check out the Product API documentation and dive on in! If you need a token, check out our pricing and plans (including our Free plan).\nQuestions? Hit us up at support@diffbot.com ."
}
```

Using diffbot-java client library this data can be mapped to any class with fields names matching json fields (type,icon,title,author ...).

This is an example java class that could be filled with data from the articles API according to fields' names :
```Java
public class BlogPost {
    private String title;
    private String author;
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
```

In order to fill this class with data all we need to do is  :
```Java
	...
	
    public static void main(String[] args ){

        String testToken="3....9c359";//set your api token here 

        // Create DiffbotClient instance with the appropriate token
        DiffbotClient articlesClient = new DiffbotClient(testToken);

        /*
         article data is loaded into the BlogPost class fields depending on the fields' names
         if the class has a certain field available in the RESTful article resource then the field is filled with the
         appropriate data
         */
       
        BlogPost b= (BlogPost) articlesClient.getArticle(BlogPost.class,"http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/");
        System.out.println(b.toString());

    }
	...
```

Compiling the project 
---------------------

From withing the diffbot-java directory run:

```Shell 
$> mvn install
```

Now diffbot-java library dependency is accessible via the following maven dependency :
```XML
	<dependency>
        <groupId>diffbot</groupId>
        <artifactId>diffbot-java</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```

Runing the Demo 
---------------

Please notice that the previous steps are necessary in order to compile and run the demo.
 
Demo code :

```Java
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
```

Compile the demo with maven from within the demo folder :

```Shell 
$> mvn install
```

See it working by using the maven exec command :

```Shell 
$> mvn exec:java
```