package com.example.toytrader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;

public class ToysAdapter extends RecyclerView.Adapter<ToysAdapter.ToysViewHolder> {

    private ArrayList<Toy> toys;
    public static class ToysViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView toyName;
        public TextView toyDesc;

        public ToysViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.toy_image);
            toyName = itemView.findViewById(R.id.category_toyname);
            toyDesc = itemView.findViewById(R.id.toy_desc_text);
        }
    }

    public ToysAdapter(ArrayList<Toy> toys){
        this.toys = toys;
    }

    @NonNull
    @Override
    public ToysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        ToysViewHolder toysViewHolder = new ToysViewHolder(view);
        return toysViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ToysViewHolder holder, int position) {
        Toy toy = toys.get(position);
        if(toy.getImage() != null && !toy.getImage().isEmpty()) {
            new DownloadImageTask(holder.imageView).execute(toy.getImage());
        }else {
            holder.imageView.setImageResource(R.drawable.softoys);
        }
        holder.toyName.setText(toy.getName());
        holder.toyDesc.setText(toy.getDescription());
    }

    @Override
    public int getItemCount() {
        return toys.size();
    }
}

