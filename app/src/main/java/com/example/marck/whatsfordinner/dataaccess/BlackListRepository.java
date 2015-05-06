package com.example.marck.whatsfordinner.dataaccess;

import android.database.Cursor;
import com.example.marck.whatsfordinner.model.BlacklistItem;
import java.util.List;

/**
 * Created by jt on 06.05.2015.
 */
public class BlackListRepository {

    DataAccess da = new DataAccess();


    public void saveBlackListEntry(BlacklistItem blItem)
    {
        //TODO: Validation of input data

        da.DropSqlStatement(
                "INSERT INTO " +
                "Blacklist" +
                "(Id , Expiration)" +
                " VALUES ( "+ blItem.getLink() +
                "," + blItem.getExpiration() + ")");
    }

    public List<BlacklistItem> getBlacklistEntries() {
        Cursor c = da.DropRawSqlQuery("SELECT * FROM Blacklist");

        List<BlacklistItem> blList = null;

        int Column1 = c.getColumnIndex("link");
        int Column2 = c.getColumnIndex("expiration");

        // Check if our result was valid.
        c.moveToFirst();
        if (c != null) {
            // Loop through all Results

            do {
                BlacklistItem blitem = new BlacklistItem(
                        c.getString(Column1),
                        c.getLong(Column2));
                blList.add(blitem);
            } while (c.moveToNext());
        }
        return blList;
    }
}
