package com.example.calculationsystem.Logic;

public class Payment {

    private String comment;
    private String date ;
    private double money;

    public Payment(String comment, String date, double money) {
        this.comment = comment;
        this.date = date;
        this.money = money;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
