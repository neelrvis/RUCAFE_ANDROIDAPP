package com.example.rucafe_androidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rucafe_androidapp.R;

import java.text.DecimalFormat;
import java.util.Collections;

/**
 * An example to demonstrate how an ObservableList can be used in a ListView.
 * @author Lily Chang
 */
public class CartActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;

    private TextView total;

    private TextView tax;

    private TextView subtotal;

    private Button add_btn;

    private ObservableArrayList<MenuItem> list;
    private CartList cartList;
    private ArrayAdapter<MenuItem> items;

    private double nj_sales_tax;

    private double current_subtotal = 0;

    private double current_total = 0;

    private double current_tax = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        cartList = CartList.getInstance();
        listView = findViewById(R.id.cartListView);
        total = findViewById(R.id.total_cart);
        subtotal = findViewById(R.id.subtotal_cart);
        tax = findViewById(R.id.sales_tax_cart);
        add_btn = findViewById(R.id.order_cart_btn);
        nj_sales_tax = Double.parseDouble(getResources().getString(R.string.tax));
        list = new ObservableArrayList<>();

        if (cartList.getItems().isEmpty()) {
            Collections.addAll(list);
        }
        else {
            Collections.addAll(list, cartList.getItems().toArray(new MenuItem[0]));
        }
        items = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(items);
        listView.setOnItemClickListener(this);
        setUpAddButtonClick();
        updateSubtotal();
        updateTax();
        updateTotal();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Are you sure you want to delete this item?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cartList.getItems().remove(i);
                list.remove(i);
                items.notifyDataSetChanged();
                updateSubtotal();
                updateTax();
                updateTotal();
                Toast.makeText(view.getContext(),"Item removed",Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(view.getContext(),"Item not removed",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    public void setUpAddButtonClick() {
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.isEmpty()) {
                    Toast.makeText(view.getContext(),"No items in cart",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(view.getContext(),"Added to order",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateTotal() {
        DecimalFormat df = new DecimalFormat("#.##");
        this.current_total = this.current_subtotal + this.current_tax;
        String formatPrice = "$"+df.format(this.current_total);
        this.total.setText(formatPrice);
    }
    public void updateTax() {
        DecimalFormat df = new DecimalFormat("#.##");
        this.current_tax = this.current_subtotal * this.nj_sales_tax;
        String formatPrice = "$"+df.format(this.current_tax);
        this.tax.setText(formatPrice);
    }
    public void updateSubtotal() {
        DecimalFormat df = new DecimalFormat("#.##");
        for (MenuItem item : this.list) {
            this.current_subtotal+=item.price();
        }
        String formatPrice = "$"+df.format(this.current_subtotal);
        this.subtotal.setText(formatPrice);
    }
}