package com.example.toytrader;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class Dbmanager extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "ToyTrader.db";
    public static final String TOY_TABLE_NAME = "toy";
    public static final String TOY_ID = "id";
    public static final String TOY_NAME = "name";
    public static final String TOY_TAGS = "tags";
    public static final String TOY_DESCRIPTION = "description";
    public static final String TOY_COST = "cost";
    public static final String TOY_IMAGE = "image";
    public static final String TOY_DATETIME = "datetime";
    public static final String TOY_LOCATION = "location";
    public static final String USER_ID = "userid";
    public static final String USER_NAME = "username";

    public Dbmanager( Context context) {
        super(context, DATABASE_NAME,null , 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table toy( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL, " +
                        "tags TEXT NOT NULL, " +
                        "description TEXT NOT NULL," +
                        "cost REAL NOT NULL," +
                        "image TEXT NOT NULL," +
                        "datetime REAL NOT NULL," +
                        "location TEXT NOT NULL," +
                        "userid INTEGER NOT NULL," +
                        "username TEXT NOT NULL)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS toy");
        onCreate(db);

    }

    //insert into database
    public void insertToy(String name, String tag, String description, double cost, String image, String location, int userid, String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "insert into toy(id, name, tags, description, cost, image, datetime, location, userid, username)" +" "+
                "values(null, " + name + ", " + tag + ", " + description + ", " + cost + ", " + image + ", julianday('now'), " + location + ", " + userid + ", " + username + ");";

        System.out.println("insert toy");
        db.execSQL(query);







    }

}

