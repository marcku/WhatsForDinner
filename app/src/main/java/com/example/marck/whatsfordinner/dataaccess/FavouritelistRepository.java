package com.example.marck.whatsfordinner.dataaccess;

import android.database.Cursor;

import com.example.marck.whatsfordinner.model.BlacklistItem;
import com.example.marck.whatsfordinner.model.FavouritelistItem;

import java.util.List;

/**
 * Created by jt on 06.05.2015.
 */
public class FavouritelistRepository {

    DataAccess da = new DataAccess();


    public void saveFavouritelistEntry(FavouritelistItem fItem)
    {
        //TODO: Validation of input data

        da.DropSqlStatement(
                "INSERT INTO " +
                "Favouritelist" +
                "(Id , Expiration)" +
                " VALUES ( "+ fItem.getLink() + ")");
    }

    public List<FavouritelistItem> getFavouritelistEntries() {
        Cursor c = da.DropRawSqlQuery("SELECT * FROM Favouritelist");

        List<FavouritelistItem> fList = null;

        int Column1 = c.getColumnIndex("link");

        // Check if our result was valid.
        c.moveToFirst();
        if (c != null) {
            // Loop through all Results

            do {
                FavouritelistItem fItem = new FavouritelistItem(
                        c.getString(Column1));
                fList.add(fItem);
            } while (c.moveToNext());
        }
        return fList;
    }
}
