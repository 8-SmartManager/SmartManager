package com.example.muctieutietkiem.packages;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.muctieutietkiem.packages.model.Goal;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class EditMucTieuTamDung extends AppCompatActivity {

    EditText edtTenMucTieu, edtSoTienMucTieu ,edtSoTienDatDuoc, edtNgayKetThuc, edtLuuY;
    Goal selectedGoal;
    ImageView imvColor, imvDropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_muctieu_tamdung);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_x);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Sửa mục tiêu");

        LinkViews();
        getData();
        addEvents();
//        LoadData();


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

        String ten= edtTenMucTieu.getText().toString(),
                soTienDatDuoc=edtSoTienDatDuoc.getText().toString(),
                soTienMucTieu=edtSoTienMucTieu.getText().toString(),
                ngayKetThuc=edtNgayKetThuc.getText().toString(),
                luuY=edtLuuY.getText().toString();
        int color = ImageViewCompat.getImageTintList(imvColor).getDefaultColor();


        switch (item.getItemId())
        {

            case android.R.id.home:
                if (ten.equals(selectedGoal.getGoalName())&&soTienDatDuoc.equals(String.format("%.0f",selectedGoal.getGoalSaved()))&&soTienMucTieu.equals(String.format("%.0f",selectedGoal.getGoalTarget()))&&ngayKetThuc.equals(selectedGoal.getGoalTime().toString())&&color==selectedGoal.getGoalColor()&&luuY.equals(selectedGoal.getGoalNote())){


                    {
                        onBackPressed();
                    }



                }
                else {

                    Dialog dialogBack = new Dialog(EditMucTieuTamDung.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                    dialogBack.setContentView(R.layout.dialog_error);
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
            case R.id.mnResume:
                Dialog dialogResume = new Dialog(EditMucTieuTamDung.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialogResume.setContentView(R.layout.dialog_error);
                TextView txtTitlePause=dialogResume.findViewById(R.id.txtTitle),
                        txtMessagePause=dialogResume.findViewById(R.id.txtMessage);
                Button btnYesPause=dialogResume.findViewById(R.id.btnYes),
                        btnNoPause=dialogResume.findViewById(R.id.btnNo);
                txtTitlePause.setText("Xác nhận");
                txtMessagePause.setText("Bạn có chắc chắn muốn tiếp tục mục tiêu này?");
                btnYesPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        muctieu_tamdung_fragment.db.execSql("DELETE FROM "+ MyDatabaseHelper.TBL_NAME_MUC_TIEU_PAUSED+" WHERE "+MyDatabaseHelper.COL_MUCTIEU_ID + "=" +selectedGoal.getGoalID());
                        muctieu_tamdung_fragment.db.insertData(selectedGoal.getGoalThumb(),selectedGoal.getGoalName(),selectedGoal.getGoalTime(),selectedGoal.getGoalColor(),selectedGoal.getGoalSaved(),selectedGoal.getGoalTarget(),selectedGoal.getGoalNote());
//                        hoat_dong_fragment.db.updateData(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);

                        finish();
                    }
                });
                btnNoPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogResume.dismiss();
                    }
                });
                dialogResume.show();
                break;
            case R.id.mnDelete:
                Dialog dialog = new Dialog(EditMucTieuTamDung.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialog.setContentView(R.layout.dialog_error);
                TextView txtTitle=dialog.findViewById(R.id.txtTitle),
                        txtMessage=dialog.findViewById(R.id.txtMessage);
                Button btnYes=dialog.findViewById(R.id.btnYes),
                        btnNo=dialog.findViewById(R.id.btnNo);
                txtTitle.setText("Xác nhận");
                txtMessage.setText("Bạn có chắc chắn muốn xóa?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hoat_dong_fragment.db.execSql("DELETE FROM "+ MyDatabaseHelper.TBL_NAME_MUC_TIEU_PAUSED+" WHERE "+MyDatabaseHelper.COL_MUCTIEU_ID + "=" +selectedGoal.getGoalID());

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

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_tamdung_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void addEvents() {

            edtNgayKetThuc.setOnClickListener(myClick);
            imvColor.setOnClickListener(myClick);
            imvDropdown.setOnClickListener(myClick);

    }

    private void getData() {
        Intent intent = getIntent();
        selectedGoal= (Goal) intent.getSerializableExtra("Muc tieu");
        edtTenMucTieu.setText(selectedGoal.getGoalName());
        edtSoTienMucTieu.setText(String.format("%.0f",selectedGoal.getGoalTarget()));
        edtSoTienDatDuoc.setText(String.format("%.0f",selectedGoal.getGoalSaved()));
        edtNgayKetThuc.setText(String.valueOf(selectedGoal.getGoalTime()));
        ImageViewCompat.setImageTintList(imvColor, ColorStateList.valueOf(selectedGoal.getGoalColor()));
        edtLuuY.setText(selectedGoal.getGoalNote());
    }

//    private void LoadData() {
//        Intent intent = new Intent(EditMucTieuHoatDong.this, TaoMucTieu.class);
//
//    }

    private void LinkViews() {

        edtTenMucTieu=findViewById(R.id.edtTenMucTieu);
        edtSoTienMucTieu=findViewById(R.id.edtSoTienMucTieu);
        edtSoTienDatDuoc=findViewById(R.id.edtSoTienDatDuoc);
        edtNgayKetThuc=findViewById(R.id.edtSelectDate);
        edtLuuY=findViewById(R.id.edtLuuY);
        imvColor=findViewById(R.id.imvColor);
        imvDropdown=findViewById(R.id.imvDrop);
    }
    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.edtSelectDate ) {
                Calendar calendarDate= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DatePickerDialog.OnDateSetListener callBack= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarDate.set(Calendar.YEAR,i);
                        calendarDate.set(Calendar.MONTH,i1);
                        calendarDate.set(Calendar.DAY_OF_MONTH,i2);
                        edtNgayKetThuc.setText(simpleDateFormat.format(calendarDate.getTime()));
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditMucTieuTamDung.this,callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
            if (view.getId() == R.id.imvDrop||view.getId() == R.id.imvColor) {
                ImageViewCompat.setImageTintList(imvDropdown, ColorStateList.valueOf(-149741));
                closeKeyBoard();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new HopChonColor();
                transaction.replace(R.id.LayoutContainerMucTieu, fragment,"fragmentColor");

                transaction.commit();




            }if(view.getId() == R.id.edtTenMucTieu||view.getId() == R.id.edtSoTienMucTieu||view.getId() == R.id.edtSoTienDatDuoc||view.getId() == R.id.edtSelectDate||view.getId() == R.id.edtLuuY){
                Fragment fragment =getSupportFragmentManager().findFragmentByTag("fragmentColor");
                if(fragment!=null) {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    ImageViewCompat.setImageTintList(imvDropdown, null);


                }


            }
        }
    };
    private void closeKeyBoard() {
        View view= this.getCurrentFocus();
        if(view!=null)
        {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
    }