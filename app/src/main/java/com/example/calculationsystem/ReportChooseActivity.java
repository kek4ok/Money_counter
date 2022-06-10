package com.example.calculationsystem;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class ReportChooseActivity extends AppCompatActivity {

    boolean isOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_choose);


    }


    public void chooseDone(View view)
    {
        if(view.getId()==R.id.btn_choose_owner)
        {
            isOwner=true;
            Intent intent=new Intent(this,ReportsActivity.class);
            intent.putExtra("type",isOwner);
            startActivity(intent);
        }

        if(view.getId()==R.id.btn_choose_renter)
        {
            isOwner=false;
            Intent intent=new Intent(this,ReportsActivity.class);
            intent.putExtra("type",isOwner);
            startActivity(intent);
        }


    }

}
