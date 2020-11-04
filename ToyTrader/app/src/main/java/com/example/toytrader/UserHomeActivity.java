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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

public class UserHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Button softtoyButton;
    String category="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        Toolbar toolbar = findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container,new ToyCategoriesFragment()).commit();
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


    public void openVehicleCategory(View v)
    {

        category= "vehicle";
        softtoyButton =(Button)v.findViewById(R.id.vehicles17);
        softtoyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".CategoryView");
                intent.putExtra("toy",category);
                startActivity(intent);
            }
        });
    }
    public void openSoftToyCategory(View v)
    {
        category="softtoy";
        softtoyButton =(Button)v.findViewById(R.id.softtoy13);
        softtoyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".CategoryView");
                intent.putExtra("toy",category);
                startActivity(intent);
            }
        });
    }
    public void openPartyCategory(View v)
    {

        category="partytoy";
        softtoyButton =(Button)v.findViewById(R.id.partytoys19);
        softtoyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".CategoryView");
                intent.putExtra("toy",category);

                startActivity(intent);
            }
        });
    }
    public void openDollsCategory(View v)
    {

        category="dolls";
        softtoyButton =(Button)v.findViewById(R.id.dolls14);
        softtoyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".CategoryView");
                intent.putExtra("toy",category);

                startActivity(intent);
            }
        });
    }
    public void openElectronicsCategory(View v)
    {

        category="electronics";
        softtoyButton =(Button)v.findViewById(R.id.electronics15);
        softtoyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".CategoryView");
                intent.putExtra("toy",category);

                startActivity(intent);
            }
        });
    }


}