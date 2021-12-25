package com.example.nhacnho;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nhacnho.dialogFragment.HopChonNhacNhoChiTietChuKy;
import com.example.nhacnho.dialogFragment.HopChonNhacNhoChiTietTheLoai;
import com.example.nhacnho.model.NhacNho;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class NhacNhoChiTietActivity extends AppCompatActivity {
    TextView txtTheLoai, txtChuKy, txtNgayBatDau, txtGioNhac;
    public  static String theLoai="";
    EditText edtTen;
    Button btnXoa,btnLuu;

    NhacNho selectedNhacNho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhacnho_chi_tiet);
        linkViews();
        getData();
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle(edtTen.getText());

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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String theLoai=txtTheLoai.getText().toString(),
                ten=edtTen.getText().toString(),
                chuKy=txtChuKy.getText().toString(),
                ngayBatDau=txtNgayBatDau.getText().toString(),
                gioNhac=txtGioNhac.getText().toString();

        switch (item.getItemId())
        {
            case android.R.id.home:
                if (ten.equals(selectedNhacNho.getTen())&&theLoai.equals(selectedNhacNho.getTheLoai())&&chuKy.equals(selectedNhacNho.getChuKy())&&ngayBatDau.equals(selectedNhacNho.getNgayBatDau().toString())&&(gioNhac+":00").equals(selectedNhacNho.getGioNhac().toString())){


                    {
                        onBackPressed();
                    }



                }
                else {

                    Dialog dialogBack = new Dialog(NhacNhoChiTietActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                    dialogBack.setContentView(R.layout.dialog_thong_bao);
                    TextView txtTitleCancel=dialogBack.findViewById(R.id.txtTitle),
                            txtMessageCancel=dialogBack.findViewById(R.id.txtMessage);
                    Button btnYesCancel=dialogBack.findViewById(R.id.btnYes),
                            btnNoCancel=dialogBack.findViewById(R.id.btnNo);
                    txtTitleCancel.setText("Xác nhận");
                    txtMessageCancel.setText("Bạn có chắc chắn muốn thoát khi chưa lưu thay đổi?");
                    btnYesCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();


                        }

                    });
                    btnNoCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogBack.dismiss();
                        }
                    });
                    dialogBack.show();



                }
                break;
//
        }
        return super.onOptionsItemSelected(item);
    }
    private void addEvents() {
        txtTheLoai.setOnClickListener(myClick);
        edtTen.setOnClickListener(myClick);
        txtChuKy.setOnClickListener(myClick);
        txtNgayBatDau.setOnClickListener(myClick);
        txtGioNhac.setOnClickListener(myClick);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(NhacNhoChiTietActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialog.setContentView(R.layout.dialog_thong_bao);
                TextView txtTitle=dialog.findViewById(R.id.txtTitle),
                        txtMessage=dialog.findViewById(R.id.txtMessage);
                Button btnYes=dialog.findViewById(R.id.btnYes),
                        btnNo=dialog.findViewById(R.id.btnNo);
                txtTitle.setText("Thông báo");
                txtMessage.setText("Bạn có chắc chắn muốn xóa?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NhacNhoActivity.db.execSql("DELETE FROM "+ MyDatabaseHelper.TBL_NAME_NHAC_NHO+" WHERE "+MyDatabaseHelper.COL_NHACNHO_ID + "=" +selectedNhacNho.getID());
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
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String theLoai=txtTheLoai.getText().toString(),
                        ten=edtTen.getText().toString(),
                        chuKy=txtChuKy.getText().toString(),
                        ngayBatDau=txtNgayBatDau.getText().toString(),
                        gioNhac=txtGioNhac.getText().toString();
                LocalDate now=LocalDate.now();
                LocalDate date=LocalDate.parse(ngayBatDau);
                Calendar calendar1 = Calendar.getInstance();
                SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm:ss");
                String currentDate = formatter1.format(calendar1.getTime());
                if(theLoai.equals("")||ten.equals("")||chuKy.equals("")||ngayBatDau.equals("")||gioNhac.equals("")){
                    Dialog dialogDone = new Dialog(NhacNhoChiTietActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                    dialogDone.setContentView(R.layout.dialog_error);
                    TextView txtTitleDone=dialogDone.findViewById(R.id.txtTitle),
                            txtMessageDone=dialogDone.findViewById(R.id.txtMessage);
                    Button btnYesDone=dialogDone.findViewById(R.id.btnYes);

                    txtTitleDone.setText("Lỗi");
                    txtMessageDone.setText("Vui lòng nhập đầy đủ thông tin!");
                    btnYesDone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogDone.dismiss();
                        }
                    });

                    dialogDone.show();
                }else if(date.compareTo(now)<0){
                    Dialog dialogDone = new Dialog(NhacNhoChiTietActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                    dialogDone.setContentView(R.layout.dialog_error);
                    TextView txtTitleDone=dialogDone.findViewById(R.id.txtTitle),
                            txtMessageDone=dialogDone.findViewById(R.id.txtMessage);
                    Button btnYesDone=dialogDone.findViewById(R.id.btnYes);

                    txtTitleDone.setText("Lỗi");
                    txtMessageDone.setText("Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại!");
                    btnYesDone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogDone.dismiss();
                        }
                    });

                    dialogDone.show();
                }else if(date.compareTo(now)==0&&currentDate.compareTo(gioNhac)>0){
                    Dialog dialogDone = new Dialog(NhacNhoChiTietActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                    dialogDone.setContentView(R.layout.dialog_error);
                    TextView txtTitleDone=dialogDone.findViewById(R.id.txtTitle),
                            txtMessageDone=dialogDone.findViewById(R.id.txtMessage);
                    Button btnYesDone=dialogDone.findViewById(R.id.btnYes);

                    txtTitleDone.setText("Lỗi");
                    txtMessageDone.setText("Thời gian nhắc phải lớn hơn thời gian hiện tại!");
                    btnYesDone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogDone.dismiss();
                        }
                    });

                    dialogDone.show();
                }else {
                    NhacNhoActivity.db.execSql("UPDATE "+MyDatabaseHelper.TBL_NAME_NHAC_NHO+" SET "+MyDatabaseHelper.COL_NHACNHO_TYPE+" = '"+theLoai+"', "+MyDatabaseHelper.COL_NHACNHO_NAME+" = '"+ten+"', "+MyDatabaseHelper.COL_NHACNHO_PERIOD+" = '"+chuKy+"', "+MyDatabaseHelper.COL_NHACNHO_START_DAY+" = '"+ngayBatDau+"', "+MyDatabaseHelper.COL_NHACNHO_REMIND_TIME+" = '"+gioNhac+":00' WHERE "+MyDatabaseHelper.COL_NHACNHO_ID+"=" +selectedNhacNho.getID());

                    finish();
                }
            }
        });
    }

    private void linkViews() {
        txtGioNhac= findViewById(R.id.txtNhacNhoChiTietGioNhac);
        txtTheLoai= findViewById(R.id.txtNhacNhoChiTietTheLoai);
        edtTen= findViewById(R.id.edtNhacNhoChiTietTen);
        txtChuKy= findViewById(R.id.txtNhacNhoChiTietChuKy);
        txtNgayBatDau= findViewById(R.id.txtNhacNhoChiTietNgayBatDau);
        btnLuu=findViewById(R.id.btnLuu);
        btnXoa=findViewById(R.id.btnXoa);
    }

    private void getData() {
        Intent intent= getIntent();
        selectedNhacNho= (NhacNho) intent.getSerializableExtra("Nhac Nho");
        txtTheLoai.setText(selectedNhacNho.getTheLoai());
        theLoai=txtTheLoai.getText().toString();
        edtTen.setText(selectedNhacNho.getTen());
        txtChuKy.setText(selectedNhacNho.getChuKy());
        txtNgayBatDau.setText(selectedNhacNho.getNgayBatDau().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        txtGioNhac.setText(simpleDateFormat.format(selectedNhacNho.getGioNhac()));
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
                if(view.getId()==R.id.edtNhacNhoChiTietTen ){
                    txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                    txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));

                    fragment= new Fragment();
            }
            transaction.replace(R.id.layoutContainerChiTietNhacNho, fragment);
            transaction.commit();}

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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
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
