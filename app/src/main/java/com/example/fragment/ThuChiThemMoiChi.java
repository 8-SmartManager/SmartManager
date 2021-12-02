package com.example.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.adapter.ActivityAdapter;
import com.example.muctieutietkiem.packages.TaoMucTieu;
import com.example.muctieutietkiem.packages.TaoMucTieuChiTiet;
import com.example.smartmanagertwo.HopChonNhacNhoThemTheLoai;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.example.smartmanagertwo.ThongKeChinhSua;
import com.example.smartmanagertwo.ThuChi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThuChiThemMoiChi extends Fragment {

    Button btnSave;
    TextView  txtNewActivityDate, txtNewActivityAccount, txtNewActivityName, txtNewActivityAmount;
    Intent intent;

    MyDatabaseHelper db;

    ActivityAdapter adapter;

    ArrayList<Activity> activities;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi_them_moi_chi, container, false);


        txtNewActivityDate = view.findViewById(R.id.txtNewActivityDate);
        txtNewActivityAccount = view.findViewById(R.id.txtNewActivityAccount);
        txtNewActivityName = view.findViewById(R.id.txtNewActivityName);
        txtNewActivityAmount = view.findViewById(R.id.txtNewActivityAmount);

        btnSave = view.findViewById(R.id.btnSave);




        db = new MyDatabaseHelper(getContext());

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
                if(activityAccount.equals("") || activityName.equals("") || activityAmount.equals("") || activityDate.equals("")){
                    AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                    builder.setTitle("Lỗi!");
                    builder.setMessage("Vui lòng nhập đầy đủ thông tin");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();

                }else{
                    db.execSql("INSERT INTO " + MyDatabaseHelper.TBL_NAME_THUCHI + " VALUES(null, '" + activityType+ "', '" + activityName + "', '" + activityAccount + "', '" + activityAmount + "', '" + activityDate + "')");
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
                    fragment= new ThuChiHopChonTaiKhoan();

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
