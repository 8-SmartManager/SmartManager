package com.example.smartmanagertwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.database.CursorWindowCompat;

import java.sql.Time;
import java.time.LocalDate;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "user_db.sqlite";

    //bảng hoạt động thu chi
    public static final String TBL_NAME_THUCHI = "ThuChi";
    public static final String COL_THUCHI_NAME = "ThuChi_Name";
    public static final String COL_THUCHI_ACCOUNT = "ThuChi_Account";
    public static final String COL_THUCHI_AMOUNT = "ThuChi_Amount";



    // Bảng nhắc nhở
    public static final String TBL_NAME_NHAC_NHO = "NhacNho";
    public static final String COL_NHACNHO_ID = "NhacNho_ID";
    public static final String COL_NHACNHO_TYPE = "NhacNho_Type";
    public static final String COL_NHACNHO_NAME = "NhacNho_Name";
    public static final String COL_NHACNHO_PERIOD = "NhacNho_Period";
    public static final String COL_NHACNHO_START_DAY = "NhacNho_Start_Day";
    public static final String COL_NHACNHO_REMIND_TIME= "NhacNho_Remind_Time";


    // Bảng Thống kê
    public static final String TBL_NAME_THONGKE = "ThongKe";
    public static final String COL_THONGKE_PERCENT = "ThongKe_Percent";
    public static final String COL_THONGKE_NAME = "ThongKe_Name";
    public static final String COL_THONGKE_AMOUNT = "ThongKe_Amount";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlThuChi = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_THUCHI + "(" + COL_THUCHI_NAME + " VARCHAR(50), "+ COL_THUCHI_ACCOUNT + " VARCHAR(50), " + COL_THUCHI_AMOUNT + " VARCHAR(50))";
        sqLiteDatabase.execSQL(sqlThuChi);
        String sqlNhacNho = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_NHAC_NHO + "(" + COL_NHACNHO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NHACNHO_TYPE + " VARCHAR(50), " + COL_NHACNHO_NAME + " VARCHAR(50), "+  COL_NHACNHO_PERIOD + " VARCHAR(50)," + COL_NHACNHO_START_DAY+ " DATE," + COL_NHACNHO_REMIND_TIME + " TIME )";
        sqLiteDatabase.execSQL(sqlNhacNho);
        String sqlThongKe = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_THONGKE + "(" + COL_THONGKE_PERCENT + " VARCHAR(10), " + COL_THONGKE_NAME + " VARCHAR(50), " + COL_THONGKE_AMOUNT + " VARCHAR(50))";
        sqLiteDatabase.execSQL(sqlThongKe);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_THUCHI);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_NHAC_NHO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_THONGKE);
        onCreate(sqLiteDatabase);

    }

    public int getCountThuChi(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME_THUCHI, null);
        int count = cursor.getCount();
        cursor.close();
        return count;

    }
    public int getCountNhacNho(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME_NHAC_NHO, null);
        int count = cursor.getCount();
        cursor.close();
        return count;

    }
    public int getCountThongKe(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME_THONGKE, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void createSomeData(){
        int countThuChi = getCountThuChi();
        if(countThuChi == 0){
            execSql("INSERT INTO " + TBL_NAME_THUCHI + " VALUES('Ăn uống', 'Tiền mặt', 20000)");
            execSql("INSERT INTO " + TBL_NAME_THUCHI + " VALUES('Giải trí', 'Tiền mặt', 20000)");
            execSql("INSERT INTO " + TBL_NAME_THUCHI + " VALUES('Tiền học', 'Tiền mặt', 20000)");
        }

        int countThongKe = getCountThongKe();
        if (countThongKe == 0){
            execSql("INSERT INTO " + TBL_NAME_THONGKE + " VALUES('80%', 'Ăn uống', 2000000)");
            execSql("INSERT INTO " + TBL_NAME_THONGKE + " VALUES('15%', 'Giải trí', 200000)");
            execSql("INSERT INTO " + TBL_NAME_THONGKE + " VALUES('5%', 'Tiền học', 20000)");
        }
    }
public  void  createSomeNhacNhoData(){
    int countNhacNho = getCountNhacNho();
    if(countNhacNho == 0){
        execSql("INSERT INTO " + TBL_NAME_NHAC_NHO + " VALUES(null, 'Tiết Kiệm','Mua xe','Hàng tháng','2021-10-19','20:30:00')");
        execSql("INSERT INTO " + TBL_NAME_NHAC_NHO + " VALUES(null, 'Thu','Lương','Hàng tháng','2021-11-14','07:30:00')");
        execSql("INSERT INTO " + TBL_NAME_NHAC_NHO + " VALUES(null, 'Chi','Chơi game','Hàng tuần','2021-09-10','20:00:00')");
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
