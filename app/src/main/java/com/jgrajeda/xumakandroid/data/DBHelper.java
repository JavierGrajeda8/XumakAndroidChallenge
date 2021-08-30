package com.jgrajeda.xumakandroid.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "xumak";
    public static final String CHARACTER_TABLE_NAME = "character";
    public static final String CHARACTER_COLUMN_ID = "_id";
    public static final String CHARACTER_COLUMN_CHAR_ID = "char_id";
    public static final String CHARACTER_COLUMN_NAME = "name";
    public static final String CHARACTER_COLUMN_BIRTHDAY = "birthday";
    public static final String CHARACTER_COLUMN_OCCUPATION = "occupation";
    public static final String CHARACTER_COLUMN_IMG = "img";
    public static final String CHARACTER_COLUMN_STATUS = "status";
    public static final String CHARACTER_COLUMN_NICKNAME = "nickname";
    public static final String CHARACTER_COLUMN_APPEARANCE = "appearance";
    public static final String CHARACTER_COLUMN_PORTRAYED = "portrayed";
    public static final String CHARACTER_COLUMN_CATEGORY = "category";
    public static final String CHARACTER_COLUMN_FAVORITE = "favorite";
    public static final String CHARACTER_COLUMN_BETTER_CALL_SAUL = "better_call_saul";

    public DBHelper(Context  context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + CHARACTER_TABLE_NAME + " (" +
                CHARACTER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CHARACTER_COLUMN_CHAR_ID + " INTEGER, " +
                CHARACTER_COLUMN_NAME + " TEXT, " +
                CHARACTER_COLUMN_BIRTHDAY + " TEXT, " +
                CHARACTER_COLUMN_OCCUPATION + " TEXT, " +
                CHARACTER_COLUMN_IMG + " TEXT, " +
                CHARACTER_COLUMN_STATUS + " TEXT, " +
                CHARACTER_COLUMN_NICKNAME + " TEXT, " +
                CHARACTER_COLUMN_APPEARANCE + " TEXT, " +
                CHARACTER_COLUMN_PORTRAYED + " TEXT, " +
                CHARACTER_COLUMN_CATEGORY + " TEXT, " +
                CHARACTER_COLUMN_FAVORITE + " INT, " +
                CHARACTER_COLUMN_BETTER_CALL_SAUL + " TEXT " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CHARACTER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


}
