package com.example.calculationsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculationsystem.DataGetters.AddNew;
import com.example.calculationsystem.DataGetters.AddPayment;
import com.example.calculationsystem.Logic.Manager;

public class CustomersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customers_menu);

        load();

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
            alertDialogBuilder.setTitle("Добавьте аккаунт");

            // set dialog message
            alertDialogBuilder.setCancelable(false).setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                   Manager.setName( et.getText().toString());


                }
            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            // show it
            alertDialog.show();
        }


    }


    public void Scenes(View view)
    {
        Intent intent = null;

        if(view.getId()==R.id.btn_addPayment)
            intent=new Intent(this, AddPayment.class);


        if(view.getId()==R.id.btn_customer)
        {
            intent=new Intent(this, AddNew.class);
            intent.putExtra("type",true);
        }

        if(view.getId()==R.id.btn_renter) {

            intent = new Intent(this, AddNew.class);
            intent.putExtra("type",false);
        }



        if(intent!=null)
            startActivity(intent);







    }

}
