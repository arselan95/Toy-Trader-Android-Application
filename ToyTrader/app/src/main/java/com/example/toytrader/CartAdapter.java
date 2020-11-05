package com.example.toytrader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{


    ArrayList<String> s;
    public CartAdapter(ArrayList<String> temp) {
        s=temp;
    }



    @Override
    public CartAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartrow,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( CartAdapter.ViewHolder holder, int position) {
        holder.name.setText(s.get(position));
        holder.price.setText(s.get(position));
        holder.location.setText(s.get(position));

    }

    @Override
    public int getItemCount() {
        return s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView name;
        public TextView price;
        public TextView location;

        public ViewHolder(View itemView)
        {
            super(itemView);
            name= itemView.findViewById(R.id.cart_toyname);
            price=itemView.findViewById(R.id.cart_toyprice);
            location=itemView.findViewById(R.id.cart_toylocation);
        }

    }

}
