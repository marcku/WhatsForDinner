package com.example.marck.whatsfordinner.dataaccess;

import android.content.Context;

import java.util.Date;

import com.example.marck.whatsfordinner.model.BlacklistItem;

public class BlackListRepository {

    public BlacklistItem[] getBlacklistEntries(Context context) {

        DBManager dbManager = new DBManager(context);

//        dbManager.deleteDatabase();
//        dbManager.createTestEntries();

        BlacklistItem[] blackListArray;
        blackListArray = dbManager.getBlackList();

        return blackListArray;

    }

    public void insertIntoBlacklist(Context context, String title, String imageSrc, String link) {

        DBManager dbManager = new DBManager(context);
        dbManager.insertBlackListItem(title, imageSrc, link, 100000L);

    }

    public boolean isBlackListItemInDb(Context context, String link){

        DBManager dbManager = new DBManager(context);
        return dbManager.isBlackListItemInDb(link);

    }

}
