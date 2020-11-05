package com.example.toytrader;

import java.sql.Date;
import java.sql.Time;

public class Toy {

//    public int id;
//    public String name;
//    public String tags;
//    public String description;
//    public String cost;
//    public String image;
//    public Time time;
//    public Date date;
//    public String location;
//    public String userID;
//    public String username;

    public int getImageResource() {
        return imageResource;
    }

    public String getToyName() {
        return toyName;
    }

    public String getToyDescription() {
        return toyDescription;
    }

    private int imageResource;
    private String toyName;
    private String toyDescription;

    public Toy(int imageResource, String toyName, String toyDescription){
        this.imageResource = imageResource;
        this.toyName = toyName;
        this.toyDescription = toyDescription;
    }

}
