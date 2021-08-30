package com.jgrajeda.xumakandroid.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jgrajeda.xumakandroid.classes.Character;

import java.util.ArrayList;

public class DataProvider {
    Context context;
    public DataProvider(Context context) {
        this.context = context;
    }

    public long saveCharacter(Character ch) {
        SQLiteDatabase database = new DBHelper(context).getWritableDatabase();
        ArrayList<Character> c = getCharacterById(ch.getChar_id());
        long newRowId = 0;
        if (c.size() == 0){
            ContentValues values = new ContentValues();
            String occupation = "";

            occupation = ch.getOcupation();
            String appearance = "";
            String[] appAux = ch.getAppearance();
            for (String app: appAux) {
                appearance += app + ", ";
            }
            appearance = appearance.substring(0, appearance.length() - 2);
            values.put(DBHelper.CHARACTER_COLUMN_NAME, ch.getName());
            values.put(DBHelper.CHARACTER_COLUMN_CHAR_ID, ch.getChar_id());
            values.put(DBHelper.CHARACTER_COLUMN_BIRTHDAY, ch.getBirthday());
            values.put(DBHelper.CHARACTER_COLUMN_OCCUPATION, occupation);
            values.put(DBHelper.CHARACTER_COLUMN_IMG, ch.getImg());
            values.put(DBHelper.CHARACTER_COLUMN_STATUS, ch.getStatus());
            values.put(DBHelper.CHARACTER_COLUMN_NICKNAME   , ch.getNickname());
            values.put(DBHelper.CHARACTER_COLUMN_APPEARANCE, appearance);
            values.put(DBHelper.CHARACTER_COLUMN_PORTRAYED, ch.getPortrayed());
            values.put(DBHelper.CHARACTER_COLUMN_CATEGORY, ch.getCategory());
            values.put(DBHelper.CHARACTER_COLUMN_FAVORITE, 0);
            values.put(DBHelper.CHARACTER_COLUMN_BETTER_CALL_SAUL, "");
            newRowId = database.insert(DBHelper.CHARACTER_TABLE_NAME, null, values);

        } else {
            newRowId = c.size();
        }

        return newRowId;
    }

    public int setFavorite(int char_id, boolean favorite){
        SQLiteDatabase database = new DBHelper(context).getReadableDatabase();

        ContentValues cv = new ContentValues();
        if (favorite == true){
            cv.put(DBHelper.CHARACTER_COLUMN_FAVORITE,1);
        } else {
            cv.put(DBHelper.CHARACTER_COLUMN_FAVORITE,0);
        }
        String selection =
                DBHelper.CHARACTER_COLUMN_CHAR_ID + " = ? " ;
        String[] selectionArgs = {"" + char_id + ""};
        int update = database.update(DBHelper.CHARACTER_TABLE_NAME, cv, selection, selectionArgs);
        return update;
    }

    public ArrayList<Character> getCharacterById(int id) {
        SQLiteDatabase database = new DBHelper(context).getReadableDatabase();
        ArrayList<Character> chs = new ArrayList<>();
        String[] projection = {
                DBHelper.CHARACTER_COLUMN_ID,
                DBHelper.CHARACTER_COLUMN_NAME,
                DBHelper.CHARACTER_COLUMN_CHAR_ID,
                DBHelper.CHARACTER_COLUMN_BIRTHDAY,
                DBHelper.CHARACTER_COLUMN_IMG,
                DBHelper.CHARACTER_COLUMN_STATUS,
                DBHelper.CHARACTER_COLUMN_NICKNAME,
                DBHelper.CHARACTER_COLUMN_APPEARANCE,
                DBHelper.CHARACTER_COLUMN_PORTRAYED,
                DBHelper.CHARACTER_COLUMN_CATEGORY,
                DBHelper.CHARACTER_COLUMN_FAVORITE,
                DBHelper.CHARACTER_COLUMN_OCCUPATION,
                DBHelper.CHARACTER_COLUMN_BETTER_CALL_SAUL,

        };
        String orderBy = DBHelper.CHARACTER_COLUMN_FAVORITE + " DESC, " + DBHelper.CHARACTER_COLUMN_CHAR_ID + " ASC";
        Cursor cursor;
        String selection = null;
        String[] selectionArgs = null;
        if (id != 0){
            selection = DBHelper.CHARACTER_COLUMN_CHAR_ID + " = ? " ;
            selectionArgs = new String[]{"" + id + ""};
        }


        cursor = database.query(
                DBHelper.CHARACTER_TABLE_NAME,   // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                orderBy                                      // don't sort
        );

        try {
            while (cursor.moveToNext()) {
                Character chAux = new Character();
                chAux.setChar_id(cursor.getInt( cursor.getColumnIndex(DBHelper.CHARACTER_COLUMN_CHAR_ID)));
                chAux.setName(cursor.getString( cursor.getColumnIndex(DBHelper.CHARACTER_COLUMN_NAME)));
                chAux.setBirthday(cursor.getString( cursor.getColumnIndex(DBHelper.CHARACTER_COLUMN_BIRTHDAY)));
                chAux.setImg(cursor.getString( cursor.getColumnIndex(DBHelper.CHARACTER_COLUMN_IMG)));
                chAux.setCategory(cursor.getString( cursor.getColumnIndex(DBHelper.CHARACTER_COLUMN_CATEGORY)));
                chAux.setNickname(cursor.getString( cursor.getColumnIndex(DBHelper.CHARACTER_COLUMN_NICKNAME)));
                chAux.setPortrayed(cursor.getString( cursor.getColumnIndex(DBHelper.CHARACTER_COLUMN_PORTRAYED)));
                chAux.setStatus(cursor.getString( cursor.getColumnIndex(DBHelper.CHARACTER_COLUMN_STATUS)));
                chAux.setFavorite(cursor.getInt( cursor.getColumnIndex(DBHelper.CHARACTER_COLUMN_FAVORITE)));
                String occupAux = cursor.getString(cursor.getColumnIndex(DBHelper.CHARACTER_COLUMN_OCCUPATION));
                String appeAux = cursor.getString( cursor.getColumnIndex(DBHelper.CHARACTER_COLUMN_APPEARANCE));
                chAux.setOccupation(occupAux.split(","));
                chAux.setAppearance(appeAux.split(","));
                Log.i("Character", chAux.getName() + " " + chAux.getFavorite());
                chs.add(chAux);
            }
        } finally {
            cursor.close();
        }

        Log.i("TAG", "The total cursor count is " + cursor.getCount());
        return chs;
    }
}
