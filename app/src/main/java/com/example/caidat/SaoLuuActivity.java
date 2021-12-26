package com.example.caidat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.smartmanagertwo.R;

public class SaoLuuActivity extends Fragment {

    LinearLayout chooseGGDrive, chooseThietBi, chooseTatCaKhoiTao, chooseChuKyKhoiTao;
    TextView txtChoice;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_cai_dat_sao_luu,container,false);
        chooseGGDrive = root.findViewById(R.id.chooseGGDrive);
        chooseThietBi =  root.findViewById(R.id.chooseThietBi);
        chooseTatCaKhoiTao =  root.findViewById(R.id.chooseTatCaKhoiTao);
        chooseChuKyKhoiTao =  root.findViewById(R.id.chooseChuKyKhoiTao);

        txtChoice =  root.findViewById(R.id.txtChoice);

        addEvents();
        return root;
    }
    private void addEvents() {
        chooseGGDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SaoLuuGGDrive.class);
                startActivity(intent);
            }
        });
        txtChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.txtChoice){
                    if (txtChoice.getText().equals("Tắt")){
                        txtChoice.setText("Bật");
                    }else {
                        txtChoice.setText("Tắt");
                    }
                }
            }
        });
        chooseThietBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SaoLuuThietBi.class);
                startActivity(intent);
            }
        });
        chooseTatCaKhoiTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity(), R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialog.setContentView(R.layout.dialog_thong_bao);
                TextView txtTitle = dialog.findViewById(R.id.txtTitle), txtMessage = dialog.findViewById(R.id.txtMessage);
                Button btnYes = dialog.findViewById(R.id.btnYes), btnNo = dialog.findViewById(R.id.btnNo);
                txtTitle.setText("Thông báo!");
                txtMessage.setText("Bạn có chắc chắn muốn thiết lập lại?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
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
        chooseChuKyKhoiTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity(), R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialog.setContentView(R.layout.dialog_thong_bao);
                TextView txtTitle = dialog.findViewById(R.id.txtTitle), txtMessage = dialog.findViewById(R.id.txtMessage);
                Button btnYes = dialog.findViewById(R.id.btnYes), btnNo = dialog.findViewById(R.id.btnNo);
                txtTitle.setText("Thông báo!");
                txtMessage.setText("[Chu kỳ] sẽ thiết lập lại. Thể loại, Tài khoản, Cài đặt không reset. Bạn có muốn tiếp tục không?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
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
    }
}
