package com.example.smartmanagertwo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nhacnho.HopChonNhacNhoChiTietChuKy;
import com.example.nhacnho.HopChonNhacNhoChiTietTheLoai;
import com.example.nhacnho.NhacNho;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NhacNhoChiTietActivity extends AppCompatActivity {
    TextView txtTheLoai, txtChuKy, txtNgayBatDau, txtGioNhac;
    public  static String theLoai="";
    EditText edtTen;

    NhacNho selectedNhacNho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhacnho_chi_tiet);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Chi tiết nhắc nhở");
        linkViews();
        getData();
        addEvents();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int[] sourceCoordinates = new int[2];
            v.getLocationOnScreen(sourceCoordinates);
            float x = ev.getRawX() + v.getLeft() - sourceCoordinates[0];
            float y = ev.getRawY() + v.getTop() - sourceCoordinates[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                hideKeyboard(this);
            }

        }
        return super.dispatchTouchEvent(ev);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null) {
            activity.getWindow().getDecorView();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nhac_nho_edit_option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.mnDelete:
                Dialog dialog = new Dialog(NhacNhoChiTietActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialog.setContentView(R.layout.dialog_error);
                TextView txtTitle=dialog.findViewById(R.id.txtTitle),
                        txtMessage=dialog.findViewById(R.id.txtMessage);
                Button btnYes=dialog.findViewById(R.id.btnYes),
                        btnNo=dialog.findViewById(R.id.btnNo);
                txtTitle.setText("Thông báo");
                txtMessage.setText("Bạn có chắc chắn muốn xóa?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NhacNhoActivity.db.execSql("DELETE FROM "+MyDatabaseHelper.TBL_NAME_NHAC_NHO+" WHERE "+MyDatabaseHelper.COL_NHACNHO_ID + "=" +selectedNhacNho.getID());

                        finish();
                    }
                });
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.mnDone:
                String theLoai=txtTheLoai.getText().toString(),
                        ten=edtTen.getText().toString(),
                        chuKy=txtChuKy.getText().toString(),
                        ngayBatDau=txtNgayBatDau.getText().toString(),
                        gioNhac=txtGioNhac.getText().toString();
                if(theLoai.equals("")||ten.equals("")||chuKy.equals("")||ngayBatDau.equals("")||gioNhac.equals("")){
                    AlertDialog.Builder builder= new AlertDialog.Builder(NhacNhoChiTietActivity.this);
                    builder.setTitle("Lỗi!");
                    builder.setMessage("Vui lòng nhập đầy đủ thông tin");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();
                }else {
                    NhacNhoActivity.db.execSql("UPDATE "+MyDatabaseHelper.TBL_NAME_NHAC_NHO+" SET "+MyDatabaseHelper.COL_NHACNHO_TYPE+" = '"+theLoai+"', "+MyDatabaseHelper.COL_NHACNHO_NAME+" = '"+ten+"', "+MyDatabaseHelper.COL_NHACNHO_PERIOD+" = '"+chuKy+"', "+MyDatabaseHelper.COL_NHACNHO_START_DAY+" = '"+ngayBatDau+"', "+MyDatabaseHelper.COL_NHACNHO_REMIND_TIME+" = '"+gioNhac+"' WHERE "+MyDatabaseHelper.COL_NHACNHO_ID+"=" +selectedNhacNho.getID());

                    finish();
                }
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void addEvents() {
        txtTheLoai.setOnClickListener(myClick);
        edtTen.setOnClickListener(myClick);

        txtChuKy.setOnClickListener(myClick);
        txtNgayBatDau.setOnClickListener(myClick);
        txtGioNhac.setOnClickListener(myClick);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               onBackPressed();
//            }
//        });
//        btnDone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String theLoai=txtTheLoai.getText().toString(),
//                        ten=edtTen.getText().toString(),
//                        chuKy=txtChuKy.getText().toString(),
//                        ngayBatDau=txtNgayBatDau.getText().toString(),
//                        gioNhac=txtGioNhac.getText().toString();
//                if(theLoai.equals("")||ten.equals("")||chuKy.equals("")||ngayBatDau.equals("")||gioNhac.equals("")){
//                    AlertDialog.Builder builder= new AlertDialog.Builder(NhacNhoChiTietActivity.this);
//                    builder.setTitle("Lỗi!");
//                    builder.setMessage("Vui lòng nhập đầy đủ thông tin");
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                        }
//                    });
//                    builder.create().show();
//                }else {
//                    NhacNhoActivity.db.execSql("UPDATE "+MyDatabaseHelper.TBL_NAME_NHAC_NHO+" SET "+MyDatabaseHelper.COL_NHACNHO_TYPE+" = '"+theLoai+"', "+MyDatabaseHelper.COL_NHACNHO_NAME+" = '"+ten+"', "+MyDatabaseHelper.COL_NHACNHO_PERIOD+" = '"+chuKy+"', "+MyDatabaseHelper.COL_NHACNHO_START_DAY+" = '"+ngayBatDau+"', "+MyDatabaseHelper.COL_NHACNHO_REMIND_TIME+" = '"+gioNhac+"' WHERE "+MyDatabaseHelper.COL_NHACNHO_ID+"=" +selectedNhacNho.getID());
//
//                    finish();
//                }
//
//            }
//        });
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog dialog = new Dialog(NhacNhoChiTietActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
//                dialog.setContentView(R.layout.dialog_error);
//                TextView txtTitle=dialog.findViewById(R.id.txtTitle),
//                        txtMessage=dialog.findViewById(R.id.txtMessage);
//                Button btnYes=dialog.findViewById(R.id.btnYes),
//                        btnNo=dialog.findViewById(R.id.btnNo);
//                txtTitle.setText("Thông báo");
//                txtMessage.setText("Bạn có chắc chắn muốn xóa?");
//                btnYes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        NhacNhoActivity.db.execSql("DELETE FROM "+MyDatabaseHelper.TBL_NAME_NHAC_NHO+" WHERE "+MyDatabaseHelper.COL_NHACNHO_ID + "=" +selectedNhacNho.getID());
//
//                        finish();
//                    }
//                });
//               btnNo.setOnClickListener(new View.OnClickListener() {
//                   @Override
//                   public void onClick(View view) {
//                       dialog.dismiss();
//                   }
//               });
//               dialog.show();
//            }
//        });

    }

    private void linkViews() {
        txtGioNhac= findViewById(R.id.txtNhacNhoChiTietGioNhac);
        txtTheLoai= findViewById(R.id.txtNhacNhoChiTietTheLoai);
        edtTen= findViewById(R.id.edtNhacNhoChiTietTen);
        txtChuKy= findViewById(R.id.txtNhacNhoChiTietChuKy);
        txtNgayBatDau= findViewById(R.id.txtNhacNhoChiTietNgayBatDau);





    }

    private void getData() {
        Intent intent= getIntent();
        selectedNhacNho= (NhacNho) intent.getSerializableExtra("Nhac Nho");
        txtTheLoai.setText(selectedNhacNho.getTheLoai());
        theLoai=txtTheLoai.getText().toString();
        edtTen.setText(selectedNhacNho.getTen());
        txtChuKy.setText(selectedNhacNho.getChuKy());
        txtNgayBatDau.setText(selectedNhacNho.getNgayBatDau().toString());
        txtGioNhac.setText(selectedNhacNho.getGioNhac().toString());
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.txtNhacNhoChiTietTheLoai ||view.getId()==R.id.edtNhacNhoChiTietTen||view.getId()==R.id.txtNhacNhoChiTietChuKy)
            {
            FragmentManager manager= getSupportFragmentManager();
            FragmentTransaction transaction= manager.beginTransaction();
            Fragment fragment= null;
            if(view.getId()==R.id.txtNhacNhoChiTietTheLoai ){

                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                        fragment= new HopChonNhacNhoChiTietTheLoai();

            }

            if(view.getId()==R.id.txtNhacNhoChiTietChuKy){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                fragment= new HopChonNhacNhoChiTietChuKy();



            }
            transaction.replace(R.id.layoutContainerChiTietNhacNho, fragment);
            transaction.commit();}
            if(view.getId()==R.id.edtNhacNhoChiTietTen ){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));




            }
            if(view.getId()==R.id.txtNhacNhoChiTietNgayBatDau){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                Calendar calendarDate= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DatePickerDialog.OnDateSetListener callBack= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarDate.set(Calendar.YEAR,i);
                        calendarDate.set(Calendar.MONTH,i1);
                        calendarDate.set(Calendar.DAY_OF_MONTH,i2);
                        txtNgayBatDau.setText(simpleDateFormat.format(calendarDate.getTime()));
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(NhacNhoChiTietActivity.this,callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();



            }
            if(view.getId()==R.id.txtNhacNhoChiTietGioNhac){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                Calendar calendarTime= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        calendarTime.set(0,0,0,i,i1);

                        txtGioNhac.setText(simpleDateFormat.format(calendarTime.getTime()));
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(NhacNhoChiTietActivity.this,callback,
                        calendarTime.get(Calendar.HOUR_OF_DAY),
                        calendarTime.get(Calendar.MINUTE),true);
                timePickerDialog.show();



            }

        }
    };

}
