package com.example.toytrader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{


    ArrayList<Toy> s;
    //String n,p,l;
    public CartAdapter(ArrayList<Toy> temp)
    {
        s=temp;

    }



    @Override
    public CartAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartrow,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( CartAdapter.ViewHolder holder, int position) {
       // holder.name.setText(s.get(position));
        //holder.price.setText(s.get(position));
        //holder.location.setText(s.get(position));
        Toy toy = s.get(position);

        holder.name.setText(toy.getName());
        holder.price.setText(toy.getCost().toString());
        holder.location.setText(toy.getLocation());

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
