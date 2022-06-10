package com.example.calculationsystem;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.calculationsystem.DataGetters.RecyclerViewAdapter;
import com.example.calculationsystem.Logic.Manager;

public class ReportsActivity extends AppCompatActivity {


    private boolean isOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reports);

        getExtras();
        initRecyclerView();

    }

    private void initRecyclerView() {

        RecyclerView recycleView=findViewById(R.id.recycler_view);
        RecyclerViewAdapter adabter=new RecyclerViewAdapter(this, Manager.getFinalReports(isOwner),isOwner);
        recycleView.setAdapter(adabter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getExtras() {

        if(getIntent().hasExtra("type"))
            isOwner=getIntent().getBooleanExtra("type",true);
    }



}
