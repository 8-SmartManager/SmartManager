package com.example.muctieutietkiem.muctieu.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.smartmanagertwo.R;

public class DialogThemTien extends DialogFragment {
    public static final String TAG = "DialogThemTien";

    public  interface OnInputListener{
        void sendInput (String tienThem);
    }
    public OnInputListener mOnInputListener;

    EditText edtThemTien;
    Button btnOk, btnCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_themtien,container,false);

        edtThemTien=view.findViewById(R.id.edtThemTien);
        btnOk=view.findViewById(R.id.btnOk);
        btnCancel=view.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: themtien");
                String tienThem = edtThemTien.getText().toString();
                if(!tienThem.equals(""));
                {
                    mOnInputListener.sendInput(tienThem);
                }
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            mOnInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: "+e.getMessage() );
        }
    }
}
