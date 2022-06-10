package com.example.calculationsystem.DataGetters;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.calculationsystem.Logic.Manager;
import com.example.calculationsystem.R;


public class AddPayment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText addMoney, addComment, addDate;
    private Spinner displayList;
    private Button addPayment;
    private CheckBox checkRenter, checkOwner;
    private boolean isOwner;
    private ArrayAdapter<String> adapterO, adapterR, adapterS;
    private ListView summary;

    private String nameSelected;


    //Finished Class of add_payment layout
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_payment);


        addComment = (EditText) findViewById(R.id.add_comment);
        addDate = (EditText) findViewById(R.id.add_date);
        addMoney = (EditText) findViewById(R.id.add_money);
        addPayment = (Button) findViewById(R.id.add_payment);
        displayList = (Spinner) findViewById(R.id.display_name);
        checkOwner = (CheckBox) findViewById(R.id.check_owner);
        checkRenter = (CheckBox) findViewById(R.id.check_renter);
        summary = (ListView) findViewById(R.id.list_moneyNow);

        nameSelected = new String();

        //display renters
        load();
        displayList.setAdapter(adapterR);
        displayList.setOnItemSelectedListener(this);
        isOwner = false;
        checkRenter.setChecked(true);
        checkOwner.setChecked(false);

    }

    private void load() {


        adapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Manager.getNames(false, true));
        adapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterO = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Manager.getNames(true, true));
        adapterO.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }


    public void startAdding(View view) {
        String comment = new String();
        String date = new String();
        double money = 0;


        boolean error = false;
        String msg = "Enter Valid";

        if (!(addMoney.getText().toString().matches("")))
            money = Double.valueOf(addMoney.getText().toString());
        else {
            msg += " Money";
            error = true;
        }

        if (!(addComment.getText().toString().matches("")))
            comment = addComment.getText().toString();
        else {
            msg += " Comment";
            error = true;
        }

        if (!(addDate.getText().toString().matches("")))
            date = addDate.getText().toString();

        else {
            msg += " date";
            error = true;
        }

        if (displayList.getSelectedItem() == null) {
            msg = "Choose someone first";
            error = true;
        }


        if (error) {
            Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
            toast.show();
        } else {

            String type = "owner";

            if (!isOwner)
                type = "renter";

            Manager.addPayment(nameSelected, type, money, comment, date);

            addDate.setText("");
            addComment.setText("");
            addMoney.setText("");
            update();

            Toast toast = Toast.makeText(getApplicationContext(), "Успешно добавлено ", Toast.LENGTH_SHORT);
            toast.show();

        }


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        nameSelected = displayList.getSelectedItem().toString();
        if (adapterS == null)
            adapterS = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Manager.getDetailedReport(nameSelected, isOwner));
        else
            update();

        summary.setAdapter(adapterS);

    }


    public void update() {
        adapterS.clear();
        adapterS.addAll(Manager.getDetailedReport(nameSelected, isOwner));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        summary.setAdapter(null);

    }


    public void renterChecked(View view) {
        checkOwner.setChecked(false);
        displayList.setAdapter(adapterR);
        isOwner = false;
    }

    public void OwnerChecked(View view) {
        checkRenter.setChecked(false);
        displayList.setAdapter(adapterO);
        isOwner = true;
    }
}
