package com.example.toytrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private Button signUpButton;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        onClickLoginButton();
        onClickRegisterButton();
    }

    public void onClickLoginButton(){
        loginButton = (Button)findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".LoginActivity");
                startActivity(intent);
            }
        });
    }
    public void onClickRegisterButton(){
        signUpButton = (Button)findViewById(R.id.signup_btn);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".RegisterActivity");
                startActivity(intent);
            }
        });
    }

    //add an employee
    public void uploadToy(View view) {
        //new DbManager (this);
        Intent intent;
        intent = new Intent(this, UploadToy.class);
        startActivity(intent);
    }
}