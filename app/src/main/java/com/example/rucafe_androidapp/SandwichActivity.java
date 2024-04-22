package com.example.rucafe_androidapp;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.Toast;


public class SandwichActivity extends AppCompatActivity {

    private RadioGroup breadGroup, proteinGroup;

    private Button addSandwichToCart;

    private Sandwich localSandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich);
        setupBreadRadio();
        setupProteinRadio();
        setupAddons();
        setOrderListener();

        localSandwich = new Sandwich(SandwichBread.WHEAT, SandwichProtein.CHICKEN, new SandwichAddons[] {});
        updatePriceDisplay();
    }

    private void setupRadioGroupButtons(int radioGroupID, int radioGroupArray) {
        RadioGroup radioGroup = findViewById(radioGroupID);

        if (radioGroup == null) {
            // System.out.println("radio group was null.");
            return;
        }

        // get array and add things
        String[] text_array = getResources().getStringArray(radioGroupArray);
        for (int i = 0; i < text_array.length; i++ ) {
            String this_str = text_array[i];
            RadioButton rb = new RadioButton(this);

            rb.setText(this_str);
            rb.setTextSize(16);

            // Set padding for the radio button
            int paddingPx = 20; // Adjust this number as needed
            rb.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);

            // Set layout parameters to make the radio button fill its container width
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT
            );
            rb.setLayoutParams(layoutParams);

            radioGroup.addView(rb);
        }

        // select the first one
        if (radioGroup.getChildCount() > 0) {
            RadioButton firstRadioButton = (RadioButton) radioGroup.getChildAt(0);
            firstRadioButton.setChecked(true);
        }
    }

    private void setupBreadRadio() {
        setupRadioGroupButtons(R.id.breadGroup, R.array.bread_types);

        breadGroup = findViewById(R.id.breadGroup);

        // add listener
        breadGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Handle radio button selection here
                RadioButton selectedRadioButton = findViewById(checkedId);
                if (selectedRadioButton != null) {
                    String selectedBread = selectedRadioButton.getText().toString();
                    // set some local string...
                    localSandwich.setBread(SandwichBread.fromString(selectedBread));
                }
            }
        });
    }

    private void setupProteinRadio() {
        setupRadioGroupButtons(R.id.proteinGroup, R.array.protein_types);

        proteinGroup = findViewById(R.id.proteinGroup);

        // add listener
        proteinGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Handle radio button selection here
                RadioButton selectedRadioButton = findViewById(checkedId);
                if (selectedRadioButton != null) {
                    String selectedProtein = selectedRadioButton.getText().toString();
                    // set some local string...
                    localSandwich.setProtein(SandwichProtein.fromString(selectedProtein));
                    updatePriceDisplay();
                }
            }
        });
    }

    private void setupCheckboxes(int viewID, int textArrayID) {
        LinearLayout container = findViewById(viewID);

        if (container == null) {
            // System.out.println("radio group was null.");
            return;
        }

        // get array and add things
        String[] text_array = getResources().getStringArray(textArrayID);
        for (int i = 0; i < text_array.length; i++ ) {
            String this_str = text_array[i];
            CheckBox cb = new CheckBox(this);

            cb.setText(this_str);
            cb.setTextSize(16);

            // Set padding for the radio button
            int paddingPx = 20; // Adjust this number as needed
            cb.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);

            cb.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // do a thing...
                    // calculate new price based on list of checked things
                    checkAddons();
                }
            });

            container.addView(cb);
        }
    }

    private void setupAddons() {
        setupCheckboxes(R.id.orderItemListContainer, R.array.sandwich_addons);
    }

    private void checkAddons() {
        // for each addon in addonList...
        LinearLayout container = findViewById(R.id.orderItemListContainer);

        // SandwichAddons[] addon_list = new SandwichAddons[] {};
        ArrayList<SandwichAddons> addonList = new ArrayList<SandwichAddons>();

        for (int i = 0; i < container.getChildCount(); i++) {
            CheckBox cb = (CheckBox) container.getChildAt(i);
            // System.out.println("cb name: "+cb.getText().toString());
            if (cb.isChecked()) {
                // System.out.println("... is checked");
                // get sandwichAddon using .fromString
                SandwichAddons addon = SandwichAddons.fromString(cb.getText().toString());
                // System.out.println("adding addon: "+addon.toString());
                // add to SandwichAddon list
                addonList.add(addon);
            }
        }

        // update sandwichAddons
        // create regular array
        SandwichAddons[] addonArray = new SandwichAddons[addonList.size()];
        // populate
        addonArray = addonList.toArray(addonArray);
        // set
        localSandwich.setAddons(addonArray);

        // update price
        updatePriceDisplay();

    }

    private void updatePriceDisplay() {
        // get price of localSandwich and update UI
        TextView label = findViewById(R.id.orderTotalDisplay);
        label.setText(String.format("%.2f", localSandwich.price()));
        label.setWidth(300); // this is stupid but it's here
    }

    private void setOrderListener() {
        addSandwichToCart = findViewById(R.id.cancelThisOrder);

        addSandwichToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSandwichToOrder(v);
            }
        });
    }

    private void addSandwichToOrder(View v) {
        CartList cartList = CartList.getInstance();

        // setup alert
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        alert.setTitle("Are you sure you want to order this sandwich?");
        alert.setMessage("Price: $"+String.format("%.2f", localSandwich.price()));

        // deal with alert
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cartList.addItem(localSandwich);
                Toast.makeText(v.getContext(),"items in cart: "+cartList.getItems().size(),Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(),"not added",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}