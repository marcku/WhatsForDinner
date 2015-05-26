package com.example.marck.whatsfordinner.dataaccess;

import android.content.ContentValues;
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
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_FAV_Title;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_FAV_ImageSrc;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_FAV_Link;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_BLKLIST_Id;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_BLKLIST_Title;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_BLKLIST_ImageSrc;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_BLKLIST_Link;
import static com.example.marck.whatsfordinner.dataaccess.DBDefinition.COL_BLKLIST_Expiration;
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
                + COL_FAV_Title + " Text NOT NULL,"
                + COL_FAV_ImageSrc + " Text NOT NULL,"
                + COL_FAV_Link + " Text NOT NULL);");

        db.execSQL("CREATE TABLE " + TABLE_Blacklist + " ("
                + COL_BLKLIST_Id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_BLKLIST_Title + " Text NOT NULL,"
                + COL_BLKLIST_ImageSrc + " Text NOT NULL,"
                + COL_BLKLIST_Link + " Text NOT NULL,"
                + COL_BLKLIST_Expiration + " Long NOT NULL);");

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

    public void createTestEntries(){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_BLKLIST_Title , "Mikrowellenkuchen");
        values.put(COL_BLKLIST_ImageSrc , "http://static.chefkoch-cdn.de/ck.de/rezepte/178/178133/723434-tiniefix-mikrowellenkuchen.jpg");
        values.put(COL_BLKLIST_Link , "http://mobile.chefkoch.de/rezepte/m1781331287996596/Mikrowellenkuchen.html");
        values.put(COL_BLKLIST_Expiration , 1000000000L);

        db.insertOrThrow(TABLE_Blacklist, null, values);

        db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(COL_FAV_Title , "Mandarinen - Torte");
        values.put(COL_FAV_ImageSrc , "http://static.chefkoch-cdn.de/ck.de/rezepte/71/71914/207365-tiniefix-mandarinen-torte.jpg");
        values.put(COL_FAV_Link , "http://mobile.chefkoch.de/rezepte/m719141174576214/Mandarinen-Torte.html");

        db.insertOrThrow(TABLE_Favourites, null, values);

    }

    public void insertBlackListItem(String title, String imageSrc, String link, Long expiration){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_BLKLIST_Title , title);
        values.put(COL_BLKLIST_ImageSrc , imageSrc);
        values.put(COL_BLKLIST_Link , link);
        values.put(COL_BLKLIST_Expiration , expiration);

        db.insertOrThrow(TABLE_Blacklist, null, values);

    }

    public void insertBlackListItemFromFavItem(String title, String imageSrc, String link, Long expiration){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Favourites, COL_FAV_Link + " = '" + link + "'", null);

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_BLKLIST_Title , title);
        values.put(COL_BLKLIST_ImageSrc , imageSrc);
        values.put(COL_BLKLIST_Link , link);
        values.put(COL_BLKLIST_Expiration , expiration);

        db.insertOrThrow(TABLE_Blacklist, null, values);

    }

    public void insertFavouriteListItem(String title, String imageSrc, String link){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FAV_Title , title);
        values.put(COL_FAV_ImageSrc , imageSrc);
        values.put(COL_FAV_Link , link);

        db.insertOrThrow(TABLE_Favourites, null, values);

    }

    public void insertIntoFavouriteListFromBlItem(String title, String imageSrc, String link){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Blacklist, COL_BLKLIST_Link + " = '" + link + "'", null);

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FAV_Title , title);
        values.put(COL_FAV_ImageSrc , imageSrc);
        values.put(COL_FAV_Link , link);

        db.insertOrThrow(TABLE_Favourites, null, values);

    }

    public void insertTagListItem(String name){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TAG_Name, name);

        db.insertOrThrow(TABLE_TagCloud, null, values);

    }

    public FavouritelistItem[] getFavouritesList() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_Favourites, null);

        FavouritelistItem[] favArray = new FavouritelistItem[100];

        int Column1 = c.getColumnIndex(COL_FAV_Title);
        int Column2 = c.getColumnIndex(COL_FAV_ImageSrc);
        int Column3 = c.getColumnIndex(COL_FAV_Link);

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

        BlacklistItem[] blackListArray = new BlacklistItem[100];

        int Column1 = c.getColumnIndex(COL_BLKLIST_Title);
        int Column2 = c.getColumnIndex(COL_BLKLIST_ImageSrc);
        int Column3 = c.getColumnIndex(COL_BLKLIST_Link);
        int Column4 = c.getColumnIndex(COL_BLKLIST_Expiration);

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

        int Column1 = c.getColumnIndex(COL_TAG_Name);

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

    public boolean isFavouriteListItemInDb(String link) {

        boolean itemInDb = false;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " + COL_FAV_Link + " FROM " + TABLE_Favourites + " WHERE " + COL_FAV_Link + " = '" + link + "'", null);

        // Check if we got results.
        if(c.getCount() > 0){

            itemInDb = true;

        }
        c.close();

        return itemInDb;
    }

    public boolean isBlackListItemInDb(String link){

        boolean itemInDb = false;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " + COL_BLKLIST_Link + " FROM " + TABLE_Blacklist + " WHERE " + COL_BLKLIST_Link + " = '" + link + "'", null);

        // Check if we got results.
        if(c.getCount() > 0){

            itemInDb = true;

        }
        c.close();

        return itemInDb;

    }

}
