package com.example.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.example.thongke.HopChonTKTaiKhoan;
import com.example.thongke.HopChonTheLoaiThu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ThuChiThemMoiThu extends Fragment {

    Button btnSave;
    TextView  txtNewActivityDate, txtNewActivityAccount, txtNewActivityName, txtNewActivityAmount;
    Intent intent;

    MyDatabaseHelper db;

    ActivityAdapter adapter;

    ArrayList<Activity> activities;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi_them_moi_thu, container, false);


        txtNewActivityDate = view.findViewById(R.id.txtNewActivityDateThu);
        txtNewActivityAccount = view.findViewById(R.id.txtNewActivityAccountThu);
        txtNewActivityName = view.findViewById(R.id.txtNewActivityNameThu);
        txtNewActivityAmount = view.findViewById(R.id.txtNewActivityAmountThu);

        btnSave = view.findViewById(R.id.btnSaveThu);




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
                String activityType = "Thu";
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
            if(view.getId()==R.id.txtNewActivityAccountThu||view.getId()==R.id.txtNewActivityNameThu||view.getId()==R.id.txtNewActivityAmountThu)
            {
                FragmentManager manager= getFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                Fragment fragment= null;
                if(view.getId()==R.id.txtNewActivityAccountThu){
                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.thu_cap));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityDate.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    fragment= new ThuChiHopChonTaiKhoanThu();

                }
                if(view.getId()==R.id.txtNewActivityNameThu ){
                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.thu_cap));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getContext().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityDate.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    fragment= new ThuChiHopChonTheLoaiThu();



                }

                if(view.getId()==R.id.txtNewActivityAmountThu){

                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.thu_cap));
                    txtNewActivityDate.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));



                }

                transaction.replace(R.id.layoutContainerThuChiThemMoiThu, fragment);
                transaction.commit();}

            if (view.getId() == R.id.txtNewActivityDateThu) {
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
