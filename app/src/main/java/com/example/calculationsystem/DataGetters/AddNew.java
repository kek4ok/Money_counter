package com.example.calculationsystem.DataGetters;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.calculationsystem.Logic.Manager;
import com.example.calculationsystem.R;


public class AddNew extends AppCompatActivity {

    private Manager manager;
    private ListView list;
    private Button addCustomer;
    private EditText customerName;
    private ArrayAdapter<String> adapter;

    private boolean isCustomer; //is owner instead


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_page);

        //loading info
        manager = Manager.getInstance();

        //assigning
        list = findViewById(R.id.list);
        customerName = findViewById(R.id.customer_name);
        addCustomer = findViewById(R.id.add_customer);


        getExtras();

        //displaying
        load();


    }

    public void getExtras() {
        if (getIntent().hasExtra("type"))
            this.isCustomer = getIntent().getBooleanExtra("type", true);


    }

    public void load() {

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Manager.getNames(isCustomer, false));
        list.setAdapter(adapter);


    }


    public void add(View view) {
        String name = customerName.getText().toString();

        if (name.matches("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Введите корректное название", Toast.LENGTH_SHORT);
            toast.show();
        } else {


            Manager.addCustomersNames(name, isCustomer);
            refreshList();

            Toast toast = Toast.makeText(getApplicationContext(), "Успешно добавленно", Toast.LENGTH_SHORT);
            toast.show();
            customerName.setText("");

        }


    }

    private void refreshList() {
        adapter.clear();
        adapter.addAll(Manager.getNames(isCustomer, false));


    }


}
