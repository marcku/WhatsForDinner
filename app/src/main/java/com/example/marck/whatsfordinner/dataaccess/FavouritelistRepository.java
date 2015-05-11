package com.example.marck.whatsfordinner.dataaccess;

import android.database.Cursor;

import com.example.marck.whatsfordinner.model.FavouritelistItem;

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

    public FavouritelistItem[] getFavouritelistEntries() {
        Cursor c = da.DropRawSqlQuery("SELECT * FROM Favouritelist");

        FavouritelistItem[] favArray = null;

        int Column1 = c.getColumnIndex("title");
        int Column2 = c.getColumnIndex("imageSrc");
        int Column3 = c.getColumnIndex("link");

        // Check if our result was valid.
        c.moveToFirst();
        if (c != null) {
            // Loop through all Results

            for (int i = 0; c.moveToNext(); i++){
                FavouritelistItem favItem = new FavouritelistItem(
                        c.getString(Column1),
                        c.getString(Column2),
                        c.getString(Column3));
                favArray[i] = favItem;
            }

/*            do {
                FavouritelistItem fItem = new FavouritelistItem(
                        c.getString(Column1));
                fList.add(fItem);
            } while (c.moveToNext());*/
        }
        return favArray;
    }
}
