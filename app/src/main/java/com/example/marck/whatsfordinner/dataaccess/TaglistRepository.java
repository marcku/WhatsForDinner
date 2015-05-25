package com.example.marck.whatsfordinner.dataaccess;

import android.content.Context;

import com.example.marck.whatsfordinner.model.TaglistItem;

public class TaglistRepository {
    public int Id;
    public String Name;

    public TaglistItem[] getTagListEntries(Context context) {

        DBManager dbManager = new DBManager(context);
        TaglistItem[] tagListArray;
        tagListArray = dbManager.getTagList();

        return tagListArray;

    }
}

