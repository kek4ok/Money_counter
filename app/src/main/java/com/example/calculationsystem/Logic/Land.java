package com.example.calculationsystem.Logic;

public class Land {

    private String name;
    private double size;
    private double price;

    public Land(double size, double price,String name) {
        this.size = size;
        this.price = price;
        this.name=name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double calculateRent()
    {
        return size*price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
