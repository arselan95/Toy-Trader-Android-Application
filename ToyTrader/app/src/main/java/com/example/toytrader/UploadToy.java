package com.example.toytrader;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UploadToy extends AppCompatActivity {

    EditText name, tags, location, cost, description, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_toy);

        name = (EditText) findViewById(R.id.nametext);
        tags = (EditText) findViewById(R.id.tagstext);
        location = (EditText) findViewById(R.id.locationtext);
        cost = (EditText) findViewById(R.id.costtext);
        description = (EditText) findViewById(R.id.descriptiontext);
        image = (EditText) findViewById(R.id.imagesrc);
    }


        //add record to database
        public void addToy(View view) {
            String parcecost= cost.getText().toString();
            double newcost=Double.parseDouble(parcecost);
//            Dbmanager db = new Dbmanager(this);
//
//           db.insertToy(name.getText().toString(), tags.getText().toString(), description.getText().toString(),newcost, image.getText().toString(), location.getText().toString(),Integer.parseInt("x"), "test");
//
//            Toast.makeText(this,"added", Toast.LENGTH_LONG).show();
//            name.setText("");
//            tags.setText("");
//            location.setText("");
//            cost.setText("");
//           description.setText("");
//            image.setText("");

            Intent intent;
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }




    }



