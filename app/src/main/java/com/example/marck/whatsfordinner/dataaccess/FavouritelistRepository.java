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

    public void insertIntoFavouriteList(Context context, String title, String imageSrc, String link) {

        DBManager dbManager = new DBManager(context);
        dbManager.insertFavouriteListItem(title, imageSrc, link);

    }

    public boolean isFavouriteListItemInDb(Context context, String link){

        DBManager dbManager = new DBManager(context);
        return dbManager.isFavouriteListItemInDb(link);

    }

}
