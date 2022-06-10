package com.example.calculationsystem.Logic;

import java.util.ArrayList;
import java.util.List;

public abstract class Customer {

    protected String name;
    protected List<Land> lands;
    protected  List<Payment> payments;
    protected double requiredMoney; // money they should pay
    protected double receviedMoney; //money they gave manager



    public Customer(String name) {

        this.name = name;
        this.lands = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.requiredMoney = 0;
        this.receviedMoney = 0;
    }

    public Customer(String name, List<Land> lands, List<Payment> payments, double requiredMoney, double receviedMoney) {
        this.name = name;
        this.lands = lands;
        this.payments = payments;
        this.requiredMoney = requiredMoney;
        this.receviedMoney = receviedMoney;

    }


    public  void CalculateRequiredMoney(){

        double temp=0;
        for(int i=0;i<this.lands.size();++i)
            temp+=lands.get(i).calculateRent();


        this.requiredMoney=temp;


    }
    public  void CalculateReceviedMoney(){


        double temp=0;
        for(int i=0;i<this.payments.size();++i)
            temp+=payments.get(i).getMoney();


        this.receviedMoney=temp;


    }


    public void addLand(Land land)
    {
        lands.add(land);
        CalculateRequiredMoney();


    }


    public void addPayment(Payment payment)
    {
        payments.add(payment);
        CalculateReceviedMoney();
    }


    public double getMoneyLeft()
    {
        return requiredMoney-receviedMoney;
    }



    public String getName() {
        return name;
    }

    public List<Land> getLands() {
        return lands;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<String> getLandsAsString() {

        List<String> temp=new ArrayList<>();

        for(int i=0;i<lands.size();++i)
        {
            String tempString = lands.get(i).getName()+"    " +lands.get(i).getPrice()+"   "+lands.get(i).getSize()+ "  Total:"+lands.get(i).calculateRent() ;
            temp.add(tempString);
        }


        return temp;


    }

    public List<String> getPaymentsAsString() {

        List<String> temp=new ArrayList<>();

        for(int i=0;i<payments.size();++i)
        {
            String tempString = payments.get(i).getMoney()+"   "+payments.get(i).getComment()+"   "+payments.get(i).getDate();
            temp.add(tempString);
        }


           return temp;
    }




    public double getRequiredMoney() {
        return requiredMoney;
    }

    public double getReceviedMoney() {
        return receviedMoney;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLands(List<Land> lands) {
        this.lands = lands;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void setRequiredMoney(double requiredMoney) {
        this.requiredMoney = requiredMoney;
    }

    public void setReceviedMoney(double receviedMoney) {
        this.receviedMoney = receviedMoney;
    }
}


