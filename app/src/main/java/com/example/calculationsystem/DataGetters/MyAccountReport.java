package com.example.calculationsystem.DataGetters;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.calculationsystem.Logic.Manager;
import com.example.calculationsystem.R;

import java.util.ArrayList;
import java.util.List;

public class MyAccountReport extends AppCompatActivity {


    private TextView moneyExpected;
    private TextView moneyCollected;
    private TextView moneyOwners;
    private TextView moneyOwnersLeft;
    private TextView MoneyLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_report);


        moneyCollected = findViewById(R.id.my_report_MCollected);
        moneyOwnersLeft = findViewById(R.id.my_report_MOPaid);
        MoneyLeft = findViewById(R.id.my_report_MExpectedLeft);

        load();


    }

    private void load() {

        List<Double> temp = new ArrayList<>();
        temp = Manager.getMoneyList();


        moneyCollected.setText("Получено: " + String.valueOf(temp.get(1)));
        moneyOwnersLeft.setText("Потрачено: " + String.valueOf(temp.get(2) - temp.get(3)));
        MoneyLeft.setText("Денег осталось: " + String.valueOf(temp.get(4)));


    }


}
