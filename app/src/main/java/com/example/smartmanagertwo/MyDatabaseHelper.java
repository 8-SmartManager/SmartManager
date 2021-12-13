package com.example.smartmanagertwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
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

    //Bảng Thống kê chi chi tiết
//    public static final String TBL_NAME_THONG_KE_CHI_CHI_TIET = "ThongKeChiChiTiet";
//    public static final String COL_TKCHICHITIET_THELOAI = "TKChiChiTiet_TheLoai";
//    public static final String COL_TKCHICHITIET_TAIKHOAN = "TKChiChiTiet_TaiKhoan";
//    public static final String COL_TKCHICHITIET_MONEY = "TKChiChiTiet_Money";
//    public static final String COL_TKCHICHITIET_TIME = "TKChiChiTiet_Time";

    //Bảng Thống kê chi chi tiết
//    public static final String TBL_NAME_THONG_KE_THU_CHI_TIET = "ThongKeThuChiTiet";
//    public static final String COL_TKTHUCHITIET_THELOAI = "TKThuChiTiet_TheLoai";
//    public static final String COL_TKTHUCHITIET_TAIKHOAN = "TKThuChiTiet_TaiKhoan";
//    public static final String COL_TKTHUCHITIET_MONEY = "TKThuChiTiet_Money";
//    public static final String COL_TKTHUCHITIET_TIME = "TKThuChiTiet_Time";

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

    //Bảng Mục Tiêu Tạm dừng
    public static final String TBL_NAME_MUC_TIEU_PAUSED = "MucTieu_Paused";

    //Bảng Mục Tiêu Hoàn thành
    public static final String TBL_NAME_MUC_TIEU_COMPLETED = "MucTieu_Completed";
    //Bảng Danh sách mua sắm
    //Bảng Item Danh Sách Mua Sắm
    public static final String TBL_NAME_DANHSACH="DanhSach";
    public static final String COL_DANHSACH_ID = "DanhSach_ID";
    public static final String COL_DANHSACH_NAME = "DanhSach_Name";
    public static final String COL_DANHSACH_PRICE_TOTAL = "DanhSach_Price_Total";

    //Bảng Item Danh Sách Mua Sắm
    public static final String TBL_NAME_DANHSACHITEM="DanhSachItem";
    public static final String COL_DANHSACHITEM_ID = "DanhSachItem_ID";
    public static final String COL_DANHSACHITEM_NAME = "DanhSachItem_Name";
    public static final String COL_DANHSACHITEM_PRICE = "DanhSachItem_Price";
    public static final String COL_DANHSACHITEM_COMPLETE = "DanhSachItem_Complete";


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
        String sqlMucTieu = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_MUC_TIEU + "(" + COL_MUCTIEU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MUCTIEU_NAME + " VARCHAR(50), " + COL_MUCTIEU_SOTIENMUCTIEU + " DOUBLE, "+  COL_MUCTIEU_SOTIENDATDUOC + " DOUBLE," + COL_MUCTIEU_NGAYKETTHUC+ " DATE," + COL_MUCTIEU_IMAGE + " BLOB, "+ COL_MUCTIEU_IMAGE_COLOR + " INT, " + COL_MUCTIEU_LUUY + " VARCHAR(50))";
        sqLiteDatabase.execSQL(sqlMucTieu);
        String sqlMucTieuPaused = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_MUC_TIEU_PAUSED + "(" + COL_MUCTIEU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MUCTIEU_NAME + " VARCHAR(50), " + COL_MUCTIEU_SOTIENMUCTIEU + " DOUBLE, "+  COL_MUCTIEU_SOTIENDATDUOC + " DOUBLE," + COL_MUCTIEU_NGAYKETTHUC+ " DATE," + COL_MUCTIEU_IMAGE + " BLOB, "+ COL_MUCTIEU_IMAGE_COLOR + " INT, " + COL_MUCTIEU_LUUY + " VARCHAR(50))";
        sqLiteDatabase.execSQL(sqlMucTieuPaused);
        String sqlMucTieuCompleted = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_MUC_TIEU_COMPLETED + "(" + COL_MUCTIEU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MUCTIEU_NAME + " VARCHAR(50), " + COL_MUCTIEU_SOTIENMUCTIEU + " DOUBLE, "+  COL_MUCTIEU_SOTIENDATDUOC + " DOUBLE," + COL_MUCTIEU_NGAYKETTHUC+ " DATE," + COL_MUCTIEU_IMAGE + " BLOB, "+ COL_MUCTIEU_IMAGE_COLOR + " INT, " + COL_MUCTIEU_LUUY + " VARCHAR(50))";
        sqLiteDatabase.execSQL(sqlMucTieuCompleted);
//        String sqlThongKeChiTiet = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_THONG_KE_CHI_CHI_TIET + "(" + COL_TKCHICHITIET_THELOAI + " VARCHAR(50), " + COL_TKCHICHITIET_TAIKHOAN + " VARCHAR(50), " + COL_TKCHICHITIET_MONEY + " VARCHAR(50), " + COL_TKCHICHITIET_TIME + " DATE )";
//        sqLiteDatabase.execSQL(sqlThongKeChiTiet);
//        String sqlThongKeThuChiTiet = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_THONG_KE_THU_CHI_TIET + "(" + COL_TKTHUCHITIET_THELOAI + " VARCHAR(50), " + COL_TKTHUCHITIET_TAIKHOAN + " VARCHAR(50), " + COL_TKTHUCHITIET_MONEY + " VARCHAR(50), " + COL_TKTHUCHITIET_TIME + " DATE )";
//        sqLiteDatabase.execSQL(sqlThongKeThuChiTiet);
        String sqlDanhSach="CREATE TABLE IF NOT EXISTS " + TBL_NAME_DANHSACH +"(" + COL_DANHSACH_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +COL_DANHSACH_NAME +" VARCHAR(50), " + COL_DANHSACH_PRICE_TOTAL +" DOUBLE)";
        sqLiteDatabase.execSQL(sqlDanhSach);
        String sqlDanhSachItem="CREATE TABLE IF NOT EXISTS " + TBL_NAME_DANHSACHITEM +"(" + COL_DANHSACHITEM_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +COL_DANHSACHITEM_NAME +" VARCHAR(50), " + COL_DANHSACHITEM_PRICE +" DOUBLE,"+COL_DANHSACHITEM_COMPLETE+" INT)";
        sqLiteDatabase.execSQL(sqlDanhSachItem);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_THUCHI);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_NHAC_NHO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_MUC_TIEU);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_MUC_TIEU_PAUSED);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_MUC_TIEU_COMPLETED);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_DANHSACH);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_DANHSACHITEM);

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


    public int getCountDanhSachItem(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME_DANHSACHITEM, null);
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
    public int getCountMucTieuPaused(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME_MUC_TIEU_PAUSED, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public int getCountMucTieuCompleted(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME_MUC_TIEU_COMPLETED, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public int getCountDanhSach(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME_DANHSACH, null);
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
    public  void createSomeMucTieuTamDung(){
        int countMucTieu = getCountMucTieuPaused();
        if(countMucTieu == 0){
            execSql("INSERT INTO " + TBL_NAME_MUC_TIEU_PAUSED + " VALUES(null, 'Mua xe','20000000','15000000','2024-05-28',null,'-11873872','Quan trọng')");
            execSql("INSERT INTO " + TBL_NAME_MUC_TIEU_PAUSED + " VALUES(null, 'Mua nhà','2500000000','15000000','2021-09-23',null,'-11873872','Quan trọng')");

        }
    }
    public  void createSomeMucTieuHoanThanh(){
        int countMucTieu = getCountMucTieuCompleted();
        if(countMucTieu == 0){
            execSql("INSERT INTO " + TBL_NAME_MUC_TIEU_COMPLETED + " VALUES(null, 'Mua xe','20000000','15000000','2024-05-28',null,'-11873872','Quan trọng')");
            execSql("INSERT INTO " + TBL_NAME_MUC_TIEU_COMPLETED + " VALUES(null, 'Mua nhà','2500000000','15000000','2021-09-23',null,'-11873872','Quan trọng')");

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
    public  void createSomeDanhSachData(){
        int countDanhSach = getCountDanhSach();
        if(countDanhSach == 0){
            execSql("INSERT INTO " + TBL_NAME_DANHSACH + " VALUES(null, 'Áo quần', '5000000')");


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


    public boolean insertData( byte[] goalThumb, String goalName, LocalDate goalTime, int goalColor, double goalSaved, double goalTarget,  String goalNote){


        try{
            SQLiteDatabase db = getWritableDatabase();
            String sql ="INSERT INTO "+TBL_NAME_MUC_TIEU+" VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);


            statement.bindString(1,goalName);
            statement.bindDouble(2,goalTarget);
            statement.bindDouble(3,goalSaved);
            statement.bindString(4, String.valueOf(goalTime));
            statement.bindBlob(5,goalThumb);
            statement.bindDouble(6,goalColor);
            statement.bindString(7,goalNote);

            statement.executeInsert();
            return true;
        }
        catch (Exception ex){
            return false;
        }


    }
    public boolean insertMucTieuPausedData( byte[] goalThumb, String goalName, LocalDate goalTime, int goalColor, double goalSaved, double goalTarget,  String goalNote){


        try{
            SQLiteDatabase db = getWritableDatabase();
            String sql ="INSERT INTO "+TBL_NAME_MUC_TIEU_PAUSED+" VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);


            statement.bindString(1,goalName);
            statement.bindDouble(2,goalTarget);
            statement.bindDouble(3,goalSaved);
            statement.bindString(4, String.valueOf(goalTime));
            statement.bindBlob(5,goalThumb);
            statement.bindDouble(6,goalColor);
            statement.bindString(7,goalNote);

            statement.executeInsert();
            return true;
        }
        catch (Exception ex){
            return false;
        }




    }

    public boolean insertMucTieuCompletedData( byte[] goalThumb, String goalName, LocalDate goalTime, int goalColor, double goalSaved, double goalTarget,  String goalNote) {


        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + TBL_NAME_MUC_TIEU_COMPLETED + " VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);


            statement.bindString(1, goalName);
            statement.bindDouble(2, goalTarget);
            statement.bindDouble(3, goalSaved);
            statement.bindString(4, String.valueOf(goalTime));
            statement.bindBlob(5, goalThumb);
            statement.bindDouble(6, goalColor);
            statement.bindString(7, goalNote);

            statement.executeInsert();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }



    public boolean updateDataHoatDong( int i,byte[] goalThumb, String goalName, LocalDate goalTime, int goalColor, double goalSaved, double goalTarget,  String goalNote){


        try{
            SQLiteDatabase db = getWritableDatabase();
//            String sql ="INSERT INTO "+TBL_NAME_MUC_TIEU+" VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
            String sql="UPDATE "+TBL_NAME_MUC_TIEU +" SET "+MyDatabaseHelper.COL_MUCTIEU_NAME +" = ?,"+ MyDatabaseHelper.COL_MUCTIEU_SOTIENMUCTIEU+"=?,"+MyDatabaseHelper.COL_MUCTIEU_SOTIENDATDUOC+" = ?,"+MyDatabaseHelper.COL_MUCTIEU_NGAYKETTHUC+"=?,"+MyDatabaseHelper.COL_MUCTIEU_IMAGE+" = ?,"+MyDatabaseHelper.COL_MUCTIEU_IMAGE_COLOR+"=?," +MyDatabaseHelper.COL_MUCTIEU_LUUY+"=? WHERE "+MyDatabaseHelper.COL_MUCTIEU_ID+"=?";
            SQLiteStatement statement = db.compileStatement(sql);


            statement.bindString(1,goalName);
            statement.bindDouble(2,goalTarget);
            statement.bindDouble(3,goalSaved);
            statement.bindString(4, String.valueOf(goalTime));
            statement.bindBlob(5,goalThumb);
            statement.bindDouble(6,goalColor);
            statement.bindString(7,goalNote);
            statement.bindDouble(8,i);
            statement.executeInsert();
            return true;
        }
        catch (Exception ex){
            return false;
        }


    }
    public boolean updateDataPaused( int i, String goalName, LocalDate goalTime, int goalColor, double goalSaved, double goalTarget,  String goalNote){


        try{
            SQLiteDatabase db = getWritableDatabase();
//            String sql ="INSERT INTO "+TBL_NAME_MUC_TIEU+" VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
            String sql="UPDATE "+TBL_NAME_MUC_TIEU_PAUSED +" SET "+MyDatabaseHelper.COL_MUCTIEU_NAME +" = ?,"+ MyDatabaseHelper.COL_MUCTIEU_SOTIENMUCTIEU+"=?,"+MyDatabaseHelper.COL_MUCTIEU_SOTIENDATDUOC+" = ?,"+MyDatabaseHelper.COL_MUCTIEU_NGAYKETTHUC+"=?,"+MyDatabaseHelper.COL_MUCTIEU_IMAGE_COLOR+"=?," +MyDatabaseHelper.COL_MUCTIEU_LUUY+"=? WHERE "+MyDatabaseHelper.COL_MUCTIEU_ID+"=?";
            SQLiteStatement statement = db.compileStatement(sql);


            statement.bindString(1,goalName);
            statement.bindDouble(2,goalTarget);
            statement.bindDouble(3,goalSaved);
            statement.bindString(4, String.valueOf(goalTime));
            statement.bindDouble(5,goalColor);
            statement.bindString(6,goalNote);
            statement.bindDouble(7,i);
            statement.executeInsert();
            return true;
        }
        catch (Exception ex){
            return false;
        }


    }
}
