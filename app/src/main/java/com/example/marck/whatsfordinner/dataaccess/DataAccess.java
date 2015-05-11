package com.example.marck.whatsfordinner.dataaccess;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jt on 06.05.2015.
 */
public class DataAccess extends Activity {

    SQLiteDatabase db = null;

    private void InitDataBase(){

        try {
            db = this.openOrCreateDatabase("WhatsForDinner", MODE_PRIVATE, null);

            db.execSQL("CREATE TABLE IF NOT EXISTS " +
                    "Blacklist"
                    + " (link VARCHAR, expiration long);");

            db.execSQL("CREATE TABLE IF NOT EXISTS " +
                    "Favouritelist"
                    + " (Link VARCHAR);");

            db.execSQL("CREATE TABLE IF NOT EXISTS " +
                    "Taglist"
                    + " (name VARCHAR);");

        }
        catch (Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public void DropSqlStatement(String sql)
    {
        this.InitDataBase();
        db.execSQL(sql);
    }

    public Cursor DropRawSqlQuery(String sql)
    {
        this.InitDataBase();
        return db.rawQuery(sql, null);
    }
}
