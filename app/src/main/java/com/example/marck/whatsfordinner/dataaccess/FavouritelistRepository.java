package com.example.marck.whatsfordinner.dataaccess;

import android.content.Context;

import com.example.marck.whatsfordinner.model.FavouritelistItem;

public class FavouritelistRepository {

    public FavouritelistItem[] getFavouriteListEntries(Context context) {

        DBManager dbManager = new DBManager(context);
        FavouritelistItem[] favouriteListArray;
        favouriteListArray = dbManager.getFavouritesList();

        return favouriteListArray;

    }
}
