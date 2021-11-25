package com.example.tintuc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmanagertwo.R;
import com.example.smartmanagertwo.TinTucActivity;

public class TinTucAdapter extends RecyclerView.Adapter<TinTucAdapter.ViewHolder> {

    TinTucData[] myNewsData;
    Context context;

    public TinTucAdapter(TinTucData[] myNewsData, TinTucActivity activity) {
        this.myNewsData = myNewsData;
        this.context = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.tintuc_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TinTucData myMovieDataList = myNewsData[position];
        holder.textViewName.setText(myMovieDataList.getMovieName());
        holder.textViewDetail.setText(myMovieDataList.getMovieDate());
        holder.newImage.setImageResource(myMovieDataList.getMovieImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, myMovieDataList.getMovieName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myNewsData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView newImage;
        TextView textViewName;
        TextView textViewDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.txtName);
            textViewDetail = itemView.findViewById(R.id.txtDetail);

        }
    }

}
