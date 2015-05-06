package com.example.marck.whatsfordinner.model;

/**
 * Created by jt on 06.05.2015.
 */
public class FavouritelistItem {

    private String link;
    private Long expiration;

    public FavouritelistItem(String link) {
        this.link = link;
    }

    public String getLink(){
        return link;
    }
}
