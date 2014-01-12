package com.diffbot.clients;

/**
 * Created by wadi chemkhi on 02/01/14.
 * Email : wadi.chemkhi@gmail.com
 */
public class Article {
    private String icon;
    private String author;
    private String notFromJSON;


    @Override
    public String toString() {
        return "Article - icon: "+icon+" author: "+author;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNotFromJSON() {
        return notFromJSON;
    }

    public void setNotFromJSON(String notFromJSON) {
        this.notFromJSON = notFromJSON;
    }
}
