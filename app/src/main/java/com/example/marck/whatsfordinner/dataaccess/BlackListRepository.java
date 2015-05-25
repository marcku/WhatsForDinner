package com.example.marck.whatsfordinner.dataaccess;

import android.content.Context;

import java.util.Date;

import com.example.marck.whatsfordinner.model.BlacklistItem;

public class BlackListRepository {
    public int Id;
    public Date ExpirationDate;

    public BlacklistItem[] getBlacklistEntries(Context context) {

        DBManager dbManager = new DBManager(context);
        BlacklistItem[] blackListArray;
        blackListArray = dbManager.getBlackList();

        return blackListArray;

    }

}
