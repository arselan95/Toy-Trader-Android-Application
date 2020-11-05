package com.example.toytrader;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class CategoryView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;
    ArrayList<String> toys;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    private Button myaboutbutton;

    String thiscategory="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        Toolbar toolbar = findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Intent i = getIntent();
        thiscategory = i.getStringExtra("toy");



        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);

        /*
        GET DATABASE VALUES HERE OF THAT PARTICULAR CATEGORY
         */

        //testing values
        if(thiscategory.equals("vehicle")) {
            toys = new ArrayList<String>();
            toys.add("Toy vehicle 1");
            toys.add("Toy vehicle 2");
            toys.add("Toy vehicle 3");
            toys.add("Toy vehicle 4");
            toys.add("Toy vehicle 5");
            toys.add("Toy vehicle 6");
        }

        else if(thiscategory.equals("softtoy")) {
            toys = new ArrayList<String>();
            toys.add("Toy soft toy 1");
            toys.add("Toy soft toy 2");
        }

        else if(thiscategory.equals("partytoy")) {
            toys = new ArrayList<String>();
            toys.add("Toy party toy 1");
            toys.add("Toy party toy 2");
        }

       else  if(thiscategory.equals("dolls")) {
            toys = new ArrayList<String>();
            toys.add("Toy doll 1");
            toys.add("Toy doll 2");
        }

       else if(thiscategory.equals("electronics")) {
            toys = new ArrayList<String>();
            toys.add("Toy electronic 1");
            toys.add("Toy electronic 2");
        }

        layoutManager=new LinearLayoutManager(this);
        adapter=new MainAdapter(toys);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
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

    public void aboutToy(View v)

    {
        myaboutbutton =(Button)v.findViewById(R.id.aboutbutton);
        myaboutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".ToyView");
                startActivity(intent);
            }
        });

    }
}