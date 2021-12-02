package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartmanagertwo.HopChonNhacNhoThemTheLoai;
import com.example.smartmanagertwo.R;

public class ThuChiThemMoiThu extends Fragment {

    Button btnSave;
    TextView  txtNewActivityDate, txtNewActivityAccount, txtNewActivityName, txtNewActivityAmount;
    Intent intent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi_them_moi_thu, container, false);


        txtNewActivityDate = view.findViewById(R.id.txtNewActivityDate);
        txtNewActivityAccount = view.findViewById(R.id.txtNewActivityAccount);
        txtNewActivityName = view.findViewById(R.id.txtNewActivityName);
        txtNewActivityAmount = view.findViewById(R.id.txtNewActivityAmount);


        addEvents();



        return view;
    }

    private void addEvents() {

//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

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
                    fragment= new HopChonNhacNhoThemTheLoai();

                }
                if(view.getId()==R.id.txtNewActivityName ){
                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.thu_cap));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getContext().getApplicationContext(),R.color.mau_xam));
                    fragment= new HopChonNhacNhoThemTheLoai();



                }

                if(view.getId()==R.id.txtNewActivityAmount){

                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(),R.color.thu_cap));

                    fragment= new HopChonNhacNhoThemTheLoai();

                }
                transaction.replace(R.id.layoutContainerThuChiThemMoi, fragment);
                transaction.commit();}



        }
    };

}
