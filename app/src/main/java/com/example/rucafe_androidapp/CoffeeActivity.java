package com.example.rucafe_androidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Creates the view for the coffee activity
 * 
 * @author Neel
 */
public class CoffeeActivity extends AppCompatActivity {

    /**
     * defines the checkboxes for addons
     */
    private CheckBox sweet_cream, french_vanilla, irish_cream, caramel, mocha;

    /**
     * defines the radio group for sizes
     */
    private RadioGroup sizes_radioGroup;

    /**
     * defines the radio buttons for sizes
     */
    private RadioButton short_radio, tall_radio, grande_radio, venti_radio;
    /**
     * defines the "add to cart" button
     */
    private Button add_btn;

    /**
     * instance of cartList
     */
    private CartList cartList;

    /**
     * defines the spinner for coffee quantity
     */
    private TextView subtotal;

    /**
     * defines the text view for the subtotal
     */
    private Spinner coffee_quantity;

    /**
     * keeps track of the current subtotal
     */
    private double currentSubtotal;
    /**
     * keeps track of the current base price
     */
    private double currentBasePrice;
    /**
     * keeps track of the current subtotal without quantity
     */
    private double currentSubtotalWithoutQuantity;
    /**
     * keeps track of the current addons
     */
    private ArrayList<String> currentAddons;

    /**
     * Creates the view for the coffee activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_activity);
        cartList = CartList.getInstance();
        sweet_cream = findViewById(R.id.sweet_cream);
        french_vanilla = findViewById(R.id.french_vanilla);
        irish_cream = findViewById(R.id.irish_cream);
        caramel = findViewById(R.id.caramel);
        mocha = findViewById(R.id.mocha);
        short_radio = findViewById(R.id.short_radio);
        tall_radio = findViewById(R.id.tall_radio);
        grande_radio = findViewById(R.id.grande_radio);
        venti_radio = findViewById(R.id.venti_radio);
        add_btn = findViewById(R.id.add_coffee_btn);
        coffee_quantity = findViewById(R.id.coffee_quantity);
        sizes_radioGroup = findViewById(R.id.sizes_radioGroup);
        setAddButtonOnClick();
        setRadioGroupOnSelect();
        setCheckBoxOnSelect();
        setCoffeeQuantityOnSelect();
        currentAddons = new ArrayList<>();
        subtotal = findViewById(R.id.coffee_price);
        String initialPrice = "$" + getString(R.string.coffee_size_short_price);
        subtotal.setText(initialPrice);
        currentBasePrice = Double.parseDouble(getString(R.string.coffee_size_short_price));
        currentSubtotal = currentSubtotalWithoutQuantity = Double
                .parseDouble(getString(R.string.coffee_size_short_price));

    }

    /**
     * Sets the onClickListener for the add button
     */
    public void setAddButtonOnClick() {
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecimalFormat df = new DecimalFormat("#.##");
                int radioButtonId = sizes_radioGroup.getCheckedRadioButtonId();
                RadioButton selected = findViewById(radioButtonId);
                String size = selected.getText().toString();
                int quantity = Integer.parseInt(coffee_quantity.getSelectedItem().toString());
                Coffee coffeeToAdd = new Coffee(size, currentAddons, currentBasePrice, quantity);
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Are you sure you want to order this item?");

                alert.setMessage("Price: $" + df.format(coffeeToAdd.price()));
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        cartList.addItem(coffeeToAdd);
                        Toast.makeText(view.getContext(), "items in cart: " + cartList.getItems().size(),
                                Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(), "not added", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    /**
     * Sets the onItemSelectedListener for the coffee quantity spinner
     */
    public void setCoffeeQuantityOnSelect() {
        coffee_quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int quantity = Integer.parseInt(parent.getItemAtPosition(position).toString());
                currentSubtotal = quantity * currentSubtotalWithoutQuantity;
                String subtotalString = "$" + currentSubtotal;
                subtotal.setText(subtotalString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Sets the onCheckedChangeListener for the radio group
     */
    public void setRadioGroupOnSelect() {
        sizes_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.short_radio) {
                    DecimalFormat df = new DecimalFormat("#.##");
                    int quantity = Integer.parseInt(coffee_quantity.getSelectedItem().toString());
                    currentBasePrice = Double.parseDouble(getString(R.string.coffee_size_short_price));
                    // currentSubtotal = quantity * (basePrice + 0.39 * currentAddons.size());
                    currentSubtotalWithoutQuantity = currentBasePrice + Coffee.addonPrice * currentAddons.size();
                    currentSubtotal = quantity * currentSubtotalWithoutQuantity;
                    String priceString = "$" + df.format(currentSubtotal);
                    subtotal.setText(priceString);
                    Toast.makeText(getApplicationContext(), priceString, Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.tall_radio) {
                    DecimalFormat df = new DecimalFormat("#.##");
                    int quantity = Integer.parseInt(coffee_quantity.getSelectedItem().toString());
                    currentBasePrice = Double.parseDouble(getString(R.string.coffee_size_tall_price));
                    currentSubtotalWithoutQuantity = currentBasePrice + Coffee.addonPrice * currentAddons.size();
                    currentSubtotal = quantity * currentSubtotalWithoutQuantity;
                    String priceString = "$" + df.format(currentSubtotal);
                    subtotal.setText(priceString);
                    Toast.makeText(getApplicationContext(), priceString, Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.grande_radio) {
                    DecimalFormat df = new DecimalFormat("#.##");
                    int quantity = Integer.parseInt(coffee_quantity.getSelectedItem().toString());
                    currentBasePrice = Double.parseDouble(getString(R.string.coffee_size_grande_price));
                    currentSubtotalWithoutQuantity = currentBasePrice + Coffee.addonPrice * currentAddons.size();
                    currentSubtotal = quantity * currentSubtotalWithoutQuantity;
                    String priceString = "$" + df.format(currentSubtotal);
                    subtotal.setText(priceString);
                    Toast.makeText(getApplicationContext(), priceString, Toast.LENGTH_SHORT).show();
                } else {
                    DecimalFormat df = new DecimalFormat("#.##");
                    int quantity = Integer.parseInt(coffee_quantity.getSelectedItem().toString());
                    currentBasePrice = Double.parseDouble(getString(R.string.coffee_size_venti_price));
                    currentSubtotalWithoutQuantity = currentBasePrice + Coffee.addonPrice * currentAddons.size();
                    currentSubtotal = quantity * currentSubtotalWithoutQuantity;
                    String priceString = "$" + df.format(currentSubtotal);
                    subtotal.setText(priceString);
                    Toast.makeText(getApplicationContext(), priceString, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    /**
     * Sets the onCheckedChangeListener for (all) the checkboxes
     */
    public void setCheckBoxOnSelect() {
        sweet_cream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleClickCheckBox(isChecked, sweet_cream.getText().toString());
                // Toast.makeText(getApplicationContext(),"sweet cream
                // unchecked",Toast.LENGTH_SHORT).show();
            }
        });
        french_vanilla.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleClickCheckBox(isChecked, french_vanilla.getText().toString());
            }
        });
        irish_cream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleClickCheckBox(isChecked, irish_cream.getText().toString());
            }
        });
        caramel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleClickCheckBox(isChecked, caramel.getText().toString());
            }
        });
        mocha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleClickCheckBox(isChecked, mocha.getText().toString());
            }
        });
    }

    /**
     * Handles the checkbox click
     * 
     * @param isChecked whether the checkbox is checked
     * @param addon     the addon (String)
     */
    public void handleClickCheckBox(boolean isChecked, String addon) {
        DecimalFormat df = new DecimalFormat("#.##");
        int quantity = Integer.parseInt(coffee_quantity.getSelectedItem().toString());
        if (isChecked) {
            currentAddons.add(addon);
            currentSubtotalWithoutQuantity += Coffee.addonPrice;
            // currentSubtotal+=0.39;
            currentSubtotal = quantity * currentSubtotalWithoutQuantity;
            String subtotalString = "$" + df.format(currentSubtotal);
            subtotal.setText(subtotalString);
            // Toast.makeText(getApplicationContext(),subtotalString,Toast.LENGTH_SHORT).show();
            return;
        }
        currentAddons.remove(addon);
        currentSubtotalWithoutQuantity -= Coffee.addonPrice;
        currentSubtotal = quantity * currentSubtotalWithoutQuantity;
        String subtotalString = "$" + df.format(currentSubtotal);
        subtotal.setText(subtotalString);
    }
}
