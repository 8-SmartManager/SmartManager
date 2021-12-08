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
import com.example.smartmanagertwo.NhacNhoActivity;
import com.example.smartmanagertwo.NhacNhoChiTietActivity;
import com.example.smartmanagertwo.R;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class EditMucTieuHoatDong extends AppCompatActivity {

    EditText edtTenMucTieu, edtSoTienMucTieu ,edtSoTienDatDuoc, edtNgayKetThuc, edtLuuY;
    Goal selectedGoal;
    ImageView imvColor, imvDropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_muctieu_hoatdong);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
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

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.mnPause:
                Dialog dialogPause = new Dialog(EditMucTieuHoatDong.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialogPause.setContentView(R.layout.dialog_error);
                TextView txtTitlePause=dialogPause.findViewById(R.id.txtTitle),
                        txtMessagePause=dialogPause.findViewById(R.id.txtMessage);
                Button btnYesPause=dialogPause.findViewById(R.id.btnYes),
                        btnNoPause=dialogPause.findViewById(R.id.btnNo);
                txtTitlePause.setText("Xác nhận");
                txtMessagePause.setText("Bạn có chắc chắn muốn tạm dừng?");
                btnYesPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hoat_dong_fragment.db.execSql("DELETE FROM "+ MyDatabaseHelper.TBL_NAME_MUC_TIEU+" WHERE "+MyDatabaseHelper.COL_MUCTIEU_ID + "=" +selectedGoal.getGoalID());
                        hoat_dong_fragment.db.insertMucTieuPausedData(selectedGoal.getGoalThumb(),selectedGoal.getGoalName(),selectedGoal.getGoalTime(),selectedGoal.getGoalColor(),selectedGoal.getGoalSaved(),selectedGoal.getGoalTarget(),selectedGoal.getGoalNote());
//                        hoat_dong_fragment.db.updateData(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);

                        finish();
                    }
                });
                btnNoPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogPause.dismiss();
                    }
                });
                dialogPause.show();
                break;
            case R.id.mnDelete:
                Dialog dialog = new Dialog(EditMucTieuHoatDong.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
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
                        hoat_dong_fragment.db.execSql("DELETE FROM "+ MyDatabaseHelper.TBL_NAME_MUC_TIEU+" WHERE "+MyDatabaseHelper.COL_MUCTIEU_ID + "=" +selectedGoal.getGoalID());

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
            case R.id.mnSave:
                String ten= edtTenMucTieu.getText().toString(),
                        soTienDatDuoc=edtSoTienDatDuoc.getText().toString(),
                        soTienMucTieu=edtSoTienMucTieu.getText().toString(),
                        ngayKetThuc=edtNgayKetThuc.getText().toString(),
//                        mau=selectedGoal.getGoalColor(),
                        luuY=edtLuuY.getText().toString();
                double goalTarget = Double.parseDouble(soTienMucTieu);
                double goalSaved = Double.parseDouble(soTienDatDuoc);
                LocalDate goalTime = LocalDate.parse(ngayKetThuc);
                LocalDate currentDate = LocalDate.now();
                int time = goalTime.compareTo(currentDate);

                int color = -11873872;
                color = HopChonColor.colorInt;

                if(ten.equals("")||soTienDatDuoc.equals("")||soTienMucTieu.equals("")||ngayKetThuc.equals("")||luuY.equals("")){
                    AlertDialog.Builder builder= new AlertDialog.Builder(EditMucTieuHoatDong.this);
                    builder.setTitle("Lỗi!");
                    builder.setMessage("Vui lòng nhập đầy đủ thông tin");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();
                }
                else if ( Double.parseDouble(soTienMucTieu)<Double.parseDouble(soTienDatDuoc)){
                    AlertDialog.Builder builder= new AlertDialog.Builder(EditMucTieuHoatDong.this);
                    builder.setTitle("Ủa alo!");
                    builder.setMessage("Sao Số tiền đạt được của bạn lại lớn hơn Số tiền mục tiêu dzợ .-. Bạn chỉnh lại chỗ này nhennn!");
                    builder.setPositiveButton("Ukii", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();

                }
                else if(time<=0)
                {
                    AlertDialog.Builder builder= new AlertDialog.Builder(EditMucTieuHoatDong.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Nhập lại Ngày kết thúc của bạn");
                    builder.setPositiveButton("Ukii", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();
                }







                else {

//                        hoat_dong_fragment.db.execSql("INSERT INTO "+MyDatabaseHelper.TBL_NAME_MUC_TIEU+" VALUES(null, '"+ten+"', '"+soTienMucTieu+"', '"+soTienDatDuoc+"', '"+ngayKetThuc+"',null,'"+color+"', '"+luuY+"')");



                    if (ten.equals("Mua nhà")){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.home);
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
                        byte[] goalThumb = byteArray.toByteArray();
                        color=-48536;
                        color = HopChonColor.colorInt;
                        hoat_dong_fragment.db.updateData(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);
                    }
                    else if (ten.equals("Mua xe")){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.car);
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
                        byte[] goalThumb = byteArray.toByteArray();

                        hoat_dong_fragment.db.updateData(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);
                    }
                    else if (ten.equals("Du lịch")){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dulich);
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
                        byte[] goalThumb = byteArray.toByteArray();

                        hoat_dong_fragment.db.updateData(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);
                    }
                    else if (ten.equals("Học tập")){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.hoctap);
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
                        byte[] goalThumb = byteArray.toByteArray();

                        hoat_dong_fragment.db.updateData(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);
                    }
                    else if (ten.equals("Sức khỏe")){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.health);
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
                        byte[] goalThumb = byteArray.toByteArray();

                        hoat_dong_fragment.db.updateData(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);
                    }
                    else if (ten.equals("Con cái")){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.concai);
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
                        byte[] goalThumb = byteArray.toByteArray();

                        hoat_dong_fragment.db.updateData(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);
                    }
                    else if (ten.equals("Kết hôn")){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.marriage);
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
                        byte[] goalThumb = byteArray.toByteArray();

                        hoat_dong_fragment.db.updateData(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);
                    }
                    else if (ten.equals("Bố mẹ")){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bame);
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
                        byte[] goalThumb = byteArray.toByteArray();

                        hoat_dong_fragment.db.updateData(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);
                    }



                    else {
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.round);
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
                        byte[] goalThumb = byteArray.toByteArray();

                        hoat_dong_fragment.db.updateData(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);

                    }



                    finish();
                }
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_hoatdong_menu, menu);
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditMucTieuHoatDong.this,callBack,
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