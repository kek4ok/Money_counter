package com.example.calculationsystem.Logic;

import java.util.List;



public class Owner extends Customer {

    private int percentage;
    private double finalRequired;

    public Owner(String name, List<Land> lands, List<Payment> payments, double requiredMoney, double receviedMoney,int percentage) {
        super(name, lands, payments, requiredMoney, receviedMoney);
        this.percentage=percentage;
    }


    public Owner(String name) {
        super(name);
        this.percentage = 0;
        finalRequired=0;
    }

    @Override
    public void CalculateRequiredMoney()
    {
        super.CalculateRequiredMoney();
        if(this.percentage!=0)
        {
            percentage=percentage/100;
            finalRequired=requiredMoney*(1-percentage);
        }
    }


    public int getPercentage() {
        return percentage;
    }

    public double getFinalRequired() {
        return finalRequired;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public void setFinalRequired(double finalRequired) {
        this.finalRequired = finalRequired;
    }
}
