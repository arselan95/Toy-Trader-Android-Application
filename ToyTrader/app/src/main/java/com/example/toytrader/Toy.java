package com.example.toytrader;

public class Toy {

    public Toy(String name, String description, Double cost, String image, String location, String userID) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.image = image;
        this.location = location;
        this.userID = userID;
    }

    public Toy(String name, double cost, String location)
    {
        this.name=name;
        this.cost=cost;
        this.location=location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private String name;
    private String description;
    private Double cost;
    private String image;
    private String location;
    private String userID;

    public Toy(){

    }

}
