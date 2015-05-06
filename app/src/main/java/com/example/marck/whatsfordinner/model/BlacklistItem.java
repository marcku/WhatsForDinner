package com.example.marck.whatsfordinner.model;

import java.sql.Date;

/**
 * Created by jt on 06.05.2015.
 */
public class BlacklistItem {

    private String link;
    private Long expiration;


    public BlacklistItem(String link, Long expiration) {
        this.link = link;
        this.expiration = expiration;
        this.link = link;
    }

    public String getLink(){
        return link;
    }

    public Long getExpiration() {
        return expiration;
    }
}
