package com.jeftech.kbfc.Db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import com.jeftech.kbfc.Models.Customer;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ajith on 30/12/15.
 */
public class CustomersDB {
    public static final String DATABASE_TABLE = "customers";
    private static final String KEY_ID = "store_id";
    private static final String KEY_STORE_NAME = "store_name";
    private static final String KEY_STORE_CODE = "store_code";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CITY = "city";
    private static final String KEY_STATE = "state";
    private static final String KEY_TYPE = "type";
    private static final String KEY_AREA_ID = "area_id";
    private static final String KEY_DIVISION_CODE = "division_code";
    public static final String DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE
                    + " (" + KEY_ID + " INTEGER NOT NULL, "
                    + KEY_STORE_NAME + " TEXT NOT NULL, "
                    + KEY_STORE_CODE + " TEXT NOT NULL, "
                    + KEY_PHONE + " TEXT, "
                    + KEY_ADDRESS + " TEXT , "
                    + KEY_CITY + " TEXT , "
                    + KEY_STATE + " TEXT ,"
                    + KEY_TYPE + " TEXT ,"
                    + KEY_AREA_ID + " TEXT ,"
                    + KEY_DIVISION_CODE + " TEXT "
                    + ");";
    private static String TAG = "CustomersDB";
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public CustomersDB(Context ctx) {

        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
        db = DBHelper.getWritableDatabase();
    }

    //---open SQLite DB---
    public CustomersDB open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---close SQLite DB---
    public void close() {
        DBHelper.close();
    }

    //---Delete All Data from table in SQLite DB---
    public void deleteAll() {
        this.open();
        db.delete(DATABASE_TABLE, null, null);
        this.close();
    }

    //---insert data into SQLite DB---
    public long insert(int storeId, String storeName, String storeCode, String city, String state, String address, String phone, String type) {
        this.open();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID, storeId);
        initialValues.put(KEY_STORE_NAME, storeName);
        initialValues.put(KEY_STORE_CODE, storeCode);
        initialValues.put(KEY_ADDRESS, address);
        initialValues.put(KEY_PHONE, phone);
        initialValues.put(KEY_CITY, city);
        initialValues.put(KEY_STATE, state);
        initialValues.put(KEY_TYPE, type);
        Long ret = db.insert(DATABASE_TABLE, null, initialValues);
        this.close();
        return ret;
    }

    public int    getLastId() {
        this.open();
        int id = 0;
        final String MY_QUERY = "SELECT MAX(" + KEY_ID + ")  FROM " + DATABASE_TABLE;
        Cursor mCursor = db.rawQuery(MY_QUERY, null);
        try {
            if (mCursor.getCount() > 0) {
                mCursor.moveToFirst();
                id = mCursor.getInt(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
          mCursor.close();
            this.close();
        }
        return id;

    }

    public int getCount() {
        this.open();
        Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM " + DATABASE_TABLE, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        this.close();
        return count;
    }

    public String getStateHint() {
        String hint = "";
        Cursor cursor;
        SharedPreferences prefs = context.getSharedPreferences("UserDetailPreference", Context.MODE_PRIVATE);
        String userId = prefs.getString("userId", "0");
        this.open();
        cursor = db.query(DATABASE_TABLE, new String[]{KEY_STATE},
                          KEY_STATE + " LIKE ? ", new String[]{"kerala"}, KEY_STATE, null, null
        );
        if (cursor.getCount() >= 1) {
            hint = "kerala";
        }
        cursor = db.query(DATABASE_TABLE, new String[]{KEY_STATE},
                          KEY_STATE + "NOT LIKE ? ", new String[]{"kerala"}, KEY_STATE, null, null
        );
        if (cursor.getCount() >= 1) {
            hint = "kerala";
        }

        while (!cursor.isAfterLast()) {

        }
        cursor.close();
        this.close();
        return hint;
    }

    public ArrayList<Customer> getCustomers(String hint, int offset) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Cursor cursor;

        this.open();

        cursor = db.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_STORE_NAME, KEY_STORE_CODE, KEY_CITY, KEY_STATE,
                                  KEY_ADDRESS, KEY_PHONE, KEY_TYPE, KEY_AREA_ID, KEY_DIVISION_CODE},
                          "(" + KEY_STORE_NAME + " LIKE ?  OR " + KEY_STORE_CODE + " LIKE ? )" ,
                          new String[]{"%" + hint + "%", "%" + hint + "%"}, null, null, null, "" + offset
        );
        cursor.moveToFirst();
        Customer customer;
        while (!cursor.isAfterLast()) {
            customer = new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)
            );
            customer.setType(cursor.getString(7));
            customer.setAreaId(cursor.getString(8));
            customer.setDivisionCode(cursor.getString(9));
            customers.add(customer);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return customers;

    }

    public ArrayList<Customer> getCustomersForOrder(String hint, int offset) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Cursor cursor;

        this.open();

        cursor = db.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_STORE_NAME, KEY_STORE_CODE, KEY_CITY, KEY_STATE, KEY_ADDRESS, KEY_PHONE, KEY_TYPE, KEY_AREA_ID, KEY_DIVISION_CODE},
                          "(" + KEY_STORE_NAME + " LIKE ?  OR " + KEY_STORE_CODE + " LIKE ? )" + " AND " + KEY_STORE_CODE + " NOT LIKE ? ",
                          new String[]{"%" + hint + "%", "%" + hint + "%", ""}, null, null, null, "" + offset
        );
        cursor.moveToFirst();
        Customer customer;
        while (!cursor.isAfterLast()) {
            customer = new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)
            );
            customer.setType(cursor.getString(7));
            customer.setAreaId(cursor.getString(8));
            customer.setDivisionCode(cursor.getString(9));
            customers.add(customer);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return customers;

    }

    public Customer getCustomerByCode(String customerCode) {
        Customer customer = null;
        Cursor cursor;
        SharedPreferences prefs = context.getSharedPreferences("UserDetailPreference", Context.MODE_PRIVATE);
        String userId = prefs.getString("userId", "0");
        this.open();
        cursor = db.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_STORE_NAME, KEY_STORE_CODE, KEY_CITY, KEY_STATE, KEY_ADDRESS, KEY_PHONE, KEY_TYPE, KEY_AREA_ID, KEY_DIVISION_CODE},
                          KEY_STORE_CODE + " LIKE ?  ", new String[]{customerCode}, null, null, null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            customer = new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)
            );
            customer.setType(cursor.getString(7));
            customer.setAreaId(cursor.getString(8));
            customer.setDivisionCode(cursor.getString(9));
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return customer;
    }

    public void bulkInsert(JSONArray customers) {

        JSONObject obj;
        String sql = "INSERT INTO " + DATABASE_TABLE
                + " (" + KEY_ID + "," + KEY_STORE_NAME + "," + KEY_STORE_CODE + "," + KEY_CITY + "," + KEY_STATE + "," + KEY_ADDRESS + "," + KEY_PHONE + "," + KEY_TYPE + "," + KEY_AREA_ID + "," + KEY_DIVISION_CODE + ") "
                + " VALUES (?,?,?,?,?,?,?,?,?,?);";
        try {
            this.open();
            SQLiteStatement statement = db.compileStatement(sql);
            db.beginTransaction();
            for (int i = 0; i < customers.length(); i++) {
                statement.clearBindings();
                obj = (JSONObject) customers.get(i);
                statement.bindString(1, "" + obj.getInt("i"));
                statement.bindString(2, obj.getString("n"));
                statement.bindString(3, obj.getString("sc"));
                statement.bindString(4, obj.getString("c"));
                statement.bindString(5, obj.getString("s"));
                statement.bindString(6, obj.getString("a"));
                statement.bindString(7, obj.getString("p"));
                statement.bindString(8, obj.getString("cg"));
                statement.bindString(9, obj.getString("ai"));
                statement.bindString(10, obj.getString("dc"));
                statement.execute();
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            this.close();
        } catch (JSONException e) {
            Log.i(TAG, "" + e);
            this.close();
        }
        Log.i(TAG, "bulkInsert: " + "INSERT INTO " + DATABASE_TABLE
                + " (" + KEY_ID + "," + KEY_STORE_NAME + "," + KEY_STORE_CODE + "," + KEY_CITY + "," + KEY_STATE + "," + KEY_ADDRESS + "," + KEY_PHONE + "," + KEY_TYPE + "," + KEY_AREA_ID + "," + KEY_DIVISION_CODE + ") "
                + " VALUES (?,?,?,?,?,?,?,?,?,?);");
    }

}
