package com.example.smartmanagertwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class KetNoi_MoiBan extends Fragment {

    Button btnInvite,btnHuy;
    private AlertDialog.Builder moibanBuilder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_ket_noi_moi_ban,container,false);
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_ketnoi_invite);
        dialog.show();

        btnInvite=dialog.findViewById(R.id.btnInvite);
        btnHuy=dialog.findViewById(R.id.btnHuy);
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                showDialog1();


            }

        });

        return root;

    }

    private void showDialog1() {
        moibanBuilder = new AlertDialog.Builder(getActivity());
        moibanBuilder.setCancelable(false);
        moibanBuilder.setView(R.layout.dialog_ketnoi_gioithieu);
        moibanBuilder.create();
        moibanBuilder.show();




    }
}