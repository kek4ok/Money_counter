package com.example.calculationsystem.DataGetters;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.calculationsystem.Logic.Manager;
import com.example.calculationsystem.R;

public class DetailedReportActivity extends AppCompatActivity {

    private boolean isOwner;
    private int index;

    private TextView name;
    private TextView req;
    private TextView left;
    private TextView paid;


    private ListView listLands;
    private ListView listPayments;

    //TODO add to list and display final name money in and out


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_report);


        name = findViewById(R.id.detailed_name);
        req = findViewById(R.id.detailed_total_req);
        left = findViewById(R.id.detailed_total_left);
        paid = findViewById(R.id.detailed_total_paid);

        listLands = findViewById(R.id.detailed_listLand);
        listPayments = findViewById(R.id.detailed_listPayment);


        getExtras();
        load();

    }

    private void load() {

        ArrayAdapter<String> adapterL = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1
                , Manager.getDetailedLand(index, isOwner));

        ArrayAdapter<String> adapterP = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1
                , Manager.getDetailedReport(index, isOwner));

        listPayments.setAdapter(adapterP);
        listLands.setAdapter(adapterL);

        paid.setText("Потрачено/получено: " + String.valueOf(Manager.getMoneyInForCustomers(index, isOwner)));
        name.setText(Manager.getNameForCustomers(index, isOwner));

    }

    public void getExtras() {
        if (getIntent().hasExtra("type"))
            this.isOwner = getIntent().getBooleanExtra("type", true);

        if (getIntent().hasExtra("index"))
            this.index = getIntent().getIntExtra("index", 0);
    }


}
