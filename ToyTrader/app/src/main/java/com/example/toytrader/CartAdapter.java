package com.example.toytrader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{


    ArrayList<Toy> s;
    private Context context;
    //String n,p,l;
    public CartAdapter(ArrayList<Toy> temp, Context context)
    {
        s=temp;
        this.context =context;
    }



    @Override
    public CartAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartrow,parent,false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder( CartAdapter.ViewHolder holder, int position) {
       // holder.name.setText(s.get(position));
        //holder.price.setText(s.get(position));
        //holder.location.setText(s.get(position));
        Toy toy = s.get(position);
        holder.t = toy;
        holder.name.setText(toy.getName());
        holder.price.setText(toy.getCost().toString());
        holder.location.setText(toy.getLocation());

    }

    @Override
    public int getItemCount() {
        return s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        public TextView name;
        public TextView price;
        public TextView location;
        public Button removeButton;
        public Context context;
        public Toy t;

        public ViewHolder(View itemView, Context context)
        {
            super(itemView);
            name= itemView.findViewById(R.id.cart_toyname);
            price=itemView.findViewById(R.id.cart_toyprice);
            location=itemView.findViewById(R.id.cart_toylocation);
            removeButton=itemView.findViewById(R.id.aboutbutton);
            this.context = context;
            final Context c = context;
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);
                    SharedPreferences.Editor editor = preferences.edit();

                    Set stringSet = preferences.getStringSet("toys", new HashSet<String>());

                    Gson gson=new Gson();

                    stringSet = Utilities.getToysWithout(stringSet, t.getToyID());

                    editor.putStringSet("toys", stringSet);
                    editor.apply();


                    editor.apply();

                    Intent intent;
                    intent = new Intent(c, CartActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    c.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }

}
