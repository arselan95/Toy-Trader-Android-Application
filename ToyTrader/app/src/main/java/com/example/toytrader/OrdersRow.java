package com.example.toytrader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersRow#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersRow extends Fragment {

    public OrdersRow() {

    }

    public static OrdersRow newInstance(String param1, String param2) {
        OrdersRow fragment = new OrdersRow();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders_row, container, false);
    }
}

class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    ArrayList<Order> s;
    private Context context;

    public OrdersAdapter(ArrayList<Order> temp, Context context) {
        s = temp;
        this.context = context;
    }


    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_orders_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(OrdersAdapter.ViewHolder holder, int position) {
        Order order = s.get(position);
        holder.t = order;
        holder.name.setText(order.toyName);
        holder.price.setText(order.cost.toString());
        holder.typeOfSale.setText(order.saleType);
    }

    @Override
    public int getItemCount() {
        return s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, FirebaseListener {

        public TextView name;
        public TextView price;
        public TextView typeOfSale;
        public Button returnButton;
        public Button reportButton;
        public Context context;
        public Order t;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.orders_toyname);
            price = itemView.findViewById(R.id.orders_toyprice);
            typeOfSale = itemView.findViewById(R.id.orders_toylocation);
            returnButton = itemView.findViewById(R.id.orders_return);
            reportButton = itemView.findViewById(R.id.orders_report);
            this.context = context;
            final Context c = context;
            returnButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            reportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        @Override
        public void onClick(View view) {

        }

        public void removeButtonAction(){

        }

        public void checkoutButtonAction(){
            Map m = new HashMap();
            FirebaseHelper f = FirebaseHelper.getInstance();

//            m.put("userid", f.getFirebaseUser().getUid());
//            m.put("type_of_sale", "Buy");
//            m.put("toyid", t.getToyID());
//            m.put("start_date", Calendar.getInstance().getTime().toString());
//            m.put("ownerid", t.getUserID());
//            m.put("cost", t.getCost());
//            f.tradeToyWithDetails(m, this);
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
