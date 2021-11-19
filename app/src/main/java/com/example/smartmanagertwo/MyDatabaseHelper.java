package com.example.smartmanagertwo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.core.database.CursorWindowCompat;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "user_db.sqlite";

    //bảng hoạt động thu chi
    public static final String TBL_NAME_THUCHI = "ThuChi";
    public static final String COL_THUCHI_NAME = "ThuChi_Name";
    public static final String COL_THUCHI_ACCOUNT = "ThuChi_Account";
    public static final String COL_THUCHI_AMOUNT = "ThuChi_Amount";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_THUCHI + "(" + COL_THUCHI_NAME + " VARCHAR(50), "+ COL_THUCHI_ACCOUNT + " VARCHAR(50), " + COL_THUCHI_AMOUNT + " VARCHAR(50))";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_THUCHI);
        onCreate(sqLiteDatabase);

    }

    public int getCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME_THUCHI, null);
        int count = cursor.getCount();
        cursor.close();
        return count;

    }

    public void createSomeData(){
        int count = getCount();
        if(count == 0){
            execSql("INSERT INTO " + TBL_NAME_THUCHI + " VALUES('Ăn uống', 'Tiền mặt', 20000)");
            execSql("INSERT INTO " + TBL_NAME_THUCHI + " VALUES('Giải trí', 'Tiền mặt', 20000)");
            execSql("INSERT INTO " + TBL_NAME_THUCHI + " VALUES('Tiền học', 'Tiền mặt', 20000)");
        }
    }

    public void execSql(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    //Select
    public  Cursor getData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return  db.rawQuery(sql, null);
    }
}
