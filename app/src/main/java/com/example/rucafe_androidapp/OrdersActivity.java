package com.example.rucafe_androidapp;

import android.os.Bundle;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class OrdersActivity extends AppCompatActivity {

    Button cancelOrderButton;

    TextView orderItemsTotal;

    Spinner selectedOrderSpinner;

    ListView orderItemList;

    private int selectedOrderID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        cancelOrderButton = findViewById(R.id.cancelThisOrder);
        orderItemsTotal = findViewById(R.id.orderTotalDisplay);
        selectedOrderSpinner = findViewById(R.id.selectedOrderSpinner);
        orderItemList = findViewById(R.id.orderItemList);


        selectedOrderID = -1;

        updateSpinnerOptions();

        // start listener for spinner
        selectedOrderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("selected an order...");
                // check if this is a valid ID
                selectedOrderID = Integer.parseInt(parent.getItemAtPosition(position).toString());
                System.out.println(selectedOrderID);
                populateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        // start listener for "cancel"
        setupListeners();
    }

    private void updateSpinnerOptions() {
        Orders currOrds = Orders.getInstance();
        ArrayList<Order> orders = currOrds.getPreviousOrders();

        ArrayList<Integer> idList = new ArrayList<>();

        for (Order o : orders) {
            idList.add(o.getOrderID());
        }

        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, idList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectedOrderSpinner.setAdapter(spinnerAdapter);
    }

    private void populateList() {
        // deals with list
        if (selectedOrderID == -1) {
            // nothing is selected, clear the list...
            ArrayList<MenuItem> itemsOfOrder = new ArrayList<>();
            ArrayAdapter<MenuItem> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,itemsOfOrder);
            listAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
            orderItemList.setAdapter(listAdapter);
            orderItemsTotal.setText(R.string.default_price);
            return;
        }

        try {
            Order specificOrder = Orders.getInstance().fetchOrderID(selectedOrderID);
            ArrayList<MenuItem> itemsOfOrder = specificOrder.getItems();
            System.out.println("items in selected order");
            System.out.println(itemsOfOrder);

            ArrayAdapter<MenuItem> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,itemsOfOrder);
            listAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
            orderItemList.setAdapter(listAdapter);


            // updates price label
            orderItemsTotal.setText(String.format("%.2f", specificOrder.getTotal()));
        }
        catch (IndexOutOfBoundsException e) {
//            System.out.println("Index out of bounds:");
//            System.out.println(e);
            // n
        }
        catch (Exception e) {
            // other error
//            System.out.println(e);
        }
    }

    private void setupListeners() {
        cancelOrderButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               cancelCurrentOrder();
           }
        });
    }

    private void cancelCurrentOrder() {
        // check if order ID is not -1
        if (selectedOrderID == -1) {
            Toast.makeText(getApplicationContext(),"No orders to cancel",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            if (Orders.getInstance().removeOrderID(selectedOrderID)) {
                int prevSelectedOrderID = selectedOrderID;
                selectedOrderID = -1;
                updateSpinnerOptions();
                populateList();
                Toast.makeText(getApplicationContext(),"Order #"+prevSelectedOrderID+" cancelled",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Couldn't cancel the order", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Couldn't cancel the order",Toast.LENGTH_SHORT).show();
            // ...
        }
    }
}