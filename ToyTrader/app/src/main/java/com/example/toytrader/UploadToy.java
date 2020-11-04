package com.example.toytrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class UploadToy extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {

    EditText name, tags, location, cost, description, image;

    private Spinner spinner;
    private static final String[] paths = {"Vehicle", "Soft toys", "Party Toys", "Dolls", "Electronics"};
    private DrawerLayout drawer;
    String category="";
    private Button addtoybutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_toy);

        Toolbar toolbar = findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        spinner = (Spinner)findViewById(R.id.categories);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(UploadToy.this,
                android.R.layout.simple_spinner_item,paths);



        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            //retrive this string on category page
            case 0:
                category = "Vehicle";
                Toast.makeText(parent.getContext(), "Vehicles selected!", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                category = "Soft Toy";
                Toast.makeText(parent.getContext(), "Soft Toys selected!", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                category = "Toy";
                    Toast.makeText(parent.getContext(), "Party Toys Selected!", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                category = "Dolls";
                Toast.makeText(parent.getContext(), "Dolls Selected!", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                category = "Electronics";
                Toast.makeText(parent.getContext(), "Electronics Selected!", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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

    public void addtoy(View V)
    {
        Intent i = new Intent (this, UserHomeActivity.class);

        i.putExtra("cate", category);
        startActivity(i);

    }
    }




