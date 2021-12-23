package com.example.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.adapter.ActivityAdapter;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ThuChiThemMoiChi extends Fragment {

    Button btnSave;
    TextView  txtNewActivityDate, txtNewActivityAccount, txtNewActivityName;
    EditText txtNewActivityAmount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi_them_moi_chi, container, false);
        txtNewActivityDate = view.findViewById(R.id.txtNewActivityDate);
        txtNewActivityAccount = view.findViewById(R.id.txtNewActivityAccount);
        txtNewActivityName = view.findViewById(R.id.txtNewActivityName);
        txtNewActivityAmount = view.findViewById(R.id.txtNewActivityAmount);

        btnSave = view.findViewById(R.id.btnSave);
        addEvents();
           return view;
    }
    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String activityDate = txtNewActivityDate.getText().toString();
                String activityAccount = txtNewActivityAccount.getText().toString();
                String activityAmount = txtNewActivityAmount.getText().toString();
                String activityName = txtNewActivityName.getText().toString();
                String activityType = "Chi";
                if(activityAccount.equals("Chọn tài khoản") || activityName.equals("Chọn thể loại") || activityAmount.equals("") || activityDate.equals("Chọn ngày")){
                    Dialog dialogDone = new Dialog(getContext(),R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
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
                }else{

                    ThuChiChinh.db.execSql("INSERT INTO " + MyDatabaseHelper.TBL_NAME_THUCHI + " VALUES(null, '" + activityType+ "', '" + activityName + "', '" + activityAccount + "', '" + activityAmount + "', '" + activityDate + "')");
                    getActivity().onBackPressed();
                    Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtNewActivityDate.setOnClickListener(myClick);
        txtNewActivityAccount.setOnClickListener(myClick);
        txtNewActivityName.setOnClickListener(myClick);
        txtNewActivityAmount.setOnClickListener(myClick);
    }
    View.OnClickListener myClick = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.txtNewActivityAccount||view.getId()==R.id.txtNewActivityName||view.getId()==R.id.txtNewActivityAmount)
            {
                FragmentManager manager= getFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                Fragment fragment= null;
                if(view.getId()==R.id.txtNewActivityAccount){
                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.thu_cap));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityDate.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    fragment= new ThuChiHopChonTaiKhoanChi();
                }
                if(view.getId()==R.id.txtNewActivityName ){
                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.thu_cap));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getContext().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityDate.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    fragment= new ThuChiHopChonTheLoaiChi();
                }
                if(view.getId()==R.id.txtNewActivityAmount){

                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.thu_cap));
                    txtNewActivityDate.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    fragment= new Fragment();
                }

                transaction.replace(R.id.layoutContainerThuChiThemMoi, fragment);
                transaction.commit();}

            if (view.getId() == R.id.txtNewActivityDate) {
                txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                txtNewActivityDate.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.thu_cap));

                Calendar calendarDate = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarDate.set(Calendar.YEAR, i);
                        calendarDate.set(Calendar.MONTH, i1);
                        calendarDate.set(Calendar.DAY_OF_MONTH, i2);
                        txtNewActivityDate.setText(simpleDateFormat.format(calendarDate.getTime()));
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        }
    };

}
