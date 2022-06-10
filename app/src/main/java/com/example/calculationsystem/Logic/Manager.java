package com.example.calculationsystem.Logic;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    private static List<Renter> renters;
    private static List<Owner> owners;
    private static List<Land> myLands;
    private static double moneyNow;
    private static double moneyExpected;
    private static double moneyIn;
    private static double moneyOut;
    private static double moneyReq;
    private static String name;
    private static Manager instance = new Manager();

    public static Manager getInstance() {
        return instance;
    }


    private Manager() {
        renters = new ArrayList<>();
        owners = new ArrayList<>();
        myLands = new ArrayList<>();
        moneyNow = 0;
        moneyExpected = 0;
        name = new String();
    }


    public static void LoadManager(List<Renter> renters, List<Owner> owners, List<Land> MyLands, double moneyNow, double moneyExpected, String name) {

        Manager.renters = renters;
        Manager.owners = owners;
        Manager.myLands = MyLands;
        Manager.moneyNow = moneyNow;
        Manager.moneyExpected = moneyExpected;
        Manager.name = name;
    }


    //adding functions


    public static void addLand(String nameO, String nameR, Land land) {

        int i = searchRenter(nameR);
        Renter temp = renters.get(i);
        temp.addLand(land);
        renters.set(i, temp);

        if (Manager.name.matches(nameO))
            myLands.add(land);


        else {

            i = searchOwner(nameO);
            Owner tempo = owners.get(i);
            tempo.addLand(land);
            owners.set(i, tempo);

        }

    }

    public static void addPayment(String name, String type, double money, String comment, String date) {
        if (type == "owner") {
            int i = searchOwner(name);
            Owner temp = owners.get(i);
            temp.addPayment(new Payment(comment, date, money));
            owners.set(i, temp);


        } else if (type == "renter") {
            int i = searchRenter(name);
            Renter temp = renters.get(i);
            temp.addPayment(new Payment(comment, date, money));
            renters.set(i, temp);


        }

    }


    public static void addCustomersNames(String name, boolean isOwner) {
        if (isOwner) {

            Owner temp = new Owner(name);
            owners.add(temp);

        } else {
            Renter temp = new Renter(name);
            renters.add(temp);
        }


    }


    //searching fun

    public static int searchRenter(String name) {
        int temp = -1;

        for (int i = 0; i < renters.size(); ++i) {
            if (name == renters.get(i).getName()) {
                temp = i;
                break;
            }
        }
        return temp;
    }

    public static int searchOwner(String name) {
        int temp = -1;

        for (int i = 0; i < owners.size(); ++i) {
            if (name == owners.get(i).getName()) {
                temp = i;
                break;
            }
        }
        return temp;
    }


    public static void moneyNow() {
        double in = 0;
        double out = 0;
        double tempOwner = 0;
        double tempExpected = 0;


        for (int i = 0; i < renters.size(); i++) {
            in += renters.get(i).getReceviedMoney();
            tempExpected += renters.get(i).getRequiredMoney();

        }

        for (int i = 0; i < owners.size(); i++) {
            out += owners.get(i).getReceviedMoney();
            tempOwner += owners.get(i).getRequiredMoney();

        }

        Manager.moneyReq = tempOwner;
        Manager.moneyIn = in;
        Manager.moneyOut = out;
        Manager.moneyExpected = tempExpected;
        Manager.moneyNow = in - out;
    }


    public static List<Double> getMoneyList() {

        moneyNow();
        List<Double> temp = new ArrayList<>();
        temp.add(moneyExpected);
        temp.add(moneyIn);
        temp.add(moneyReq);
        temp.add(moneyOut);
        temp.add(moneyNow);

        return temp;
    }


    public static List<String> getNames(boolean isOwner, boolean isPayment) {
        List<String> list = new ArrayList<>();

        if (isOwner) {


            for (int i = 0; i < owners.size(); ++i) {
                String temp = owners.get(i).getName();
                list.add(temp);
            }

            if (!isPayment)
                list.add(Manager.name);


        } else {
            for (int i = 0; i < renters.size(); ++i) {
                String temp = renters.get(i).getName();
                list.add(temp);
            }
        }

        return list;

    }


    public static List<String> getFinalReports(boolean isOwner) {
        List<String> list = new ArrayList<>();

        if (isOwner) {

            for (int i = 0; i < owners.size(); ++i) {
                String temp = owners.get(i).getName()
                        + "\nПотрачено: " + owners.get(i).getReceviedMoney();
                list.add(temp);
            }


        } else {
            for (int i = 0; i < renters.size(); ++i) {
                String temp = renters.get(i).getName()
                        + "\nПолучено: " + renters.get(i).getReceviedMoney();
                list.add(temp);
            }
        }

        return list;

    }


    public static List<String> getDetailedReport(String name, boolean isOwner) {

        int index;

        if (isOwner) {
            index = searchOwner(name);
            return owners.get(index).getPaymentsAsString();
        } else {
            index = searchRenter(name);
            return renters.get(index).getPaymentsAsString();

        }


    }


    public static List<String> getDetailedReport(int index, boolean isOwner) {

        if (isOwner) {
            return owners.get(index).getPaymentsAsString();
        } else {
            return renters.get(index).getPaymentsAsString();

        }


    }


    public static List<String> getDetailedLand(int index, boolean isOwner) {

        if (isOwner) {
            return owners.get(index).getLandsAsString();
        } else {
            return renters.get(index).getLandsAsString();

        }


    }

    public static double getMoneyRequiredForCust(int index, boolean isOwner) {

        if (isOwner) {
            return owners.get(index).getRequiredMoney();
        } else {
            return renters.get(index).getRequiredMoney();

        }


    }

    public static double getMoneyInForCustomers(int index, boolean isOwner) {

        if (isOwner) {
            return owners.get(index).getReceviedMoney();
        } else {
            return renters.get(index).getReceviedMoney();

        }


    }


    public static double getMoneyleftForCustomers(int index, boolean isOwner) {

        if (isOwner) {
            return owners.get(index).getMoneyLeft();
        } else {
            return renters.get(index).getMoneyLeft();

        }


    }


    public static String getNameForCustomers(int index, boolean isOwner) {

        if (isOwner) {
            return owners.get(index).getName();
        } else {
            return renters.get(index).getName();

        }


    }


    //setters and getters

    public static List<Renter> getRenters() {
        return renters;
    }

    public static void setRenters(List<Renter> renters) {
        Manager.renters = renters;
    }

    public static List<Owner> getOwners() {
        return owners;
    }

    public static void setOwners(List<Owner> owners) {
        Manager.owners = owners;
    }

    public static List<Land> getMyLands() {
        return myLands;
    }

    public static void setMyLands(List<Land> myLands) {
        Manager.myLands = myLands;
    }

    public static double getMoneyNow() {
        return moneyNow;
    }

    public static void setMoneyNow(double moneyNow) {
        Manager.moneyNow = moneyNow;
    }

    public static double getMoneyExpected() {
        return moneyExpected;
    }

    public static void setMoneyExpected(double moneyExpected) {
        Manager.moneyExpected = moneyExpected;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Manager.name = name;
    }


}
