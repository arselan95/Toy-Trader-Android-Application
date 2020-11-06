package com.example.toytrader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ToyView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FirebaseListener {


    private DrawerLayout drawer;
    TextView tisname;
    TextView tiscategory;
    TextView tisdescription;
    TextView tiscost;
    TextView tislocation;
    ImageView toyImage;
    private Button addcart;
    private Toy selectedToy;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toy_view);

        Toolbar toolbar = findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Intent i = getIntent();
        String toyID = i.getStringExtra("ToyID");

        FirebaseHelper.getInstance().getToyForID(toyID, this);

         tisname= (TextView)findViewById(R.id.toyviewnametextview);
         tiscategory= (TextView)findViewById(R.id.categorytextview);
         tisdescription= (TextView)findViewById(R.id.descriptiontextview);
         tiscost= (TextView)findViewById(R.id.costtextview);
         tislocation= (TextView)findViewById(R.id.locationtextview);
        toyImage = findViewById(R.id.toy_view_image);
        toyImage.setImageResource(R.drawable.softoys);
        addToCart();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home:
                Intent home = new Intent(this, UserHomeActivity.class);
                startActivity(home);
                break;
            case R.id.add_toys:
                Intent intent;
                intent = new Intent(this, UploadToy.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                Intent intent2 = new Intent(this, ProfileActivity.class);
                startActivity(intent2);
                break;

            case R.id.nav_cart:
                intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_logout:
                FirebaseHelper.getInstance().cleanUpForLogout();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void addToCart()
    {
        final Context c = this;

        addcart =findViewById(R.id.addtocartbutton);
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double i= Double.parseDouble(tiscost.getText().toString());

                Toy t= new Toy(selectedToy.getToyID(), tisname.getText().toString(), i, tislocation.getText().toString());

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);
                SharedPreferences.Editor editor = preferences.edit();

                Gson gson=new Gson();
                String json=gson.toJson(t);
                Set stringSet = preferences.getStringSet("toys", new HashSet<String>());
                stringSet.add(json);
                editor.putStringSet("toys", stringSet);
                editor.apply();
                Intent intent = new Intent(".UserHomeActivity");
                startActivity(intent);
            }
        });
    }

    private void setToyData() {
        String nam= selectedToy.getName();
        String cat= Utilities.getCategory(selectedToy.getCategory());
        String cos= selectedToy.getCost().toString();
        String desc= selectedToy.getDescription();
        String loca= selectedToy.getLocation();

        tisname.setText(nam);
        tiscategory.setText(cat);
        tisdescription.setText( desc);
        tiscost.setText(cos);
        tislocation.setText(loca);

        if(selectedToy.getImage() != null && !selectedToy.getImage().isEmpty()) {
            new DownloadImageTask(toyImage).execute(selectedToy.getImage());
        }else {
            toyImage.setImageResource(R.drawable.softoys);
        }
    }

    @Override
    public <T> void getFBData(T event) {
        if(event instanceof Toy) {
            selectedToy = (Toy) event;
            setToyData();
        }
    }

    @Override
    public <T> void updateFBResult(T event) {

    }
}