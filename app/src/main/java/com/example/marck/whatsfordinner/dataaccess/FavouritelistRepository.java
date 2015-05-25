package com.example.marck.whatsfordinner.dataaccess;

import android.content.Context;

import com.example.marck.whatsfordinner.model.FavouritelistItem;

public class FavouritelistRepository {
    public int Id;
    public String Link;


    public FavouritelistItem[] getFavouriteListEntries(Context context) {

        DBManager dbManager = new DBManager(context);
        FavouritelistItem[] favouriteListArray;
        favouriteListArray = dbManager.getFavouritesList();

        return favouriteListArray;

    }
}
