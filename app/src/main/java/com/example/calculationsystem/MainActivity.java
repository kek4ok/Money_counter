package com.example.calculationsystem;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


//Main Menu Activity
//have to load the list at first then continue

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }


    public void SceneChanger(View view)
    {
        Intent intent=new Intent(this,CustomersActivity.class);

        if(view.getId()==R.id.btn_myAccount)
            intent.setClass(this,MyAccountActivity.class);


        if(view.getId()==R.id.btn_reports)
            intent.setClass(this,ReportChooseActivity.class);




        startActivity(intent);

    }

}
