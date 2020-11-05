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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;

public class ToyView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;
    TextView tisname;
    TextView tiscategory;
    TextView tisdescription;
    TextView tiscost;
    TextView tislocation;
    private Button addcart;




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


         tisname= (TextView)findViewById(R.id.toyviewnametextview);
         tiscategory= (TextView)findViewById(R.id.categorytextview);
         tisdescription= (TextView)findViewById(R.id.descriptiontextview);
         tiscost= (TextView)findViewById(R.id.costtextview);
         tislocation= (TextView)findViewById(R.id.locationtextview);

        /*
        GET DATABASE VALUES HERE
         */

        String nam="toy name";
        String cat=" vehicle ";
        String cos=" 15$ ";
        String desc=" This all new fully rechargeable and powered fun Kidzone ride-on toy car " +
                "can spin a full 360 degrees with its simple joystick or remote controls. This brilliant little car is built " +
                "from a tough plastic shell and has a " +
                "soft bumper outside system allowing you to bump around if you make the wrong turn. " +
                "ASTM-certified to this bumper car and comprise a safety belt," +
                " anti-flat tires and Light. Recommended for children aged one and a half and above.";
        String loca="San Jose ";

       tisname.setText(nam);
       tiscategory.setText(cat);
       tisdescription.setText( desc);
       tiscost.setText(cos);
       tislocation.setText(loca);
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
            case R.id.add_toys:
                Intent intent;
                intent = new Intent(this, UploadToy.class);
                startActivity(intent);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void addToCart(View v)
    {
        tisname= (TextView)findViewById(R.id.toyviewnametextview);
        tiscategory= (TextView)findViewById(R.id.categorytextview);
        tisdescription= (TextView)findViewById(R.id.descriptiontextview);
        tiscost= (TextView)findViewById(R.id.costtextview);
        tislocation= (TextView)findViewById(R.id.locationtextview);

        /*
       TODO:  ADD to SHARED PREFERENCES / OR SOME LIST

       UPDATE THE CART

       REFRESH THIS PAGE
         */

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name",tisname.getText().toString());
        editor.putString("category",tiscategory.getText().toString());
        editor.putString("description",tisdescription.getText().toString());
        editor.putString("cost",tiscost.getText().toString());
        editor.putString("location",tislocation.getText().toString());
        editor.apply();


        addcart =(Button)v.findViewById(R.id.addtocartbutton);
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".UserHomeActivity");
                startActivity(intent);
            }
        });
    }
}