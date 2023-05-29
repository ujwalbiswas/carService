package com.example.carservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "booking.db";
    public static final String TABLE_NAME = "carServiceBooking";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "PhoneNo";
    public static final String COL_4 = "Date";
    public static final String COL_5 = "Time";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, Name TEXT, PhoneNo INTEGER, Date TEXT, Time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Integer ID, String Nm, Integer PhoneNo, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, ID);
        contentValues.put(COL_2, Nm);
        contentValues.put(COL_3, PhoneNo);
        contentValues.put(COL_4, date);
        contentValues.put(COL_5, time);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(Integer ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = ("Select * From "+TABLE_NAME+" Where ID="+ID);
        Cursor cursor=db.rawQuery(query,null);

        return cursor;
    }
    public Integer deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }

    public boolean updateData(String id, String name, String pn, String date, String time)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,pn);
        contentValues.put(COL_4,date);
        contentValues.put(COL_5,time);
        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return true;
    }

}
