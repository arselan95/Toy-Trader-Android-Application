package com.example.toytrader;

import android.text.TextUtils;
import android.util.Patterns;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Utilities {
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidPassword(CharSequence target) {
        return (!TextUtils.isEmpty(target) && target.length() > 6);
    }

    public static String getCategory(String category) {
        if(category.equalsIgnoreCase(Constants.SOFT_TOYS)) {
            return "Soft Toys";
        }else if(category.equalsIgnoreCase(Constants.ELECTRONICS)){
            return "Electronics";
        }else if(category.equalsIgnoreCase(Constants.DOLLS)){
            return "Dolls";
        }else if(category.equalsIgnoreCase(Constants.VEHICLE)){
            return "Vehicle";
        }else if(category.equalsIgnoreCase(Constants.PARTY_TOYS)){
            return "Party Toys";
        }else {
            return "";
        }
    }

    public static ArrayList<Toy> getToys(Set<String> s){
        Gson gson = new Gson();
        ArrayList <Toy> tlist = new ArrayList<>();
        for (String temp : s) {
            Toy t = gson.fromJson(temp, Toy.class);
            tlist.add(t);
        }
        return tlist;
    }

    public static Set<String> getToysWithout(Set<String> s, String toyID ){
        Gson gson = new Gson();
        Set <String> tlist = new HashSet<>();
        for (String temp : s) {
            Toy t = gson.fromJson(temp, Toy.class);
            String json=gson.toJson(t);
            if(!t.getToyID().equalsIgnoreCase(toyID)){
                tlist.add(json);
            }
        }
        return tlist;
    }
}
