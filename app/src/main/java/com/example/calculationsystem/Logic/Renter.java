package com.example.calculationsystem.Logic;

import java.util.List;

public class Renter extends Customer {




    public Renter(String name, List<Land> lands, List<Payment> payments, double requiredMoney, double receviedMoney) {
        super(name, lands, payments, requiredMoney, receviedMoney);
    }

    public Renter(String name) {
        super(name);
    }

    public double getLeftMoney() {
        super.CalculateReceviedMoney();
        super.CalculateRequiredMoney();

       return requiredMoney-receviedMoney;

    }
}
