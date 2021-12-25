package com.example.moiban;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.smartmanagertwo.R;

public class KetNoi_MoiBan extends Fragment {

    Button btnInvite;
    private AlertDialog.Builder moibanBuilder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_ket_noi_moi_ban,container,false);
        btnInvite=root.findViewById(R.id.btnInvite);
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog1();


            }

        });


        return root;

    }

    private void showDialog1() {
        Dialog dialog1 = new Dialog(getContext(),R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
        dialog1.setContentView(R.layout.dialog_ketnoi_gioithieu);
        Button btnHuy=dialog1.findViewById(R.id.btnHuy);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
        dialog1.show();




    }
}