package com.example.marck.whatsfordinner.dataaccess;

import android.provider.BaseColumns;

public interface DBDefinition extends BaseColumns {

    public static final String TABLE_Favourites = "Favourites";
    public static final String TABLE_Blacklist = "Blacklist";
    public static final String TABLE_TagCloud = "TagCloud";

    // Columns of the Favourites table
    public static final String COL_FAV_Id = "Id";
    public static final String COL_FAV_Title = "Title";
    public static final String COL_FAV_ImageSrc = "ImageSrc";
    public static final String COL_FAV_Link = "Link";

    //Columns of the Blacklist table
    public static final String COL_BLKLIST_Id = "Id";
    public static final String COL_BLKLIST_Title = "Title";
    public static final String COL_BLKLIST_ImageSrc = "ImageSrc";
    public static final String COL_BLKLIST_Link = "Link";
    public static final String COL_BLKLIST_Expiration = "Expiration";

    //Columns of the TagCloud table
    public static final String COL_TAG_Id = "Id";
    public static final String COL_TAG_Name = "Name";

}
