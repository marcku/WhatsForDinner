package com.example.marck.whatsfordinner.model;

/**
 * Created by jt on 06.05.2015.
 */
public class BlacklistItem {

    private String title;
    private String imageSrc;
    private String link;
    private Long expiration;

    public BlacklistItem(String title, String imageSrc, String link, Long expiration) {
        this.title = title;
        this.imageSrc = imageSrc;
        this.link = link;
        this.expiration = expiration;
    }

    public String getTitle(){
        return title;
    }

    public String getImageSrc(){
        return imageSrc;
    }

    public String getLink(){
        return link;
    }

    public Long getExpiration() {
        return expiration;
    }
}
