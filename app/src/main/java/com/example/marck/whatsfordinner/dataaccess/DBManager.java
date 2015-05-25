package com.example.marck.whatsfordinner.dataaccess;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.marck.whatsfordinner.model.BlacklistItem;
import com.example.marck.whatsfordinner.model.FavouritelistItem;
import com.example.marck.whatsfordinner.model.TaglistItem;

import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.TABLE_Favourites;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.TABLE_Blacklist;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.TABLE_TagCloud;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_FAV_Id;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_FAV_Link;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_BLKLIST_Id;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_BLKLIST_ExpDate;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_TAG_Id;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_TAG_Name;


public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "WhatsForDinner.db";
    private static final int DATABASE_VERSION = 1;

    public DBManager (Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_Favourites + " ("
                + COL_FAV_Id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_FAV_Link + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + TABLE_Blacklist + " ("
                + COL_BLKLIST_Id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_BLKLIST_ExpDate + " DATE NOT NULL);");

        db.execSQL("CREATE TABLE " + TABLE_TagCloud + " ("
                + COL_TAG_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TAG_Name + " TEXT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Favourites);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Blacklist);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TagCloud);
        onCreate(db);

    }

    public void deleteDatabase(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Favourites);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Blacklist);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TagCloud);
        onCreate(db);

    }

    public void testInsert(){



    }

    public FavouritelistItem[] getFavouritesList() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_Favourites, null);

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

        }
        return favArray;
    }

    public BlacklistItem[] getBlackList(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_Blacklist, null);

        BlacklistItem[] blackListArray = null;

        int Column1 = c.getColumnIndex("title");
        int Column2 = c.getColumnIndex("imageSrc");
        int Column3 = c.getColumnIndex("link");
        int Column4 = c.getColumnIndex("expiration");

        // Check if our result was valid.
        c.moveToFirst();
        if (c != null) {

            // Loop through all Results
            for (int i = 0; c.moveToNext(); i++){
                BlacklistItem blitem = new BlacklistItem(
                        c.getString(Column1),
                        c.getString(Column2),
                        c.getString(Column3),
                        c.getLong(Column4));
                blackListArray[i] = blitem;
            }

        }
        return blackListArray;
    }

    public TaglistItem[] getTagList() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_TagCloud, null);

        TaglistItem[] tagListArray = null;

        int Column1 = c.getColumnIndex("link");

        // Check if our result was valid.
        c.moveToFirst();
        if (c != null) {

            // Loop through all Results
            for (int i = 0; c.moveToNext(); i++){
                TaglistItem tagItem = new TaglistItem(
                        c.getString(Column1));
                tagListArray[i] = tagItem;
            }

        }
        return tagListArray;
    }

}