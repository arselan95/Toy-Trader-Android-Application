package com.example.toytrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //add an employee
    public void uploadToy(View view) {
        //new DbManager (this);
        DbManager db = new DbManager(this);
        Intent intent;
        intent = new Intent(this, UploadToy.class);
        startActivity(intent);


       // String res = db.insertEmployee("andy", "andy@yahoo.cim", 9090);
        //db.insertToy("car", "vehivle", "jdshfjh",50, "sljd", "sanjose",9, "test");

    }
}