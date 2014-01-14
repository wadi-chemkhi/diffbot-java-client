package com.diffbot.clients.models;

/**
 * Created by wadi chemkhi on 10/01/14.
 * Email : wadi.chemkhi@gmail.com
 */
public class Product {
    String title;
    String  description;
    String offerPrice;
    String regularPrice;
    String saveAmount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getSaveAmount() {
        return saveAmount;
    }

    public void setSaveAmount(String saveAmount) {
        this.saveAmount = saveAmount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", offerPrice='" + offerPrice + '\'' +
                ", regularPrice='" + regularPrice + '\'' +
                ", saveAmount='" + saveAmount + '\'' +
                '}';
    }
}
