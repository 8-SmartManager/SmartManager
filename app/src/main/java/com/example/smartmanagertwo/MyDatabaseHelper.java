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
    public static final String COL_THUCHI_ID = "ThuChi_Id";
    public static final String COL_THUCHI_TYPE = "ThuChi_Type";
    public static final String COL_THUCHI_TIME = "ThuChi_Time";
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

    // Bảng Mục Tiêu
    public static final String TBL_NAME_MUC_TIEU = "MucTieu";
    public static final String COL_MUCTIEU_ID = "MucTieu_ID";
    public static final String COL_MUCTIEU_NAME = "MucTieu_Name";
    public static final String COL_MUCTIEU_SOTIENMUCTIEU = "MucTieu_SoTIenMucTieu";
    public static final String COL_MUCTIEU_SOTIENDATDUOC = "MucTieu_SoTIenDatDuoc";
    public static final String COL_MUCTIEU_NGAYKETTHUC = "MucTieu_NgayKetThuc";
    public static final String COL_MUCTIEU_IMAGE="MucTieu_Image";
    public static final String COL_MUCTIEU_IMAGE_COLOR="MucTieu_Image_Color";
    public static final String COL_MUCTIEU_LUUY = "MucTieu_LuuY";


    //Bảng Danh Sách Mua Sắm
//    public static final String TBL_NAME_DANHSACH="DanhSach";
//    public static final String COL_DANHSACH_ID = "DanhSach_ID";
//    public static final String COL_DANHSACH_NAME = "DanhSach_Name";
//    public static final String COL_DANHSACH_PRICE = "DanhSach_Price";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlThuChi = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_THUCHI + "(" + COL_THUCHI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_THUCHI_TYPE + " VARCHAR(50), "+ COL_THUCHI_NAME + " VARCHAR(50), "+ COL_THUCHI_ACCOUNT + " VARCHAR(50), " + COL_THUCHI_AMOUNT + " DOUBLE, " + COL_THUCHI_TIME + " DATE)";
        sqLiteDatabase.execSQL(sqlThuChi);
        String sqlNhacNho = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_NHAC_NHO + "(" + COL_NHACNHO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NHACNHO_TYPE + " VARCHAR(50), " + COL_NHACNHO_NAME + " VARCHAR(50), "+  COL_NHACNHO_PERIOD + " VARCHAR(50)," + COL_NHACNHO_START_DAY+ " DATE," + COL_NHACNHO_REMIND_TIME + " TIME )";
        sqLiteDatabase.execSQL(sqlNhacNho);
//        String sqlThongKe = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_THONGKE + "(" + COL_THONGKE_PERCENT + " VARCHAR(10), " + COL_THONGKE_NAME + " VARCHAR(50), " + COL_THONGKE_AMOUNT + " VARCHAR(50))";
//        sqLiteDatabase.execSQL(sqlThongKe);
        String sqlMucTieu = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_MUC_TIEU + "(" + COL_MUCTIEU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MUCTIEU_NAME + " VARCHAR(50), " + COL_MUCTIEU_SOTIENMUCTIEU + " DOUBLE, "+  COL_MUCTIEU_SOTIENDATDUOC + " DOUBLE," + COL_MUCTIEU_NGAYKETTHUC+ " DATE," + COL_MUCTIEU_IMAGE + " INT, "+ COL_MUCTIEU_IMAGE_COLOR + " INT, " + COL_MUCTIEU_LUUY + " VARCHAR(50))";
        sqLiteDatabase.execSQL(sqlMucTieu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_THUCHI);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_NHAC_NHO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_MUC_TIEU);
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

    public int getCountMucTieu(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME_MUC_TIEU, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public void createSomeData(){
        int countThuChi = getCountThuChi();
        if(countThuChi == 0){
            execSql("INSERT INTO " + TBL_NAME_THUCHI + " VALUES(null, 'Chi', 'Ăn uống', 'Tiền mặt', 10000, '2021-05-28')");
            execSql("INSERT INTO " + TBL_NAME_THUCHI + " VALUES(null, 'Chi', 'Giải trí', 'Tiền mặt', 20000, '2021-05-28')");
            execSql("INSERT INTO " + TBL_NAME_THUCHI + " VALUES(null, 'Thu', 'Tiền lương', 'Tiền mặt', 30000, '2021-05-28')");
            execSql("INSERT INTO " + TBL_NAME_THUCHI + " VALUES(null, 'Thu', 'Tiền trợ cấp', 'Tiền mặt', 100000, '2021-05-28')");
            execSql("INSERT INTO " + TBL_NAME_THUCHI + " VALUES(null, 'Chi', 'Ăn uống', 'Tiền mặt', 50000, '2021-05-28')");
        }
    }
    public  void createSomeMucTieuHoatDong(){
        int countMucTieu = getCountMucTieu();
        if(countMucTieu == 0){
            execSql("INSERT INTO " + TBL_NAME_MUC_TIEU + " VALUES(null, 'Mua xe','20000000','15000000','2024-05-28',null,'-11873872','Quan trọng')");
            execSql("INSERT INTO " + TBL_NAME_MUC_TIEU + " VALUES(null, 'Mua nhà','2500000000','15000000','2021-09-23',null,'-11873872','Quan trọng')");
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

    public  Cursor getData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return  db.rawQuery(sql, null);
    }

}
