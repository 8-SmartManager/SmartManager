package com.example.muctieutietkiem.muctieu;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.muctieutietkiem.muctieu.adapter.ImageAdapter;
import com.example.muctieutietkiem.muctieu.fragment.HopChonColor;
import com.example.muctieutietkiem.muctieu.fragment.muctieu_hoatdong_fragment;
import com.example.muctieutietkiem.muctieu.model.Goal;
import com.example.muctieutietkiem.muctieu.model.ImageMucTieu;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class EditMucTieuHoatDong extends AppCompatActivity {

    EditText edtTenMucTieu, edtSoTienMucTieu ,edtSoTienDatDuoc, edtNgayKetThuc, edtLuuY;
    Goal selectedGoal;
    ImageView imvColor, imvDropdown, imvIcon, imvDropdownIcon;
    ImageMucTieu im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_muctieu_hoatdong);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_x);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Sửa mục tiêu");

        LinkViews();
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

                        Dialog dialogBack = new Dialog(EditMucTieuHoatDong.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
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
            case R.id.mnPause:
                Dialog dialogPause = new Dialog(EditMucTieuHoatDong.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialogPause.setContentView(R.layout.dialog_thong_bao);
                TextView txtTitlePause=dialogPause.findViewById(R.id.txtTitle),
                        txtMessagePause=dialogPause.findViewById(R.id.txtMessage);
                Button btnYesPause=dialogPause.findViewById(R.id.btnYes),
                        btnNoPause=dialogPause.findViewById(R.id.btnNo);
                txtTitlePause.setText("Xác nhận");
                txtMessagePause.setText("Bạn có chắc chắn muốn tạm dừng?");
                btnYesPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        muctieu_hoatdong_fragment.db.execSql("DELETE FROM "+ MyDatabaseHelper.TBL_NAME_MUC_TIEU+" WHERE "+MyDatabaseHelper.COL_MUCTIEU_ID + "=" +selectedGoal.getGoalID());
                        muctieu_hoatdong_fragment.db.insertMucTieuPausedData(selectedGoal.getGoalThumb(),selectedGoal.getGoalName(),selectedGoal.getGoalTime(),selectedGoal.getGoalColor(),selectedGoal.getGoalSaved(),selectedGoal.getGoalTarget(),selectedGoal.getGoalNote());

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
                dialog.setContentView(R.layout.dialog_thong_bao);
                TextView txtTitle=dialog.findViewById(R.id.txtTitle),
                        txtMessage=dialog.findViewById(R.id.txtMessage);
                Button btnYes=dialog.findViewById(R.id.btnYes),
                        btnNo=dialog.findViewById(R.id.btnNo);
                txtTitle.setText("Xác nhận");
                txtMessage.setText("Bạn có chắc chắn muốn xóa?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        muctieu_hoatdong_fragment.db.execSql("DELETE FROM "+ MyDatabaseHelper.TBL_NAME_MUC_TIEU+" WHERE "+MyDatabaseHelper.COL_MUCTIEU_ID + "=" +selectedGoal.getGoalID());

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
                LocalDate goalTime = LocalDate.parse(ngayKetThuc);
                LocalDate currentDate = LocalDate.now();
                int time = goalTime.compareTo(currentDate);
                int goalThumb= im.getImageId();

                if(ten.equals("")||soTienDatDuoc.equals("")||soTienMucTieu.equals("")||ngayKetThuc.equals("")){
                    Dialog dialogFill = new Dialog(EditMucTieuHoatDong.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                    dialogFill.setContentView(R.layout.dialog_error);
                    TextView txtTitleFill=dialogFill.findViewById(R.id.txtTitle),
                            txtMessageFill=dialogFill.findViewById(R.id.txtMessage);
                    Button btnYesFill=dialogFill.findViewById(R.id.btnYes);
                    txtTitleFill.setText("Thông báo");
                    txtMessageFill.setText("Vui lòng nhập đầy đủ thông tin");
                    btnYesFill.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogFill.dismiss();
                        }
                    });
                    dialogFill.show();
                }
                else
                {   double goalTarget = Double.parseDouble(soTienMucTieu);
                    double goalSaved = Double.parseDouble(soTienDatDuoc);
                    if ( Double.parseDouble(soTienMucTieu)<Double.parseDouble(soTienDatDuoc)){
                        Dialog dialogTien = new Dialog(EditMucTieuHoatDong.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                        dialogTien.setContentView(R.layout.dialog_error);
                        TextView txtTitleTien=dialogTien.findViewById(R.id.txtTitle),
                                txtMessageTien=dialogTien.findViewById(R.id.txtMessage);
                        Button btnYesTien=dialogTien.findViewById(R.id.btnYes);
                        txtTitleTien.setText("Thông báo");
                        txtMessageTien.setText("Số tiền đạt được đã lớn hơn số tiền mục tiêu. Vui lòng nhập lại!");
                        btnYesTien.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogTien.dismiss();
                            }
                        });
                        dialogTien.show();
                    }
                    else if(time<=0)
                    {
                        Dialog dialogNgay = new Dialog(EditMucTieuHoatDong.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                        dialogNgay.setContentView(R.layout.dialog_error);
                        TextView txtTitleNgay=dialogNgay.findViewById(R.id.txtTitle),
                                txtMessageNgay=dialogNgay.findViewById(R.id.txtMessage);
                        Button btnYesNgay=dialogNgay.findViewById(R.id.btnYes);
                        txtTitleNgay.setText("Thông báo");
                        txtMessageNgay.setText("Vui lòng nhập lại ngày kết thúc!");
                        btnYesNgay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogNgay.dismiss();
                            }
                        });
                        dialogNgay.show();
                    }
                    else {
                        muctieu_hoatdong_fragment.db.updateDataHoatDong(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);
                        finish();
                    }
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
        imvIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog= new Dialog(EditMucTieuHoatDong.this);
                dialog.setContentView(R.layout.dialog_image);
                GridView gvImage=dialog.findViewById(R.id.gvImage);
                ArrayList<ImageMucTieu> image= new ArrayList<>();
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__1_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__2_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__3_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__4_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__5_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__6_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__7_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__8_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__9_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__10_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__11_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__12_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__13_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__14_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__15_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__16_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__17_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__18_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__19_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__20_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__21_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__22_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__23_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__24_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__25_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__26_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__27_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__28_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__29_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__30_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__31_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__32_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__33_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__34_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__35_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__36_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__37_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__38_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__39_));
                image.add(new ImageMucTieu(R.drawable.icon_muctieu__40_));
                Button btnDismiss = dialog.findViewById(R.id.btnDismiss);
                btnDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                ImageAdapter adapter= new ImageAdapter(EditMucTieuHoatDong.this,R.layout.item_layout_image_muctieu,image);
                gvImage.setAdapter(adapter);
                gvImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        im= (ImageMucTieu) adapter.getItem(i);
                        imvIcon.setImageResource(im.getImageId());
                        dialog.dismiss();
                        ImageViewCompat.setImageTintList(imvDropdownIcon, null);
                    }
                });
                dialog.show();
            }
        });
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
        imvIcon.setImageResource(selectedGoal.getGoalThumb());
        ImageViewCompat.setImageTintList(imvIcon, ColorStateList.valueOf(Color.parseColor("#191919")));
        im=new ImageMucTieu(selectedGoal.getGoalThumb());
    }
    private void LinkViews() {
        edtTenMucTieu=findViewById(R.id.edtTenMucTieu);
        edtSoTienMucTieu=findViewById(R.id.edtSoTienMucTieu);
        edtSoTienDatDuoc=findViewById(R.id.edtSoTienDatDuoc);
        edtNgayKetThuc=findViewById(R.id.edtSelectDate);
        edtLuuY=findViewById(R.id.edtLuuY);
        imvColor=findViewById(R.id.imvColor);
        imvDropdown=findViewById(R.id.imvDrop);
        imvIcon=findViewById(R.id.imvIcon);
        imvDropdownIcon=findViewById(R.id.imvDropIcon);
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
            }
            if (view.getId() == R.id.imvDropIcon||view.getId() == R.id.imvIcon) {
                ImageViewCompat.setImageTintList(imvDropdownIcon, ColorStateList.valueOf(-149741));}
            if(view.getId() == R.id.edtTenMucTieu||view.getId() == R.id.edtSoTienMucTieu||view.getId() == R.id.edtSoTienDatDuoc||view.getId() == R.id.edtSelectDate||view.getId() == R.id.edtLuuY){
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