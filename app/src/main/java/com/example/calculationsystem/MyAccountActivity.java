package com.example.calculationsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculationsystem.DataGetters.MyAccountReport;
import com.example.calculationsystem.Logic.Manager;

public class MyAccountActivity extends AppCompatActivity {


  private TextView managerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_account);
        managerName=findViewById(R.id.Manager_name);

        load();
        managerName.setText("Добрый день, "+Manager.getName());







    }

    private void load() {


        if(Manager.getName().matches(""))
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);




            final EditText et = new EditText(this);
            final TextView view=new TextView(this);




            et.setPadding(5,5,5,5);
            et.setHint("Введите имя");
            et.setGravity(Gravity.CENTER);

            et.setBackgroundColor(getResources().getColor(android.R.color.transparent));

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(et);
            alertDialogBuilder.setTitle("Введите ваше имя");

            // set dialog message
            alertDialogBuilder.setCancelable(false).setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    Manager.setName( et.getText().toString());
                    managerName.setText("Добро пожаловать, "+Manager.getName());


                }
            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            // show it
            alertDialog.show();
        }

    }


    public void change(View view)
    {

        if(view.getId()==R.id.myAcc_report)
        {
            Intent intent=new Intent(this, MyAccountReport.class);
            startActivity(intent);

        }


    }


}


