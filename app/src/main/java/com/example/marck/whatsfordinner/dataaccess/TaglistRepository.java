package com.example.marck.whatsfordinner.dataaccess;

import android.database.Cursor;

import com.example.marck.whatsfordinner.model.TaglistItem;

import java.util.List;

/**
 * Created by jt on 06.05.2015.
 */
public class TaglistRepository {

    DataAccess da = new DataAccess();


    public void saveTaglistEntry(TaglistItem tItem)
    {
        //TODO: Validation of input data

        da.DropSqlStatement(
                "INSERT INTO " +
                "Taglist" +
                "(Id , Expiration)" +
                " VALUES ( "+ tItem.getName() + ")");
    }

    public List<TaglistItem> getTaglistEntries() {
        Cursor c = da.DropRawSqlQuery("SELECT * FROM Taglist");

        List<TaglistItem> tList = null;

        int Column1 = c.getColumnIndex("link");

        // Check if our result was valid.
        c.moveToFirst();
        if (c != null) {
            // Loop through all Results

            do {
                TaglistItem tItem = new TaglistItem(
                        c.getString(Column1));
                tList.add(tItem);
            } while (c.moveToNext());
        }
        return tList;
    }
}
