package com.jeftech.kbfc.Db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *
 * @author ajith
 * @date 10/11/15
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sfm";
    private static final int DATABASE_VERSION = 86;
    private static final String TAG = "DBAdapter";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL(CustomersDB.DATABASE_CREATE);

        } catch (SQLException e) {
            Log.i(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

            db.execSQL("DROP TABLE IF EXISTS " + CustomersDB.DATABASE_TABLE);
            

        } catch (SQLException e) {
            e.printStackTrace();
            Log.i(TAG, "onUpgrade: ==>" + e.getMessage());
        }
        onCreate(db);

    }
}
