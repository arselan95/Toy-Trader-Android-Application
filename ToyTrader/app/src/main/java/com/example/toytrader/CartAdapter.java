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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Calendar;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<Toy> s;
    private Context context;

    public CartAdapter(ArrayList<Toy> temp, Context context) {
        s = temp;
        this.context = context;
    }


    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartrow, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, FirebaseListener {

        public TextView name;
        public TextView price;
        public TextView location;
        public Button removeButton;
        public Button checkoutButton;
        public Context context;
        public Toy t;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.cart_toyname);
            price = itemView.findViewById(R.id.cart_toyprice);
            location = itemView.findViewById(R.id.cart_toylocation);
            removeButton = itemView.findViewById(R.id.aboutbutton);
            checkoutButton = itemView.findViewById(R.id.cart_checkout);
            this.context = context;
            final Context c = context;
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeButtonAction();
                }
            });

            checkoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkoutButtonAction();
                }
            });
        }

        @Override
        public void onClick(View view) {

        }

        public void removeButtonAction(){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
            SharedPreferences.Editor editor = preferences.edit();
            Set stringSet = preferences.getStringSet("toys", new HashSet<String>());
            stringSet = Utilities.getToysWithout(stringSet, t.getToyID());
            editor.putStringSet("toys", stringSet);
            editor.apply();
            editor.commit();
            Intent intent;
            intent = new Intent(this.context, CartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(intent);
        }

        public void checkoutButtonAction(){
            Map m = new HashMap();
            FirebaseHelper f = FirebaseHelper.getInstance();

            m.put("userid", f.getFirebaseUser().getUid());
            m.put("type_of_sale", "Buy");
            m.put("toyid", t.getToyID());
            m.put("start_date", Calendar.getInstance().getTime().toString());
            m.put("ownerid", t.getUserID());
            m.put("cost", t.getCost());
            f.tradeToyWithDetails(m, this);
        }

        @Override
        public <T> void getFBData(T event) {
            if(event instanceof Boolean) {
                //Move to My orders screen
            }else if(event instanceof Exception) {
                SnackbarHelper.showMessage(((Error)event).getLocalizedMessage(), this.itemView);
            }
        }

        @Override
        public <T> void updateFBResult(T event) {

        }
    }


}
