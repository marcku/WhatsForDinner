package com.example.marck.whatsfordinner.model;

/**
 * Created by jt on 06.05.2015.
 */
public class FavouritelistItem {

    private String title;
    private String imageSrc;
    private String link;

    public FavouritelistItem(String title, String imageSrc, String link) {
        this.title = title;
        this.imageSrc = imageSrc;
        this.link = link;
    }

    public String getTitle(){
        return title;
    }

    public String getimageSrc(){
        return imageSrc;
    }

    public String getLink(){
        return link;
    }
}
