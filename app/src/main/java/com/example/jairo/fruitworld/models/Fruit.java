package com.example.jairo.fruitworld.models;

public class Fruit {
    private String name;
    private String origin;
    private int icon;

    public Fruit(){}

    public Fruit(String name, String origin, int icon){
        this.name = name;
        this.origin = origin;
        this.icon = icon;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setOrigin(String origin){
        this.origin = origin;
    }

    public String getOrigin(){
        return this.origin;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

    public int getIcon(){
        return this.icon;
    }


}
