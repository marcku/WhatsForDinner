package com.example.marck.whatsfordinner;

/**
 * Created by marck on 01.05.15.
 */
public class Tag {
    private String name;
    private String link;

    public Tag(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }
}
