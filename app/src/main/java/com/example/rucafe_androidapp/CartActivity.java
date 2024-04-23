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

import java.text.DecimalFormat;
import java.util.Collections;

/**
 * An example to demonstrate how an ObservableList can be used in a ListView.
 * 
 * @author Neel, Tadhg
 */
public class CartActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /**
     * reference to the ListView in the layout
     */
    private ListView listView;

    /**
     * reference to all TextViews in the layout
     */
    private TextView total, tax, subtotal;

    /**
     * reference to the "add to order" button in the layout
     */
    private Button add_btn;

    /**
     * data for list
     */
    private ObservableArrayList<MenuItem> list;
    /**
     * instance of cartList
     */
    private CartList cartList;

    /**
     * adapter for list items
     */
    private ArrayAdapter<MenuItem> items;

    /**
     * constant tax number
     */
    private double nj_sales_tax;

    /**
     * local vars for prices
     */
    private double current_subtotal = 0;
    /**
     * local vars for prices
     */
    private double current_total = 0;
    /**
     * local vars for prices
     */
    private double current_tax = 0;

    /**
     * creates the CartActivity
     * 
     * @param savedInstanceState (unused)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        cartList = CartList.getInstance();
        listView = findViewById(R.id.orderItemList);
        total = findViewById(R.id.total_cart);
        subtotal = findViewById(R.id.subtotal_cart);
        tax = findViewById(R.id.sales_tax_cart);
        add_btn = findViewById(R.id.order_cart_btn);
        nj_sales_tax = Double.parseDouble(getResources().getString(R.string.tax));
        list = new ObservableArrayList<>();

        if (cartList.getItems().isEmpty()) {
            Collections.addAll(list);
        } else {
            Collections.addAll(list, cartList.getItems().toArray(new MenuItem[0]));
        }
        items = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(items);
        listView.setOnItemClickListener(this);
        setUpAddButtonClick();
        updateSubtotal();
        updateTax();
        updateTotal();
    }

    /**
     * handles click of item in cart (so it gets removed)
     * 
     * @param adapterView adapter context
     * @param view        view context
     * @param i           index
     * @param l           unused
     */
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
                Toast.makeText(view.getContext(), "Item removed", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(view.getContext(), "Item not removed", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * sets up the "add to order" button click listener
     */
    public void setUpAddButtonClick() {
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.isEmpty()) {
                    Toast.makeText(view.getContext(), "No items in cart", Toast.LENGTH_SHORT).show();
                } else {
                    Orders globalOrders = Orders.getInstance();
                    // set current order to match...
                    globalOrders.currentOrder.setCartContents(cartList.getItems());
                    System.out.println("cart before placing...");
                    System.out.println(globalOrders.currentOrder);
                    globalOrders.placeCurrentOrder();

                    Toast.makeText(view.getContext(), "Added to order", Toast.LENGTH_SHORT).show();

                    // refresh everything...
                    System.out.println("cart after placing...");
                    System.out.println(globalOrders.currentOrder);
                    cartList.setCartContents(globalOrders.currentOrder.getItems());
                    list.clear();
                    items.notifyDataSetChanged();
                    // update prices on bottom
                    updateAll();

                    // debug...
                    System.out.println("current:");
                    System.out.println(Orders.getInstance().currentOrder);
                    System.out.println("previous:");
                    for (Order o : Orders.getInstance().previousOrders) {
                        System.out.println("Order " + o.getOrderID() + "'s items");
                        System.out.println(o);
                    }
                }
            }
        });
    }

    /**
     * updates prices on display based on cart content
     */
    public void updateAll() {
        updateSubtotal();
        updateTax();
        updateTotal();
    }

    /**
     * updates the total price on the display
     */
    public void updateTotal() {
        DecimalFormat df = new DecimalFormat("#.##");
        this.current_total = this.current_subtotal + this.current_tax;
        String formatPrice = "$" + df.format(this.current_total);
        this.total.setText(formatPrice);
    }

    /**
     * updates the tax price on the display
     */
    public void updateTax() {
        DecimalFormat df = new DecimalFormat("#.##");
        this.current_tax = this.current_subtotal * this.nj_sales_tax;
        String formatPrice = "$" + df.format(this.current_tax);
        this.tax.setText(formatPrice);
    }

    /**
     * updates the subtotal price on the display
     */
    public void updateSubtotal() {
        this.current_subtotal = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        for (MenuItem item : this.list) {
            this.current_subtotal += item.price();
        }
        String formatPrice = "$" + df.format(this.current_subtotal);
        this.subtotal.setText(formatPrice);
    }
}